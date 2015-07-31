package com.rsharipov.tasks.hackerrank.projecteuler;

import java.math.BigInteger;
import java.util.Scanner;

public class task020 {
    
    /**
     * sum of n! digits
     * @param n
     */
    public int solve(int n) {
        BigInteger value = BigInteger.valueOf(1);
        for (int i = 2; i <= n; ++i) {
            value = value.multiply(BigInteger.valueOf(i));
        }
        String number = value.toString();
        int sum = 0;
        for (int i = 0; i < number.length(); ++i) {
            sum += (number.charAt(i) - '0');
        }
        return sum;
    }
    
    public static void main(String[] args) {
        task020 solution = new task020();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            System.out.println(solution.solve(scanner.nextInt()));
        }
    }
    
}

