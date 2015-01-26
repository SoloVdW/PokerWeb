package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import repositories.GameRepositoryJPA;

import java.time.Instant;
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

    public void GameUpdated(long id) {
        Instant instant = Instant.now();
        updateMap.put(id, instant);
    }

    public Result getUpdatedGame(Context context) throws InterruptedException {
        Instant instant = Instant.now();
        Long id= Long.parseLong(context.getSession().get("gameId"));

        while (updateMap.get(id).isBefore(instant)) {
            Thread.sleep(100);
        }

        Optional<Game> game = gameRepositoryJPA.findGameById(id);
        if (game.isPresent())
            return Results.json().render(game);
        return Results.notFound();
    }
}
