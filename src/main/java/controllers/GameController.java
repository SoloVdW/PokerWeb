/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import filters.SecureFilter;
import models.Game;
import models.PlayerGame;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import repositories.GameRepositoryJPA;
import services.IPokerService;

import java.util.List;
import java.util.Optional;

@Singleton
@FilterWith(SecureFilter.class)
public class GameController {
    @Inject
    private IPokerService pokerService;

    @Inject
    private GameRepositoryJPA gameRepositoryJPA;

    public Result index(Context context) {
        Result result = Results.html();

        Optional<Game> optionalGame = pokerService.createNewGame(context.getSession().get(SecureFilter.USERNAME));
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            List<PlayerGame> playerGames = game.getPlayerGames();

            gameRepositoryJPA.persist(game);

            result.render("playerGames", playerGames);
        }

        return result;
    }

    public Result dealHands(Context context)
    {
        Optional<Game> optionalGame = pokerService.createNewGame(context.getSession().get(SecureFilter.USERNAME));
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            List<PlayerGame> playerGames = game.getPlayerGames();

            gameRepositoryJPA.persist(game);

            return Results.json().render("playerGames", playerGames);
        }

        return Results.badRequest();
    }

    public Result history(Context context) {
        Result result = Results.html();

        Optional<List<PlayerGame>> optionalPlayerGames = gameRepositoryJPA.findPlayerGamesByUsername(context.getSession().get(SecureFilter.USERNAME));
        if (optionalPlayerGames.isPresent())
            result.render("playerGames", optionalPlayerGames.get());

        return result;
    }

    public Result getGameHistory(@PathParam("id") Long id) {
        Optional<List<PlayerGame>> optionalPlayerGames = gameRepositoryJPA.findPlayerGamesByGameId(id);
        if (optionalPlayerGames.isPresent()) {
            return Results.json().render(optionalPlayerGames.get());
        }

        return Results.badRequest();
    }

    public void setPokerService(IPokerService pokerService) {
        this.pokerService = pokerService;
    }


}
