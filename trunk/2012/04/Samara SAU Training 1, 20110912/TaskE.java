package mypackage;

import graphalgorithms.MinCostMaxFlowGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
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
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        out.println(dfs(start, finish, -1, Integer.MAX_VALUE));
	}

    static int dfs(int v, int t, int p, int cMin) {
        if (t == v) {
            return cMin;
        }
        for (Edge e : edges[v]) {
            if (e.to == p) {
                continue;
            }
            int got = dfs(e.to, t, v, Math.min(cMin, e.w));
            if (got == 0) {
                continue;
            }
            return got;
        }
        return 0;
    }
}
