package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class ProblemB {
    static class Edge {
        int from;
        int to;
        int f;
        Edge rev;

        Edge(int from, int to, int f) {
            this.from = from;
            this.to = to;
            this.f = f;
        }
    }

    static List<Edge>[] edges;

    static int dfs(int v, int parent, int target, int cMin) {
        if (v == target) {
            return cMin;
        }
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (e.to == parent) {
                continue;
            }
            int got = dfs(e.to, v, target, Math.min(cMin, e.f));
            if (got == 0) {
                continue;
            }
            e.f -= got;
            e.rev.f -= got;
            return got;
        }
        return 0;
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int c1 = in.nextInt() - 1;
        int c2 = in.nextInt() - 1;
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int flow = in.nextInt();
            Edge e1 = new Edge(from, to, flow);
            Edge e2 = new Edge(to, from, flow);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
        }
        int ans = dfs(c1, -1, c2, Integer.MAX_VALUE);
        int best1 = 0;
        for (Edge e : edges[c1]) {
            best1 = Math.max(best1, e.f);
        }
        int best2 = 0;
        for (Edge e : edges[c2]) {
            best2 = Math.max(best2, e.f);
        }
        ans += Math.min(best1, best2);
        out.println(ans);
	}
}
