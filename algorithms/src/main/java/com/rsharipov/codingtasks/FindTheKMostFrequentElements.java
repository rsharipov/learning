package com.rsharipov.codingtasks;

import com.rsharipov.Partitioner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindTheKMostFrequentElements {
    
    private final Partitioner partitioner;
    
    private static class ValueComparator <T, V extends Comparable<V>> implements Comparator<
        Map.Entry<T, V>> {

        @Override
        public int compare(Entry<T, V> o1, Entry<T, V> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
        
    }
    
    FindTheKMostFrequentElements(Partitioner partitioner) {
        this.partitioner = partitioner;
    }
    
    public <T> List<T> findTheMostFrequentElements(int howMany, T[] data) {
        HashMap<T, Integer> counts = new HashMap<>();
        for (T x : data) {
            addToCounts(counts, x);
        }
        List<Entry<T, Integer>> entries = new ArrayList<>(counts.entrySet());
        partitioner.partition(howMany, entries, new ValueComparator<T, Integer>());
        return entries.subList(entries.size() - howMany, entries.size())
            .stream().map(x -> x.getKey()).collect(Collectors.toList());
    }

    public <T> List<T> findTheMostFrequentElements(int howMany, Stream<T> stream) {
        // TODO
        return null;
    }
    
    private <T> void addToCounts(HashMap<T, Integer> counts, T item) {
        Integer value = counts.get(item);
        if (value == null) {
            counts.put(item, 0);
        }
        else {
            counts.put(item, value + 1);
        }
    }
    
}
