package repositories;

import models.User;

import java.util.Optional;

/**
 * Created by Charl on 2015-01-16.
 */
public interface UserRepository {

    public Optional<User> findUserByUsername(String username);

    public void persistUser(User user);
}
