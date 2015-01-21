package repositories;

import models.User;
import ninja.jpa.UnitOfWork;

import java.util.Optional;

/**
 * Created by Charl on 2015-01-20.
 */
public class UserRepositoryJPA extends BaseJPARepository<User> implements UserRepository{

    @UnitOfWork
    public Optional<User> findUserByUsername(String username)
    {
        return getSingleResult(getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username", username));
    }

    public void persistUser(User user) {
         persist(user);
    }
}
