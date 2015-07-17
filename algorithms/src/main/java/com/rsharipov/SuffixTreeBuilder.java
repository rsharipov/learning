package com.rsharipov;

import java.util.HashMap;
import java.util.Map;

public class SuffixTreeBuilder {

    public static class Node {
        
        private int leftBoundInclusive;
        private int rightBoundInclusive;
        private Node parent;
        private final Map<Character, Node> children;
        private Node suffixLink;        
        
        public Node(int leftBoundInclusive, int rightBoundInclusive, Node parent) {
            this.leftBoundInclusive = leftBoundInclusive;
            this.rightBoundInclusive = rightBoundInclusive;
            this.parent = parent;
            this.children = new HashMap<>();
            this.suffixLink = null;
        }

        public int getEdgeLength() {
            return rightBoundInclusive - leftBoundInclusive + 1;
        }

        public int getLeftBoundInclusive() {
            return leftBoundInclusive;
        }

        public int getRightBoundInclusive() {
            return rightBoundInclusive;
        }

        public Node getParent() {
            return parent;
        }
        
        public Node getChild(char ch) {
            return children.get(ch);
        }
        
        public Node getSuffixLink() {
            return suffixLink;
        }

        public void setLeftBoundInclusive(int leftBoundInclusive) {
            this.leftBoundInclusive = leftBoundInclusive;
        }

        public void setRightBoundInclusive(int rightBoundInclusive) {
            this.rightBoundInclusive = rightBoundInclusive;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
        
        public void setSuffixLink(Node node) {
            this.suffixLink = node;
        }
        
        public Node addChild(char ch, Node node) {
            children.put(ch, node);
            node.setParent(this);
            return this;
        }
        
        public Map<Character, Node> getChildren() {
            return new HashMap<>(children);
        }
        
        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }
    }
    
    public class BuildContext {
        
        private final Node root;
        private final String line;
        private Node activeNode;
        private char activeEdgeStart;
        private int activeLength;
        private int remainder;
        
        public BuildContext(Node root, String line) {
            this.root = root;
            this.activeNode = root;
            this.activeEdgeStart = '\0';
            this.activeLength = 0;
            this.remainder = 0;
            this.line = line;
        }
        
        /**
         * returns true if on the edge, where the next character
         * is character, or at the node and it has an edge 
         * starting with the character
         * @return 
         */
        private boolean canGo(char character) {
            if (activeLength == 0) {
                return activeNode.hasChild(character);
            }
            Node child = activeNode.getChild(activeEdgeStart);
            int nextCharacterIndex = child.getLeftBoundInclusive() + activeLength;
            return line.charAt(nextCharacterIndex) == character;
        }
        
        private void goOneDown(int charIndex) {
            if (activeEdgeStart == '\0') {
                activeEdgeStart = line.charAt(charIndex);
            }
            Node child = activeNode.getChild(activeEdgeStart);
            int edgeLength = child.getEdgeLength();
            ++activeLength;
            if (activeLength >= edgeLength) {
                activeLength = 0;
                activeNode = child;
                activeEdgeStart = '\0';
            }
        }
        
        /**
         * Splits current edge, if on the edge and leaves on the upper part of 
         * the split edge
         * If not on the edge, just leaves
         * Returns false, if split wasn't necessary and true, if it has been
         * performed
         */
        private boolean split() {
            if (activeLength == 0) return false;
            Node oldChild = activeNode.getChild(activeEdgeStart);
            Node newParent = new Node(oldChild.getLeftBoundInclusive(), oldChild.getLeftBoundInclusive() + activeLength - 1, activeNode);
            activeNode.addChild(activeEdgeStart, newParent);  
            char newChildStartChar = line.charAt(oldChild.getLeftBoundInclusive() + activeLength);
            oldChild.setLeftBoundInclusive(oldChild.getLeftBoundInclusive() + activeLength);
            oldChild.setParent(newParent);
            newParent.addChild(newChildStartChar, oldChild);
            activeNode = newParent;
            activeEdgeStart = '\0';
            activeLength = 0;
            return true;
        }
        
        private void goSuffixLink() {
            int startInc = -1;
            int endInc = -1;
            if (activeLength > 0) {
                Node child = activeNode.getChild(activeEdgeStart);
                startInc = child.getLeftBoundInclusive();
                endInc = startInc + activeLength - 1;
            }
            activeNode = activeNode.getSuffixLink();
            activeEdgeStart = '\0';
            activeLength = 0;
            if (startInc != -1) {
                goDown(startInc, endInc);
            }
        }
        
        private void goDown(int startIndexInclusive, int endIndexInclusive) {
            while (startIndexInclusive <= endIndexInclusive) {
                Node child = activeLength > 0 ? activeNode.getChild(activeEdgeStart) : activeNode.getChild(line.charAt(startIndexInclusive));
                if (activeLength + (endIndexInclusive - startIndexInclusive + 1) < child.getEdgeLength()) {
                    activeLength += (endIndexInclusive - startIndexInclusive + 1);
                    activeEdgeStart = line.charAt(child.getLeftBoundInclusive());
                    return;
                }
                activeNode = child;
                activeLength = 0;
                startIndexInclusive += child.getEdgeLength() - activeLength;
            }
            activeLength = 0;
            activeEdgeStart = '\0';
        }
        
        public void addCharacter(int characterBeingAddedIndex) {            
            ++remainder;
            Node previouslyAdded = null;
            while (remainder > 0) {
                if (canGo(line.charAt(characterBeingAddedIndex))) {
                    goDown(characterBeingAddedIndex, characterBeingAddedIndex);
                    return;
                }
                final boolean wasSplit = split();
                if (previouslyAdded != null) {
                    previouslyAdded.setSuffixLink(activeNode);                    
                }
                if (wasSplit) {
                    previouslyAdded = activeNode;
                }
                else {
                    previouslyAdded = null;
                }
                Node newLeaf = new Node(characterBeingAddedIndex, line.length() - 1, activeNode);
                activeNode.addChild(line.charAt(characterBeingAddedIndex), newLeaf);
                if (activeNode.getSuffixLink() != null) {
                    goSuffixLink();
                }
                else {
                    activeNode = root;
                    goDown(characterBeingAddedIndex - remainder + 1 + 1, characterBeingAddedIndex  - 1);
                }
                --remainder;
            }            
        }
    }
    
    public class Position {
        private final Node node;
        private final int position;
        public Position(Node node, int position) {
            this.node = node;
            this.position = position;
        }
        public Node getNode() {
            return node;
        }
        public int getPosition() {
            return position;
        }
    }
    
    public Position traverse(Node textTreeRoot, String line, String needle) {
        Node current = textTreeRoot;
        while (!needle.isEmpty()) {
            if (!current.hasChild(needle.charAt(0))) {
                return null;
            }
            current = current.getChild(needle.charAt(0));
            String edgeLabel = line.substring(current.getLeftBoundInclusive(), current.getRightBoundInclusive() + 1);
            if (edgeLabel.length() < needle.length()) {
                if (!needle.startsWith(edgeLabel)) {
                    return null;
                }
                needle = needle.substring(edgeLabel.length());
            }        
            else {
                if (!edgeLabel.startsWith(needle)) {
                    return null;
                }
                return new Position(current, needle.length());
            }
        }
        return new Position(current, 0);
    }
    
    public int howManyEntries(Node textTreeRoot, String line, String needle) {
        Position position = traverse(textTreeRoot, line, needle);
        if (position == null) return 0;
        return howManyLeafsFrom(position.getNode());
    }
    
    public int howManyLeafsFrom(Node node) {
        if (node.getChildren().isEmpty()) return 1;
        int result = 0;
        for (Node child : node.getChildren().values()) {
            result += howManyLeafsFrom(child);
        }
        return result;
    }
    
    public static interface SuffixTreeBuildingVisualizer {
        public void visualize(int step, String line, SuffixTreeBuilder.Node node);
    }
    
    public static class NullVisualizer implements SuffixTreeBuildingVisualizer {
        @Override
        public void visualize(int step, String line, SuffixTreeBuilder.Node node) {        
        }
    }
    
    public Node build(String line, SuffixTreeBuildingVisualizer visualizer) {
        final Node root = new Node(-1, -1, null);
        BuildContext context = new BuildContext(root, line);
        for (int characterBeingAdded = 0; characterBeingAdded < line.length(); ++characterBeingAdded) {
            context.addCharacter(characterBeingAdded);
            visualizer.visualize(characterBeingAdded, line, root);
        }
        return root;
    }
    
    public Node build(String line) {
        return build(line, new NullVisualizer());
    }
}
