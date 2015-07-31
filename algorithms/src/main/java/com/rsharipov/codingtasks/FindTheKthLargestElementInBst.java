package com.rsharipov.codingtasks;

import com.rsharipov.BinaryTreeNode;

public class FindTheKthLargestElementInBst {

    public <T> T find(BinaryTreeNode<T> root, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k cannot be negative");
        }
        if (root == null) {
            return null;
        }
        if (root.right() == null) {
            if (k == 0) return root.data();
            return find(root.left(), k - 1);
        }
        int rightSize = root.right().size();
        if (rightSize < k) {
            return find(root.left(), k - rightSize - 1);
        }
        if (rightSize == k) {
            return root.data();
        }                        
        // rightSize > k
        return find(root.right(), k);
    }
    
}
