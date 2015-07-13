import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task006 {

    public static void main(String[] args) {
        Scanner scanner = new  Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            long n = scanner.nextLong();
            System.out.println(Math.abs(((1 + n) * n / 2) * ((1 + n) * n / 2) - n * (n + 1) * (2 * n + 1) / 6));
        }
    }
}