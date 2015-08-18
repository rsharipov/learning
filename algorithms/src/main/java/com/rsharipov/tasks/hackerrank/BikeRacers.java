package com.rsharipov.tasks.hackerrank;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class BikeRacers {
    
    private int n;
    private int m;
    private int k;
    private Point[] bikers;
    private Point[] bikes;
    private boolean[] used;
    private int[] matching;

    private long find() {
        if (maximumMatchingSize(0) >= k) return 0;
        long begin = 0;
        long end = 1_000_000_000_000_00L;
        while (begin + 1 < end) {
            long mid = (begin + end) / 2;
            if (maximumMatchingSize(mid) < k) {
                begin = mid;
            }
            else {
                end = mid;
            }
        }       
        return end;
    }
    
    private long diff(int v, int j) {
        return (bikers[v].x - bikes[j].x) * (bikers[v].x - bikes[j].x) +
            (bikers[v].y - bikes[j].y) * (bikers[v].y - bikes[j].y);
    }
    
    private boolean tryAdd(long length, int v) {
        if (used[v]) return false;
        used[v] = true;        
        for (int j = 0; j < m; ++j) {
            if (diff(v, j) <= length &&
                (matching[j] == -1 || tryAdd(length, matching[j]))) {
                matching[j] = v;
                return true;
            }                
        }
        return false;
    }

    private int maximumMatchingSize(long mid) {
        used = new boolean[n];
        matching = new int[m];
        Arrays.fill(matching, -1);
        for (int i = 0; i < n; ++i) {
            Arrays.fill(used, false);
            tryAdd(mid, i);
        }
        int result = 0;
        for (int i = 0; i < matching.length; ++i) {
            if (matching[i] != -1) ++result;
        }
        return result;
    }
    
    public static class Point {
        long x;
        long y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        new BikeRacers().solve(System.in, System.out);
    }

    public void solve(InputStream in, OutputStream out) throws IOException {
        input(in);
        out.write(Long.toString(find()).getBytes()); 
        out.write('\n');
    }

    private void input(InputStream in) {
        Scanner scanner = new Scanner(new BufferedInputStream(in));
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        bikers = new Point[n];
        bikes = new Point[m];
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            bikers[i] = new Point(x, y);
        }        
        for (int i = 0; i < m; ++i) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            bikes[i] = new Point(x, y);
        }
    }
    
}
