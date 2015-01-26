package services;


import models.Game;
import models.Hand;
import models.HandType;
import models.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-12.
 */
public interface IPokerService {
    Hand dealHand();
    HandType evaluateHand(Hand hand);
    Optional<Game> createNewGame(String username);
    Optional<Game> createNewGame(List<User> users, String username);
}
