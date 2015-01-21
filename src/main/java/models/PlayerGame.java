package models;

import javax.persistence.*;

/**
 * Created by Charl on 2015-01-20.
 */
@Entity
public class PlayerGame{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;

    @ManyToOne
    private User player;
    @ManyToOne
    private Game game;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player_game")
    private Hand hand;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
