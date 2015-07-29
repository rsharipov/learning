package com.rsharipov.codingtasks;

import java.util.Objects;

public class BinaryTreeNode <T> {

    private final T data;
    private final BinaryTreeNode left;
    private final BinaryTreeNode right;
    
    public BinaryTreeNode(T data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    
    public BinaryTreeNode left() {
        return left;
    }
    
    public BinaryTreeNode right() {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryTreeNode)) {
            return false;
        }
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
}
