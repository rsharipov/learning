package com.rsharipov.tasks.hackerrank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.junit.Test;
import static org.junit.Assert.*;

public class TwoTwoTest {
    
    @Test
    public void testMultiply() {
        for (int i = 0; i < 20; ++i) {
            int power = 1 << i;
            assertEquals(Integer.toString(2 * power), new TwoTwo().multiply(Integer.toString(power), 2));
        }
    }
    
    @Test
    public void testSolve() throws IOException {
        String input = "5 2222222 24256 65536 023223 33579";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        new TwoTwo().solve(new ByteArrayInputStream(
            input.getBytes(Charset.forName("UTF-8"))), 
            output);
        assertEquals("7\n4\n1\n4\n0\n", output.toString());
    }

}
