package com.rsharipov;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BinaryTreeNode <T> {

    private final T data;
    private final BinaryTreeNode<T> left;
    private final BinaryTreeNode<T> right;
    private final int height;
    private final int size;
    
    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(
            left == null ? 0 : left.height,
            right == null ? 0 : right.height);
        this.size = 1 + (left == null ? 0 : left.size) + (right == null ? 0 : right.size);
    }

    public static <T> BinaryTreeNode<T> buildStaticBstFrom(List<T> list, 
            Comparator<T> comparator) {
        list.sort(comparator);
        return buildStaticBstFrom(list, 0, list.size());
    }
    
    public BinaryTreeNode(T data) {
        this(data, null, null);
    }

    @Override
    public String toString() {
        return data.toString() + "(" +
            (left == null ? "" : left.toString()) +
            ", " +
            (right == null ? "" : right.toString()) +
            ")";
    }
    
    public int height() {
        return height;
    }
    
    public int size() {
        return size;
    }
    
    public boolean balanced() {
        int leftHeight = left == null ? 0 : left.height;
        int rightHeight = right == null ? 0 : right.height;
        return Math.abs(leftHeight - rightHeight) <= 1 &&
            (left == null || left.balanced()) &&
            (right == null || right.balanced());
    }
    
    public BinaryTreeNode<T> left() {
        return left;
    }
    
    public BinaryTreeNode<T> right() {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryTreeNode)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        BinaryTreeNode<T> other = (BinaryTreeNode<T>) obj;
        return
            Objects.equals(data(), other.data()) &&
            Objects.equals(left(), other.left()) &&
            Objects.equals(right(), other.right());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.data);
        hash = 79 * hash + Objects.hashCode(this.left);
        hash = 79 * hash + Objects.hashCode(this.right);
        return hash;
    }
    
    public T data() {
        return data;
    }
    
    private static <T> BinaryTreeNode<T> buildStaticBstFrom(
        List<T> list, int startIncl, int endExcl) {
        if (startIncl == endExcl) return null;
        int mid = (startIncl + endExcl) / 2;
        return new BinaryTreeNode<>(list.get(mid),
            buildStaticBstFrom(list, startIncl, mid),
            buildStaticBstFrom(list, mid + 1, endExcl));
    }
}
