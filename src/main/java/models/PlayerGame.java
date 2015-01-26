package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Charl on 2015-01-20.
 */
@Entity
public class PlayerGame extends BaseEntityLongId{

    @Enumerated(EnumType.STRING)
    private ResultType result =ResultType.LOOSE;

    @ManyToOne
    private User player;
    @ManyToOne
    @JsonIgnore
    private Game game;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "playerGame")
    private Hand hand;

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
        this.hand.setPlayerGame(this);
    }

    @Override
    public String toString() {
        return "PlayerGame{" +
                "id=" + id +
                ", result=" + result +
                ", player=" + player +
                ", game=" + game +
                ", hand=" + hand +
                '}';
    }
}
