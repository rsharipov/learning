package com.rsharipov.tasks.hackerrank;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TestTask025 {
    
    @Test
    public void testSum() {
        assertEquals("1 + 1", "2", new task025().sum("1", "1"));
        assertEquals("1 + 2", "3", new task025().sum("1", "2"));
        assertEquals("2 + 3", "5", new task025().sum("2", "3"));
        assertEquals("3 + 5", "8", new task025().sum("3", "5"));
        assertEquals("5 + 8", "13", new task025().sum("5", "8"));
        assertEquals("8 + 13", "21", new task025().sum("8", "13"));
        assertEquals("50 + 50", "100", new task025().sum("50", "50"));
        assertEquals("1 + 99999", "100000", new task025().sum("1", "99999"));
    }
    
    @Test
    public void testFirstTermToContainDigits() {
        assertEquals(12, new task025().firstTermToContainDigits(3));
    }

    @Test
    public void testPerformance() {
        long start = System.nanoTime();
        new task025();
        assertThat("Calculation time", (System.nanoTime() - start) / 1000000000.0, lessThan(4.0));
    }
    
}
