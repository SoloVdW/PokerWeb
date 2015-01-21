package models;

/**
 * Created by Charl on 2015-01-09.
 */
public enum  Suit {

    SPADES ("S"),
    HEARTS ("H"),
    DIAMONDS ("D"),
    CLUBS ("C");

   private final String symbol;

   private Suit(String symbol) {
        this.symbol = symbol;
    }
    public boolean equalsSymbol(String otherSymbol){
        return (otherSymbol == null)? false:symbol.equalsIgnoreCase(otherSymbol);
    }

    public static Suit getSuit(String shorthand) {
        for (Suit suit : Suit.values()) {
            if (suit.equalsSymbol(shorthand)) {
                return suit;
            }
        }
        return null;
    }

}


