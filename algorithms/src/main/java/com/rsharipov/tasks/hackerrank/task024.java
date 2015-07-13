package com.rsharipov.tasks.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class task024 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            ArrayList<Character> letters = new ArrayList<>(Arrays.<Character>asList('a', 'b', 'c', 
                'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'));
            long n = scanner.nextLong() - 1;
            long f = 1;
            for (int j = 1; j <= 12; ++j) {
                f *= j;
            }
            StringBuilder result = new StringBuilder();
            for (int j = 12; j >= 1; --j) {
                int index = (int)(n / f);
                result.append(letters.get(index));
                letters.remove(index);
                n %= f;
                f /= j;
            }
            result.append(letters.get(0));
            System.out.println(result.toString());
        }
    }
    
}
