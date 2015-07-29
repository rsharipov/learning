package com.rsharipov;

import java.util.List;
import java.util.stream.Collectors;

public class IntervalNode {

    private final int center;
    private final Integer[] sortedByStart;
    private final Integer[] sortedByEnd;
    private final IntervalNode left;
    private final IntervalNode right;
    
    public IntervalNode(List<Interval> intervals) {
        this.center = chooseCenter(intervals);
        List<Interval> leftIntervals = intervals.stream().filter(a -> a.right() < center).collect(Collectors.toList());
        if (leftIntervals.size() == intervals.size()) {
            throw new AssertionError("Left branch should have less intervals than all");
        }
        left = leftIntervals.isEmpty() ? null : new IntervalNode(leftIntervals);
        List<Interval> rightIntervals = intervals.stream().filter(a -> center < a.left()).collect(Collectors.toList());
        if (rightIntervals.size() == intervals.size()) {
            throw new AssertionError("Right branch should have less intervals than all");
        }
        right = rightIntervals.isEmpty() ? null : new IntervalNode(rightIntervals);
        sortedByStart = intervals.stream().filter(a -> a.contains(center)).map(x -> x.left()).sorted(Integer::compare).toArray(size -> new Integer[size]);
        sortedByEnd = intervals.stream().filter(a -> a.contains(center)).map(x -> x.right()).sorted(Integer::compare).toArray(size -> new Integer[size]);
    }
    
    /**
     * return first of the elements that are greater than key
     * or 0 if there's no elements in the array
     * @param array
     * @param key
     * @return 
     */
    private int firstGreater(Integer[] array, int key) {
        int begin = 0;
        int end = array.length;
        if (array.length == 0 || key < array[0]) return 0;
        while (begin + 1 < end) {
            int mid = (begin + end) / 2;
            if (key < array[mid]) {
                end = mid;
            }
            else {
                begin = mid;
            }
        }
        return end;
    }
    
    private int firstGreaterOrEqual(Integer[] array, int key) {
        int begin = 0;
        int end = array.length;
        if (array.length == 0 || key <= array[0]) return 0;
        while (begin + 1 < end) {
            int mid = (begin + end) / 2;
            if (key <= array[mid]) {
                end = mid;
            }
            else {
                begin = mid;
            }
        }
        return end;
        
    }
    
    public long countContaining(int point) {
        if (center == point) {
            return sortedByStart.length;
        }
        if (point < center) {
            return firstGreater(sortedByStart, point) + 
                (left == null ? 0 : left.countContaining(point));
        }
        else {
            return sortedByEnd.length - firstGreaterOrEqual(sortedByEnd, point) + 
                (right == null ? 0 : right.countContaining(point));
        }
    }

    private int chooseCenter(List<Interval> intervals) {
        return intervals.get(0).left();
    }
}
