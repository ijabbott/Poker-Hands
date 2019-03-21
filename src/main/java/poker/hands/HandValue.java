package poker.hands;

public enum HandValue {
    HighCard(0),
    Pair(1);

    private final int value;

    HandValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
