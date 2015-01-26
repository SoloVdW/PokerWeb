package services;

import com.google.inject.Inject;
import models.*;
import repositories.GameRepositoryJPA;
import repositories.UserRepositoryJPA;
import services.evaluators.PlayerGameHandComparator;

import java.util.*;

import static services.evaluators.HandEvaluatorFunctional.determineTypeOfHand;

/**
 * Created by Charl on 2015-01-26.
 */
public class MultiplayerService {
    @Inject
    private UserRepositoryJPA userRepositoryJPA;

    @Inject
    private GameRepositoryJPA gameRepositoryJPA;

    @Inject
    IPokerService pokerService;

    public Optional<Game> createMultiPlayerGame(String username) {
        Optional<User> user = userRepositoryJPA.findUserByUsername(username);
        if (!user.isPresent())
            return Optional.empty();

        Game game = new Game();

        Calendar calendar = Calendar.getInstance();
        game.setDateTime(calendar.getTime());

        PlayerGame playerGame = new PlayerGame();
        playerGame.setPlayer(user.get());

        game.addPlayerGame(playerGame);

        gameRepositoryJPA.persist(game);

        return Optional.of(game);
    }

    public Optional<Game> createMultiPlayerGameFromOldGame(long id, String username)
    {
        Optional<Game> optionalGame = gameRepositoryJPA.findGameById(id);
        if (!optionalGame.isPresent())
            return Optional.empty();

        Game game = optionalGame.get();

        List<User> users= new ArrayList<>();
        for (PlayerGame playerGame: game.getPlayerGames())
        {
            users.add(playerGame.getPlayer());
        }

       return pokerService.createNewGame(users,username);
    }



    public Optional<List<Game>> getOpenGames() {
        return gameRepositoryJPA.findOpenGames();
    }

    public Optional<Game> joinGame(long gameId, String username) {
        Optional<Game> optionalGame = gameRepositoryJPA.findGameById(gameId);
        if (!optionalGame.isPresent())
            return Optional.empty();

        Game game = optionalGame.get();

        boolean alreadyJoined = false;
        for (PlayerGame playerGame : game.getPlayerGames()) {
            if (playerGame.getPlayer().getUsername().equals(username)) {
                alreadyJoined = true;
                break;
            }
        }

        if (alreadyJoined) {
            return Optional.of(game);
        }

        Optional<User> user = userRepositoryJPA.findUserByUsername(username);
        if (!user.isPresent())
            return Optional.empty();

        PlayerGame playerGame = new PlayerGame();
        playerGame.setPlayer(user.get());

        game.addPlayerGame(playerGame);
        gameRepositoryJPA.merge(game);
        return Optional.of(game);
    }

    public Optional<Game> playGame(long id, String username) {
        Optional<Game> optGame = gameRepositoryJPA.findGameById(id);
        if (!optGame.isPresent()) {
            return optGame;
        }

        Optional<User> optUser = userRepositoryJPA.findUserByUsername(username);
        if (!optUser.isPresent()) {
            return Optional.empty();
        }

        Game game = optGame.get();

        Deck deck = new Deck();
        for (PlayerGame playerGame : game.getPlayerGames()) {
            Hand hand = deck.dealHand();
            hand.setHandType(determineTypeOfHand(hand));
            playerGame.setHand(hand);
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
        gameRepositoryJPA.merge(game);
        return Optional.of(game);
    }

    public Optional<Game> getGame(long id) {
        return gameRepositoryJPA.findGameById(id);
    }
}
