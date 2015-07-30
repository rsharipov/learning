package com.rsharipov.codingtasks;

import java.util.Random;

public class ShuffleArray {

    public <T> void shuffle(int[] array) {
        Random random = new Random();
        for (int i = 1; i < array.length; ++i) {
            int newPosition = random.nextInt(i + 1);
            if (newPosition != i) {
                int temp = array[newPosition];
                array[newPosition] = array[i];
                array[i] = temp;
            }
        }        
    }
    
    public <T> void shuffle(T[] array) {
        Random random = new Random();
        for (int i = 1; i < array.length; ++i) {
            int newPosition = random.nextInt(i + 1);
            if (newPosition != i) {
                T temp = array[newPosition];
                array[newPosition] = array[i];
                array[i] = temp;
            }
        }        
    }
    
}
