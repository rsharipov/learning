package com.rsharipov.codingtasks;

import com.rsharipov.Interval;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class APointOverlappingTheMostIntervalsFinderTest {

    private final Random random = new Random();
    
    public List<Interval> generateRandom(int size) {
        ArrayList<Interval> result = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            int left = random.nextInt();
            int right = random.nextInt();
            if (right < left) {
                int t = left;
                left = right;
                right = t;
            }
            result.add(new Interval(left, right));
        }
        return result;
    }
    
    @Test
    public void testCorrectness() {
        APointOverlappingTheMostIntervalsFinder finder = new APointOverlappingTheMostIntervalsFinder();
        for (int i = 0; i < 1000; ++i) {
            List<Interval> intervals = generateRandom(1000);
            assertEquals(
                countContaining(intervals, finder.findAPointOverlappingTheMostIntervalsInQuadraticTime(intervals)),
                countContaining(intervals, finder.findAPointOverlappingTheMostIntervalsInLinearithmicTime(intervals)));
            assertEquals(
                countContaining(intervals, finder.findAPointOverlappingTheMostIntervalsInQuadraticTime(intervals)),
                countContaining(intervals, finder.findAPointOverlappingTheMostIntervalsWithPriorityQueue(intervals)));
        }
    }
    
    private long countContaining(List<Interval> intervals, int point) {
        return intervals.stream().filter((i) -> (i.contains(point))).count();
    }
    
    @Test
    public void testNonQuadraticness() {
        int firstSize = 100;
        int secondSize = 10000;
        double coefficient = (Math.log(howMuchTimeFor(secondSize)) - Math.log(howMuchTimeFor(firstSize))) /
            (Math.log(secondSize) - Math.log(firstSize));
        assertThat(coefficient, lessThan(1.2));
    }
    
    public long howMuchTimeFor(int size) {
        APointOverlappingTheMostIntervalsFinder finder = new APointOverlappingTheMostIntervalsFinder();
        List<Interval> intervals = generateRandom(size);
        long start = System.nanoTime();
        finder.findAPointOverlappingTheMostIntervalsInLinearithmicTime(intervals);
        return System.nanoTime() - start;
    }
    
}
