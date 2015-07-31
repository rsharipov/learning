package com.rsharipov.codingtasks;

import com.rsharipov.BinaryTreeNode;

public class BinaryTreeJoiner {

    public final <T> String joinBinaryTree(BinaryTreeNode<T> root) {
        StringBuilder builder = new StringBuilder();
        joinBinaryTree(root, builder);
        return builder.toString();
    }
    
    public final <T> void joinBinaryTree(BinaryTreeNode<T> root, StringBuilder builder) {
        if (root == null) return;
        joinBinaryTree(root.left(), builder);
        builder.append(root.data().toString());
        joinBinaryTree(root.right(), builder);
    }
    
}
