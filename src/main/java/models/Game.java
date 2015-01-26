package models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Charl on 2015-01-20.
 */

@Entity
public class Game extends BaseEntityLongId {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<PlayerGame> playerGames;

    @Temporal(TemporalType.DATE)
    private Date dateTime;

    @Enumerated(EnumType.STRING)
    private GameStatus status=GameStatus.WAITING;

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

    public void addPlayerGame(PlayerGame playerGame) {
        if (playerGames == null)
            playerGames = new ArrayList<>();

        playerGame.setGame(this);
        Hand hand = playerGame.getHand();
        if (hand != null)
            hand.setPlayerGame(playerGame);
        playerGames.add(playerGame);
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
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
