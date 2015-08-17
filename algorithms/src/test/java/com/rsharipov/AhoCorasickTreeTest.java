package com.rsharipov;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AhoCorasickTreeTest {

    @Test
    public void testAhoCorasickTree() {
        AhoCorasickTreeBuilder builder = new AhoCorasickTreeBuilder();
        builder.add(Arrays.asList("123", "234", "236", "23"));
        AhoCorasickTree tree = builder.build();
        assertEquals(7, tree.countOccurences("12345612376236"));
    }
    
    @Test
    public void testAhoCorasickTreeAllCharsTheSame() {
        AhoCorasickTreeBuilder builder = new AhoCorasickTreeBuilder();
        builder.add(Arrays.asList("a", "aa", "aaa", "aaaa"));
        AhoCorasickTree tree = builder.build();
        assertEquals(14, tree.countOccurences("aaaaa"));
    }
    
    @Test
    public void testAhoCorasickTreeEmptyForest() {
        AhoCorasickTreeBuilder builder = new AhoCorasickTreeBuilder();
        builder.add(Arrays.asList());
        AhoCorasickTree tree = builder.build();
        assertEquals(0, tree.countOccurences("aaaaa"));
    }
    
}
