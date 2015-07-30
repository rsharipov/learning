package com.rsharipov.codingtasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class ReverseABinaryTreeTest {
    
    private final ReverseABinaryTree reverser = new ReverseABinaryTree();
    
    @Test
    public void testEmptyTreeRemainsEmpty() {
        assertNull(reverser.reverse(null));
    }
    
    @Test
    public void testTreeWithOneNodeRemainsTheSame() {
        assertEquals(new BinaryTreeNode<>("A", null, null), reverser.reverse(new BinaryTreeNode<>("A", null, null)));
    }
    
    @Test
    public void testTreeWithThreeNodesReversesOk() {
        assertEquals(
            new BinaryTreeNode<>("A", new BinaryTreeNode<>("C", null, null), new BinaryTreeNode<>("B", null, null)), 
            reverser.reverse(
                new BinaryTreeNode<>("A", new BinaryTreeNode<>("B", null, null), new BinaryTreeNode<>("C", null, null))));
    }
    
    @Test
    public void testTreeWithSevenNodesReversesOk() {
        BinaryTreeNode<String> expected = new BinaryTreeNode<>("D", 
            new BinaryTreeNode<>("F", new BinaryTreeNode<>("G", null, null), new BinaryTreeNode<>("E", null, null)), 
            new BinaryTreeNode<>("B", new BinaryTreeNode<>("C", null, null), new BinaryTreeNode<>("A", null, null))
        );
        assertEquals("GFEDCBA", new BinaryTreeJoiner().joinBinaryTree(expected));
        BinaryTreeNode<String> preReverse = new BinaryTreeNode<>("D", 
            new BinaryTreeNode<>("B", new BinaryTreeNode<>("A", null, null), new BinaryTreeNode<>("C", null, null)), 
            new BinaryTreeNode<>("F", new BinaryTreeNode<>("E", null, null), new BinaryTreeNode<>("G", null, null))
        );
        assertEquals("ABCDEFG", new BinaryTreeJoiner().joinBinaryTree(preReverse));
        BinaryTreeNode<String> actual = reverser.reverse(preReverse);
        assertEquals(expected, actual);
    }
    
}
