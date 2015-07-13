import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task016 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            String value = BigInteger.valueOf(2).pow(n).toString();
            int sum = 0;
            for (int j = 0; j < value.length(); ++j) {
                sum += value.charAt(j) - '0';
            }
            System.out.println(sum);
        }
    }
}