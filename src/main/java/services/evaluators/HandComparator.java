package services.evaluators;

import models.Hand;
import models.Rank;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static services.evaluators.HandEvaluatorFunctional.determineTypeOfHand;

/**
 * Created by Charl on 2015-01-21.
 */
public class HandComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand o1, Hand o2) {
        o1.setHandType(determineTypeOfHand(o1));
        o2.setHandType(determineTypeOfHand(o2));

        if (o1.getHandType().ordinal()< o2.getHandType().ordinal())
        {
            return -1;
        }
        if (o1.getHandType().ordinal()> o2.getHandType().ordinal())
        {
            return 1;
        }

        switch (o1.getHandType())
        {
            case STRAIGHT_FLUSH:

                break;

            case FOUR_OF_A_KIND: break;

            case FULL_HOUSE: break;

            case FLUSH: break;

            case STRAIGHT: break;

            case THREE_OF_A_KIND: break;

            case TWO_PAIR: break;

            case ONE_PAIR: break;

            case HIGH_CARD:
                List<Rank> o1ranks = o1.getCards().stream().map(c -> c.getRank()).collect(Collectors.toList());
                Integer o1Max = o1ranks.stream().mapToInt(c -> c.ordinal()).max().getAsInt();

                List<Rank> o2ranks = o2.getCards().stream().map(c -> c.getRank()).collect(Collectors.toList());
                Integer o2Max = o2ranks.stream().mapToInt(c -> c.ordinal()).max().getAsInt();

                return o1Max.compareTo(o2Max);

        }
        return 0;

    }
}
