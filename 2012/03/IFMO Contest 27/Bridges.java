package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bridges {
    static class Edge {
        int to;
        int id;
        Edge rev;

        Edge(int to, int id) {
            this.to = to;
            this.id = id;
        }
    }

    static List<Edge>[] edges;
    static int[] depth;
    static int[] up;
    static boolean[] was;


    static void dfs(int v, Edge lastEdge, int d, List<Integer> bridges) {
        was[v] = true;
        up[v] = depth[v] = d;
        for (Edge e : edges[v]) {
            if (e.rev == lastEdge) {
                continue;
            }
            if (was[e.to]) {
                up[v] = Math.min(up[v], depth[e.to]);
            } else {
                dfs(e.to, e, d + 1, bridges);
                up[v] = Math.min(up[v], up[e.to]);
                if (up[e.to] > depth[v]) {
                    bridges.add(e.id + 1);
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            Edge e1 = new Edge(to, i);
            edges[from].add(e1);
            Edge e2 = new Edge(from, i);
            e1.rev = e2;
            e2.rev = e1;
            edges[to].add(e2);
        }
        List<Integer> answer = new ArrayList<Integer>();
        was = new boolean[n];
        up = new int[n];
        depth = new int[n];
        dfs(0, null, 0, answer);
        Collections.sort(answer);
        out.println(answer.size());
        out.printArray(ArrayUtils.toPrimitiveArray(answer));
    }
}
