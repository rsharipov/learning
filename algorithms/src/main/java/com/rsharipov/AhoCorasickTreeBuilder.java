package com.rsharipov;

import java.util.Collection;

public class AhoCorasickTreeBuilder {
    
    private AhoCorasickTree tree;
    
    public AhoCorasickTreeBuilder() {
        tree = new AhoCorasickTree();
    }
    
    public void add(String s) {        
        AhoCorasickNode current = tree.getRoot();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            AhoCorasickNode next = current.next(ch);
            if (next == null) {
                next = new AhoCorasickNode(current, i == s.length() - 1, ch);
                current.setNext(ch, next);
            }
            current = next;
        }
        current.setIsEnd();
    }
    
    public void add(Collection<String> strings) {
        for (String s : strings) {
            add(s);
        }        
    }
    
    public AhoCorasickTree build() {
        AhoCorasickTree result = tree;
        tree = new AhoCorasickTree();
        return result;
    }
}
