package com.rsharipov.codingtasks;

import org.junit.Test;
import static org.junit.Assert.*;

public class MedianOfTwoSortedArraysTest {
    
    @Test
    public void testMedianOfEmptyAndOneElementArrays() {
        assertEquals(1.0, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {},
            new int[] {1}), 0.0);
    }
    
    @Test
    public void testMedianOfOneElementAndEmptyArrays() {
        assertEquals(1.0, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {1},
            new int[] {}), 0.0);
    }
    
    @Test
    public void testMedianOfTwoOneElementArrays() {
        assertEquals(1.5, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {1},
            new int[] {2}), 0.001);
    }
    
    @Test
    public void testMedianOfEmptyAndOddElementsArrays() {
        assertEquals(3, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {},
            new int[] {1, 2, 3, 4, 5}), 0.001);
    }
    
    @Test
    public void testMedianOfOddElementsAndEmptyArrays() {
        assertEquals(3, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {1, 2, 3, 4, 5}, 
            new int[] {}), 0.001);
    }
    
    @Test
    public void testMedianOfEmptyAndEvenElementsArrays() {
        assertEquals(4.5, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {},
            new int[] {1, 2, 4, 5, 5, 6}), 0.001);
    }
    
    @Test
    public void testMedianOfEvenElementsAndEmptyArrays() {
        assertEquals(4.5, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {1, 2, 4, 5, 5, 6}, 
            new int[] {}), 0.001);
    }
    
    @Test
    public void testMedianOfTwoArraysFirstIsAllSmallerThanSecond() {
        assertEquals(6.0, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {1, 1, 2, 3, 5, 6}, 
            new int[] {7, 8, 10, 11, 12}), 0.001);
    }
    
    @Test
    public void testMedianOfTwoArraysSecondIsAllSmallerThanFirst() {
        assertEquals(6.0, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {7, 8, 10, 11, 12},
            new int[] {1, 1, 2, 3, 5, 6}), 0.001);
    }
    
    @Test
    public void testMedianOfTwoArraysOverlapping() {
        assertEquals(5.0, new MedianOfTwoSortedArrays().findMedianSortedArrays(
            new int[] {1, 2, 5, 7, 9, 10, 17},
            new int[] {1, 3, 4, 13}), 0.001);
    }
    
    // TODO more tests
    
}
