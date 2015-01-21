package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charl on 2015-01-09.
 */
@Entity
public class Hand{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;

    @Enumerated(EnumType.STRING)
    private HandType handType;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="card_hand",
            joinColumns={@JoinColumn(name="hand_id")},
            inverseJoinColumns={@JoinColumn(name="card_suit", referencedColumnName="suit"),
                    @JoinColumn(name="card_rank", referencedColumnName="rank")})
    private List<Card> cards;
    @OneToOne(cascade = CascadeType.PERSIST)
    private PlayerGame player_game;

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


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

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

    public PlayerGame getPlayer_game() {
        return player_game;
    }

    public void setPlayer_game(PlayerGame player_game) {
        this.player_game = player_game;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", Id=" + Id +
                '}';
    }
}
