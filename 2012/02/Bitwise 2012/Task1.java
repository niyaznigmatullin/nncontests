package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class Task1 {

    static boolean[][] edges;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.indianNextInt();
        edges = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int k = in.indianNextInt();
            for (int j = 0; j < k; j++) {
                int v = in.indianNextInt() - 1;
                if (v < 0) {
                    v = 0;
                }
                edges[i][v] = edges[v][i] = true;
            }
        }
        removed = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!solveForComponent(i)) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }

    static int n;
    static boolean[] removed;

    static boolean solveForComponent(int root) {
        if (removed[root]) {
            return true;
        }
        int[] depth = new int[n];
        int[] up = new int[n];
        boolean[] was = new boolean[n];
        int[] parent = new int[n];
        int[] count = new int[n];
        List<Integer> list = new ArrayList<Integer>();
        dfs(root, -1, 0, depth, up, parent, was, count, list);
        if ((count[root] & 1) == 1) {
            return false;
        }
        int bU = -1;
        int bV = -1;
        all:
        for (int v : list) {
            for (int u = 0; u < n; u++) {
                if (!edges[v][u]) {
                    continue;
                }
                if ((count[u] & 1) == 1 && depth[u] == depth[v] + 1 && up[u] > depth[v]) {
                    bU = u;
                    bV = v;
                    break all;
                }
            }
        }
        if (bU < 0) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            edges[i][bU] = false;
        }
        for (int i = 0; i < n; i++) {
            edges[i][bV] = false;
        }
        removed[bU] = true;
        removed[bV] = true;
        for (int i = 0; i < n; i++) {
            if (!edges[bU][i]) {
                continue;
            }
            if (!solveForComponent(i)) {
                return false;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!edges[bV][i]) {
                continue;
            }
            if (!solveForComponent(i)) {
                return false;
            }
        }
        return true;
    }

    static void dfs(int v, int p, int d, int[] depth, int[] up, int[] parent, boolean[] was, int[] count, List<Integer> list) {
        up[v] = depth[v] = d;
        was[v] = true;
        parent[v] = p;
        list.add(v);
        count[v] = 1;
        for (int i = 0; i < n; i++) {
            if (!edges[v][i]) {
                continue;
            }
            if (was[i]) {
                if (i != p) {
                    up[v] = Math.min(up[v], depth[i]);
                }
            } else {
                dfs(i, v, d + 1, depth, up, parent, was, count, list);
                count[v] += count[i];
                up[v] = Math.min(up[v], up[i]);
            }
        }
    }
}
