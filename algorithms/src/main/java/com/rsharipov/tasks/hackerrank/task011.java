import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task011 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] grid = new int[20][];
        for (int i = 0; i < 20; ++i) {
            grid[i] = new int[20];
            for (int j = 0; j < 20; ++j) {
                grid[i][j] = scanner.nextInt();
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 17; ++j) {
                int product = 1;
                for (int k = 0; k < 4; ++k) {
                    product *= grid[i + k][j + k];
                }
                maxProduct = Math.max(maxProduct, product);
            }
        }
        for (int i = 0; i < 17; ++i) {
            for (int j = 3; j < 20; ++j) {
                int product = 1;
                for (int k = 0; k < 4; ++k) {
                    product *= grid[i + k][j - k];
                }
                maxProduct = Math.max(maxProduct, product);
            }
        }
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 17; ++j) {
                int product = 1;
                for (int k = 0; k < 4; ++k) {
                    product *= grid[i][j + k];
                }
                maxProduct = Math.max(maxProduct, product);
            }
        }
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 20; ++j) {
                int product = 1;
                for (int k = 0; k < 4; ++k) {
                    product *= grid[i + k][j];
                }
                maxProduct = Math.max(maxProduct, product);
            }
        }
        System.out.println(maxProduct);
    }
}