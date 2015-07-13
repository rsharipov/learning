import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task014 {

    private static int countCycleLength(long n) {
    	int cycle = 0;
    	while (n != 1) {
    		++cycle;
	    	if (n % 2 == 0) {
	    		n /= 2;
	    	}
	    	else {
	    		n = 3 * n + 1;
	    	}
    	}
    	return cycle;
    }

    public static void main(String[] args) {
        
        int result[] = new int[5000001];
        int maxCycleLength = Integer.MIN_VALUE;
        int number = -1;
        for (int i = 1; i < result.length; ++i) {
            int cycleLength = countCycleLength(i);
            if (cycleLength >= maxCycleLength) {
                number = i;
                maxCycleLength = cycleLength;
            }
            result[i] = number;
        }
        
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int test = 0; test < t; ++test) {
            int n = scanner.nextInt();
            System.out.println(result[n]);
        }
    }
}