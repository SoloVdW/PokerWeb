package services;


import models.Game;
import models.Hand;
import models.HandType;

import java.util.Optional;

/**
 * Created by Charl on 2015-01-12.
 */
public interface IPokerService {
    Hand dealHand();
    HandType evaluateHand(Hand hand);
    Optional<Game> createNewGame(String username);
}
