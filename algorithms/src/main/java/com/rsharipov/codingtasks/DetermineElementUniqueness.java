package com.rsharipov.codingtasks;

import java.util.HashSet;

public class DetermineElementUniqueness {

    public <T> T findNonUnique(T[] array) {
        HashSet<T> set = new HashSet<>();
        for (int i = 0; i < array.length; ++i) {
            if (set.contains(array[i])) {
                return array[i];
            }
            set.add(array[i]);
        }
        return null;
    }
    
}
