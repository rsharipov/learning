package com.rsharipov.tasks.hackerrank.projecteuler;

import java.util.Scanner;
import java.util.TreeSet;

public class task021 {
 
    private final TreeSet<Integer> friendly = new TreeSet<>();
    
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
            if (!friendly.contains(i)) {
                int other = d(i);
                if (other == i) {
                    continue;
                }
                if (d(other) == i) {
                    friendly.add(i);
                    friendly.add(other);
                }
            }
        }
    }

    public int solve(int n) {
        int sum = 0;
        for (Integer i : friendly) {
            if (i >= n) {
                break;
            }
            sum += i;
        }
        return sum;
    }

    
}
