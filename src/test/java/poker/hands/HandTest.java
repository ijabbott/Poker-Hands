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
        Card card2 = new Card(Four, Spades);
        Card card3 = new Card(Eight, Spades);
        Card card4 = new Card(Ten, Hearts);
        Card highCard = new Card(King, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(lowCard, card2, card3, card4, highCard));

        Hand hand = new Hand(cards);


        assertEquals(HandValue.HighCard.getValue() + King.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenAPairGetPairReturnsPair() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Eight, Spades);
        Card card4 = new Card(Ten, Hearts);
        Card card5 = new Card(King, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.Pair.getValue() + Two.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenTwoPairGetTwoPairReturnsTwoPair() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Three, Hearts);
        Card card4 = new Card(Three, Clubs);
        Card card5 = new Card(Ten, Hearts);

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.TwoPair.getValue() + Three.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenAThreeOfAKindGetThreeOfAKindReturnsThreeOfAKind() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Two, Diamonds);
        Card card4 = new Card(Ten, Hearts);
        Card card5 = new Card(King, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.ThreeOfAKind.getValue() + Two.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenAStraightGetStraightReturnsStraight() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Three, Clubs);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Clubs);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.Straight.getValue() + Six.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenAnOutOfOrderStraightGetStraightReturnsStraight() {
        Card card1 = new Card(Three, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Clubs);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.Straight.getValue() + Six.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenAFlushGetFlushReturnsFlush() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Hearts);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Hearts);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.Flush.getValue() + Six.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenFullHouseGetFullHouseReturnsFullHouse() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Three, Hearts);
        Card card4 = new Card(Three, Clubs);
        Card card5 = new Card(Three, Diamonds);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.FullHouse.getValue() + Three.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenAFourOfAKindGetFourOfAKindReturnsFourOfAKind() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Two, Diamonds);
        Card card4 = new Card(Two, Spades);
        Card card5 = new Card(Ten, Spades);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.FourOfAKind.getValue() + Two.getValue(), hand.getHandValue());
    }

    @Test
    public void GivenAStraightFlushGetStraightFlushReturnsStraightFlush() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Three, Hearts);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Hearts);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        Hand hand = new Hand(cards);

        assertEquals(HandValue.StraightFlush.getValue() + Six.getValue(), hand.getHandValue());
    }
}
