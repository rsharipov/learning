import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task013 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 0; i < n; ++i) {
            BigInteger number = new BigInteger(scanner.next());
            sum = sum.add(number);            
        }
        System.out.println(sum.toString().substring(0, 10));
    }
}