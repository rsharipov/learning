package com.rsharipov;


import com.rsharipov.BinaryTreeNode;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTreeNodeTest {
    
    @Test
    public void testLeft() {
        BinaryTreeNode<String> left = new BinaryTreeNode<>("left", null, null);
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root", left, null);
        assertSame(left, root.left());
        assertNull(root.right());
    }

    @Test
    public void testRight() {
        BinaryTreeNode<String> right = new BinaryTreeNode<>("right", null, null);
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root", null, right);
        assertNull(root.left());
        assertSame(right, root.right());
    }

    @Test
    public void testEqualsReturnsTrueForEqualTrees() {
        BinaryTreeNode<String> left = new BinaryTreeNode<>("left", null, null);
        BinaryTreeNode<String> right = new BinaryTreeNode<>("right", null, null);
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root", left, right);
        assertEquals(new BinaryTreeNode<>("root", left, right), root);        
    }

    @Test
    public void testEqualsReturnsFalseForEqualTrees() {
        BinaryTreeNode<String> left = new BinaryTreeNode<>("left", null, null);
        BinaryTreeNode<String> right = new BinaryTreeNode<>("right", null, null);
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root", left, right);
        assertNotEquals(new BinaryTreeNode<>("root1", left, right), root);        
        assertNotEquals(new BinaryTreeNode<>("root", null, right), root);        
        assertNotEquals(new BinaryTreeNode<>("root", left, null), root);        
    }

    
    @Test
    public void testHashCodeIsEqualForEqualTrees() {
        BinaryTreeNode<String> left = new BinaryTreeNode<>("left", null, null);
        BinaryTreeNode<String> right = new BinaryTreeNode<>("right", null, null);
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root", left, right);
        assertEquals(new BinaryTreeNode<>("root", left, right).hashCode(), root.hashCode());
    }

    @Test
    public void testDataReturnsWhatHasBeenSpecifiedInConstructor() {
        assertEquals("abc", new BinaryTreeNode<>("abc", null, null).data());
    }

    @Test
    public void testHeightOfOneElementTreeIsOne() {
        assertEquals(1, new BinaryTreeNode<>("A", null, null).height());
    }

    @Test
    public void testHeightOfTwoElementTreeIsTwo() {
        assertEquals(2, new BinaryTreeNode<>("A", new BinaryTreeNode<>("B"), null).height());
    }
    
    @Test
    public void testHeightOfThreeLevelTreeIsThree() {
        assertEquals(3, new BinaryTreeNode<>("A", 
            new BinaryTreeNode<>("B"), 
            new BinaryTreeNode<>("C", 
                new BinaryTreeNode<>("D"), 
                new BinaryTreeNode<>("E"))).height());
    }
    
    @Test
    public void testOneElementTreeIsBalanced() {
        assertEquals(true, new BinaryTreeNode<>("A", null, null).balanced());
    }

    @Test
    public void testTwoElementsTreeIsBalanced() {
        assertEquals(true, new BinaryTreeNode<>("A", new BinaryTreeNode<>("B"), null).balanced());
    }
    
    @Test
    public void testThreeLevelTreeMightBeUnbalanced() {
        assertEquals(false, new BinaryTreeNode<>("A", 
            null, 
            new BinaryTreeNode<>("C", 
                new BinaryTreeNode<>("D"), 
                new BinaryTreeNode<>("E"))).balanced());
    }
    
    @Test
    public void testThreeLevelTreeMightBeBalanced() {
        assertEquals(true, new BinaryTreeNode<>("A", 
            new BinaryTreeNode<>("B"), 
            new BinaryTreeNode<>("C", 
                new BinaryTreeNode<>("D"), 
                new BinaryTreeNode<>("E"))).balanced());
    }
    
    @Test
    public void testSizeOfOneElementTreeIsOne() {
        assertEquals(1, new BinaryTreeNode<>("A", null, null).size());
    }

    @Test
    public void testSizeOfTwoElementTreeIsTwo() {
        assertEquals(2, new BinaryTreeNode<>("A", new BinaryTreeNode<>("B"), null).size());
    }
    
    @Test
    public void testSizeOfFiveElementsTreeIsFive() {
        assertEquals(5, new BinaryTreeNode<>("A", 
            new BinaryTreeNode<>("B"), 
            new BinaryTreeNode<>("C", 
                new BinaryTreeNode<>("D"), 
                new BinaryTreeNode<>("E"))).size());
    }
    
    @Test
    public void testStaticBstIsGettingBuiltCorrectly() {
        BinaryTreeNode<Integer> actual = BinaryTreeNode.buildStaticBstFrom(Arrays.asList(2, 6, 4, 2, 3, 3, 1), null);
        BinaryTreeNode<Integer> expected =
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(2,
                    new BinaryTreeNode<>(1),
                    new BinaryTreeNode<>(2)
                ),
                new BinaryTreeNode<>(4,
                    new BinaryTreeNode<>(3),
                    new BinaryTreeNode<>(6)
                )
            );
        assertEquals(expected, actual);
    }
    
    @Test
    public void testStaticBstIsGettingBuiltCorrectlyWhenNotAllLevelsAreFull() {
        BinaryTreeNode<Integer> actual = BinaryTreeNode.buildStaticBstFrom(Arrays.asList(2, 3, 1, 4, 6), null);
        BinaryTreeNode<Integer> expected =
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(2,
                    new BinaryTreeNode<>(1),
                    null
                ),
                new BinaryTreeNode<>(6,
                    new BinaryTreeNode<>(4),
                    null
                )
            );
        assertEquals(expected, actual);
    }


}
