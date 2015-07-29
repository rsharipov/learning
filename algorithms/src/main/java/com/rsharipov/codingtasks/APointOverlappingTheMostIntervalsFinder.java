package com.rsharipov.codingtasks;

import com.rsharipov.Interval;
import com.rsharipov.IntervalNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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
    
    public int findAPointOverlappingTheMostIntervalsInLinearithmicTime(List<Interval> intervals) {
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
    
    public int findAPointOverlappingTheMostIntervalsWithPriorityQueue(List<Interval> intervals) {
        ArrayList<Interval> copy = new ArrayList<>(intervals);
        Collections.sort(copy, (a, b) -> Integer.compare(a.left(), b.left()));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int maxCount = -1;
        int maxPoint = 0;
        for (Interval i : copy) {
            queue.add(i.right());
            while (queue.peek() < i.left()) {
                queue.remove();
            }
            if (maxCount < queue.size()) {
                maxCount = queue.size();
                maxPoint = i.left();
            }
        }
        return maxPoint;
    }
    
}
