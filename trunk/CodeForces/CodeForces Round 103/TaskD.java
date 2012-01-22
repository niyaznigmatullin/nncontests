package mypackage;

import graphalgorithms.DijkstraGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskD {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        DijkstraGraph g = new DijkstraGraph(n);
        DijkstraGraph.Edge[] edges = new DijkstraGraph.Edge[m];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            edges[i] = g.addEdge(from, to, w);
            g.addEdge(to, from, w);
        }
        int length = in.nextInt();
        long[] d = g.dijkstra(s);
        int ans = 0;
        for (long e : d) {
            if (e == length) {
                ans++;
            }
        }
        for (DijkstraGraph.Edge e : edges) {
            long len1 = length - d[e.from];
            long len2 = length - d[e.to];
            int add = 0;
            if (len1 < e.w && len1 > 0 && e.w - len1 + d[e.to] >= length) {
                add++;
            }
            if (len2 < e.w && len2 > 0 && e.w - len2 + d[e.from] >= length) {
                add++;
            }
            if (add == 2 && len1 + len2 == e.w) {
                add--;
            }
            ans += add;
        }
        out.println(ans);
	}
}
