package com.rsharipov.codingtasks;

import com.rsharipov.BinaryHeap;
import com.rsharipov.BinaryHeap.Handle;
import java.util.Comparator;
import java.util.HashMap;

public class LeastRecentlyUsedCache <K, V> {

    public static class GenerationComparator <K, V> 
        implements Comparator<EntryGeneration<K, V>>
    {
        @Override
        public int compare(EntryGeneration<K, V> o1, EntryGeneration<K, V> o2) {
            return -Long.compare(o1.generation, o2.generation);
        }
    }
    
    public static class EntryGeneration<K, V> {
        private final K key;
        private final V value;
        private long generation;
        private EntryGeneration(K key, V value, long generation) {
            this.key = key;
            this.value = value;
            this.generation = generation;
        }

        @Override
        public String toString() {
            return key.toString() + " -> " + value.toString() + "(" + generation + ")";
        }
        
    }
    
    private static class EntryGenerationHandle<K, V> {
        private final EntryGeneration<K, V> entryGeneration;
        private final Handle handle;
        private EntryGenerationHandle(EntryGeneration<K, V> entryGeneration, Handle handle) {
            this.entryGeneration = entryGeneration;
            this.handle = handle;
        }
    }
    
    private final HashMap<K, EntryGenerationHandle<K, V>> data;
    private final int maxSize;
    private long generation;
    private final BinaryHeap<EntryGeneration<K, V>> heap;
    
    public LeastRecentlyUsedCache(int maxSize) {
        this.maxSize = maxSize;
        this.data = new HashMap<>();
        this.heap = new BinaryHeap<>(new GenerationComparator<>());
    }
    
    public boolean isCached(K key) {
        return get(key) != null;
    }
    
    public V get(K key) {
        EntryGenerationHandle<K, V> entryGenerationHandle = data.get(key);
        if (entryGenerationHandle == null) {
            return null;
        }
        entryGenerationHandle.entryGeneration.generation = ++generation;
        heap.update(entryGenerationHandle.handle);
        return entryGenerationHandle.entryGeneration.value;
    }
    
    public int size() {
        return data.size();
    }
    
    public void add(K key, V value) {
        if (data.size() == maxSize) {
            removeLeastRecentlyUsed();
        }
        EntryGeneration<K, V> entryGeneration = 
            new EntryGeneration<>(key, value, ++generation);
        Handle handle = heap.add(entryGeneration);
        data.put(key, new EntryGenerationHandle<>(entryGeneration, handle));
    }
    
    private void removeLeastRecentlyUsed() {
        EntryGeneration<K, V> entryGeneration = heap.remove();
        data.remove(entryGeneration.key);
    }
    
}
