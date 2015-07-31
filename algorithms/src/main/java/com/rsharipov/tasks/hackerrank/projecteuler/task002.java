package com.rsharipov.tasks.hackerrank.projecteuler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task002 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            long n = scanner.nextLong();
            long a = 1;
            long b = 1;
            long sum = 0;
            while (b <= n) {
                if (b % 2 == 0) {
                    sum += b;
                }
                long tmp = b;
                b = a + b;
                a = tmp;
            }
            System.out.println(sum);
        }
    }
}