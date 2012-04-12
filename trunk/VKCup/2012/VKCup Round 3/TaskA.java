package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = in.nextInt();
        }
        List<Integer>[] edges = new ArrayList[n];
        List<Integer>[] revEdges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>(2);
            revEdges[i] = new ArrayList<Integer>(2);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            edges[from].add(to);
            revEdges[to].add(from);
        }
        boolean[] a = bfs(1, -1, edges, f);
        boolean[] b = bfs(2, 1, revEdges, f);
        for (int i = 0; i < n; i++) {
            out.println(a[i] && b[i] ? 1 : 0);
        }
	}

    static boolean[] bfs(int from, int ignore, List<Integer>[] edges, int[] f) {
        int n = edges.length;
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        boolean[] was = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (f[i] == from) {
                was[i] = true;
                q[tail++] = i;
            }
        }
        while (head < tail) {
            int v = q[head++];
            if (f[v] == ignore) {
                continue;
            }
            for (int i : edges[v]) {
                if (was[i]) {
                    continue;
                }
                q[tail++] = i;
                was[i] = true;
            }
        }
        return was;
    }
}
