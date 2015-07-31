package com.rsharipov.codingtasks;

import com.rsharipov.SimpleLinkedList;
import java.util.Comparator;

public class MergeSort {
    
    
    public <T extends Comparable<T>> SimpleLinkedList<T> sort(SimpleLinkedList<T> list) {
        return sort(list, new StdComparator<T>());
    }
    
    public <T> SimpleLinkedList<T> sort(SimpleLinkedList<T> list, Comparator<T> comparator) {
        if (list == null || list.next() == null) return list;
        SimpleLinkedList<T> firstHalfEnd = lastOfFirstHalf(list);
        SimpleLinkedList<T> secondHalf = firstHalfEnd.next();        
        final int size = size(list);
        firstHalfEnd.setNext(null);
        assert(size(list) < size);
        assert(size(secondHalf) < size);
        SimpleLinkedList<T> firstHalf = sort(list, comparator);
        secondHalf = sort(secondHalf, comparator);
        return merge(firstHalf, secondHalf, comparator);
    }
    
    <T> int size(SimpleLinkedList<T> list) {
        int size = 0;
        while (list != null) {
            list = list.next();
            ++size;
        }
        return size;
    }
    
    <T> SimpleLinkedList<T> lastOfFirstHalf(SimpleLinkedList<T> list) {
        SimpleLinkedList<T> mid = list;
        SimpleLinkedList<T> last = list.next();
        if (last != null) last = last.next();
        while (last != null) {
            mid = mid.next();
            last = last.next();
            if (last != null) {
                last = last.next();
            }
        }
        return mid;
    }
    
    private <T> SimpleLinkedList<T> merge(
            SimpleLinkedList<T> first, 
            SimpleLinkedList<T> second,
            Comparator<T> comparator) {
        SimpleLinkedList<T> result = null;
        SimpleLinkedList<T> last = null;
        while (first != null && second != null) {
            if (comparator.compare(first.data(), second.data()) < 0) {
                if (result == null) {
                    result = last = first;
                }
                else {
                    last.setNext(first);
                    last = first;
                }
                first = first.next();
            }
            else {
                if (result == null) {
                    result = last = second;
                }
                else {
                    last.setNext(second);
                    last = second;
                }
                second = second.next();
            }
        }
        while (first != null) {
            last.setNext(first);
            last = first;
            first = first.next();
        }
        while (second != null) {
            last.setNext(second);
            last = second;
            second = second.next();
        }
        last.setNext(null);
        return result;
    }
    
}
