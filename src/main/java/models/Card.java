package models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Charl on 2015-01-09.
 */
@Entity
public class Card implements Serializable{

    @Id
    @Enumerated(EnumType.STRING)
    private Suit suit;
    @Id
    @Enumerated(EnumType.ORDINAL)
    private Rank rank;

    @ManyToMany
    private List<Hand> hands;

    public Card() {
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(String rankAndSuit) {
        if (rankAndSuit== null)
            return;
        if (rankAndSuit.isEmpty())
            return;
        if (rankAndSuit.length()!= 2)
            return;


        String rankStr= "" + rankAndSuit.charAt(0);
        String suitStr= ""+ rankAndSuit.charAt(1);

        rank= Rank.getRank(rankStr);
        suit= Suit.getSuit(suitStr);
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    @Override
    public String toString() {
        return "" + rank.getShorthand() + " " + suit;
    }
}
