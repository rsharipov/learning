package com.rsharipov.codingtasks;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FindTheNumberOfWordsInAStringTest {
    
    @Test
    public void test0ReturnedForAnEmptyString() {
        assertEquals(0, new FindTheNumberOfWordsInAString().countWordsInAString(""));
    }
    @Test
    public void test0ReturnedForABlankString() {
        assertEquals(0, new FindTheNumberOfWordsInAString().countWordsInAString("     "));
    }
    
    @Test
    public void test1ReturnedForNonEmptyStringWithNoSpaces() {
        assertEquals(1, new FindTheNumberOfWordsInAString().countWordsInAString("aaaaa"));
    }
    
    @Test
    public void test3IsReturnedForStringContaining() {
        assertEquals(3, new FindTheNumberOfWordsInAString().countWordsInAString(" abc   d   efff"));
        assertEquals(1, new FindTheNumberOfWordsInAString().countWordsInAString("aaaaa "));
        assertEquals(2, new FindTheNumberOfWordsInAString().countWordsInAString("a aaaaa "));
    }
}
