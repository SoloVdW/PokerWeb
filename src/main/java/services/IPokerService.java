package services;


import models.Hand;
import models.HandType;

/**
 * Created by Charl on 2015-01-12.
 */
public interface IPokerService {
    Hand dealHand();
    HandType evaluateHand(Hand hand);
}
