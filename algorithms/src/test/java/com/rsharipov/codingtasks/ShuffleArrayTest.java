package com.rsharipov.codingtasks;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShuffleArrayTest {
    
    public ShuffleArrayTest() {
    }

    @Test
    public void testShuffle() {
        final int SIZE = 25;
        final int ROUNDS = 1000000;
        int[][] count = new int[SIZE][];
        for (int i = 0; i < SIZE; ++i) {
            count[i] = new int[SIZE];
        }
        for (int i = 0; i < ROUNDS; ++i) {
            int[] array = new int[SIZE];
            for (int j = 0; j < SIZE; ++j) {
                array[j] = j;
            }
            new ShuffleArray().shuffle(array);
            for (int j = 0; j < SIZE; ++j) {
                ++count[array[j]][j];
            }
        }
        final double THRESHOLD = 0.05;
        final double EXPECTED = (double)ROUNDS / SIZE;
        for (int i = 0; i < SIZE; ++i) {
            System.out.println(Arrays.toString(count[i]));
            for (int j = 0; j < SIZE; ++j) {
                assertEquals(EXPECTED, (double)count[i][j], EXPECTED * THRESHOLD);
            }            
        }
    }
    
}
