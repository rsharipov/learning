package com.rsharipov;

import com.rsharipov.SuffixTreeBuilder.Node;
import java.awt.BorderLayout;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SuffixTreeBuilderTest {
    
    public SuffixTreeBuilderTest() {
    }
    
    public static void assertNodeEquals(Node left, Node right) {
        IdentityHashMap<Node, Integer> leftNodes = new IdentityHashMap<>();
        enumerateNodes(left, leftNodes);
        IdentityHashMap<Node, Integer> rightNodes = new IdentityHashMap<>();
        enumerateNodes(right, rightNodes);
        assertNodesAreEqual(left, leftNodes, right, rightNodes);
    }
    
    public static void enumerateNodes(Node root, IdentityHashMap<Node, Integer> result) {
        result.put(root, result.size());
        TreeSet<Character> children = new TreeSet<>(root.getChildren().keySet());
        for (Character ch : children) {
            enumerateNodes(root.getChild(ch), result);
        }
    }
    
    public static void assertNodesAreEqual(Node left, Map<Node, Integer> leftNodes, Node right, Map<Node, Integer> rightNodes) {
        assertEquals(left.getLeftBoundInclusive(), right.getLeftBoundInclusive());
        assertEquals(left.getRightBoundInclusive(), right.getRightBoundInclusive());
        assertEquals(left.getChildren().keySet(), right.getChildren().keySet());
        assertEquals(leftNodes.get(left.getSuffixLink()), rightNodes.get(right.getSuffixLink()));
        assertEquals(leftNodes.get(left.getParent()), rightNodes.get(right.getParent()));
        for (char ch : left.getChildren().keySet()) {
            assertNodesAreEqual(left.getChild(ch), leftNodes, right.getChild(ch), rightNodes);
        }
    }
    
    @Test
    public void testNode() {
        Node parent = new Node(2, 3, null);
        Node node = new Node(1, 2, parent);
        assertSame(parent, node.getParent());
        assertEquals(1, node.getLeftBoundInclusive());
        assertEquals(2, node.getRightBoundInclusive());
        Node child = new Node(2, 3, node);
        node.addChild('A', child);
        assertTrue(node.hasChild('A'));
        assertFalse(node.hasChild('B'));
        assertSame(child, node.getChild('A'));
        node.addChild('B', child);
        assertTrue(node.hasChild('A'));
        assertTrue(node.hasChild('B'));
        assertSame(child, node.getChild('B'));
    }
    
    @Test
    public void testBuildForGatagaca$() {
        SuffixTreeBuilder instance = new SuffixTreeBuilder();
        Node actual = instance.build("GATAGACA$", new NullVisualizer());
        Node gChild, aChild;
        Node expected = new Node(-1, -1, null)                
            .addChild('G', gChild = new Node(0, 1, null)
                    .addChild('T', new Node(2, 8, null))
                    .addChild('C', new Node(6, 8, null)))
            .addChild('A', aChild = new Node(1, 1, null)
                    .addChild('G', new Node(4, 8, null))
                    .addChild('T', new Node(2, 8, null))
                    .addChild('C', new Node(6, 8, null))
                    .addChild('$', new Node(8, 8, null)))
            .addChild('T', new Node(2, 8, null))
            .addChild('C', new Node(6, 8, null))
            .addChild('$', new Node(8, 8, null));
        gChild.setSuffixLink(aChild);
        assertNodeEquals(expected, actual);
    }
    
    @Test
    public void testBuildForbookkepper() {
        SuffixTreeBuilder instance = new SuffixTreeBuilder();
        Node actual = instance.build("bookkeeper", new NullVisualizer());
        Node eChild, kChild, oChild;
        Node expected = new Node(-1, -1, null)
            .addChild('b', new Node(0, 9, null))
            .addChild('k', kChild = new Node(3, 3, null)
                    .addChild('e', new Node(5, 9, null))
                    .addChild('k', new Node(4, 9, null)))
            .addChild('o', oChild = new Node(1, 1, null)
                .addChild('o', new Node(2, 9, null))
                .addChild('k', new Node(3, 9, null)))
            .addChild('e', eChild = new Node(5, 5, null)
                .addChild('e', new Node(6, 9, null))
                .addChild('p', new Node(7, 9, null))
                .addChild('r', new Node(9, 9, null)))
            .addChild('r', new Node(9, 9, null))
            .addChild('p', new Node(7, 9, null));
        eChild.setSuffixLink(expected);
        kChild.setSuffixLink(expected);
        oChild.setSuffixLink(expected);
        assertNodeEquals(expected, actual);
    }
    
    @Test
    public void testBuildGoogol$() {
        SuffixTreeBuilder instance = new SuffixTreeBuilder();
        Node actual = instance.build("googol$", new NullVisualizer());
        Node expected = new Node(-1, -1, null)
            .addChild('l', new Node(5, 6, null))
            .addChild('$', new Node(6, 6, null));
        Node goChild = new Node(0, 1, null)
            .addChild('o', new Node(2, 6, null))
            .addChild('l', new Node(5, 6, null));
        Node oChild = new Node(1, 1, null)
            .addChild('g', new Node(3, 6, null))
            .addChild('o', new Node(2, 6, null))
            .addChild('l', new Node(5, 6, null));
        goChild.setSuffixLink(oChild);
        expected.addChild('g', goChild);
        expected.addChild('o', oChild);
        assertNodeEquals(expected, actual);
    }
    
    public static void main(String[] args) {
        SuffixTreeBuilder instance = new SuffixTreeBuilder();
        String line = "googol$";        
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.getContentPane().setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
        
        instance.build(line, new SuffixTreeBuildingVisualizer() {
            @Override
            public void visualize(int step, String line, SuffixTreeBuilder.Node node) {
                SuffixTreeVisualizer visualizer = new SuffixTreeVisualizer();
                tabbedPane.addTab(line + " - Step " + step, visualizer.visualize(line, node));
            }
        });
    }
    
}
