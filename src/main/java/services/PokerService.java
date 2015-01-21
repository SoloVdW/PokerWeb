package services;

import com.google.inject.Singleton;
import models.Deck;
import models.Hand;
import models.HandType;

import java.util.HashMap;
import java.util.Map;

import static services.evaluators.HandEvaluatorFunctional.*;

/**
 * Created by Charl on 2015-01-12.
 */
@Singleton
public class PokerService implements IPokerService {
    private static final String STRAIGHT_FLUSH = "Straight Flush";
    private static final String FOUR_OF_A_KIND="Four of a Kind";
    private static final String FULL_HOUSE="Full House";
    private static final String FLUSH="Flush";
    private static final String STRAIGHT="Straight";
    private static final String THREE_OF_A_KIND="Three of a Kind";
    private static final String TWO_PAIR="Two Pair";
    private static final String ONE_PAIR="One Pair";
    private static final String NOTHING="High Card";


    public Hand dealHand() {
        Deck deck = new Deck();
        return deck.dealHand();
    }

    public HandType evaluateHand(Hand hand) {
        if (isStraightFlush(hand))
            return HandType.STRAIGHT_FLUSH;
        if (isFourOfAKind(hand))
            return HandType.FOUR_OF_A_KIND;
        if (isFullHouse(hand))
            return HandType.FULL_HOUSE;
        if (isFlush(hand))
            return HandType.FLUSH;
        if (isStraight(hand))
            return HandType.STRAIGHT;
        if (isThreeOfAKind(hand))
            return HandType.THREE_OF_A_KIND;
        if (isTwoPair(hand))
            return HandType.TWO_PAIR;
        if (isPair(hand))
            return HandType.ONE_PAIR;
        return HandType.HIGH_CARD;
    }
}

