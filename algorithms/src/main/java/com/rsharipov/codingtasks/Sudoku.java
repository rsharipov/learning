package com.rsharipov.codingtasks;

import java.io.*;
import java.util.*;

public class Sudoku {

    private boolean banned[][][];
    private char[][] data;
    
    private void disallow(int i, int j, int digit) {
        for (int k = 0; k < 9; ++k) {
            banned[i][k][digit] = true;
            banned[k][j][digit] = true;
        }
        int cellI = i / 3;
        int cellJ = j / 3;
        for (int k = 0; k < 9; ++k) {
            banned[cellI * 3 + k / 3][cellJ * 3 + k % 3][digit] = true;
        }        
    }
    
    public void input(InputStream input) {
        Scanner scanner = new Scanner(input);
        data = new char[9][];
        for (int i = 0; i < 9; ++i) {
            data[i] = scanner.next().toCharArray();
        }
        banned = new boolean[9][][];
        for (int i = 0; i < 9; ++i) {
            banned[i] = new boolean[9][];
            for (int j = 0; j < 9; ++j) {
                banned[i][j] = new boolean[10];
            }
        }
    }
    
    public void solve() {
        int setDigits = 0;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                int digit = data[i][j] - '0';
                if (digit > 0) {
                    disallow(i, j, digit);
                    ++setDigits;
                }
            }
        }
        while (setDigits < 9 * 9) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    int digit = data[i][j] - '0';
                    if (digit == 0) {
                        int onlyAllowed = 0;
                        for (int k = 1; k <= 9; ++k) {
                            if (!banned[i][j][k]) {
                                onlyAllowed = onlyAllowed == 0 ? k : -1;
                            }
                        }
                        if (onlyAllowed > 0) {
                            data[i][j] = (char)('0' + onlyAllowed);
                            disallow(i, j, onlyAllowed);
                            ++setDigits;
                        }
                    }
                }
            }    
        }
    }
    
    public void output(OutputStream out) throws IOException {
        for (int i = 0; i < 9; ++i) {
            out.write(new String(data[i]).getBytes());
            out.write('\n');
        }
    }
    
    public static void main(String[] args) throws Exception {
        Sudoku solution = new Sudoku();
        solution.input(System.in);
        solution.solve();
        solution.output(System.out);
    }
}