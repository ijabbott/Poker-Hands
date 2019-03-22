package poker.hands;

import java.util.*;
import java.util.stream.Collectors;

public class Hand {
    private List<Card> cardList;
    private static final int HAND_SIZE = 5;

    public Hand(List<Card> cards) {
        cardList = cards;
    }

    interface HandClassifier {
        int classify();
    }

    private List<HandClassifier> classifyActions = new ArrayList<HandClassifier>() {{
        add(() -> hasStraightFlush());
        add(() -> hasFourOfAKind());
        add(() -> hasFullHouse());
        add(() -> hasFlush());
        add(() -> hasStraight());
        add(() -> hasThreeOfAKind());
        add(() -> hasTwoPair());
        add(() -> hasPair());
        add(() -> hasHighCard());
    }};

    public int getHandValue() {
        if(invalidHandSize()) {
            return 0;
        }

        return Collections.max(classifyActions.stream().map(HandClassifier::classify).collect(Collectors.toList()));
    }

    public int getHighCardValue() {
        return Collections.max(cardList.stream().map(Card::getValue).collect(Collectors.toList()));
    }

    private int hasHighCard() {
        int highValue = Collections.max(cardList.stream().map(Card::getValue).collect(Collectors.toList()));

        return HandValue.HighCard.getValue() + highValue;
    }

    private int hasPair() {
        List<Card> duplicates = getDuplicateCards(cardList, 2);

        if(duplicates.size() != 2) {
            return 0;
        }

        int highValue = duplicates.stream().max(Comparator.comparing(Card::getValue)).orElse(null).getValue();
        return HandValue.Pair.getValue() + highValue;
    }

    private int hasTwoPair() {
        List<Card> duplicates = getDuplicateCards(cardList, 2);

        if(duplicates.size() != 4) {
            return 0;
        }

        int highValue = Collections.max(duplicates.stream().map(Card::getValue).collect(Collectors.toList()));
        return HandValue.TwoPair.getValue() + highValue;
    }

    private int hasThreeOfAKind() {
        List<Card> duplicates = getDuplicateCards(cardList, 3);

        if(duplicates.size() != 3) {
            return 0;
        }

        int highValue = Collections.max(duplicates.stream().map(Card::getValue).collect(Collectors.toList()));
        return HandValue.ThreeOfAKind.getValue() + highValue;
    }

    private int hasStraight() {
        List<Integer> valueList = cardList.stream().map(Card::getValue).sorted().collect(Collectors.toList());

        int expectedValue = valueList.get(0);

        for (int value : valueList) {
            if (value != expectedValue) {
                return 0;
            }
            expectedValue++;
        }

        int highValue = Collections.max(cardList.stream().map(Card::getValue).collect(Collectors.toList()));

        return HandValue.Straight.getValue() + highValue;
    }

    private int hasFlush() {
        Set<String> suitList = cardList.stream().map(Card::getSuit).collect(Collectors.toSet());

        if (suitList.size() != 1) {
            return 0;
        }

        int highValue = Collections.max(cardList.stream().map(Card::getValue).collect(Collectors.toList()));

        return HandValue.Flush.getValue() + highValue;
    }

    private int hasFullHouse() {
        List<Card> fullHouse = new ArrayList<>();
        fullHouse.addAll(getDuplicateCards(cardList, 2));
        fullHouse.addAll(getDuplicateCards(cardList, 3));

        if (fullHouse.size() != 5) {
            return 0;
        }

        int highValue = fullHouse.stream().max(Comparator.comparing(Card::getValue)).orElse(null).getValue();

        return HandValue.FullHouse.getValue() + highValue;
    }

    private int hasFourOfAKind() {
        List<Card> duplicates = getDuplicateCards(cardList, 4);

        if(duplicates.size() != 4) {
            return 0;
        }

        int highValue = Collections.max(duplicates.stream().map(Card::getValue).collect(Collectors.toList()));
        return HandValue.FourOfAKind.getValue() + highValue;
    }

    private int hasStraightFlush() {
        if (hasStraight() == 0 || hasFlush() == 0) {
            return 0;
        }

        int highValue = Collections.max(cardList.stream().map(Card::getValue).collect(Collectors.toList()));

        return HandValue.StraightFlush.getValue() + highValue;
    }

    private List<Card> getDuplicateCards(List<Card> hand, int i) {
        List<Integer> valueList = hand.stream().map(Card::getValue).collect(Collectors.toList());

        return hand.stream().filter(card -> Collections.frequency(valueList, card.getValue()) == i)
                .collect(Collectors.toList());
    }

    private boolean invalidHandSize() {
        return cardList.size() != HAND_SIZE;
    }
}
