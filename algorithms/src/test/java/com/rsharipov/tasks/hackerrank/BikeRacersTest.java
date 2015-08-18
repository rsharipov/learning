package com.rsharipov.tasks.hackerrank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class BikeRacersTest {
    
    public BikeRacersTest() {
    }

    @Test
    public void testSolve() throws Exception {
        String input = 
            "3 3 2 " +
            "0 1 " +
            "0 2 " +
            "0 3 " +
            "100 1 " +
            "200 2 " +
            "300 3 ";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new BikeRacers().solve(new ByteArrayInputStream(input.getBytes()), output);
        assertEquals("40000\n", output.toString());
    }
    
    @Test
    public void testSolveBigCase() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new BikeRacers().solve(getClass().getResourceAsStream("BikeRacers.txt"), output);
        assertEquals("51311849\n", output.toString());
    }
    
}
