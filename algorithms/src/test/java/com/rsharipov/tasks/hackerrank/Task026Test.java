package com.rsharipov.tasks.hackerrank;

import com.rsharipov.tasks.hackerrank.projecteuler.Task026;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Task026Test {
    
    @Test
    public void testSomeMethod() {
        Task026 task = new Task026();
        assertEquals(0, task.recurringCycleInDecimalRepresentation(2)); // 1/2 = 0.5
        assertEquals(1, task.recurringCycleInDecimalRepresentation(3)); // 1/3 = 0.(3)
        assertEquals(0, task.recurringCycleInDecimalRepresentation(4)); // 1/4 = 0.25
        assertEquals(0, task.recurringCycleInDecimalRepresentation(5)); // 1/5 = 0.2
        assertEquals(1, task.recurringCycleInDecimalRepresentation(6)); // 1/6 = 0.1(6)
        assertEquals(6, task.recurringCycleInDecimalRepresentation(7)); // 1/7 = 0.(142857)
        assertEquals(0, task.recurringCycleInDecimalRepresentation(8)); // 1/8 = 0.125
        assertEquals(1, task.recurringCycleInDecimalRepresentation(9)); // 1/9 = 0.(1)
        assertEquals(0, task.recurringCycleInDecimalRepresentation(10)); // 1/10 = 0.1
    }
    
    @Test
    public void testSolution() {
        assertEquals(3, new Task026().firstMaximumUntilIsReachedAt(5));
        assertEquals(7, new Task026().firstMaximumUntilIsReachedAt(10));
    }
    
}
