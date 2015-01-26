package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import repositories.GameRepositoryJPA;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Charl on 2015-01-26.
 */
@Singleton
public class AsyncController {
    @Inject
    GameRepositoryJPA gameRepositoryJPA;

    private volatile ConcurrentHashMap<Long, Instant> updateMap = new ConcurrentHashMap<>();
    private volatile Instant updateGameList;

    public void updatedGame(long id) {
        Instant instant = Instant.now();
        updateMap.put(id, instant);
    }

    public void updatedGameList() {
        updateGameList = Instant.now();
    }

    public Result getUpdatedGame(Context context) throws InterruptedException {
        Instant instant = Instant.now();
        Long id= Long.parseLong(context.getSession().get("gameId"));

        while (updateMap.get(id) == null ||updateMap.get(id).isBefore(instant)) {
            Thread.sleep(100);
        }

        Optional<Game> game = gameRepositoryJPA.findGameById(id);
        if (game.isPresent())
            return Results.json().render(game.get());
        return Results.notFound();
    }

    public Result getGameList() throws InterruptedException {
        Instant instant = Instant.now();

        while (updateGameList== null || updateGameList.isBefore(instant)) {
            Thread.sleep(100);
        }

        Optional<List<Game>> gameList = gameRepositoryJPA.findOpenGames();
        if (gameList.isPresent())
            return Results.json().render(gameList.get());
        return Results.notFound();
    }


}
