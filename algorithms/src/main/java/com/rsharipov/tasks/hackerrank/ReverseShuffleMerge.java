package com.rsharipov.tasks.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseShuffleMerge {

    public int[] countCharsAndDivideInHalf(String line) {
        int counts[] = new int[26];
        for (int i = 0; i < line.length(); ++i) {
            counts[line.charAt(i) - 'a']++;
        }
        for (int i = 0; i < counts.length; ++i) {
            counts[i] /= 2;
        }
        return counts;
    }
    
    public int sumArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
        return sum;
    }
    
    public int findLeftmostEndOfLineWithCharsExclusive(
            String line, int[] charsCounts, int sum) {
        int countFromEnd[] = new int[26];
        int start = 0;
        while (sum > 0) {
            int ch = line.charAt(start);
            if (countFromEnd[ch - 'a'] < charsCounts[ch - 'a']) {
                --sum;
                countFromEnd[ch - 'a']++;
            }
            ++start;
        }
        return start;
    }
    
    public String solve(String line) {
        int[] counts = countCharsAndDivideInHalf(line);
        int start = findLeftmostEndOfLineWithCharsExclusive(line, counts, sumArray(counts));
        return findGreatestReverse(line, start, counts);
    }

    public String findGreatestReverse(String line, int start, int[] counts) {
        int end = line.length();
        StringBuilder result = new StringBuilder();
        int toSkip[] = new int[26];
        for (int j = 0; j < line.length(); ++j) {
            while (start > 0 && toSkip[line.charAt(start - 1) - 'a'] > 0) {
                toSkip[line.charAt(start - 1) - 'a']--;
                --start;
            }
            for (int ch = 0; ch < 26; ++ch) {
                if (counts[ch] > 0) {
                    int index = line.lastIndexOf((char)('a' + ch), end - 1);
                    if (index > -1 && index >= start - 1) {
                        result.append((char)(ch + 'a'));
                        --counts[ch];
                        end = index;
                        toSkip[ch]++;
                        break;
                    }                    
                }
            }            
        }
        return result.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        System.out.println(new ReverseShuffleMerge().solve(line));
    }
}
