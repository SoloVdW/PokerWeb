package services.evaluators;

import models.PlayerGame;

import java.util.Comparator;

/**
 * Created by Charl on 2015-01-22.
 */
public class PlayerGameHandComparator implements Comparator<PlayerGame> {

    private HandComparator handComparator;

    @Override
    public int compare(PlayerGame o1, PlayerGame o2) {
        if (o1.getHand()==null || o2.getHand()==null)
        {
            return 0;
        }
        handComparator= new HandComparator();
        return handComparator.compare(o1.getHand(), o2.getHand());
    }
}
