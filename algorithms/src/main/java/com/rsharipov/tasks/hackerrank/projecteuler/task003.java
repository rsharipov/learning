package com.rsharipov.tasks.hackerrank.projecteuler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task003 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            long n = scanner.nextLong();
            long result = 1;
            for (long j = 2; j * j <= n; ++j) {
                if (n % j == 0) {
                    result = j;
                    while (n > 1 && n % j == 0) n /= j;
                }
            }
            System.out.println(n > 1 ? n : result);
        }
    }
}