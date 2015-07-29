package com.rsharipov.codingtasks;

import java.util.Arrays;
import java.util.TreeSet;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SetIntersectionTest {
    
    @Test
    public void testIntersect() {
        TreeSet<Integer> first = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 6, 8, 10, 11, 12, 14));
        TreeSet<Integer> second = new TreeSet<>(Arrays.asList(-1, 3, 4, 5, 10, 12, 14, 16));
        assertEquals(new TreeSet<>(Arrays.asList(3, 4, 10, 12, 14)), new SetIntersection().intersect(first, second));
    }
    
    
    @Test
    public void testIntersectionOfMultipleSets() {
        assertEquals(
            new TreeSet<>(Arrays.asList(3, 12)), 
            new SetIntersection().intersect(
                Arrays.asList(
                    new TreeSet<>(Arrays.asList(1, 2, 3, 4, 6, 8, 10, 11, 12, 14)),
                    new TreeSet<>(Arrays.asList(-1, 3, 4, 5, 10, 12, 14, 16)),
                    new TreeSet<>(Arrays.asList(3, 6, 12, 15, 16))
                )
            )
        );
    }

    
}
