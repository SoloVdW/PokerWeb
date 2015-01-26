package repositories;

import com.google.inject.Singleton;
import models.Game;
import models.GameStatus;
import models.PlayerGame;
import ninja.jpa.UnitOfWork;

import java.util.List;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-21.
 */
@Singleton
public class GameRepositoryJPA extends BaseJPARepository<Game> {

    @UnitOfWork
    public Optional<List<Game>> findGamesByUsername(String username) {
        List<Game> games = getEntityManager().createQuery("SELECT g FROM PlayerGame pg INNER JOIN pg.game g WHERE pg.player.username = :username").setParameter("username", username).getResultList();
        if (games == null || games.isEmpty())
            return Optional.empty();
        return Optional.of(games);
    }

    @UnitOfWork
    public Optional<List<PlayerGame>> findPlayerGamesByUsername(String username) {
        List<PlayerGame> playerGames = getEntityManager().createQuery("SELECT pg FROM PlayerGame pg WHERE pg.player.username = :username AND pg.game.status = :status").setParameter("username", username).setParameter("status", GameStatus.COMPLETE).getResultList();
        if (playerGames == null || playerGames.isEmpty())
            return Optional.empty();
        return Optional.of(playerGames);
    }

    @UnitOfWork
    public Optional<List<PlayerGame>> findPlayerGamesByGameId(Long id) {
        List<PlayerGame> playerGames = getEntityManager().createQuery("SELECT pg FROM PlayerGame pg WHERE pg.game.id = :id").setParameter("id", id).getResultList();
        if (playerGames == null || playerGames.isEmpty())
            return Optional.empty();
        return Optional.of(playerGames);
    }

    @UnitOfWork
    public Optional<List<Game>> findOpenGames() {
        List<Game> games = getEntityManager().createQuery("SELECT g FROM Game g WHERE g.status = :status").setParameter("status", GameStatus.WAITING).getResultList();
        if (games == null || games.isEmpty())
            return Optional.empty();
        for (Game game: games)
        {
            game.setPlayerGames(null);
        }
        return Optional.of(games);
    }

    @UnitOfWork
    public Optional<Game> findGameById(long id) {
       return getSingleResult(getEntityManager().createQuery("SELECT g FROM Game g WHERE g.id = :id").setParameter("id", id));
    }

    @UnitOfWork
    public Optional<Game> findGameIdAndUserNameBy(long id) {
        return getSingleResult(getEntityManager().createQuery("SELECT g FROM Game g WHERE g.id = :id").setParameter("id", id));
    }
}
