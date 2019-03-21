package poker.hands;

import java.util.*;
import java.util.stream.Collectors;


public class HandEvaluator {

    private static final int HAND_SIZE = 5;

    public HandEvaluator() {

    }

    interface HandClassifier {
        List<Card> classify(ArrayList<Card> hand);
    }

    private List<HandClassifier> classifyActions = new ArrayList<HandClassifier>() {{
        add(hand -> getHighCard(hand));
        add(hand -> getPair(hand));
        add(hand -> getTwoPair(hand));
        add(hand -> getThreeOfAKind(hand));
        add(hand -> getStraight(hand));
        add(hand -> getFlush(hand));
        add(hand -> getFullHouse(hand));
        add(hand -> getFourOfAKind(hand));
        add(hand -> getStraightFlush(hand));
    }};

    public List<Card> getHighCard(ArrayList<Card> hand) {
        return new ArrayList<Card>() {{
            add(hand.stream().max(Comparator.comparing(Card::getValue)).orElse(null));
        }};
    }

    public List<Card> getPair(ArrayList<Card> hand) {
        List<Card> duplicates = getDuplicateCards(hand, 2);
        if (duplicates.size() == 2) {
            return duplicates;
        }

        return new ArrayList<>();
    }

    public List<Card> getTwoPair(ArrayList<Card> hand) {
        List<Card> duplicates = getDuplicateCards(hand, 2);
        if (duplicates.size() == 4) {
            return duplicates;
        }

        return new ArrayList<>();
    }

    public List<Card> getThreeOfAKind(ArrayList<Card> hand) {
        return getDuplicateCards(hand, 3);
    }

    public List<Card> getStraight(ArrayList<Card> hand) {
        if (invalidHandSize(hand)) {
            return new ArrayList<>();
        }

        List<Integer> valueList = hand.stream().map(Card::getValue).sorted().collect(Collectors.toList());

        int expectedValue = valueList.get(0);

        for (int value : valueList) {
            if (value != expectedValue) {
                return new ArrayList<>();
            }
            expectedValue++;
        }

        return hand;
    }

    public List<Card> getFlush(ArrayList<Card> hand) {
        if (invalidHandSize(hand)) {
            return new ArrayList<>();
        }

        Set<String> suitList = hand.stream().map(Card::getSuit).collect(Collectors.toSet());

        if (suitList.size() != 1) {
            return new ArrayList<>();
        }

        return hand;
    }

    public List<Card> getFullHouse(ArrayList<Card> hand) {
        if (invalidHandSize(hand)) {
            return new ArrayList<>();
        }

        List<Card> fullHouse = new ArrayList<>();
        fullHouse.addAll(getDuplicateCards(hand, 2));
        fullHouse.addAll(getDuplicateCards(hand, 3));

        if (fullHouse.size() != 5) {
            return new ArrayList<>();
        }

        return hand;
    }

    public List<Card> getFourOfAKind(ArrayList<Card> hand) {
        return getDuplicateCards(hand, 4);
    }

    public List<Card> getStraightFlush(ArrayList<Card> hand) {
        if (invalidHandSize(hand)) {
            return new ArrayList<>();
        }

        if (getStraight(hand) != hand || getFlush(hand) != hand) {
            return new ArrayList<>();
        }

        return hand;
    }

    public List<Card> evaluateHands(ArrayList<ArrayList<Card>> hands) {
        List<Integer> rankList = new ArrayList<>(Collections.nCopies(hands.size(), 0));
        List<Integer> tieBreakerList = new ArrayList<>(Collections.nCopies(hands.size(), 0));

        for (HandClassifier classifier : this.classifyActions) {
            for (ArrayList<Card> hand : hands) {
                List<Card> classification = classifier.classify(hand);
                if (!classification.isEmpty()) {
                    rankList.set(hands.indexOf(hand), classifyActions.indexOf(classifier));// If I return highest card in relevant part of hand, can I combine that with index into rank list?
                    tieBreakerList.set(hands.indexOf(hand), classification.stream().max(Comparator.comparing(Card::getValue)).get().getValue());
                }
            }
        }

        if (Collections.frequency(rankList, Collections.max(rankList)) > 1) {
            return hands.stream().max(Comparator.comparing(hand -> tieBreakerList.get(hands.indexOf(hand)))).get();
        }

        return hands.get(rankList.indexOf(Collections.max(rankList)));
    }

    private List<Card> getDuplicateCards(ArrayList<Card> hand, int i) {
        List<Integer> valueList = hand.stream().map(Card::getValue).collect(Collectors.toList());

        return hand.stream().filter(card -> Collections.frequency(valueList, card.getValue()) == i)
                .collect(Collectors.toList());
    }

    private boolean invalidHandSize(ArrayList<Card> hand) {
        return hand.size() != HAND_SIZE;
    }
}
