package com.rsharipov.codingtasks;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuTest {
    
    @Test
    public void testSomeMethod() throws IOException {
        testSudoku(Arrays.asList(
            "123456789",
            "456789123",
            "789123456",
            "234567891",
            "567891234",
            "891234567",
            "345678912",
            "000000005",
            "010000600"),
        Arrays.asList(            
            "123456789",
            "456789123",
            "789123456",
            "234567891",
            "567891234",
            "891234567",
            "345678912",
            "678912345",
            "912345678"));
    }
    
    public void testSudoku(List<String> input, List<String> output) throws IOException {
        Sudoku sudoku = new Sudoku();
        sudoku.input(new ByteArrayInputStream(join(input).getBytes()));
        sudoku.solve();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        sudoku.output(outputStream);
        assertEquals(join(output), outputStream.toString());
    }
    
    public String join(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.size(); ++i) {
            builder.append(strings.get(i));
            builder.append("\n");
        }
        return builder.toString();
    }
    
}
