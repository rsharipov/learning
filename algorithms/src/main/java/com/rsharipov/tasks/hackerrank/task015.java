import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task015 {

    public static class GcdResult {
        private final long fM;
        private final long sM;
        private final long gcd;
        GcdResult(long fM, long sM, long gcd) {
            this.fM = fM;
            this.sM = sM;
            this.gcd = gcd;
        }
    }
    
    public static GcdResult gcd(long a, long b) {
        if (b == 0) return new GcdResult(1, 0, a);
        GcdResult result = gcd(b, a % b);
        return new GcdResult(result.sM, result.fM - a / b * result.sM, result.gcd);
    }
    
    public static long getReciprocal(long a, long modulo) {
        GcdResult result = gcd(a, modulo);
        if (result.gcd != 1) return 0;
        return normalize(result.fM, modulo);
    }
    
    public static long normalize(long number, long modulo) {
        while (number < 0) number += modulo;
        number = (number % modulo);
        return number;
    }
    
    public static final long MODULO = 1_000_000_007L;
    
    public static long c(long n, long m) {
        long result = m + 1;
        for (long i = m + 2; i <= n; ++i) {
            result = (result * i) % MODULO;
            result = (result * getReciprocal(i - m, MODULO)) % MODULO;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println(c(n + m, n));
        }
    }
}