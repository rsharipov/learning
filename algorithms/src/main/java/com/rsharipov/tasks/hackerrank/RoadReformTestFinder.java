package com.rsharipov.tasks.hackerrank;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class RoadReformTestFinder {

    public static void main(String[] args) throws IOException {
        final int N = 100;
        final int M = 200;
        final int MAX_Z = 10;
        Random random = new Random();
        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("%d %d\n", N, M));
            for (int i = 0; i < N - 1; ++i) {
                builder.append(String.format("%d %d %d\n", i + 1, i + 2, random.nextInt(MAX_Z)));
            }
            for (int i = 0; i < M - (N - 1); ++i) {
                builder.append(String.format("%d %d %d\n", 1 + random.nextInt(N), 1 + random.nextInt(N), 1 + random.nextInt(MAX_Z)));
            }
            long expected = new RoadReform().solve(new ByteArrayInputStream(builder.toString().getBytes()));
            long actual = new RoadReformFast().solve(new ByteArrayInputStream(builder.toString().getBytes()));
            if (expected != actual) {
                System.out.println("Found erroneous input");
                Files.copy(new ByteArrayInputStream(builder.toString().getBytes()), new File("RoadReform4.txt").toPath());
                break;
            }
        }
    }
    
}
