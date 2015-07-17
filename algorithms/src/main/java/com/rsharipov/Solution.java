
package com.rsharipov;

import com.rsharipov.SuffixTreeBuilder;
import com.rsharipov.SuffixTreeBuilder;
import com.rsharipov.SuffixTreeBuilder.Node;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {
    
    private static String[] powers;
    
    public static void calculatePowers() {
        powers = new String[801];
        BigInteger pow = BigInteger.ONE;
        for (int i = 0; i <= 800; ++i) {
            powers[i] = pow.toString();
            if (i < 800) {
                pow = pow.multiply(BigInteger.valueOf(2));
            }
        }
    }
    
    public static void main(String[] args) {
        calculatePowers();
        InputStream stream = Solution.class.getResourceAsStream("TEST1.txt");
        Scanner scanner = new Scanner(new BufferedInputStream(stream));
        int t = scanner.nextInt();
        
        SuffixTreeBuilder solution = new SuffixTreeBuilder();
        for (int test = 0; test < t; ++test) {
            String line = scanner.next() + "$";
            Node root = solution.build(line);
            long count = 0;
            for (int i = 0; i <= 800 && powers[i].length() <= line.length(); ++i) {
                count += solution.howManyEntries(root, line, powers[i]);
            }
            System.out.println(count);
        }
    }
    
}
