package models;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Charl on 2015-01-09.
 */
@Entity
public class Card implements Serializable {

    @EmbeddedId
    private SuitRank suitRank;

    @ManyToMany
    private List<Hand> hands;

    public Card() {
    }

    public Card(Suit suit, Rank rank) {
        suitRank = new SuitRank(suit, rank);
    }

    public Card(String rankAndSuit) {
        if (rankAndSuit == null)
            return;
        if (rankAndSuit.isEmpty())
            return;
        if (rankAndSuit.length() != 2)
            return;


        String rankStr = "" + rankAndSuit.charAt(0);
        String suitStr = "" + rankAndSuit.charAt(1);

        suitRank= new SuitRank(Suit.getSuit(suitStr),Rank.getRank(rankStr));
    }

    public Suit getSuit() {
        if (suitRank== null)
            return null;
        return suitRank.getSuit();
    }

    public Rank getRank() {
        if (suitRank== null)
            return null;
        return suitRank.getRank();
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suitRank=" + suitRank +
                '}';
    }
}
