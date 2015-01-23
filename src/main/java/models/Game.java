package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Charl on 2015-01-20.
 */

@Entity
public class Game extends BaseEntityLongId{

   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id*/;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    @JsonIgnore
    private List<PlayerGame> playerGames;

    @Temporal(TemporalType.DATE)
    private Date dateTime;

    /*public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }*/

    public List<PlayerGame> getPlayerGames() {
        return playerGames;
    }

    public void setPlayerGames(List<PlayerGame> playerGames) {
        this.playerGames = playerGames;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", playerGames=" + playerGames +
                ", dateTime=" + dateTime +
                '}';
    }
}
