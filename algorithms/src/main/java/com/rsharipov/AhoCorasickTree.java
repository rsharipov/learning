package com.rsharipov;

public class AhoCorasickTree {

    AhoCorasickNode getRoot() {
        return root;
    }
    
    private AhoCorasickNode root;
    
    AhoCorasickTree() {
        root = new AhoCorasickNode(null, false, ' ');
    }
    
    public int countOccurences(String line) {
        AhoCorasickNode current = root;
        int result = 0;
        for (int i = 0; i < line.length(); ++i) {
            char ch = line.charAt(i);
            while (current != root && current.next(ch) == null) {
                current = current.suffixLink();
            }
            if (current.next(ch) != null) {
                current = current.next(ch);
            }
            result += current.countEndingHere();
        }
        return result;
    }
}
