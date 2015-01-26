package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.*;
import repositories.GameRepositoryJPA;
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

    @Inject
    private GameRepositoryJPA gameRepositoryJPA;

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

        Calendar calendar = Calendar.getInstance();
        game.setDateTime(calendar.getTime());

        Deck deck = new Deck();
        List<Hand> hands = deck.dealhands(users.size());

        for (int i = 0; i < users.size(); i++) {
            PlayerGame playerGame = new PlayerGame();
            hands.get(i).setHandType(determineTypeOfHand(hands.get(i)));
            playerGame.setHand(hands.get(i));
            playerGame.setPlayer(users.get(i));

            game.addPlayerGame(playerGame);
        }

        //Sort according to hand
        Collections.sort(game.getPlayerGames(), new PlayerGameHandComparator());

        game.getPlayerGames().get(0).setResult(ResultType.WIN);

        for (int i = 1; i < game.getPlayerGames().size(); i++) {
            User player = game.getPlayerGames().get(i).getPlayer();
            if (player.getUsername().equals(username)) {
                PlayerGame playerGame = game.getPlayerGames().get(i);
                game.getPlayerGames().remove(playerGame);
                game.getPlayerGames().add(0, playerGame);
            }

        }

        game.setStatus(GameStatus.COMPLETE);
        gameRepositoryJPA.persist(game);

        return Optional.of(game);
    }

    public Optional<Game> createNewGame(List<User> users, String username) {
        Game game = new Game();

        Calendar calendar = Calendar.getInstance();
        game.setDateTime(calendar.getTime());

        Deck deck = new Deck();
        List<Hand> hands = deck.dealhands(users.size());

        for (int i = 0; i < users.size(); i++) {
            PlayerGame playerGame = new PlayerGame();
            hands.get(i).setHandType(determineTypeOfHand(hands.get(i)));
            playerGame.setHand(hands.get(i));
            playerGame.setPlayer(users.get(i));

            game.addPlayerGame(playerGame);
        }

        //Sort according to hand
        Collections.sort(game.getPlayerGames(), new PlayerGameHandComparator());

        game.getPlayerGames().get(0).setResult(ResultType.WIN);

        //Place host into 0 index
        for (int i = 1; i < game.getPlayerGames().size(); i++) {
            User player = game.getPlayerGames().get(i).getPlayer();
            if (player.getUsername().equals(username)) {
                PlayerGame playerGame = game.getPlayerGames().get(i);
                game.getPlayerGames().remove(playerGame);
                game.getPlayerGames().add(0, playerGame);
            }

        }

        game.setStatus(GameStatus.COMPLETE);
        gameRepositoryJPA.persist(game);

        return Optional.of(game);
    }


}

