package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskM {

    static class Edge {
        int from;
        int to;
        int w;

        Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static List<Edge>[] edges;
    static long[] dp1;
    static long[] dp2;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            edges[from].add(new Edge(from, to, w));
            edges[to].add(new Edge(to, from, w));
        }
        dp1 = new long[n];
        dp2 = new long[n];
        dfs(0, -1);
        out.println(dp1[0]);
	}

    static final long INF = Integer.MAX_VALUE * 10L;
    static void dfs(int v, int p) {
        long max1 = -INF;
        long max2 = -INF;
        long sum = 0;
        for (Edge e : edges[v]) {
            if (e.to == p) {
                continue;
            }
            dfs(e.to, v);
            sum += dp1[e.to];
            long delta = dp2[e.to] - dp1[e.to] + e.w;
            if (delta > max1) {
                max2 = max1;
                max1 = delta;
            } else if (delta > max2) {
                max2 = delta;
            }
        }
        dp1[v] = Math.max(max1 + sum, max1 + max2 + sum);
        dp2[v] = Math.max(max1 + sum, sum);
    }
}
