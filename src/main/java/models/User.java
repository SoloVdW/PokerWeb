package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Charl on 2015-01-16.
 */
@Entity
public class User {

    @Id
    @Size(max = 20)
    private String username;

    private byte[] salt;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<PlayerGame> player_games;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PlayerGame> getPlayer_games() {
        return player_games;
    }

    public void setPlayer_games(List<PlayerGame> player_games) {
        this.player_games = player_games;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", salt=" + Arrays.toString(salt) +
                ", password='" + password + '\'' +
                ", player_games=" + player_games +
                '}';
    }
}
