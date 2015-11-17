package com.rsharipov.tasks.hackerrank;

import com.rsharipov.SuffixArray;
import java.io.*;

public class MorganAndAString {

    public static void solve(InputStream input, OutputStream output) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        int testCount = Integer.parseInt(reader.readLine());
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(output))) {
            for (int test = 0; test < testCount; ++test) {
                String first = reader.readLine();
                String second = reader.readLine();
                StringBuilder inputBuilder = new StringBuilder(first);
                inputBuilder.append("$");
                inputBuilder.append(second);
                String joined = inputBuilder.toString();
                SuffixArray array = new SuffixArray(joined, '$', 'Z');
                StringBuilder builder = new StringBuilder();
                int i = 0;
                int j = 0;
                StringBuilder result = new StringBuilder();
                while (i < first.length() && j < second.length()) {
                    int commonLength = array.lcp(i, first.length() + 1 + j);
                    if (commonLength == first.length() - i
                            && commonLength == second.length() - j) {
                        result.append(first.charAt(i));
                        ++i;
                    } else if (commonLength == first.length() - i) {
                        if (first.charAt(i) < second.charAt(j + commonLength)) {
                            result.append(first.charAt(i));
                            ++i;
                        } else {
                            result.append(second.charAt(j));
                            ++j;
                        }
                    } else if (commonLength == second.length() - j) {
                        if (second.charAt(j) < first.charAt(i + commonLength)) {
                            result.append(second.charAt(j));
                            ++j;
                        } else {
                            result.append(first.charAt(i));
                            ++i;
                        }
                    } else if (array.rank(i) < array.rank(first.length() + 1 + j)) {
                        result.append(first.charAt(i));
                        ++i;
                    } else {
                        result.append(second.charAt(j));
                        ++j;
                    }
                }
                if (i < first.length()) {
                    builder.append(first.substring(i));
                }
                if (j < second.length()) {
                    builder.append(second.substring(j));
                }
                writer.write(builder.toString());
                writer.write("\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solve(System.in, System.out);
    }
}
