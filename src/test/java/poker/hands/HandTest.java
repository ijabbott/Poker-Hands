package poker.hands;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static poker.hands.CardSuit.*;
import static poker.hands.CardValue.*;


public class HandTest {

    @Test
    public void GivenTwoCardsGetHighCardReturnsHighestCard() {
        Card lowCard = new Card(Two, Hearts);
        Card highCard = new Card(Three, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(lowCard, highCard));

        HandEvaluator evaluator = new HandEvaluator();
        Hand hand = new Hand(cards, evaluator);


        assertEquals(hand.getHandValue(), HandValue.HighCard);
    }

    @Test
    public void GivenAPairGetPairReturnsPair() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2));

        HandEvaluator evaluator = new HandEvaluator();
        Hand hand = new Hand(cards, evaluator);

        assertEquals(HandValue.Pair, hand.getHandValue());
    }
}
