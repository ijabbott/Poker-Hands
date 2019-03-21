package poker.hands;

import org.junit.Test;
import static org.junit.Assert.*;
import static poker.hands.CardSuit.*;
import static poker.hands.CardValue.*;

public class CardTest {

    @Test
    public void CardTwoOfHeartsHasValueTwo() {
        Card card = new Card(Two, Hearts);
        assertEquals(2, card.getValue());
    }

    @Test
    public void CardKingOfHeartsHasValueThirteen() {
        Card card = new Card(King, Hearts);
        assertEquals(13, card.getValue());
    }

    @Test
    public void CardTwoOfHeartsHasSuitHearts() {
        Card card = new Card(Two, Hearts);
        assertEquals("H", card.getSuit());
    }

    @Test
    public void CardTwoOfHeartsToStringReturns2H() {
        Card card = new Card(Two, Hearts);
        assertEquals("2H", card.toString());
    }
}
