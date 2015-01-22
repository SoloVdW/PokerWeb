package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.User;
import repositories.UserRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Charl on 2015-01-16.
 */
@Singleton
public class AuthenticationService {
    @Inject
    UserRepository userRepository;

    public boolean userExists(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (!user.isPresent())
            return false;
        return true;
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (!optionalUser.isPresent())
            return false;

        String hashedSaltedPassword = hashSaltedPassword(optionalUser.get().getSalt(), password);
        if (hashedSaltedPassword.equals(optionalUser.get().getPassword()))
            return true;

        return false;
    }

    public Optional<User> register(String username, String password) {
        if (userExists(username))
            return Optional.empty();

        byte[] salt= generateSalt();
        String hashedPW= hashSaltedPassword(salt, password);

        User user= new User();
        user.setUsername(username);
        user.setSalt(salt);
        user.setPassword(hashedPW);

        userRepository.persist(user);

        return Optional.ofNullable(user);
    }


    private String hashSaltedPassword(byte[] salt, String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();

            return toHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private byte[] generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return salt;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }
}
