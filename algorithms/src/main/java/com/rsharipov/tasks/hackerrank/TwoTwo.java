package com.rsharipov.tasks.hackerrank;

import com.rsharipov.AhoCorasickTree;
import com.rsharipov.AhoCorasickTreeBuilder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class TwoTwo {
    
    public String multiply(String s, int number) {
        char[] chars = s.toCharArray();
        LinkedList<Character> result = new LinkedList<>();
        int carry = 0;
        for (int i = chars.length - 1; i >= 0; --i) {
            int digit = chars[i] - '0';
            int resultDigit = (digit * number + carry) % 10;
            carry = (digit * number + carry) / 10;
            result.add(0, (char)(resultDigit + '0'));
        }
        while (carry > 0) {
            result.add(0, (char)('0' + carry % 10));
            carry /= 10;
        }
        StringBuilder builder = new StringBuilder();
        for (Character ch : result) {
            builder.append(ch);
        }
        return builder.toString();
    }
    
    public void solve(InputStream in, OutputStream out) throws IOException {
        Scanner scanner = new Scanner(new BufferedInputStream(in));
        int t = scanner.nextInt();
        AhoCorasickTreeBuilder builder = new AhoCorasickTreeBuilder();
        String pow = "1";
        for (int i = 0; i <= 800; ++i) {
            builder.add(pow);
            pow = multiply(pow, 2);
        }        
        AhoCorasickTree automaton = builder.build();
        for (int test = 0; test < t; ++test) {
            String line = scanner.next();
            int count = automaton.countOccurences(line);
            out.write(Integer.toString(count).getBytes());
            out.write("\n".getBytes());
        }
    }
    
    public static void main(String[] args) throws IOException {
        new TwoTwo().solve(System.in, System.out);
    }
    
}
