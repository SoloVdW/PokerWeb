package repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.PlayerGame;

/**
 * Created by Charl on 2015-01-21.
 */
@Singleton
public class PlayerGameRepositoryJPA extends BaseJPARepository<PlayerGame> {

    @Inject
    HandRepositoryJPA handRepositoryJPA;
}
