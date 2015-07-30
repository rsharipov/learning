package com.rsharipov.codingtasks;

public class ReverseABinaryTree {

    public <T> BinaryTreeNode<T> reverse(BinaryTreeNode<T> root) {
        if (root == null) return null;
        return new BinaryTreeNode<T>(root.data(), reverse(root.right()), reverse(root.left()));
    }
    
}
