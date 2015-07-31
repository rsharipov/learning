package com.rsharipov.tasks.hackerrank.projecteuler;

import java.util.HashMap;
import java.util.Scanner;

public class Task026 {

    private final int[] recurringCycleLengths;
    
    /**
     * @param n
     * @return the length of the recurring cycle in decimal representation of 1 / n
     */
    public final int recurringCycleInDecimalRepresentation(int n) {
        HashMap<Integer, Integer> met = new HashMap<>();
        int base = 10;
        int index = 0;
        while (base != 0) {
            if (met.containsKey(base)) {
                return index - met.get(base);
            }
            met.put(base, index);
            base = base % n * 10;
            ++index;
        }
        return 0;
    }
    
    public Task026() {
        recurringCycleLengths = new int[10001];
        for (int i = 1; i < recurringCycleLengths.length; ++i) {
            recurringCycleLengths[i] = recurringCycleInDecimalRepresentation(i);
        }
    }
    
    /**
     * @param n
     * @return first d such that recurringCycleLengths[d] = 
     * max(recuringCycleLengths[x]) for x=1..n-1
     */
    public int firstMaximumUntilIsReachedAt(int n) {
       int maximumLength = -1;
       int maximumAtIndex = 0;
       for (int i = 1; i < n; ++i) {
            if (maximumLength < recurringCycleLengths[i]) {
                maximumLength = recurringCycleLengths[i];
                maximumAtIndex = i;
            }
       }
       return maximumAtIndex;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        Task026 task = new Task026();
        while (t > 0) {
            --t;
            int n = scanner.nextInt();
            System.out.println(task.firstMaximumUntilIsReachedAt(n));
        }
    }
    
}
