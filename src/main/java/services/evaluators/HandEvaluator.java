package services.evaluators;

import models.Card;
import models.Hand;
import models.Rank;
import models.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Charl on 2015-01-09.
 */
public class HandEvaluator {

    private static boolean isValidHand(Hand hand) {
        if (hand == null)
            return false;

        List<Card> cards = hand.getCards();
        if (cards == null || cards.size() != 5)
            return false;

        return true;
    }

    public static boolean isStraightFlush(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getRank().compareTo(o2.getRank());
            }
        });

        Rank previousRank = null;
        Suit previousSuit = null;

        for (Card card : cards) {
            if (previousRank != null && card.getRank().ordinal() != previousRank.ordinal() + 1) {
                return false;
            }
            if (previousSuit != null && card.getSuit() != previousSuit) {
                return false;
            }
            previousRank = card.getRank();
            previousSuit = card.getSuit();
        }
        return true;
    }

    public static boolean isFourOfAKind(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        Rank previousRank = null;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            int counter = 0;
            for (int j = 0; j < cards.size(); j++) {
                if (i != j && cards.get(j).getRank() == card.getRank())
                    counter++;
            }

            if (counter >= 3)
                return true;
        }

        return false;
    }

    public static boolean isFullHouse(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        Rank previousRank = null;

        boolean pair = false;
        boolean threeOfAKind = false;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            int counter = 0;
            for (int j = 0; j < cards.size(); j++) {
                if (i != j && cards.get(j).getRank() == card.getRank())
                    counter++;
            }

            if (counter == 1)
                pair = true;
            else if (counter == 2)
                threeOfAKind = true;

        }

        if (pair && threeOfAKind)
            return true;

        return false;

    }

    public static boolean isFlush(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        Suit previousSuit = null;

        for (Card card : cards) {
            if (previousSuit != null && card.getSuit() != previousSuit) {
                return false;
            }
            previousSuit = card.getSuit();
        }
        return true;
    }

    public static boolean isStraight(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getRank().compareTo(o2.getRank());
            }
        });

        Rank previousRank = null;

        for (Card card : cards) {
            if (previousRank != null && card.getRank().ordinal() != previousRank.ordinal() + 1) {
                return false;
            }
            previousRank = card.getRank();
        }
        return true;
    }

    public static boolean isThreeOfAKind(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        Rank previousRank = null;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            int counter = 0;
            for (int j = 0; j < cards.size(); j++) {
                if (i != j && cards.get(j).getRank() == card.getRank())
                    counter++;
            }

            if (counter >= 2)
                return true;
        }

        return false;
    }

    public static boolean isTwoPair(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        Rank previousRank = null;
        int counter = 0;

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            for (int j = 0; j < cards.size(); j++) {
                if (i != j && cards.get(j).getRank() == card.getRank())
                    counter++;
                break;
            }
            if (counter >= 2) {
                return true;
            }
        }

        return false;
    }

    public static boolean isPair(Hand hand) {
        if (!isValidHand(hand)) {
            throw new IllegalArgumentException("Not a valid hand");
        }

        List<Card> cards = hand.getCards();

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            for (int j = 0; j < cards.size(); j++) {
                if (i != j && cards.get(j).getRank() == card.getRank())
                    return true;
            }
        }

        return false;
    }

}
