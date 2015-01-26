package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import filters.SecureFilter;
import models.Game;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import services.IPokerService;
import services.MultiplayerService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-23.
 */
@Singleton
@FilterWith(SecureFilter.class)
public class MultiplayerController {
    @Inject
    IPokerService pokerService;

    @Inject
    MultiplayerService multiplayerService;

    public Result index(Context context) {
        Result result = Results.html();
        String username = context.getSession().get(SecureFilter.USERNAME);
        Optional<Game> game = multiplayerService.createMultiPlayerGame(username);
        if (game.isPresent()) {
            result.render("game", game.get())
                    .render(SecureFilter.USERNAME, username);
        }

        return result;
    }

    public Result hostGame(Context context) {
        Optional<Game> game = multiplayerService.createMultiPlayerGame(context.getSession().get(SecureFilter.USERNAME));
        if (game.isPresent()) {
            SimplePojo simplePojo = new SimplePojo();
            simplePojo.gameId = game.get().getId();
            return Results.json().render(simplePojo);
        }
        return Results.notFound();
    }

    public Result showGames(Context context) {
        Optional<List<Game>> games = multiplayerService.getOpenGames();
        if (games.isPresent()) {
            return Results.html().render("games", games.get());
        }
        return Results.html();
    }

    public Result joinGame(@PathParam("id") long id, Context context) {
        String username = context.getSession().get(SecureFilter.USERNAME);
        Optional<Game> game = multiplayerService.joinGame(id, username);
        if (game.isPresent())
            return Results.json().render(game.get());

        return Results.badRequest();
    }

    public Result gameLobby(@PathParam("id") long id, Context context) {
        Optional<Game> game = multiplayerService.getGame(id);
        if (game.isPresent())
            return Results.html().render(game.get());

        return Results.notFound();
    }

    public Result playGame(@PathParam("id") long id, Context context) {
        Optional<Game> game = multiplayerService.playGame(id, context.getSession().get(SecureFilter.USERNAME));
        if (game.isPresent()) {
            SimplePojo simplePojo= new SimplePojo();
            simplePojo.gameId= game.get().getId();
            return Results.json().render(simplePojo);
        }
        return Results.notFound();
    }

    public Result showGame(@PathParam("id") long id, Context context) {
        Optional<Game> game = multiplayerService.getGame(id);
        if (game.isPresent())
            return Results.html().render(game.get());

        return Results.notFound();
    }

    public static class SimplePojo {

        public Long gameId;

    }
}
