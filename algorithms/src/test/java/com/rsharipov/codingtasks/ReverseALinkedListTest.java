package com.rsharipov.codingtasks;

import com.rsharipov.SimpleLinkedList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author rsharipov
 */
@RunWith(Parameterized.class)
public class ReverseALinkedListTest {
    
    @FunctionalInterface
    public interface Reverser {
        SimpleLinkedList<String> reverse(SimpleLinkedList<String> input);
    }
    
    private final Reverser reverser;

    @Parameters
    public static Collection<Object[]> parameters() {
        ReverseALinkedList reverser = new ReverseALinkedList();
        return Arrays.asList(new Object[][] { 
            new Reverser[] { reverser::reverseIteratively }, 
            new Reverser[] { reverser::reverseRecursively } 
        });
    }
    
    public ReverseALinkedListTest(Reverser reverser) {
        this.reverser = reverser;
    }
    
    @Test
    public void testAOneElementListReversedOk() {
        
        SimpleLinkedList<String> toReverse = new SimpleLinkedList<>("A", null);
        SimpleLinkedList<String> actual = reverser.reverse(toReverse);
        SimpleLinkedList<String> expected = new SimpleLinkedList<>("A", null);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testATwoElementsListReversedOk() {
        SimpleLinkedList<String> toReverse = new SimpleLinkedList<>("A",
            new SimpleLinkedList<>("B", null));
        SimpleLinkedList<String> actual = reverser.reverse(toReverse);
        SimpleLinkedList<String> expected = new SimpleLinkedList<>("B",
            new SimpleLinkedList<>("A", null));
        assertEquals(expected, actual);
    }
    
    @Test
    public void testAThreeElementsListReversedOk() {
        SimpleLinkedList<String> toReverse = new SimpleLinkedList<>("A",
            new SimpleLinkedList<>("B", new SimpleLinkedList<>("C", null)));
        SimpleLinkedList<String> actual = reverser.reverse(toReverse);
        SimpleLinkedList<String> expected = new SimpleLinkedList<>("C",
            new SimpleLinkedList<>("B", new SimpleLinkedList<>("A", null)));
        assertEquals(expected, actual);
    }
    
}
