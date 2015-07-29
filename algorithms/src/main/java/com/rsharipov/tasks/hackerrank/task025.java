package com.rsharipov.tasks.hackerrank;

import java.util.*;

public class task025 {

    public String reverse(String line) {
        return new StringBuilder(line).reverse().toString();
    }
    
    public String sum(String a, String b) {
        return reverse(reverseNumbersSum(reverse(a), reverse(b)));
    }
    
    public String reverseNumbersSum(String a, String b) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        int carry = 0;
        while (index < a.length() && index < b.length()) {
            int currentDigitsSum = a.charAt(index) - '0' + b.charAt(index) - '0' + carry;
            result.append((char)('0' + (currentDigitsSum % 10)));
            carry = currentDigitsSum / 10;
            ++index;
        }
        while (index < a.length()) {
            int currentDigit = a.charAt(index) - '0';
            int withCarry = currentDigit + carry;
            carry = withCarry / 10;
            result.append((char)('0' + (withCarry % 10)));
            ++index;
        }
        while (index < b.length()) {
            int currentDigit = b.charAt(index) - '0';
            int withCarry = currentDigit + carry;
            carry = withCarry / 10;
            result.append((char)('0' + (withCarry % 10)));
            ++index;
        }
        if (carry > 0) {
            result.append('1');
        }
        return result.toString();
    }

    private Integer[] result = new Integer[5001];
    
    public task025() {
        result[1] = 1;
        String first = "1";
        String second = "1";
        int index = 2;
        while (second.length() < result.length - 1) {
            String sum = reverseNumbersSum(first, second);
            first = second;
            second = sum;
            ++index;
            if (result[second.length()] == null) {
                result[second.length()] = index;
            }
        }
        result[second.length()] = index;
    }
    
    public int firstTermToContainDigits(int n) {
        return result[n];
    }
    
    public static void main(String[] args) {
        long start = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        task025 solver = new task025();
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            System.out.println(solver.firstTermToContainDigits(n));
        }
        System.out.println(((System.nanoTime() - start) / 1000000000.0) + "s");
    }
}