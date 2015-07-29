package com.rsharipov.tasks.hackerrank;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class ReverseShuffleMergeTest {
    
    @Test
    public void testCountChars() {
        String line = "bdabaceadaedaaaeaecdeadababdbeaeeacacaba";
        ReverseShuffleMerge instance = new ReverseShuffleMerge();
        int[] expResult = new int[] { 8, 3, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        int[] result = instance.countCharsAndDivideInHalf(line);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testSumArray() {
        int[] array = new int[] { 8, 3, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
        ReverseShuffleMerge instance = new ReverseShuffleMerge();
        int expResult = 20;
        int result = instance.sumArray(array);
        assertEquals(expResult, result);
    }

//    @Test
//    public void testFindRightmostStartOfLineWithChars() {
//        String line = "bdabaceadaedaaaeaecdeadababdbeaeeacacaba";
//        int[] charsCounts = new int[] { 8, 3, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
//        };
//        int sum = 20;
//        ReverseShuffleMerge instance = new ReverseShuffleMerge();
//        int expResult = 19;
//        int result = instance.findRightmostStartOfLineWithChars(line, charsCounts, sum);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of solve method, of class ReverseShuffleMerge.
     */
    @Test
    public void testSolve() {
        String line = "eggegg";
        ReverseShuffleMerge instance = new ReverseShuffleMerge();
        String expResult = "egg";
        String result = instance.solve(line);        
        assertEquals(expResult, result);
    }

    /**
     * Test of solve method, of class ReverseShuffleMerge.
     */
    @Test
    @Ignore
    public void testSolveBigLine() {
        String line = "bdabaceadaedaaaeaecdeadababdbeaeeacacaba";
        ReverseShuffleMerge instance = new ReverseShuffleMerge();
        String expResult = "aaaaaabaaceededecbdb";
        System.out.println(expResult);
        String result = instance.findGreatestReverse(line, 19, 
            new int[] { 8, 3, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        });
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
