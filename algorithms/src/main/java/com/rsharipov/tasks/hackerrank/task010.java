import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task010 {

    public static void main(String[] args) {
        boolean composite[] = new boolean[1000001];
        composite[1] = true;
        for (long i = 2; i < composite.length; ++i) {
            for (long j = i * i; j < composite.length; j += i) {
                composite[(int)j] = true;                
            }
        }
        long sum = 0;
        long sums[] = new long[1000001];
        for (int i = 0; i < composite.length; ++i) {
            if (!composite[i]) {
                sum += i;
            }
            sums[i] = sum;
        }
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            System.out.println(sums[scanner.nextInt()]);
        }
    }
}