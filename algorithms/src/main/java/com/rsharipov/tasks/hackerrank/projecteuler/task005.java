package com.rsharipov.tasks.hackerrank.projecteuler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task005 {

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    private static long leastMultiple(long a, long b) {
        return a * b / gcd(a, b);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            long result = 1;
            for (int j = 1; j <= n; ++j) {
                result = leastMultiple(result, j);
            }
            System.out.println(result);
        }
    }
}