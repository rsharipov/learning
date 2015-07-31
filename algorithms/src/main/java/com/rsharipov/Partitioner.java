package com.rsharipov;

import com.rsharipov.codingtasks.StdComparator;
import java.util.Comparator;
import java.util.List;

public class Partitioner {

    public <T> void partition(int howMany, List<T> source, Comparator<T> comparator) {
        partition(howMany, source, comparator, 0, source.size());
    }
    
    public <T extends Comparable<T>> void partition(int howMany, List<T> source) {
        partition(howMany, source, new StdComparator<>());
    }
    
    private <T> void partition(int howMany, List<T> source, Comparator<T> comparator, int startInclusive, int endExclusive) {
        if (endExclusive - startInclusive <= howMany) {
            return;
        }
        int determinedPosition = partitionTheList(startInclusive, endExclusive, comparator, source);
        swap(source, startInclusive, determinedPosition);
        int rightFromPositionedIncludingCount = endExclusive - determinedPosition;
        if (howMany < rightFromPositionedIncludingCount) {
            partition(howMany, source, comparator, determinedPosition + 1, endExclusive);
        }
        else if (howMany > rightFromPositionedIncludingCount) {
            partition(howMany - rightFromPositionedIncludingCount, source, comparator, startInclusive, determinedPosition);
        }
    }

    private <T> int partitionTheList(int startInclusive, int endExclusive, Comparator<T> comparator, List<T> source) {
        int afterLesserElements = startInclusive + 1;
        for (int i = startInclusive + 1; i < endExclusive; ++i) {
            if (comparator.compare(source.get(i), source.get(startInclusive)) < 0) {
                swap(source, afterLesserElements, i);
                ++afterLesserElements;
            }
        }
        return afterLesserElements - 1;
    }
    
    private <T> void swap(List<T> source, int a, int b) {
        T temp = source.get(a);
        source.set(a, source.get(b));
        source.set(b, temp);
    }    
}
