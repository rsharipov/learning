package com.rsharipov.codingtasks;

public class ReverseABinaryTree {

    public BinaryTreeNode reverse(BinaryTreeNode root) {
        if (root == null) return null;
        return new BinaryTreeNode(root.data(), reverse(root.right()), reverse(root.left()));
    }
    
}
