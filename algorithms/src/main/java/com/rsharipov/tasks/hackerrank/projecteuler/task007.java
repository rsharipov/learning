package com.rsharipov.tasks.hackerrank.projecteuler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task007 {

    private static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private static List<Integer> generatePrimes() {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        int p = 2;
        while (primes.size() < 10000) {
            if (isPrime(p)) {
                primes.add(p);
            }            
            ++p;
        }
        return primes;
    }
    
    public static void main(String[] args) {
        List<Integer> primes = generatePrimes();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            System.out.println(primes.get(n - 1));
        }
    }
}