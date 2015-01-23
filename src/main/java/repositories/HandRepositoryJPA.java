package repositories;

import com.google.inject.Singleton;
import models.Hand;
import ninja.jpa.UnitOfWork;

import java.util.Optional;

/**
 * Created by Charl on 2015-01-21.
 */
@Singleton
public class HandRepositoryJPA extends BaseJPARepository<Hand> {

    @UnitOfWork
    public Optional<Hand> findHandByPlayerGameId(Long id) {
        return getSingleResult(getEntityManager().createQuery("SELECT h FROM Hand h WHERE h.player_game.id = :id").setParameter("id", id));
    }
}
