package models;

/**
 * Created by Charl on 2015-01-20.
 */
public enum HandType {

    STRAIGHT_FLUSH("Straight Flush"),
    FOUR_OF_A_KIND("Four of a Kind"),
    FULL_HOUSE("Full House"),
    FLUSH("Flush"),
    STRAIGHT("Straight"),
    THREE_OF_A_KIND("Three of a Kind"),
    TWO_PAIR("Two Pair"),
    ONE_PAIR("One Pair"),
    HIGH_CARD("High Card");

    private final String strRepresentation;

    private HandType(String strRepresentation)
    {
        this.strRepresentation= strRepresentation;
    }

    @Override
    public String toString() {
        return strRepresentation;
    }
}
