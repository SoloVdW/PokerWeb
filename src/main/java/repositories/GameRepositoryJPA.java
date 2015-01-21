package repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Game;
import models.PlayerGame;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Charl on 2015-01-21.
 */
@Singleton
public class GameRepositoryJPA extends BaseJPARepository<Game> {

    @Inject
    PlayerGameRepositoryJPA playerGameRepositoryJPA;

    @Transactional
    public void saveGame(Game game)
    {
        EntityManager em= getEntityManager();
        List<PlayerGame> playerGames= game.getPlayer_games();
        game.setPlayer_games(null);

        em.persist(game);
        em.flush();
        for (PlayerGame playerGame:playerGames)
        {
            playerGame.setGame(game);
            playerGameRepositoryJPA.persistPlayerGame(playerGame);
        }

    }
}
