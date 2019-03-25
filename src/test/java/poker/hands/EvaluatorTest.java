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
    public void GivenTwoHandsWithHighCardVsHighCardEvaluateHandReturnsHandWithHigherCard() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand lowerHand = new Hand(Arrays.asList(new Card(Two, Hearts), new Card(Jack, Hearts), new Card(King, Clubs), new Card(Ten, Diamonds), new Card(Nine, Spades)));
        Hand higherHand = new Hand(Arrays.asList(new Card(Three, Hearts), new Card(Jack, Hearts), new Card(King, Clubs), new Card(Ten, Diamonds), new Card(Ace, Clubs)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(lowerHand, higherHand));

        assertEquals(new ArrayList<>(Arrays.asList(higherHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithPairVsHighCardEvaluateHandReturnsHandWithPair() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand highCardHand = new Hand(Arrays.asList(new Card(Two, Hearts), new Card(Three, Hearts), new Card(Jack, Hearts), new Card(King, Clubs), new Card(Ten, Diamonds)));
        Hand pairHand = new Hand(Arrays.asList(new Card(Two, Diamonds), new Card(Two, Clubs), new Card(Jack, Spades), new Card(King, Spades), new Card(Ten, Clubs)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(highCardHand, pairHand));

        assertEquals(new ArrayList<>(Arrays.asList(pairHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithPairVsPairEvaluateHandReturnsHandWithHighestPair() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand lowPairHand = new Hand(Arrays.asList(new Card(Two, Hearts), new Card(Two, Diamonds), new Card(Jack, Hearts), new Card(King, Clubs), new Card(Ten, Diamonds)));
        Hand highPairHand = new Hand(Arrays.asList(new Card(Three, Diamonds), new Card(Three, Clubs), new Card(Five, Hearts), new Card(King, Spades), new Card(Ten, Spades)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(lowPairHand, highPairHand));

        assertEquals(new ArrayList<>(Arrays.asList(highPairHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithPairVsPairWithSameValuesEvaluateHandReturnsHandWithHighestCard() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand lowCardPairHand = new Hand(Arrays.asList(new Card(Three, Hearts), new Card(Three, Diamonds), new Card(Jack, Hearts), new Card(Seven, Clubs), new Card(Ten, Diamonds)));
        Hand highCardPairHand = new Hand(Arrays.asList(new Card(Three, Spades), new Card(Three, Clubs), new Card(Five, Hearts), new Card(King, Spades), new Card(Ten, Spades)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(lowCardPairHand, highCardPairHand));

        assertEquals(new ArrayList<>(Arrays.asList(highCardPairHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithPairVsPairWithSameValuesAndSameHighCardEvaluateHandReturnsListOfTiedHands() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand cardPairHand1 = new Hand(Arrays.asList(new Card(Three, Hearts), new Card(Three, Diamonds), new Card(King, Hearts), new Card(Seven, Clubs), new Card(Ten, Diamonds)));
        Hand cardPairHand2 = new Hand(Arrays.asList(new Card(Three, Spades), new Card(Three, Clubs), new Card(Five, Hearts), new Card(King, Spades), new Card(Ten, Spades)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(cardPairHand1, cardPairHand2));

        assertEquals(new ArrayList<>(Arrays.asList(cardPairHand1, cardPairHand2)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithTwoPairVsPairEvaluateHandReturnsHandWithTwoPair() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand pairHand = new Hand(Arrays.asList(new Card(Two, Diamonds), new Card(Two, Clubs), new Card(Three, Hearts), new Card(King, Clubs), new Card(Ten, Diamonds)));
        Hand twoPairHand = new Hand(Arrays.asList(new Card(Two, Hearts), new Card(Three, Clubs), new Card(Two, Spades), new Card(Three, Spades), new Card(Ten, Spades)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(pairHand, twoPairHand));

        assertEquals(new ArrayList<>(Arrays.asList(twoPairHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithThreeOfAKindVsTwoPairEvaluateHandReturnsHandWithThreeOfAKind() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand twoPairHand = new Hand(Arrays.asList(new Card(Two, Diamonds), new Card(Two, Clubs), new Card(Four, Hearts), new Card(Four, Clubs), new Card(Ten, Diamonds)));
        Hand threeOfAKindHand = new Hand(Arrays.asList(new Card(Three, Hearts), new Card(Three, Clubs), new Card(Three, Diamonds), new Card(King, Clubs), new Card(Ten, Spades)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(twoPairHand, threeOfAKindHand));

        assertEquals(new ArrayList<>(Arrays.asList(threeOfAKindHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithStraightVsThreeOfAKindEvaluateHandReturnsHandWithStraight() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand threeOfAKindHand = new Hand(Arrays.asList(new Card(Three, Hearts), new Card(Three, Clubs), new Card(Three, Diamonds), new Card(Four, Clubs), new Card(Ten, Diamonds)));
        Hand straightHand = new Hand(Arrays.asList(new Card(Two, Diamonds), new Card(Three, Spades), new Card(Four, Hearts), new Card(Five, Clubs), new Card(Six, Hearts)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(threeOfAKindHand, straightHand));

        assertEquals(new ArrayList<>(Arrays.asList(straightHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithFlushVsStraightEvaluateHandReturnsHandWithFlush() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand straightHand = new Hand(Arrays.asList(new Card(Two, Diamonds), new Card(Three, Spades), new Card(Four, Hearts), new Card(Five, Hearts), new Card(Six, Hearts)));
        Hand flushHand = new Hand(Arrays.asList(new Card(Two, Clubs), new Card(Seven, Clubs), new Card(Four, Clubs), new Card(King, Clubs), new Card(Ten, Clubs)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(straightHand, flushHand));

        assertEquals(new ArrayList<>(Arrays.asList(flushHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithFullHouseVsFlushEvaluateHandReturnsHandWithFullHouse() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand flushHand = new Hand(Arrays.asList(new Card(Two, Clubs), new Card(Seven, Clubs), new Card(Four, Clubs), new Card(King, Clubs), new Card(Ten, Clubs)));
        Hand fullHouseHand = new Hand(Arrays.asList(new Card(Eight, Diamonds), new Card(Eight, Spades), new Card(Eight, Hearts), new Card(Five, Hearts), new Card(Five, Spades)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(flushHand, fullHouseHand));

        assertEquals(new ArrayList<>(Arrays.asList(fullHouseHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithFourOfAKindVsFullHouseEvaluateHandReturnsHandWithFourOfAKind() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand fullHouseHand = new Hand(Arrays.asList(new Card(Eight, Diamonds), new Card(Eight, Spades), new Card(Eight, Hearts), new Card(Five, Hearts), new Card(Five, Spades)));
        Hand fourOfAKindHand = new Hand(Arrays.asList(new Card(Two, Clubs), new Card(Two, Hearts), new Card(Two, Diamonds), new Card(Two, Spades), new Card(Eight, Spades)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(fullHouseHand, fourOfAKindHand));

        assertEquals(new ArrayList<>(Arrays.asList(fourOfAKindHand)), evaluator.evaluateHands(handList));
    }

    @Test
    public void GivenTwoHandsWithStraightFlushVsFourOfAKindEvaluateHandReturnsHandWithStraightFlush() {
        HandEvaluator evaluator = new HandEvaluator();
        Hand fourOfAKindHand = new Hand(Arrays.asList(new Card(Two, Clubs), new Card(Two, Hearts), new Card(Two, Diamonds), new Card(Two, Spades), new Card(Eight, Spades)));
        Hand straightFlushHand = new Hand(Arrays.asList(new Card(Three, Hearts), new Card(Four, Hearts), new Card(Five, Hearts), new Card(Six, Hearts), new Card(Seven, Hearts)));

        ArrayList<Hand> handList = new ArrayList<>(Arrays.asList(fourOfAKindHand, straightFlushHand));

        assertEquals(new ArrayList<>(Arrays.asList(straightFlushHand)), evaluator.evaluateHands(handList));
    }
}
