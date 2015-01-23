package models;

import javax.persistence.*;

/**
 * Created by Charl on 2015-01-20.
 */
@Entity
public class PlayerGame implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private ResultType result =ResultType.LOOSE;

    @ManyToOne
    private User player;
    @ManyToOne
    private Game game;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player_game")
    private Hand hand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
