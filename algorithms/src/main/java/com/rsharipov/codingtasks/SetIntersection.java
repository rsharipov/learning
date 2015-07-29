package com.rsharipov.codingtasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

public class SetIntersection {

    public <T extends Comparable<T>> NavigableSet<T> intersect(Collection<NavigableSet<T>> sets) {
        Optional<NavigableSet<T>> result = sets.stream().reduce(this::intersect);
        return result.isPresent() ? result.get() : new TreeSet<>();
    }
    
    public <T extends Comparable<T>> NavigableSet<T> intersect(NavigableSet<T> left, NavigableSet<T> right) {
        Iterator<T> leftIt = left.iterator();
        Iterator<T> rightIt = right.iterator();
        T firstItem = null;
        T secondItem = null;
        TreeSet<T> result = new TreeSet<>();
        while (leftIt.hasNext() && rightIt.hasNext()) {
            if (firstItem == null) firstItem = leftIt.next();
            if (secondItem == null) secondItem = rightIt.next();
            final int comparisonResult = firstItem.compareTo(secondItem);
            if (comparisonResult < 0) {
                firstItem = null;
            }
            else if (comparisonResult == 0) {
                result.add(firstItem);
                firstItem = null;
                secondItem = null;
            }
            else {
                secondItem = null;
            }
        }
        return result;
    }
    
}
