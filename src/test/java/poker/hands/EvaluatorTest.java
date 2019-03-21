package poker.hands;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static poker.hands.CardSuit.*;
import static poker.hands.CardSuit.Hearts;
import static poker.hands.CardValue.*;

public class EvaluatorTest {

    @Test
    public void GivenTwoCardsGetHighCardReturnsHighestCard() {
        Card lowCard = new Card(Two, Hearts);
        Card highCard = new Card(Three, Hearts);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(lowCard, highCard));

        HandEvaluator evaluator = new HandEvaluator();


        assertEquals(Arrays.asList(highCard), evaluator.getHighCard(hand));
    }

    @Test
    public void GivenAPairGetPairReturnsPair() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getPair(hand));
    }

    @Test
    public void GivenTwoPairGetTwoPairReturnsTwoPair() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Three, Hearts);
        Card card4 = new Card(Three, Clubs);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getTwoPair(hand));
    }

    @Test
    public void GivenAThreeOfAKindGetThreeOfAKindReturnsThreeOfAKind() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Two, Diamonds);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getThreeOfAKind(hand));
    }

    @Test
    public void GivenAStraightGetStraightReturnsStraight() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Three, Clubs);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Clubs);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getStraight(hand));
    }

    @Test
    public void GivenAnOutOfOrderStraightGetStraightReturnsStraight() {
        Card card1 = new Card(Three, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Clubs);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getStraight(hand));
    }

    @Test
    public void GivenAFlushGetFlushReturnsFlush() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Hearts);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Hearts);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getFlush(hand));
    }

    @Test
    public void GivenFullHouseGetFullHouseReturnsFullHouse() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Three, Hearts);
        Card card4 = new Card(Three, Clubs);
        Card card5 = new Card(Three, Diamonds);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getFullHouse(hand));
    }

    @Test
    public void GivenAFourOfAKindGetFourOfAKindReturnsFourOfAKind() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Two, Clubs);
        Card card3 = new Card(Two, Diamonds);
        Card card4 = new Card(Two, Spades);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getFourOfAKind(hand));
    }

    @Test
    public void GivenAStraightFlushGetStraightFlushReturnsStraightFlush() {
        Card card1 = new Card(Two, Hearts);
        Card card2 = new Card(Three, Hearts);
        Card card3 = new Card(Four, Hearts);
        Card card4 = new Card(Five, Hearts);
        Card card5 = new Card(Six, Hearts);
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(hand, evaluator.getStraightFlush(hand));
    }

    @Test
    public void GivenTwoHandsWithHighCardVsHighCardEvaluateHandReturnsHandWithHigherCard() {
        ArrayList<Card> lowerHand = new ArrayList<>(Arrays.asList(new Card(Two, Hearts)));
        ArrayList<Card> higherHand = new ArrayList<>(Arrays.asList(new Card(Three, Hearts)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(lowerHand, higherHand));

        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(higherHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithPairVsHighCardEvaluateHandReturnsHandWithPair() {
        ArrayList<Card> highCardHand = new ArrayList<>(Arrays.asList(new Card(Two, Hearts), new Card(Three, Hearts)));
        ArrayList<Card> pairHand = new ArrayList<>(Arrays.asList(new Card(Two, Diamonds), new Card(Two, Clubs)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(highCardHand, pairHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(pairHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithPairVsPairEvaluateHandReturnsHandWithHighestPair() {
        ArrayList<Card> lowPairHand = new ArrayList<>(Arrays.asList(new Card(Two, Hearts), new Card(Two, Hearts), new Card(Jack, Hearts)));
        ArrayList<Card> highPairHand = new ArrayList<>(Arrays.asList(new Card(Three, Diamonds), new Card(Three, Clubs), new Card(Five, Hearts)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(lowPairHand, highPairHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(highPairHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithTwoPairVsPairEvaluateHandReturnsHandWithTwoPair() {
        ArrayList<Card> pairHand = new ArrayList<>(Arrays.asList(new Card(Two, Diamonds), new Card(Two, Clubs)));
        ArrayList<Card> twoPairHand = new ArrayList<>(Arrays.asList(new Card(Two, Hearts), new Card(Three, Hearts), new Card(Two, Spades), new Card(Three, Clubs)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(pairHand, twoPairHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(twoPairHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithThreeOfAKindVsTwoPairEvaluateHandReturnsHandWithThreeOfAKind() {
        ArrayList<Card> twoPairHand = new ArrayList<>(Arrays.asList(new Card(Two, Diamonds), new Card(Two, Clubs), new Card(Four, Hearts), new Card(Four, Clubs)));
        ArrayList<Card> threeOfAKindHand = new ArrayList<>(Arrays.asList(new Card(Three, Hearts), new Card(Three, Clubs), new Card(Three, Diamonds)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(twoPairHand, threeOfAKindHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(threeOfAKindHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithStraightVsThreeOfAKindEvaluateHandReturnsHandWithStraight() {
        ArrayList<Card> threeOfAKindHand = new ArrayList<>(Arrays.asList(new Card(Three, Hearts), new Card(Three, Clubs), new Card(Three, Diamonds)));
        ArrayList<Card> straightHand = new ArrayList<>(Arrays.asList(new Card(Two, Diamonds), new Card(Three, Spades), new Card(Four, Hearts), new Card(Five, Clubs), new Card(Six, Hearts)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(threeOfAKindHand, straightHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(straightHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithFlushVsStraightEvaluateHandReturnsHandWithFlush() {
        ArrayList<Card> straightHand = new ArrayList<>(Arrays.asList(new Card(Two, Diamonds), new Card(Three, Spades), new Card(Four, Hearts), new Card(Five, Hearts), new Card(Six, Hearts)));
        ArrayList<Card> flushHand = new ArrayList<>(Arrays.asList(new Card(Two, Clubs), new Card(Seven, Clubs), new Card(Four, Clubs), new Card(King, Clubs), new Card(Ten, Clubs)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(straightHand, flushHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(flushHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithFullHouseVsFlushEvaluateHandReturnsHandWithFullHouse() {
        ArrayList<Card> flushHand = new ArrayList<>(Arrays.asList(new Card(Two, Clubs), new Card(Seven, Clubs), new Card(Four, Clubs), new Card(King, Clubs), new Card(Ten, Clubs)));
        ArrayList<Card> fullHouseHand = new ArrayList<>(Arrays.asList(new Card(Eight, Diamonds), new Card(Eight, Spades), new Card(Eight, Hearts), new Card(Five, Hearts), new Card(Five, Spades)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(flushHand, fullHouseHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(fullHouseHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithFourOfAKindVsFullHouseEvaluateHandReturnsHandWithFourOfAKind() {
        ArrayList<Card> fullHouseHand = new ArrayList<>(Arrays.asList(new Card(Eight, Diamonds), new Card(Eight, Spades), new Card(Eight, Hearts), new Card(Five, Hearts), new Card(Five, Spades)));
        ArrayList<Card> fourOfAKindHand = new ArrayList<>(Arrays.asList(new Card(Two, Clubs), new Card(Two, Hearts), new Card(Two, Diamonds), new Card(Two, Spades)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(fullHouseHand, fourOfAKindHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(fourOfAKindHand, evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithStraightFlushVsFourOfAKindEvaluateHandReturnsHandWithStraightFlush() {
        ArrayList<Card> fourOfAKindHand = new ArrayList<>(Arrays.asList(new Card(Two, Clubs), new Card(Two, Hearts), new Card(Two, Diamonds), new Card(Two, Spades)));
        ArrayList<Card> straightFlushHand = new ArrayList<>(Arrays.asList(new Card(Three, Hearts), new Card(Four, Hearts), new Card(Five, Hearts), new Card(Six, Hearts), new Card(Seven, Hearts)));
        ArrayList<ArrayList<Card>> handList = new ArrayList<>(Arrays.asList(fourOfAKindHand, straightFlushHand));
        HandEvaluator evaluator = new HandEvaluator();

        assertEquals(straightFlushHand, evaluator.evaluateHands(handList));
    }
}
