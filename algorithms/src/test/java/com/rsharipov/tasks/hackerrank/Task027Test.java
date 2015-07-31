package com.rsharipov.tasks.hackerrank;

import com.rsharipov.tasks.hackerrank.projecteuler.Task027;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class Task027Test {
    
    @Test
    public void testIsPrime() {
        assertFalse(new Task027().isPrime(1));
        assertTrue(new Task027().isPrime(2));
        assertTrue(new Task027().isPrime(3));
        assertFalse(new Task027().isPrime(4));
        assertTrue(new Task027().isPrime(5));
        assertFalse(new Task027().isPrime(6));
        assertTrue(new Task027().isPrime(7));
        assertFalse(new Task027().isPrime(8));
    }

    @Test
    public void testLength() {
        assertEquals(40, new Task027().length(1, 41));
        assertEquals(80, new Task027().length(-79, 1601));
    }

    @Test
    public void testSolve() {
        assertEquals(Arrays.asList(-1, 41), new Task027().solve(42));
    }
    
}
