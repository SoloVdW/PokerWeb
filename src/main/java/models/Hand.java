package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charl on 2015-01-09.
 */
@Entity
public class Hand extends BaseEntityLongId{

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;*/

    @Enumerated(EnumType.STRING)
    private HandType handType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="card_hand",
            joinColumns={@JoinColumn(name="hand_id")},
            inverseJoinColumns={@JoinColumn(name="card_suit", referencedColumnName="suit"),
                    @JoinColumn(name="card_rank", referencedColumnName="rank")})
    private List<Card> cards;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private PlayerGame playerGame;

    public Hand() {
    }

    public Hand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Hand(String... cards) {
        this.cards = new ArrayList<Card>(5);
        for (int i = 0; i < cards.length; i++) {
            this.cards.add(new Card(cards[i]));
        }

    }


   /* public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }*/

    public HandType getHandType() {
        return handType;
    }

    public void setHandType(HandType handType) {
        this.handType = handType;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public PlayerGame getPlayerGame() {
        return playerGame;
    }

    public void setPlayerGame(PlayerGame playerGame) {
        this.playerGame = playerGame;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", id=" + id +
                '}';
    }
}
