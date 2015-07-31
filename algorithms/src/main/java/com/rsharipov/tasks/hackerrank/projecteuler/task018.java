package com.rsharipov.tasks.hackerrank.projecteuler;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task018 {

    private static List<List<Integer>> readTree(Scanner scanner) {
        int n = scanner.nextInt();
        List<List<Integer>> tree = new ArrayList<List<Integer>>();
        for (int j = 0; j < n; ++j) {
            List<Integer> level = new ArrayList<Integer>();
            for (int k = 0; k < j + 1; ++k) {
                level.add(scanner.nextInt());
            }
            tree.add(level);
        }
        return tree;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            List<List<Integer>> tree = readTree(scanner);
            for (int level = tree.size() - 2; level >= 0; --level) {
                for (int index = 0; index < level + 1; ++index) {
                    tree.get(level).set(index, tree.get(level).get(index) + Math.max(
                        tree.get(level + 1).get(index), tree.get(level + 1).get(index + 1)));
                }
            }
            System.out.println(tree.get(0).get(0));
        }
    }
}