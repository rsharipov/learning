package com.rsharipov.tasks.hackerrank;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class RoadReformFast {

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
    
    private static class Distance implements Comparable<Distance> {
        int vertex;
        int shortest;
        private Distance(int vertex, int shortest) {
            this.vertex = vertex;
            this.shortest = shortest;
        }

        @Override
        public int compareTo(Distance o) {
            return Integer.compare(shortest, o.shortest);
        }
    }

    public long solve(InputStream in) {
        Scanner scanner = new Scanner(new BufferedInputStream(in));
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
        Distance[] sDistances = findDistances(0, n, edges);
        Distance[] eDistances = findDistances(n - 1, n, edges);
        int shortest = sDistances[n - 1].shortest;
        long result = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (sDistances[i].shortest + 1 < shortest) {
                result += shortest - (sDistances[i].shortest + 1);
            }
            if (eDistances[i].shortest + 1 < shortest) {
                result += shortest - (eDistances[i].shortest + 1);
            }
        }
        result += shortest - 1;        
        Arrays.sort(sDistances);
        Arrays.sort(eDistances);
        
//        for (int i = 0; i < sDistances.length; ++i) {
//            if (sDistances[i].shortest + 1 > shortest) {
//                break;
//            }
//            if (sDistances[i].vertex == 0 || sDistances[i].vertex == n - 1) {
//                continue;
//            }
//            for (int j = 0; j < eDistances.length; ++j) {
//                if (eDistances[j].vertex == sDistances[i].vertex ||
//                    eDistances[j].vertex == 0 ||
//                    eDistances[j].vertex == n - 1) {
//                    continue;
//                }
//                if (sDistances[i].shortest + eDistances[j].shortest + 1 > shortest) {
//                    break;
//                }
//                result += shortest - (sDistances[i].shortest + 1 + eDistances[j].shortest);
//            }
//        }
        
        int[] eDistancesSums = new int[eDistances.length];
        for (int i = 0; i < eDistancesSums.length; ++i) {
            eDistancesSums[i] = eDistances[i].shortest;
            if (i > 0) {
                eDistancesSums[i] += eDistancesSums[i - 1];
            }
        }
        HashMap<Integer, Integer> positionInEDistance = new HashMap<Integer, Integer>();
        for (int i = 0; i < eDistances.length; ++i) {
            positionInEDistance.put(eDistances[i].vertex, i);
        }
        for (int i = 0; i < sDistances.length; ++i) {
            if (sDistances[i].shortest + 1 > shortest) {
                break;
            }
            if (sDistances[i].vertex == 0 || sDistances[i].vertex == n - 1) {
                continue;
            }
            // should stop when eDistances[j].shortest > shortest - 1 - sDistances[i].shortest
            if (eDistances[0].shortest > shortest - 1 - sDistances[i].shortest) {
                continue;
            }
            int begin = 0;
            int end = eDistances.length;
            while (begin + 1 < end) {
                int mid = (begin + end) / 2;
                if (eDistances[mid].shortest > shortest - 1 - sDistances[i].shortest) {
                    end = mid;
                }
                else {
                    begin = mid;
                }
            }
            // end - is the first eDistances, that has eDistances[j].shortest > shortest - 1 - sDistances[i].shortest
            result += end * (shortest - (sDistances[i].shortest + 1)) - eDistancesSums[end - 1];
            if (positionInEDistance.get(0) < end) {
                result -= shortest - (sDistances[i].shortest + 1 + eDistances[positionInEDistance.get(0)].shortest);
            }
            if (positionInEDistance.get(n - 1) < end) {
                result -= shortest - (sDistances[i].shortest + 1 + eDistances[positionInEDistance.get(n - 1)].shortest);
            }
            if (positionInEDistance.get(sDistances[i].vertex) < end) {
                result -= shortest - (sDistances[i].shortest + 1 + eDistances[positionInEDistance.get(sDistances[i].vertex)].shortest);
            }
        }
        return result;
    }

    private Distance[] findDistances(int s, int n, Map<Integer, Map<Integer, Integer>> edges) {
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
        Distance[] result = new Distance[n];
        for (int i = 0; i < n; ++i) {
            result[i] = new Distance(i, distances[i]);
        }
        return result;
    }
    
    public long solve(List<Integer> path) {
        long result = - (path.size()) * (1 + path.size()) / 2;
        for (int i = 0 ; i < path.size(); ++i) {
            result += ((long)(i + 1)) * path.get(i) * (path.size() - i);
        }
        return result;
    }
    
    public static void main(String[] args) throws IOException {
        RoadReformFast reform = new RoadReformFast();
        System.out.println(reform.solve(System.in));
    }
    
    
}
