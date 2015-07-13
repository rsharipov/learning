import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task008 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String value = scanner.next();
            String[] parts = value.split("0");
            int maxProduct = 0;
            for (String part : parts) {
                if (part.length() >= k) {
                    int product = 1;
                    for (int j = 0; j < k; ++j) {
                        product *= Integer.valueOf(part.substring(j, j + 1));
                    }
                    maxProduct = Math.max(maxProduct, product);
                    for (int j = k; j < part.length(); ++j) {
                        product /= Integer.valueOf(part.substring(j - k, j - k + 1));
                        product *= Integer.valueOf(part.substring(j, j + 1));
                        maxProduct = Math.max(maxProduct, product);
                    }
                }
            }
            System.out.println(maxProduct);
        }
    }
}