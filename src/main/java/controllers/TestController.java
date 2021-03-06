package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.*;
import ninja.Result;
import ninja.Results;
import repositories.GameRepositoryJPA;
import repositories.UserRepositoryJPA;
import services.AuthenticationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-21.
 */
@Singleton
public class TestController {

    @Inject
    AuthenticationService authenticationService;

    @Inject
    GameRepositoryJPA gameRepositoryJPA;

    @Inject
    UserRepositoryJPA userRepositoryJPA;

    public Result test() {

        saveGame();
        SimplePOJO simplePOJO = new SimplePOJO();
        simplePOJO.executed = true;
        return Results.json().render(simplePOJO);
    }

    private void saveGame() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            String username = "computer" + i;

            Optional<User> user = userRepositoryJPA.findUserByUsername(username);
            if (user.isPresent())
                users.add(user.get());
        }

        Game game = new Game();
        game.setDateTime(new Date());

        Deck deck = new Deck();
        List<Hand> hands = deck.dealhands(6);

        List<PlayerGame> playerGames = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            PlayerGame playerGame = new PlayerGame();
            playerGame.setHand(hands.get(i));
            playerGame.setPlayer(users.get(i));

            playerGames.add(playerGame);
        }

        game.setPlayerGames(playerGames);

        gameRepositoryJPA.persist(game);


    }

    private class SimplePOJO {
        private boolean executed;

        public boolean isExecuted() {
            return executed;
        }

        public void setExecuted(boolean executed) {
            this.executed = executed;
        }
    }
}
