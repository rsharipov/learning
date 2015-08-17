package com.rsharipov;

import java.util.HashMap;
import java.util.Map;

public class AhoCorasickNode {
    private final Map<Character, AhoCorasickNode> to = new HashMap<>();
    private final AhoCorasickNode parent;
    private boolean isEnd;
    private AhoCorasickNode suffixLink;
    private final char charLeadingHere; 
    private Integer countEndingHere;
    
    public AhoCorasickNode(AhoCorasickNode parent, boolean isEnd, char charLeadingHere) {
        if (parent == null) {
            suffixLink = this;
            countEndingHere = 0;
        }
        this.parent = parent;
        this.isEnd = isEnd;
        this.charLeadingHere = charLeadingHere;
    }
    
    public AhoCorasickNode next(char ch) {
        return to.get(ch);
    }
    
    public void setNext(char ch, AhoCorasickNode node) {
        to.put(ch, node);
    }
    
    public AhoCorasickNode getParent() {
        return parent;
    }
    
    public boolean isEnd() {
        return isEnd;
    }
    
    public AhoCorasickNode suffixLink() {
        if (suffixLink != null) {
            return suffixLink;
        }
        if (parent.parent == null) {
            suffixLink = parent;
            return suffixLink;
        }
        AhoCorasickNode link = parent.suffixLink();
        while (link.parent != null && !link.to.containsKey(charLeadingHere)) {
            link = link.suffixLink();
        }
        if (link.to.containsKey(charLeadingHere)) {
            link = link.to.get(charLeadingHere);
        }
        suffixLink = link;
        return suffixLink;
    }
    
    public char charLeadingHere() {
        return charLeadingHere;
    }
    
    public int countEndingHere() {
        if (countEndingHere == null) {
            countEndingHere = suffixLink().countEndingHere() + (isEnd ? 1 : 0);
        }
        return countEndingHere;
    }
    
    public void setIsEnd() {
        isEnd = true;
    }
}
