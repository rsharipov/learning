package com.rsharipov.tasks.hackerrank.projecteuler;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class task022 {

    public static int alphabeticalWorth(String name) {
        int result = 0;
        for (int i = 0; i < name.length(); ++i) {
            result += name.charAt(i) - 'A' + 1;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list.add(scanner.next());
        }
        sort(list);
        Map<String, Integer> worth = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            worth.put(list.get(i), alphabeticalWorth(list.get(i)) * (i + 1));
        }
        int q = scanner.nextInt();
        for (int i = 0; i < q; ++i) {
            String name = scanner.next();
            System.out.println(worth.get(name));
        }
    }
    
}
