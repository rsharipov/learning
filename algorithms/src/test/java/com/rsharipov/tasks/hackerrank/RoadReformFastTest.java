package com.rsharipov.tasks.hackerrank;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoadReformFastTest {
    
    @Test
    public void testSolve() {
        assertEquals("Incorrect result for case# 1", 3, new RoadReformFast().solve(getClass().getResourceAsStream("RoadReform1.txt")));
        assertEquals("Incorrect result for case# 2", 20, new RoadReformFast().solve(getClass().getResourceAsStream("RoadReform2.txt")));
        assertEquals("Incorrect result for case# 3", 
            new RoadReform().solve(getClass().getResourceAsStream("RoadReform3.txt")), 
            new RoadReformFast().solve(getClass().getResourceAsStream("RoadReform3.txt")));
        assertEquals("Incorrect result for case# 4", 
            new RoadReform().solve(getClass().getResourceAsStream("RoadReform4.txt")), 
            new RoadReformFast().solve(getClass().getResourceAsStream("RoadReform4.txt")));
    }
}
