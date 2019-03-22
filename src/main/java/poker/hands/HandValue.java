package poker.hands;

import java.util.Arrays;
import java.util.Optional;

public enum HandValue {
    HighCard(0),
    Pair(100),
    TwoPair(200),
    ThreeOfAKind(300),
    Straight(400),
    Flush(500),
    FullHouse(600),
    FourOfAKind(700),
    StraightFlush(800);

    private final int value;

    HandValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HandValue valueOf(int value) {
        return Arrays.stream(HandValue.values()).filter(handValue -> handValue.value == value).findFirst().orElse(null);
    }
}
