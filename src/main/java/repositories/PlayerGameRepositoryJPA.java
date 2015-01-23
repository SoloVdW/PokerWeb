package repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Hand;
import models.PlayerGame;

import javax.persistence.EntityManager;

/**
 * Created by Charl on 2015-01-21.
 */
@Singleton
public class PlayerGameRepositoryJPA extends BaseJPARepository<PlayerGame> {

    @Inject
    HandRepositoryJPA handRepositoryJPA;

    @Transactional
    @Override
    public void persist(PlayerGame playerGame) {
        Hand hand = playerGame.getHand();
        EntityManager em= getEntityManager();

        playerGame.setHand(null);
        hand.setPlayerGame(playerGame);

        em.persist(hand);
        em.flush();

        playerGame.setHand(hand);
    }
}
