package com.rsharipov.codingtasks;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTreeNodeTest {
    
    @Test
    public void testLeft() {
        BinaryTreeNode left = new BinaryTreeNode("left", null, null);
        BinaryTreeNode root = new BinaryTreeNode("root", left, null);
        assertSame(left, root.left());
        assertNull(root.right());
    }

    @Test
    public void testRight() {
        BinaryTreeNode right = new BinaryTreeNode("right", null, null);
        BinaryTreeNode root = new BinaryTreeNode("root", null, right);
        assertNull(root.left());
        assertSame(right, root.right());
    }

    @Test
    public void testEqualsReturnsTrueForEqualTrees() {
        BinaryTreeNode left = new BinaryTreeNode("left", null, null);
        BinaryTreeNode right = new BinaryTreeNode("right", null, null);
        BinaryTreeNode root = new BinaryTreeNode("root", left, right);
        assertEquals(new BinaryTreeNode("root", left, right), root);        
    }

    @Test
    public void testEqualsReturnsFalseForEqualTrees() {
        BinaryTreeNode left = new BinaryTreeNode("left", null, null);
        BinaryTreeNode right = new BinaryTreeNode("right", null, null);
        BinaryTreeNode root = new BinaryTreeNode("root", left, right);
        assertNotEquals(new BinaryTreeNode("root1", left, right), root);        
        assertNotEquals(new BinaryTreeNode("root", null, right), root);        
        assertNotEquals(new BinaryTreeNode("root", left, null), root);        
    }

    
    @Test
    public void testHashCodeIsEqualForEqualTrees() {
        BinaryTreeNode left = new BinaryTreeNode("left", null, null);
        BinaryTreeNode right = new BinaryTreeNode("right", null, null);
        BinaryTreeNode root = new BinaryTreeNode("root", left, right);
        assertEquals(new BinaryTreeNode("root", left, right).hashCode(), root.hashCode());
    }

    @Test
    public void testDataReturnsWhatHasBeenSpecifiedInConstructor() {
        assertEquals("abc", new BinaryTreeNode("abc", null, null).data());
    }
    
}
