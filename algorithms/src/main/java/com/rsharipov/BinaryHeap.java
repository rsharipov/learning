package com.rsharipov;

import java.util.ArrayList;
import java.util.Comparator;

public class BinaryHeap<T> {
    
    private final ArrayList<DataAndHandle<T>> list = new ArrayList<>();
    private final Comparator<T> comparator;
    
    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    
    public static class Handle {
        private int position;
        private Handle(int position) {
            this.position = position;
        }
    }
    
    private static class DataAndHandle <T> {
        private final T data;
        private final Handle handle;
        private DataAndHandle(T data, Handle handle) {
            this.data = data;
            this.handle = handle;
        }
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public T peek() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Can't peek on empty heap");
        }
        return list.get(0).data;
    }
    
    public T remove() {
        DataAndHandle<T> result = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        if (!isEmpty()) {
            pushDown(0);
        }
        return result.data;
    }
    
    public Handle add(T item) {
        Handle handle = new Handle(list.size() - 1);
        list.add(new DataAndHandle<>(item, handle));
        pullUp(list.size() - 1);
        return handle;
    }   
    
    public void update(Handle handle) {
        moveIfNecessary(handle.position);
    }
    
    private void pushDown(int index) {
        T current = list.get(index).data;
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        if (rightChildIndex < list.size()) {
            T leftChild = list.get(leftChildIndex).data;
            T rightChild = list.get(rightChildIndex).data;
            if (comparator.compare(current, leftChild) < 0 ||
                comparator.compare(current, rightChild) < 0) {
                if (comparator.compare(leftChild, rightChild) < 0) {
                    swap(index, rightChildIndex);
                    pushDown(rightChildIndex);
                }
                else {
                    swap(index, leftChildIndex);
                    pushDown(leftChildIndex);
                }
            }
        }
        else if (leftChildIndex < list.size()) {
            T leftChild = list.get(leftChildIndex).data;
            if (comparator.compare(current, leftChild) < 0) {
                swap(index, leftChildIndex);
                pushDown(leftChildIndex);
            }
        }
    }
    
    private void pullUp(int index) {
        T current = list.get(index).data;
        if (index > 0) {
            int parentIndex = (index - 1) / 2;
            T parent = list.get(parentIndex).data;
            if (comparator.compare(parent, current) < 0) {
                swap(parentIndex, index);
                pullUp(parentIndex);
            }
        }
    }
    
    private void moveIfNecessary(int index) {
        pullUp(index);
        pushDown(index);
    }
    
    private void swap(int i, int j) {
        DataAndHandle<T> temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        list.get(i).handle.position = i;
        list.get(j).handle.position = j;
    }
}
