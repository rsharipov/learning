package com.rsharipov.codingtasks;

import com.rsharipov.SimpleLinkedList;

public class ReverseALinkedList {

    public <T> SimpleLinkedList<T> reverseIteratively(SimpleLinkedList<T> list) {
        if (list.next() == null) return list;
        SimpleLinkedList prevPrev = null;
        SimpleLinkedList prev = list;
        SimpleLinkedList current = prev.next();
        while (current != null) {
            prev.setNext(prevPrev);
            prevPrev = prev;
            prev = current;
            current = current.next();
        }
        prev.setNext(prevPrev);
        return prev;
    }

    public <T> SimpleLinkedList<T> reverseRecursively(SimpleLinkedList<T> list) {
        return reverseRecursively(list, null);
    }
    
    private <T> SimpleLinkedList<T> reverseRecursively(SimpleLinkedList<T> list, SimpleLinkedList<T> prev) {
        SimpleLinkedList<T> next = list.next();
        if (next == null) {
            list.setNext(prev);
            return list;
        }
        list.setNext(prev);
        return reverseRecursively(next, list);        
    }
    
}
