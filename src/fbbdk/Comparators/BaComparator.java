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
public class BaComparator implements Comparator<BaseballPlayer> {

    @Override
    public int compare(BaseballPlayer o1, BaseballPlayer o2) {
        if(o1.getHitterStats().getBa() < o2.getHitterStats().getBa()){
            return 1;
        }
        else if(o1.getHitterStats().getBa() == o2.getHitterStats().getBa()){
            return 0;
        }
        else{
            return -1;
        }
    }
    
}
