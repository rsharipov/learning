package com.rsharipov.tasks.hackerrank;

import com.rsharipov.TimeAsserter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MorganAndAStringTest {

    @Test
    public void testPerformance() throws IOException {
        try (TimeAsserter asserter = new TimeAsserter(2000)) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            MorganAndAString.solve(getClass().getResourceAsStream("Morgan_slow.TXT"), output);
        }
    }

    @Test
    public void testCorrectness() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        MorganAndAString.solve(getClass().getResourceAsStream("Morgan_incorrect.TXT"), output);
        assertEquals(
            readAll(getClass().getResourceAsStream("Morgan_incorrect_out.txt")),
            output.toString());
    }

    public String readAll(InputStream inputStream) throws IOException {
        try {
            return IOUtils.toString(inputStream);
        } finally {
            inputStream.close();
        }
    }
}
