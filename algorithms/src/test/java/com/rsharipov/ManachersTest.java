package com.rsharipov;

import org.junit.Test;
import static org.junit.Assert.*;

public class ManachersTest {
    
    @Test
    public void testFindLongestPalindrome() {
        assertEquals("AABAA", new Manachers().findLongestPalindrome("AABAA"));
        assertEquals("ABA", new Manachers().findLongestPalindrome("ABAA"));
        assertEquals("BABAB", new Manachers().findLongestPalindrome("BABABAA"));
        assertEquals("ABCBA", new Manachers().findLongestPalindrome("ABCBAD"));
        assertEquals("AAA", new Manachers().findLongestPalindrome("AAABA"));
        assertEquals("ABCBBCBA", new Manachers().findLongestPalindrome("ABCBBCBABBA"));
        assertEquals("A", new Manachers().findLongestPalindrome("ABCDEFG"));
    }
    
    @Test
    public void testFindLongestEvenLengthPalindrome() {
        assertEquals("CC", new Manachers().findLongestPalindrome("ADEBFCC"));
        assertEquals("FCCF", new Manachers().findLongestPalindrome("ADEBFCCF"));
    }
    
    @Test
    public void testFindLongestPalindromeInEmptyString() {
        assertEquals("", new Manachers().findLongestPalindrome(""));
        assertEquals("", new Manachers().findLongestPalindrome(null));
    }
    
}
