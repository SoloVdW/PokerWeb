package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.*;
import repositories.UserRepositoryJPA;
import services.evaluators.PlayerGameHandComparator;

import java.util.*;

import static services.evaluators.HandEvaluatorFunctional.determineTypeOfHand;

/**
 * Created by Charl on 2015-01-12.
 */
@Singleton
public class PokerService implements IPokerService {

    @Inject
    private UserRepositoryJPA userRepositoryJPA;

    public Hand dealHand() {
        Deck deck = new Deck();
        return deck.dealHand();
    }

    public HandType evaluateHand(Hand hand) {
        return determineTypeOfHand(hand);
    }

    public Optional<Game> createNewGame(String username) {
        Optional<User> user = userRepositoryJPA.findUserByUsername(username);
        if (!user.isPresent())
            return Optional.empty();

        List<User> users = new ArrayList<>();
        users.add(user.get());

        Optional<List<User>> optionalUsers = userRepositoryJPA.findUsersByUsername("computer", 4);
        if (optionalUsers.isPresent()) {

            users.addAll(optionalUsers.get());
        }

        Game game = new Game();

        Calendar calendar= Calendar.getInstance();
        game.setDateTime(calendar.getTime());

        Deck deck = new Deck();
        List<Hand> hands = deck.dealhands(users.size());

        List<PlayerGame> playerGames = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            PlayerGame playerGame = new PlayerGame();
            hands.get(i).setHandType(determineTypeOfHand(hands.get(i)));
            playerGame.setHand(hands.get(i));
            playerGame.setPlayer(users.get(i));

            playerGames.add(playerGame);
        }

        //Sort according to hand
        Collections.sort(playerGames, new PlayerGameHandComparator());

        playerGames.get(0).setResult(ResultType.WIN);

        for (int i = 1; i < playerGames.size(); i++) {
            User player = playerGames.get(i).getPlayer();
            if (player.getUsername().equals(username)) {
                PlayerGame playerGame = playerGames.get(i);
                playerGames.remove(playerGame);
                playerGames.add(0, playerGame);
            }

        }


        game.setPlayer_games(playerGames);

        return Optional.of(game);
    }
}

