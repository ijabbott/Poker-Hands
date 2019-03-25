package poker.hands;

import java.util.*;
import java.util.stream.Collectors;


public class HandEvaluator {

    public HandEvaluator() {

    }

    public List<Hand> evaluateHands(List<Hand> hands) {
        int maxValue = Collections.max(hands.stream().map(Hand::getHandValue).collect(Collectors.toList()));
        List<Hand> maxHands = hands.stream().filter(hand -> hand.getHandValue() == maxValue).collect(Collectors.toList());

        if(maxHands.size() > 1) {
            int maxHighCardValue = Collections.max(maxHands.stream().map(Hand::getHandHighCardValue).collect(Collectors.toList()));
            return maxHands.stream().filter(hand -> hand.getHandHighCardValue() == maxHighCardValue).collect(Collectors.toList());
        }

        return maxHands;
    }
}
