package com.rsharipov.codingtasks;

import org.junit.Test;
import static org.junit.Assert.*;

public class DetermineElementUniquenessTest {
    
    public DetermineElementUniquenessTest() {
    }

    @Test
    public void testFindNonUnique() {
        assertEquals("D", new DetermineElementUniqueness().findNonUnique(
            new String[] { "A", "B", "C", "D", "E", "D", "E", "F" }));
    }
    
    @Test
    public void testFindNonUniqueReturnsNullIfAllAreUnique() {
        assertNull(new DetermineElementUniqueness().findNonUnique(
            new String[] { "A", "B", "C", "D", "E", "F", "G", "H" }));
    }
    
}
