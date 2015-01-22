package models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by Charl on 2015-01-21.
 */
public class SuitRank implements Serializable{

    @Enumerated(EnumType.STRING)
    private Suit suit;

    @Enumerated(EnumType.ORDINAL)
    private Rank rank;

    public SuitRank() {
    }

    public SuitRank(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuitRank)) return false;

        SuitRank suitRank = (SuitRank) o;

        if (rank != suitRank.rank) return false;
        if (suit != suitRank.suit) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "("+suit + ", " + rank.getShorthand() + ')';
    }
}
