package com.rsharipov.codingtasks;

import com.rsharipov.Interval;
import java.util.List;

public class CheckIfTwoIntervalsOverlap {
    
    public boolean check(List<Interval> sortedListOfIntervals) {
        Interval previous = null;
        for (Interval i : sortedListOfIntervals) {
            if (previous != null) {
                if (previous.overlaps(i)) {
                    return true;
                }
            }
            previous = i;
        }
        return false;
    }
    
}
