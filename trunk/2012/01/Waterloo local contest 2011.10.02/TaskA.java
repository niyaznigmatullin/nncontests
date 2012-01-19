package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class TaskA {

    static List<Edge>[] edges;

    static class Edge {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt() + 1;
        int[] c = new int[n];
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
            edges[i] = new ArrayList<Edge>();
        }
        int sum = 0;
        for (int i = 0; i + 1 < n; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int w = in.nextInt();
            edges[from].add(new Edge(to, w));
            sum += 2 * w;
        }
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(0);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge e : edges[v]) {
                if (d[e.to] != Integer.MAX_VALUE) {
                    continue;
                }
                d[e.to] = d[v] + e.w;
                q.add(e.to);
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.min(answer, c[i] + sum - d[i]);
        }
        out.println(answer);
	}
}
