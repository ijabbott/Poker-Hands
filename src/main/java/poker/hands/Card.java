package poker.hands;

public class Card {
    private CardValue value;
    private CardSuit suit;

    Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;

    }

    public int getValue() {
        return value.getValue();
    }

    public String getSuit() {
        return suit.getSuit();
    }

    @Override
    public String toString() {
        return this.value.getValue() + this.suit.getSuit();
    }
}
