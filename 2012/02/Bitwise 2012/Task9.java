package mypackage;

import graphalgorithms.DijkstraGraph;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Task9 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        DijkstraGraph g = new DijkstraGraph(n);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            g.addEdge(from, to, w);
            g.addEdge(to, from, w);
        }
        long[] d = g.dijkstra(s);
        int k = in.nextInt();
        long answer = 0;
        for (int i = 0; i < k; i++) {
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            answer += d[v] * w;
        }
        out.println(answer);
    }

}
