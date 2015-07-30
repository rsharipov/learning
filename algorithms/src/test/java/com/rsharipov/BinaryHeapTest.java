package com.rsharipov;

import com.rsharipov.BinaryHeap.Handle;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryHeapTest {
    
    private static class A {
        
        private String data = "";
        
        public static final Comparator<A> comparator = new Comparator<A>() {
            @Override
            public int compare(A o1, A o2) {
                return o1.compare(o2);
            }
        };
        
        public A(String data) {
            this.data = data;
        }
        
        public int compare(A right) {
            return data.compareTo(right.data);
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            if (data == null) {
                throw new IllegalArgumentException("data cannot be null");
            }
            this.data = data;
        }
    }
    
    private Comparator<String> stdComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };
    
    @Test
    public void testBinaryHeapIsInitiallyEmpty() {
        assertTrue(new BinaryHeap<>(stdComparator).isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testPeekOnEmptyThrows() {
        BinaryHeap<String> heap = new BinaryHeap<>(stdComparator);
        heap.peek();
    }
    
    @Test
    public void testPeekOnOneElementHeapReturnsThisElement() {
        BinaryHeap<String> heap = new BinaryHeap<>(stdComparator);
        heap.add("A");
        assertEquals("A", heap.peek());
        assertFalse(heap.isEmpty());
    }
    
    @Test
    public void testPeekOnTwoElementsHeapReturnsTheLargestElement() {
        BinaryHeap<String> heap = new BinaryHeap<>(stdComparator);
        heap.add("A");
        heap.add("B");
        assertEquals("B", heap.peek());
        assertFalse(heap.isEmpty());
    }

    @Test
    public void testOneRemoveFromTwoElementsHeapLeavesTheSmallest() {
        BinaryHeap<String> heap = new BinaryHeap<>(stdComparator);
        heap.add("A");
        heap.add("B");
        heap.remove();
        assertEquals("A", heap.peek());
        assertFalse(heap.isEmpty());
    }
    
    @Test
    public void testTwoRemovesFromTwoElementsHeapLeavesItEmpty() {
        BinaryHeap<String> heap = new BinaryHeap<>(stdComparator);
        heap.add("A");
        heap.add("B");
        heap.remove();
        heap.remove();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testUpdate() {
        BinaryHeap<A> heap = new BinaryHeap<>(A.comparator);
        A a = new A("A");
        A b = new A("B");
        Handle aHandle = heap.add(a);
        heap.add(b);
        a.setData("C");
        heap.update(aHandle);
        assertEquals("C", heap.peek().getData());
    }
    
}
