package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Charl on 2015-01-09.
 */
public class Deck {
    ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>(52);

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(suit, rank));
            }
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Hand> dealhands(int numberOfHands) {
        List<Hand> hands = new ArrayList<>(numberOfHands);
        for (int i = 0; i < numberOfHands; i++) {
            Hand hand = dealHand();
            if (hand != null)
                hands.add(hand);
            else
                break;
        }

        return hands;
    }

    public Hand dealHand() {
        if (cards == null || cards.size() < 5)
            return null;
        ArrayList<Card> handCards = new ArrayList<Card>(5);
        for (int j = 0; j < 5; j++) {
            handCards.add(cards.get(0));
            cards.remove(0);
        }
        return new Hand(handCards);
    }
}
