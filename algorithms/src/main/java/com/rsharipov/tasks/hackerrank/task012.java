import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task012 {

    public static void main(String[] args) {
        Map<Integer, Integer> numbers = new HashMap<Integer, Integer>();
        numbers.put(1, 1);
        numbers.put(2, 3);
        numbers.put(4, 6);
        numbers.put(6, 28);
        numbers.put(9, 36);
        numbers.put(16, 120);
        numbers.put(18, 300);
        numbers.put(20, 528);
        numbers.put(24, 630);
        numbers.put(36, 2016);
        numbers.put(40, 3240);
        numbers.put(48, 5460);
        numbers.put(90, 25200);
        numbers.put(112, 73920);
        numbers.put(128, 157080);
        numbers.put(144, 437580);
        numbers.put(162, 749700);
        numbers.put(168, 1385280);
        numbers.put(192, 1493856);
        numbers.put(240, 2031120);
        numbers.put(320, 2162160);
        numbers.put(480, 17907120);
        numbers.put(576, 76576500);
        numbers.put(648, 103672800);
        numbers.put(768, 236215980);
        numbers.put(1024, 842161320);
        
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int j = 0; j < t; ++j) {
            int n = scanner.nextInt();
            int min = Integer.MAX_VALUE;
            int value = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
                if (entry.getKey() > n) {
                    if (entry.getKey() < min) {
                        min = entry.getKey();
                        value = entry.getValue();
                    }
                }
            }
            System.out.println(value);
        }
    }
}