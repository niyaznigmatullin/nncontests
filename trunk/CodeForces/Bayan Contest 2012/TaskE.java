package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskE {

    static List<Edge>[] edges;
    static List<Edge>[] revEdges;

    static class Edge {
        int id;
        int from;
        int to;
        int w;
        Edge rev;

        Edge(int id, int from, int to) {
            this.id = id;
            this.from = from;
            this.to = to;
            w = 1;
        }
    }

//    static int[][] can;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        Edge[] allEdges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            allEdges[i] = new Edge(i, from, to);
            Edge rev = new Edge(i, to, from);
            rev.rev = allEdges[i];
            allEdges[i].rev = rev;
            edges[from].add(allEdges[i]);
            revEdges[to].add(rev);
        }
        was = new boolean[n];
        dfs(0);
        can = was;
        was = new boolean[n];
        dfsRev(n - 1);
        for (int i = 0; i < n; i++) {
            can[i] &= was[i];
        }
        d = new int[n];
        List<Integer> topSort = new ArrayList<Integer>();
        dfs(0, topSort);
        Collections.reverse(topSort);
        Arrays.fill(d, Integer.MAX_VALUE);
        for (int i : topSort) {
            int minDistance = Integer.MAX_VALUE;
            int maxDistance = Integer.MIN_VALUE;
            for (Edge e : revEdges[i]) {
                minDistance = Math.min(minDistance, d[e.from]);
                maxDistance = Math.max(maxDistance, d[e.from]);
            }

        }
    }

    static int[] d;

    static void dfs(int v) {
        was[v] = true;
        for (Edge e : edges[v]) {
            if (was[e.to]) {
                dfs(e.to);
            }
        }
    }

    static void dfsRev(int v) {
        was[v] = true;
        for (Edge e : revEdges[v]) {
            if (was[e.to]) {
                dfs(e.to);
            }
        }
    }

    static boolean[] can;

    static void dfs(int v, List<Integer> topSort) {
        was[v] = true;
        for (Edge e : edges[v]) {
            if (was[e.to] && can[e.to]) {
                dfs(e.to, topSort);
            }
        }
        topSort.add(v);
    }

    static boolean[] was;

//    static void restoreAnswer(int v, int len) {
//        if (v == can.length - 1) {
//            if (len != 0) {
//                throw new AssertionError();
//            }
//            return;
//        }
//        for (int i = 0; i < edges[v].size(); i++) {
//            Edge e = edges[v].get(i);
//            if (len == 1) {
//                if (!dfs(e.to, len - 1)) {
//                    throw new AssertionError();
//                }
//                restoreAnswer(e.to, len - 1);
//            } else {
//
//            }
//        }
//    }

//    static boolean dfs(int v, int len) {
//        if (v == can.length - 1) {
//            return len == 0;
//        }
//        if (len == 0) {
//            return false;
//        }
//        if (can[v][len] >= 0) {
//            return can[v][len] == 1;
//        }
//        boolean ret = true;
//        for (int i = 0; i < edges[v].size(); i++) {
//            Edge e = edges[v].get(i);
//            if (len == 1) {
//                ret &= dfs(e.to, len - 1);
//            } else {
//                ret &= dfs(e.to, len - 1) || dfs(e.to, len - 2);
//            }
//        }
//        can[v][len] = ret ? 1 : 0;
//        return ret;
//    }

}
