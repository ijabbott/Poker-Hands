package poker.hands;

import java.util.*;
import java.util.stream.Collectors;

public class Hand {
    private List<Card> cardList;
    private static final int HAND_SIZE = 5;

    public Hand(List<Card> cards) {
        cardList = cards;
    }

    private boolean invalidHandSize(ArrayList<Card> hand) {
        return hand.size() != HAND_SIZE;
    }


}
