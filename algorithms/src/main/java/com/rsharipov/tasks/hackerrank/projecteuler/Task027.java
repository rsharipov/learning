package com.rsharipov.tasks.hackerrank.projecteuler;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task027 {

    public boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return n > 1;
    }
    
    public int length(int a, int b) {
        int n = 0;
        while (isPrime(n * n + a * n + b)) {
            ++n;
        }
        return n;
    }
    
    public List<Integer> solve(int n) {
        int maxLength = -1;
        int resultA = -1;
        int resultB = -1;
        for (int a = -n; a <= n; ++a) {
            for (int b = -n; b <= n; ++b) {
                int currentLength = length(a, b);
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    resultA = a;
                    resultB = b;
                }                       
            }
        }
        return Arrays.asList(resultA, resultB);
    }
    
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        List<Integer> result = new Task027().solve(N);
        System.out.println(result.get(0) + " " + result.get(1));
    }
    
}
