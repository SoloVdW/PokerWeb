package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Charl on 2015-01-20.
 */

@Entity
public class Game{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<PlayerGame> player_games;

    @Temporal(TemporalType.DATE)
    private Date dateTime;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<PlayerGame> getPlayer_games() {
        return player_games;
    }

    public void setPlayer_games(List<PlayerGame> player_games) {
        this.player_games = player_games;
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
                "Id=" + Id +
                ", player_games=" + player_games +
                ", dateTime=" + dateTime +
                '}';
    }
}
