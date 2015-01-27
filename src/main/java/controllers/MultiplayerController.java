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
import ninja.session.Session;
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

    @Inject
    AsyncController asyncController;

    private static final String GAME="game";
    private static final String GAMES="games";
    private static final String GAME_ID="gameId";
    private static final String HOSTING="hosting";

    public Result index(Context context) {
        Result result = Results.html();
        String username = context.getSession().get(SecureFilter.USERNAME);

        Optional<Game> game = multiplayerService.createMultiPlayerGame(username);
        if (game.isPresent()) {
            result.render(GAME, game.get())
                    .render(SecureFilter.USERNAME, username);
            Boolean hosting= true;
            context.getSession().put(HOSTING,hosting.toString());
            context.getSession().put(GAME_ID, game.get().getId().toString());
            asyncController.updatedGame(game.get().getId());
            asyncController.updatedGameList();
        }

        return result;
    }

    public Result hostGame(@PathParam("id") long id, Context context) {
        Optional<Game> game = multiplayerService.createMultiPlayerGameFromOldGame(id,context.getSession().get(SecureFilter.USERNAME));
        if (game.isPresent()) {
            Boolean hosting= true;
            context.getSession().put(HOSTING,hosting.toString());
            context.getSession().put(GAME_ID,game.get().getId().toString());

            SimplePojo simplePojo = new SimplePojo();
            simplePojo.gameId = game.get().getId();

            asyncController.updatedGame(game.get().getId());
            asyncController.updatedGameList();
            return Results.json().render(simplePojo);
        }
        return Results.notFound();
    }

    public Result showGames(Context context) {
        Optional<List<Game>> games = multiplayerService.getOpenGames();
        if (games.isPresent()) {
            return Results.html().render(GAMES, games.get());
        }
        return Results.html();
    }

    public Result joinGame(@PathParam("id") long id, Context context) {
        String username = context.getSession().get(SecureFilter.USERNAME);
        Optional<Game> game = multiplayerService.joinGame(id, username);
        if (game.isPresent()) {
            String strHosting= context.getSession().get(HOSTING);
            if (strHosting!= null && !strHosting.isEmpty() && Boolean.valueOf(strHosting))
            {
                String gameId= context.getSession().get(GAME_ID);
                long gId=Long.parseLong(gameId);
                if (gId!= id)
                {
                    context.getSession().put(HOSTING, Boolean.FALSE.toString());
                }
            }
            asyncController.updatedGame(game.get().getId());
            return Results.json().render(game.get());
        }

        return Results.badRequest();
    }

    public Result gameLobby(@PathParam("id") long id, Context context) {
        Optional<Game> game = multiplayerService.getGame(id);
        if (game.isPresent()) {
            Session session= context.getSession();
            session.put(GAME_ID, game.get().getId().toString());
            String strHosting= session.get(HOSTING);

            return Results.html().render(GAME,game.get()).render(HOSTING,strHosting);
        }

        return Results.notFound();
    }

    public Result playGame(@PathParam("id") long id, Context context) {
        Optional<Game> game = multiplayerService.playGame(id, context.getSession().get(SecureFilter.USERNAME));
        if (game.isPresent()) {
            context.getSession().put(HOSTING, Boolean.FALSE.toString());
            SimplePojo simplePojo= new SimplePojo();
            simplePojo.gameId = game.get().getId();
            asyncController.updatedGame(game.get().getId());
            return Results.json().render(simplePojo);
        }
        return Results.notFound();
    }

    public Result showGame(@PathParam("id") long id, Context context) {
        Optional<Game> game = multiplayerService.getGame(id);
        if (game.isPresent())
            return Results.html().render(GAME,game.get());

        return Results.notFound();
    }

    public static class SimplePojo {

        public Long gameId;

    }
}
