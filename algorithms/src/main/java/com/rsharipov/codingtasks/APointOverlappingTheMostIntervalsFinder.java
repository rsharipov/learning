package com.rsharipov.codingtasks;

import com.rsharipov.Interval;
import com.rsharipov.IntervalNode;
import java.util.List;

public class APointOverlappingTheMostIntervalsFinder {

    public int findAPointOverlappingTheMostIntervalsInQuadraticTime(List<Interval> intervals) {
        int maxCount = 0;
        int maxPoint = -1;
        for (Interval i : intervals) {
            int point = i.right();
            int count = 0;
            for (Interval v : intervals) {
                if (v.contains(point)) {
                    ++count;
                }
            }
            if (maxCount < count) {
                maxCount = count;
                maxPoint = point;
            }
        }
        return maxPoint;
    }
    
    public int findAPointOverlappingTheMostIntervalsInLogarithmicTime(List<Interval> intervals) {
        IntervalNode node = new IntervalNode(intervals);
        long maxCount = 0;
        int maxPoint = -1;
        for (Interval i : intervals) {
            int point = i.right();
            long count = node.countContaining(point);
            if (maxCount < count) {
                maxCount = count;
                maxPoint = point;
            }
        }        
        return maxPoint;
    }
    
}
