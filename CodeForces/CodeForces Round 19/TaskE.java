package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class TaskE {

    static class Edge {
        int from;
        int to;
        int id;
        int count;

        Edge(int from, int to, int id) {
            this.from = from;
            this.to = to;
            this.id = id;
        }
    }

    static List<Edge>[] edges;
    static int[] depth;
    static int[] count;
    static Edge[] last;
    static int countOdd;
    static int v1;
    static int v2;
    static int edge;
    static Set<Integer> all;

    static void dfs(int v, int d, Edge p) {
        depth[v] = d;
        last[v] = p;
        for (Edge e : edges[v]) {
            int i = e.to;
            if (p != null && e.id == p.id) {
                continue;
            }
            if (depth[i] < 0) {
                dfs(i, d + 1, e);
            } else if (depth[i] < depth[v]) {
                Set<Integer> f = new HashSet<Integer>();
                f.add(e.id);
                for (int z = v; z != i; z = last[z].from) {
                    f.add(last[z].id);
                }
                if ((depth[v] - depth[i] & 1) == 0) {
                    countOdd++;
                    all.retainAll(f);
                } else {
                    all.removeAll(f);
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
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            edges[x].add(new Edge(x, y, i));
            edges[y].add(new Edge(y, x, i));
        }
        countOdd = 0;
        count = new int[n];
        depth = new int[n];
        last = new Edge[n];
        Arrays.fill(depth, -1);
        all = new HashSet<Integer>();
        for (int i = 0; i < m; i++) {
            all.add(i);
        }
        for (int i = 0; i < n; i++) {
            if (depth[i] < 0) {
                dfs(i, 0, null);
            }
        }
        if (countOdd == 0) {
            all = new HashSet<Integer>();
            for (int i = 0; i < m; i++) {
                all.add(i);
            }
        }
        List<Integer> answer = new ArrayList<Integer>(all);
        Collections.sort(answer);
        out.println(answer.size());
        int[] ans = ArrayUtils.toPrimitiveArrayInteger(answer);
        for (int i = 0; i < ans.length; i++) {
            ans[i]++;
        }
        out.printArray(ans);
    }
}
