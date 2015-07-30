package com.rsharipov.codingtasks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LeastRecentlyUsedCacheTest {

    @Test
    public void testLeastRecentryUsedIsRemoved() {
        LeastRecentlyUsedCache<Integer, String> cache = new LeastRecentlyUsedCache<>(4);
        System.out.println("Adding ONE");
        cache.add(1, "ONE");
        System.out.println("Adding TWO");
        cache.add(2, "TWO");
        System.out.println("Adding THREE");
        cache.add(3, "THREE");
        System.out.println("Adding FOUR");
        cache.add(4, "FOUR");
        cache.get(1);
        cache.get(3);
        System.out.println("Adding FIVE");
        cache.add(5, "FIVE");
        assertTrue(cache.isCached(1));
        assertFalse(cache.isCached(2));
        assertTrue(cache.isCached(3));
        assertTrue(cache.isCached(4));
        assertTrue(cache.isCached(5));
    }
    
    @Test
    public void testLeastRecentryUsedIsRemovedAnotherCase() {
        LeastRecentlyUsedCache<Integer, String> cache = new LeastRecentlyUsedCache<>(4);
        System.out.println("Adding ONE");
        cache.add(1, "ONE");
        System.out.println("Adding TWO");
        cache.add(2, "TWO");
        System.out.println("Adding THREE");
        cache.add(3, "THREE");
        System.out.println("Adding FOUR");
        cache.add(4, "FOUR");
        cache.get(1);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.get(1);
        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        System.out.println("Adding FIVE");
        cache.add(5, "FIVE");
        assertTrue(cache.isCached(1));
        assertTrue(cache.isCached(2));
        assertTrue(cache.isCached(3));
        assertFalse(cache.isCached(4));
        assertTrue(cache.isCached(5));
    }

    
}
