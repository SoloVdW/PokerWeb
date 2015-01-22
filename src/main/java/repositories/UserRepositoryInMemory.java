package repositories;

import com.google.inject.Singleton;
import models.User;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-16.
 */
@Singleton
public class UserRepositoryInMemory implements UserRepository {

    HashMap<String, User> usersMap = new HashMap<>();

    public Optional<User> findUserByUsername(String username)
    {
        return Optional.ofNullable(usersMap.get(username));
    }

    public void persist(User user)
    {
        usersMap.put(user.getUsername(), user);
    }
}
