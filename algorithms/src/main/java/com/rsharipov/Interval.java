package com.rsharipov;

public class Interval {

    private final int left;
    private final int right;
    
    public Interval(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Interval)) {
            return false;
        }
        Interval other = (Interval) obj;
        return left == other.left && right == other.right;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.left;
        hash = 41 * hash + this.right;
        return hash;
    }
    
    public int left() {
        return left;
    }
    
    public int right() {
        return right;
    }

    public boolean contains(int point) {
        return left <= point && point <= right;
    }
    
}
