/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.Comparators;

import fbbdk.data.BaseballPlayer;
import java.util.Comparator;

/**
 *
 * @author Tony
 */
public class EraComparator implements Comparator<BaseballPlayer> {

    @Override
    public int compare(BaseballPlayer o1, BaseballPlayer o2) {
        if (o1.getPitcherStats().getEra() < o2.getPitcherStats().getEra()) {
            return -1;
        } else if (o1.getPitcherStats().getEra() == o2.getPitcherStats().getEra()) {
            return 0;
        } else {
            return 1;
        }

    }
}
