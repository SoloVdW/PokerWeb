package services;

import com.google.inject.Singleton;
import models.Deck;
import models.Hand;
import models.HandType;
import services.evaluators.HandEvaluatorFunctional;

/**
 * Created by Charl on 2015-01-12.
 */
@Singleton
public class PokerService implements IPokerService {
    public Hand dealHand() {
        Deck deck = new Deck();
        return deck.dealHand();
    }

    public HandType evaluateHand(Hand hand) {
        return HandEvaluatorFunctional.determineTypeOfHand(hand);
    }
}

