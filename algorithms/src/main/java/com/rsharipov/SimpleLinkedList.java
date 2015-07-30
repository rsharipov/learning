package com.rsharipov;

import java.util.Objects;

public class SimpleLinkedList<T> {
    private T data;
    private SimpleLinkedList<T> next;
    
    public SimpleLinkedList(T data, SimpleLinkedList<T> next) {    
        this.data = data;
        this.next = next;
    }
    
    public T data() {
        return data;
    }
    
    public SimpleLinkedList<T> next() {
        return next;
    }
    
    public void setNext(SimpleLinkedList<T> next) {
        this.next = next;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleLinkedList<?> other = (SimpleLinkedList<?>) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(data.toString());
        if (next != null) {
            builder.append(" -> ");
            builder.append(next.toString());
        }
        return builder.toString();
    }
    
}
