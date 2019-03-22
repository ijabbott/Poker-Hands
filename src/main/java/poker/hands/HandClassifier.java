package poker.hands;

import java.util.ArrayList;
import java.util.List;


public interface HandClassifier {
    List<Card> classify(List<Card> hand);
}


