package poker.hands;

public enum CardSuit {
    Hearts("H"),
    Diamonds("D"),
    Clubs("C"),
    Spades("S");

    private final String suit;

    CardSuit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return this.suit;
    }
}
