package poker.hands;

import java.util.*;
import java.util.stream.Collectors;

public class Hand {
    private List<Card> cardList;
    private HandEvaluator handEvaluator;
    private static final int HAND_SIZE = 5;

    public Hand(List<Card> cards, HandEvaluator evaluator) {
        cardList = cards;
        handEvaluator = evaluator;

    }

    private boolean invalidHandSize(ArrayList<Card> hand) {
        return hand.size() != HAND_SIZE;
    }


    public HandValue getHandValue() {
        if (!handEvaluator.getPair(cardList).isEmpty()) {
            return HandValue.Pair;
        } else if (!handEvaluator.getHighCard(cardList).isEmpty()) {
            return HandValue.HighCard;
        }
        return null;
    }
}
