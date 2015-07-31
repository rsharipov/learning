package com.rsharipov.codingtasks;

import com.rsharipov.PolynomialTimeEstimater;
import com.rsharipov.SimpleLinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;
import static org.junit.Assert.*;

public class MergeSortTest {
    
    @Test
    public void testSort() {
        SimpleLinkedList<Integer> list = toSimpleLinkedList(1, 4, 2, 6, 3, 2, 2, 0, 10);
        assertEquals(toSimpleLinkedList(0, 1, 2, 2, 2, 3, 4, 6, 10), new MergeSort().sort(list));
    }
    
    @Test
    public void testSortEmptyList() {
        SimpleLinkedList<Integer> list = toSimpleLinkedList();
        assertEquals(toSimpleLinkedList(), new MergeSort().sort(list));
    }
    
    @Test
    public void testSortOneElementList() {
        SimpleLinkedList<Integer> list = toSimpleLinkedList(1);
        assertEquals(toSimpleLinkedList(1), new MergeSort().sort(list));
    }
    
    @Test
    public void testSortTwoElementsList() {
        SimpleLinkedList<Integer> list = toSimpleLinkedList(2, 1);
        assertEquals(toSimpleLinkedList(1, 2), new MergeSort().sort(list));
    }
    
    @Test
    public void testLastOfFirstHalfEvenCase() {
        SimpleLinkedList<Integer> list = toSimpleLinkedList(1, 2, 3, 4, 5, 6);
        assertSame(list.next().next(), new MergeSort().lastOfFirstHalf(list));
    }
    
    @Test
    public void testLastOfFirstHalfOddCase() {
        SimpleLinkedList<Integer> list = toSimpleLinkedList(1, 2, 3, 4, 5);
        assertSame(list.next().next(), new MergeSort().lastOfFirstHalf(list));
    }
    
    @Test
    public void testLastOfFirstTwoElementsCase() {
        SimpleLinkedList<Integer> list = toSimpleLinkedList(1, 2);
        assertSame(list, new MergeSort().lastOfFirstHalf(list));
    }
    
    public <T> SimpleLinkedList<T> toSimpleLinkedList(T... array) {
        return toSimpleLinkedList(Arrays.asList(array));
    }
    
    public <T> SimpleLinkedList<T> toSimpleLinkedList(List<T> list) {
        if (list.isEmpty()) return null;
        return new SimpleLinkedList<>(list.get(0), toSimpleLinkedList(list.subList(1, list.size())));
    }
    
    @Test
    public void testTimeOrderOfGrowth() {
        PolynomialTimeEstimater estimater = new PolynomialTimeEstimater(100000);
        System.out.println(estimater.estimateHigherOrderMemberDegree(this::timeToSort));
        assertThat(estimater.estimateHigherOrderMemberDegree(this::timeToSort),
            lessThan(2.0));
    }
    
    public long timeToSort(long taskSize) {
        final int ROUNDS_COUNT = 10;
        long result = 0;
        for (int i = 0; i < ROUNDS_COUNT; ++i) {
            SimpleLinkedList<Integer> list = generate(taskSize);
            long start = System.nanoTime();
            new MergeSort().sort(list);
            result += System.nanoTime() - start;
        }
        return result / ROUNDS_COUNT;
    }
    
    private SimpleLinkedList<Integer> generate(long size) {
        SimpleLinkedList<Integer> result = null;
        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            result = new SimpleLinkedList<Integer>(random.nextInt(), result);
        }
        return result;
    }

}
