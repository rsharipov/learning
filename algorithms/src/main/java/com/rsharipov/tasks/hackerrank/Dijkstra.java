package com.rsharipov.tasks.hackerrank;

import java.io.*;
import java.util.*;

public class Dijkstra {
    
    static class Node implements Comparable<Node> {
        private int vertex;
        private int mark;
        Node(int vertex, int mark) {
            this.vertex = vertex;
            this.mark = mark;
        }
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node right = (Node)obj;
            return vertex == right.vertex && mark == right.mark;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + this.vertex;
            hash = 97 * hash + this.mark;
            return hash;
        }
        @Override
        public int compareTo(Node right) {
            if (mark < right.mark) return -1;
            if (mark > right.mark) return 1;
            return Integer.compare(vertex, right.vertex);
        }
    }
    
    static class Edge {
        int to;
        int length;
        Edge(int to, int length) {
            this.to = to;
            this.length = length;
        }
    }
    
    public void addEdge(Map<Integer, Map<Integer, Integer>> edges, int a, int b, int r) {
        Map<Integer, Integer> edgeList = edges.get(a);
        if (edgeList == null) {
            edgeList = new HashMap<>();
            edges.put(a, edgeList);
        }
        if (edgeList.containsKey(b)) {
            edgeList.put(b, Math.min(edgeList.get(b), r));
        }
        else {
            edgeList.put(b, r);
        }
    }

    public void solve(InputStream in, OutputStream out) throws IOException {
        Scanner scanner = new Scanner(new BufferedInputStream(in));
        final int testCount = scanner.nextInt();
        try (BufferedOutputStream output = new BufferedOutputStream(out)) {
            for (int test = 0; test < testCount; ++test) {
                int n = scanner.nextInt();
                int m = scanner.nextInt();
                Map<Integer, Map<Integer, Integer>> edges = new HashMap<>();
                for (int i = 0; i < m; ++i) {
                    int a = scanner.nextInt() - 1;
                    int b = scanner.nextInt() - 1;
                    int r = scanner.nextInt();
                    addEdge(edges, a, b, r);
                    addEdge(edges, b, a, r);
                }
                int s = scanner.nextInt() - 1;
                TreeSet<Node> marks = new TreeSet<>();
                int[] distances = new int[n];
                marks.add(new Node(s, 0));
                for (int i = 0; i < n; ++i) {
                    if (i != s) {
                        distances[i] = Integer.MAX_VALUE;
                        marks.add(new Node(i, Integer.MAX_VALUE));
                    }
                }
                while (true) {
                    Node node = marks.pollFirst();
                    if (node == null || node.mark == Integer.MAX_VALUE) break;
                    for (Map.Entry<Integer, Integer> entry : edges.get(node.vertex).entrySet()) {
                        int to = entry.getKey();
                        int length = entry.getValue();
                        if (distances[to] > distances[node.vertex] + length) {
                            Node toRemove = new Node(to, distances[to]);
                            marks.remove(toRemove);
                            distances[to] = distances[node.vertex] + length;
                            marks.add(new Node(to, distances[to]));
                        }
                    }
                }            
                for (int i = 0; i < n; ++i) {
                    if (i != s) {
                        output.write(((distances[i] == (Integer.MAX_VALUE) ? -1 : distances[i]) + " ").getBytes());
                    }
                }
                output.write('\n');
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        new Dijkstra().solve(System.in, System.out);
    }
}