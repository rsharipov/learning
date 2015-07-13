import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class task019 {
    
    public static class Date {
        private final long y;
        private final long m;
        private final long d;
        public Date(long y, long m, long d) {
            this.y = y;
            this.m = m;
            this.d = d;
        }
        public Date nextMonthStart() {
            long nextD = 1;
            long nextM = m + 1;
            long nextY = y;
            if (nextM == 13) {
                nextM = 1;
                ++nextY;
            }
            return new Date(nextY, nextM, nextD);
        }
        public String toString() {
            return y + "/" + m + "/" + d;
        }
        public boolean isLeapYear() {
            return y % 4 == 0 && ((y % 100 != 0) || y % 400 == 0);
        }
        public boolean lessOrEqualTo(Date date) {
            return y < date.y || y == date.y && m < date.m || y == date.y && m == date.m && d <= date.d;
        }
    }
    
    public static long daysSinceFirst1900(Date t) {
        long result = 0;
        long yearsPassed = t.y - 1900;
        result += yearsPassed * 365;
        result += (yearsPassed - 1) / 4 + 1;
        result -= (yearsPassed - 1) / 100 + 1;
        if (yearsPassed > 100) {
            result += (yearsPassed - 101) / 400 + 1;
        }
        int[] daysInMonths = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (t.isLeapYear()) {
            daysInMonths[1]++;
        }
        for (int i = 1; i < t.m; ++i) {
            result += daysInMonths[i - 1];
        }
        result += t.d - 1;
        return result;
    }
    
    public static int getWeekday(Date t) {
        return (int)((1 + daysSinceFirst1900(t)) % 7);
    }
    
    public static String toStringWeekday(int d) {
        return new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}[d];
    }
    
    public static int howManySundays(Date first, Date second) {
        Date t = first;
        int count = 0;
        while (t.lessOrEqualTo(second)) {
            if (t.d == 1 && getWeekday(t) == 0) {
                ++count;
            }
            t = t.nextMonthStart();
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            long y1 = scanner.nextLong();
            long m1 = scanner.nextLong();
            long d1 = scanner.nextLong();
            long y2 = scanner.nextLong();
            long m2 = scanner.nextLong();
            long d2 = scanner.nextLong();
            System.out.println(howManySundays(new Date(y1, m1, d1), new Date(y2, m2, d2)));
        }
    }
}