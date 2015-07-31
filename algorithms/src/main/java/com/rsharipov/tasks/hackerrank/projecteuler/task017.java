package com.rsharipov.tasks.hackerrank.projecteuler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task017 {

    public static List<String> threeDigitsToString(int n) {
        String[] oneDigitNames = new String[] { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        List<String> result = new ArrayList<String>();
        if (n / 100 > 0) {
            result.add(oneDigitNames[n / 100 - 1]);
            result.add("Hundred");
        }
        String[] tenToTwenty = new String[] { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
        if (n % 100 >= 10 && n % 100 <= 19) {
            result.add(tenToTwenty[n % 100 - 10]);
        }
        else {
            String[] tens = new String[] {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
            if (n % 100 / 10 >= 2) {
                result.add(tens[n % 100 / 10 - 2]);
            }
            if (n % 10 > 0) {
                result.add(oneDigitNames[n % 10 - 1]);
            }
        }
        return result;
    }
    
    public static String join(List<String> parts) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String part : parts) {
            if (!first) builder.append(" ");
            first = false;
            builder.append(part);
        }
        return builder.toString();
    }
    
    public static String toString(long n) {
        if (n == 0) {
            return "Zero";
        }
        if (n == 1_000_000_000_000L) {
            return "One Trillion";
        }
        String[] names = new String[] { "Billion", "Million", "Thousand"};
        ArrayList<String> parts = new ArrayList<String>();
        for (int i = 1_000_000_000, nameIndex = 0; i >= 1; i /= 1000, ++nameIndex) {
            int part = (int)(n / i % 1000);
            if (part != 0) {
                parts.addAll(threeDigitsToString(part));
                if (nameIndex < names.length) {
                    parts.add(names[nameIndex]);
                }
            }
        }
        return join(parts);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            long n = scanner.nextLong();
            System.out.println(toString(n));
        }
    }
}