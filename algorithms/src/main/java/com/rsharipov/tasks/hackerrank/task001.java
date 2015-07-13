import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task001 {

    private static long sumDividable(int n, int divider) {
        long count = (n - 1) / divider;
        return (1 + count) * count / 2 * divider;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            System.out.println(sumDividable(n, 3) + sumDividable(n, 5) - sumDividable(n, 15));
        }
    }
}