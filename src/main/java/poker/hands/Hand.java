package poker.hands;

import java.util.*;
import java.util.stream.Collectors;

public class Hand {

    private static final int HAND_SIZE = 5;

    private List<Card> cardList;


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
        if (invalidHandSize()) {
            return 0;
        }

        return Collections.max(classifyActions.stream().map(HandClassifier::classify).collect(Collectors.toList()));
    }

    public int getHandHighCardValue() {
        return getHighestCardValue(cardList);
    }

    private int hasHighCard() {
        return HandValue.HighCard.getValue() + getHighestCardValue(cardList);
    }

    private int hasPair() {
        List<Card> duplicates = getDuplicateCards(cardList, 2);

        if (duplicates.size() != 2) {
            return 0;
        }

        return HandValue.Pair.getValue() + getHighestCardValue(duplicates);
    }

    private int hasTwoPair() {
        List<Card> duplicates = getDuplicateCards(cardList, 2);

        if (duplicates.size() != 4) {
            return 0;
        }

        return HandValue.TwoPair.getValue() + getHighestCardValue(duplicates);
    }

    private int hasThreeOfAKind() {
        List<Card> duplicates = getDuplicateCards(cardList, 3);

        if (duplicates.size() != 3) {
            return 0;
        }

        return HandValue.ThreeOfAKind.getValue() + getHighestCardValue(duplicates);
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

        return HandValue.Straight.getValue() + getHighestCardValue(cardList);
    }

    private int hasFlush() {
        Set<String> suitList = cardList.stream().map(Card::getSuit).collect(Collectors.toSet());

        if (suitList.size() != 1) {
            return 0;
        }

        return HandValue.Flush.getValue() + getHighestCardValue(cardList);
    }

    private int hasFullHouse() {
        List<Card> fullHouse = new ArrayList<>();
        fullHouse.addAll(getDuplicateCards(cardList, 2));
        fullHouse.addAll(getDuplicateCards(cardList, 3));

        if (fullHouse.size() != 5) {
            return 0;
        }

        return HandValue.FullHouse.getValue() + getHighestCardValue(cardList);
    }

    private int hasFourOfAKind() {
        List<Card> duplicates = getDuplicateCards(cardList, 4);

        if (duplicates.size() != 4) {
            return 0;
        }

        return HandValue.FourOfAKind.getValue() + getHighestCardValue(duplicates);
    }

    private int hasStraightFlush() {
        if (hasStraight() == 0 || hasFlush() == 0) {
            return 0;
        }

        return HandValue.StraightFlush.getValue() + getHighestCardValue(cardList);
    }

    private List<Card> getDuplicateCards(List<Card> hand, int i) {
        List<Integer> valueList = hand.stream().map(Card::getValue).collect(Collectors.toList());

        return hand.stream().filter(card -> Collections.frequency(valueList, card.getValue()) == i)
                .collect(Collectors.toList());
    }

    private int getHighestCardValue(List<Card> cards) {
        return Collections.max(cards.stream().map(Card::getValue).collect(Collectors.toList()));
    }

    private boolean invalidHandSize() {
        return cardList.size() != HAND_SIZE;
    }
}
