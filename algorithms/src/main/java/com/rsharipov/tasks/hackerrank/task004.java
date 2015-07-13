import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task004 {
    
    private static boolean palindrome(int n) {
        String value = Integer.toString(n);
        for (int i = 0; i < value.length() / 2; ++i) {
            if (value.charAt(i) != value.charAt(value.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    
    private static Set<Integer> findNumbers() {
        Set<Integer> numbers = new HashSet<Integer>();
        for (int i = 100; i < 1000; ++i) {
            for (int j = i; j < 1000; ++j) {
                int n = i * j;
                if (palindrome(n)) {
                    numbers.add(n);
                }
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        Set<Integer> numbers = findNumbers();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            int max = 0;
            for (Integer j : numbers) {
                if (j < n) {
                    max = Math.max(max, j);
                }
            }
            System.out.println(max);
        }
    }
}