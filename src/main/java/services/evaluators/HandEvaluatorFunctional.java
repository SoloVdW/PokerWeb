package services.evaluators;

import models.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Charl on 2015-01-09.
 */
public class HandEvaluatorFunctional {

    public static boolean isStraightFlush(Hand hand) {
        List<Card> cards = hand.getCards();

        boolean flush = cards.stream().allMatch(c -> c.getSuit() == cards.get(0).getSuit());

        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());
        boolean straight = ranks.stream().mapToInt(r -> r.ordinal()).max().getAsInt() -
                ranks.stream().mapToInt(r -> r.ordinal()).min().getAsInt() == 4 && ranks.stream().distinct().count() == 5;
        return flush && straight;
    }

    public static boolean isFourOfAKind(Hand hand) {
        List<Card> cards = hand.getCards();

        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());
        if (ranks.stream().distinct().count() != 2)
            return false;

        long count = ranks.stream().filter(r -> r == ranks.stream().distinct().findFirst().get()).count();
        return (count == 1L || count == 4L);
    }

    public static boolean isFullHouse(Hand hand) {
        List<Card> cards = hand.getCards();

        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());
        List<Rank> distinctRanks = ranks.stream().distinct().collect(Collectors.toList());

        if (distinctRanks.size() != 2)
            return false;

        return ranks.stream().filter(r -> r == distinctRanks.get(0)).count() == 3L ||
                ranks.stream().filter(r -> r == distinctRanks.get(1)).count() == 3L;
    }

    public static boolean isFlush(Hand hand) {
        List<Card> cards = hand.getCards();

        Stream<Suit> suitStream = cards.stream().map(c -> c.getSuit());
        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());

        boolean straight = ranks.stream().mapToInt(r -> r.ordinal()).max().getAsInt() -
                ranks.stream().mapToInt(r -> r.ordinal()).min().getAsInt() == 4 && ranks.stream().distinct().count() == 5;

        return !straight && suitStream.distinct().count() == 1;
    }

    public static boolean isStraight(Hand hand) {
        List<Card> cards = hand.getCards();
        Stream<Suit> suitStream = cards.stream().map(c -> c.getSuit());

        if (suitStream.distinct().count() < 2)
            return false;

        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());

        return ranks.stream().mapToInt(r -> r.ordinal()).max().getAsInt() -
                ranks.stream().mapToInt(r -> r.ordinal()).min().getAsInt() == 4 && ranks.stream().distinct().count() == 5;
    }

    public static boolean isThreeOfAKind(Hand hand) {
        List<Card> cards = hand.getCards();
        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());
        List<Rank> distinctRanks = ranks.stream().distinct().collect(Collectors.toList());

        if (distinctRanks.size() != 3)
            return false;

        long count1 = ranks.stream().filter(r -> r == distinctRanks.get(0)).count();
        long count2 = ranks.stream().filter(r -> r == distinctRanks.get(1)).count();
        long count3 = ranks.stream().filter(r -> r == distinctRanks.get(2)).count();

        return count1 == 3L ||
                count2 == 3L ||
                count3 == 3L;
    }

    public static boolean isTwoPair(Hand hand) {
        List<Card> cards = hand.getCards();
        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());
        List<Rank> distinctRanks = ranks.stream().distinct().collect(Collectors.toList());

        if (distinctRanks.size() != 3)
            return false;

        return ranks.stream().filter(r -> r == distinctRanks.get(0)).count() == 2L ||
                ranks.stream().filter(r -> r == distinctRanks.get(1)).count() == 2L ||
                ranks.stream().filter(r -> r == distinctRanks.get(2)).count() == 2L;
    }

    public static boolean isPair(Hand hand) {
        List<Card> cards = hand.getCards();

        List<Rank> ranks = cards.stream().map(c -> c.getRank()).collect(Collectors.toList());
        return ranks.stream().distinct().count() == 4;
    }

    public static HandType determineTypeOfHand(Hand hand)
    {
        if (isStraightFlush(hand))
            return HandType.STRAIGHT_FLUSH;
        if (isFourOfAKind(hand))
            return HandType.FOUR_OF_A_KIND;
        if (isFullHouse(hand))
            return HandType.FULL_HOUSE;
        if (isFlush(hand))
            return HandType.FLUSH;
        if (isStraight(hand))
            return HandType.STRAIGHT;
        if (isThreeOfAKind(hand))
            return HandType.THREE_OF_A_KIND;
        if (isTwoPair(hand))
            return HandType.TWO_PAIR;
        if (isPair(hand))
            return HandType.ONE_PAIR;
        return HandType.HIGH_CARD;
    }

}
