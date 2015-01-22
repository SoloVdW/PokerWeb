package repositories;

import models.User;
import ninja.jpa.UnitOfWork;

import java.util.List;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-20.
 */
public class UserRepositoryJPA extends BaseJPARepository<User> implements UserRepository {

    @UnitOfWork
    public Optional<User> findUserByUsername(String username) {
        return getSingleResult(getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username", username));
    }

    @UnitOfWork
    public Optional<List<User>> findUsersByUsername(String username, int numberOfUsersToReturn) {
        List<User> users = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username LIKE :username").setParameter("username", username+"%").setMaxResults(numberOfUsersToReturn).getResultList();
        if (users == null || users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users);
    }
}
