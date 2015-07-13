package com.rsharipov.tasks.hackerrank;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class task021Test {

    @Test
    public void testExample() {
        task021 t = new task021();
        t.precalculate();
        assertEquals(284, t.d(220));
        assertEquals(220, t.d(284));
        assertEquals(504, t.solve(300));
    }
    
}
