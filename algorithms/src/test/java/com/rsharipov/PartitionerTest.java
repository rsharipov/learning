package com.rsharipov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartitionerTest {
    
    public PartitionerTest() {
    }

    @Test
    public void testPartitionOf1SelectsMaximum() {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(3, 1, 4, 2, 7, 1, 5, 4));
        new Partitioner().partition(1, data);
        assertEndsIn(data, 7);
    }
    
    @Test
    public void testPartitionOf2SelectsMaximum2() {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(3, 1, 4, 2, 7, 1, 5, 4));
        new Partitioner().partition(2, data);
        assertEndsIn(data, 5, 7);
    }
    
    @Test
    public void testPartitionOf3SelectsMaximum3() {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(3, 1, 4, 2, 7, 1, 5, 4));
        new Partitioner().partition(3, data);
        assertEndsIn(data, 4, 5, 7);
    }
    
    @Test
    public void testPartitionOf4SelectsMaximum4() {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(3, 1, 4, 2, 7, 1, 5, 4));
        new Partitioner().partition(4, data);
        assertEndsIn(data, 4, 4, 5, 7);
    }
    
    @Test
    public void testPartitionOf5SelectsMaximum5() {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(3, 1, 4, 2, 7, 1, 5, 4));
        new Partitioner().partition(5, data);
        assertEndsIn(data, 3, 4, 4, 5, 7);
    }
    
    @Test
    public void testPartitionOf6SelectsMaximum6() {
        ArrayList<Integer> data = new ArrayList<>(Arrays.asList(3, 1, 4, 2, 7, 1, 5, 4));
        new Partitioner().partition(6, data);
        assertEndsIn(data, 2, 3, 4, 4, 5, 7);
    }

    private void assertEndsIn(ArrayList<Integer> data, Integer... ints) {
        assertEquals(
            Arrays.asList(ints).stream().sorted().collect(Collectors.toList()), 
            data.subList(data.size() - ints.length, data.size()).stream().sorted().collect(Collectors.toList()));
    }
    
}
