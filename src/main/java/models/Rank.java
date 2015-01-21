package models;

/**
 * Created by Charl on 2015-01-09.
 */
public enum Rank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String shorthand;

    private Rank(String shorthand) {
        this.shorthand = shorthand;
    }

    public boolean equalsSymbol(String otherShorthand) {
        return (otherShorthand == null) ? false : shorthand.equalsIgnoreCase(otherShorthand);
    }

    public static Rank getRank(String shorthand) {
        for (Rank rank : Rank.values()) {
            if (rank.equalsSymbol(shorthand)) {
                return rank;
            }
        }
        return null;
    }

    public  String getShorthand()
    {
        return shorthand;
    }

}
