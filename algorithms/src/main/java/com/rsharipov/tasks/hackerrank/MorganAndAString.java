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
                while (i < first.length() && j < second.length()) {
                    if (array.rank(i) < array.rank(first.length() + 1 + j)) {
                        builder.append(first.charAt(i));
                        ++i;
                    }
                    else {
                        builder.append(second.charAt(j));
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