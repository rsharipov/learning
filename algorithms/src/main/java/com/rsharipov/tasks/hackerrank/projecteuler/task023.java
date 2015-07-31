package com.rsharipov.tasks.hackerrank.projecteuler;

import java.util.Scanner;
import java.util.TreeSet;

public class task023 {

    private final TreeSet<Integer> abundant = new TreeSet<>();
    
    public static void main(String[] args) {
        task021 solution = new task021();
        solution.precalculate();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            System.out.println(solution.solve(scanner.nextInt()));
        }
    }
    
    public int d(long n) {
        int sum = 1;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                sum += i;
                if (i * i != n) {
                    sum += n / i;
                }
            }
        }
        return sum;
    }
    
    public void precalculate() {
        for (int i = 1; i <= 100000; ++i) {
            if (d(i) > i) {
                abundant.add(i);
            }
        }
    }

    public String solve(int n) {
        int sum = 0;
        for (Integer i : abundant) {
            if (i >= n) {
                break;
            }
            if (abundant.contains(n - i)) {
                return "YES";
            }
        }
        return "NO";
    }

    
}
