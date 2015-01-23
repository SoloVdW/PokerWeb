package repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Game;
import models.PlayerGame;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-21.
 */
@Singleton
public class GameRepositoryJPA extends BaseJPARepository<Game> {

    @Inject
    PlayerGameRepositoryJPA playerGameRepositoryJPA;

    @Inject
    HandRepositoryJPA handRepositoryJPA;

    @Transactional
    @Override
    public void persist(Game game) {
        EntityManager em = getEntityManager();
        List<PlayerGame> playerGames = game.getPlayerGames();
        game.setPlayerGames(null);

        em.persist(game);
        em.flush();
        for (PlayerGame playerGame : playerGames) {
            playerGame.setGame(game);
            playerGameRepositoryJPA.persist(playerGame);

        }
        game.setPlayerGames(playerGames);

    }

    @UnitOfWork
    public Optional<List<Game>> findGamesByUsername(String username) {
        List<Game> games = getEntityManager().createQuery("SELECT g FROM PlayerGame pg INNER JOIN pg.game g WHERE pg.player.username = :username").setParameter("username", username).getResultList();
        if (games == null || games.isEmpty())
            return Optional.empty();
        return Optional.of(games);
    }

    @UnitOfWork
    public Optional<List<PlayerGame>> findPlayerGamesByUsername(String username) {
        List<PlayerGame> playerGames = getEntityManager().createQuery("SELECT pg FROM PlayerGame pg WHERE pg.player.username = :username").setParameter("username", username).getResultList();
        if (playerGames == null || playerGames.isEmpty())
            return Optional.empty();
        return Optional.of(playerGames);
    }

    @UnitOfWork
    public Optional<List<PlayerGame>> findPlayerGamesByGameId(Long id) {
        List<PlayerGame> playerGames = getEntityManager().createQuery("SELECT pg FROM PlayerGame pg WHERE pg.game.id = :id").setParameter("id", id).getResultList();
        if (playerGames == null || playerGames.isEmpty())
            return Optional.empty();

        for (PlayerGame playerGame : playerGames) {
//            playerGame.getHand().setPlayerGame(null);
//            playerGame.setGame(null);
            /*playerGame.getPlayer().setPlayerGames(null);

            for (Card card : playerGame.getHand().getCards()) {
                card.setHands(null);
            }*/
        }
        return Optional.of(playerGames);
    }
}
