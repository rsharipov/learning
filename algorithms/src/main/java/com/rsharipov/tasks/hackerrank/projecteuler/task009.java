package com.rsharipov.tasks.hackerrank.projecteuler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task009 {

    private static class abc {
        private int a;
        private int b;
        private int c;
        public abc(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    
    public static void main(String[] args) {
        Map<Integer, Integer> nToMax = new HashMap<Integer, Integer>();
        for (int a = 1; a <= 3000; ++a) {
            for (int b = 1; b <= 3000; ++b) {
                int c2 = a * a + b * b;
                int c = (int)Math.round(Math.sqrt(c2));
                if (c * c == c2) {
                    Integer value = nToMax.get(a + b + c);
                    if (value == null) {
                        value = 0;
                    }
                    nToMax.put(a + b + c, Math.max(value, a * b * c));
                }
            }            
        }
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int N = scanner.nextInt();
            Integer value = nToMax.get(N);
            System.out.println(value == null ? -1 : value);
        }
    }
}