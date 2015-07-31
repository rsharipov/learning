package com.rsharipov.codingtasks;

import com.rsharipov.Interval;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckIfTwoIntervalsOverlapTest {
    
    @Test
    public void testCheckReturnsFalseWhenExpected() {
        List<Interval> sortedList = Arrays.asList(
            new Interval(-2, -1), 
            new Interval(0, 1),
            new Interval(2, 3),
            new Interval(4, 5));
        assertFalse(new CheckIfTwoIntervalsOverlap().check(sortedList));
    }
    
    @Test
    public void testCheckReturnsTrueWhenExpected() {
        List<Interval> sortedList = Arrays.asList(
            new Interval(-2, 0), 
            new Interval(0, 1),
            new Interval(2, 3),
            new Interval(4, 5));
        assertTrue(new CheckIfTwoIntervalsOverlap().check(sortedList));
    }
    
}
