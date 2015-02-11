package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskD1 {

    static class Edge {
        int from;
        int to;
        int type;

        Edge(int from, int to, int type) {
            this.from = from;
            this.to = to;
            this.type = type;
        }
    }

    static List<Edge>[] edges;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int c = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] deg = new int[n];
        for (int i = 0; i < c; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int type = in.next().equals("LEFT") ? 1 : 2;
            if (to <= from) {
                out.println("IMPOSSIBLE");
                return;
            }
            edges[from].add(new Edge(from, to, type));
            deg[to]++;
        }
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                q[tail++] = i;
            }
        }
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < edges[v].size(); i++) {
                Edge e = edges[v].get(i);
                deg[e.to]--;
                if (deg[e.to] == 0) {
                    q[tail++] = e.to;
                }
            }
        }
        if (head != n) {
            out.println("IMPOSSIBLE");
            return;
        }
        int[] max1 = new int[n];
//        int[] dMin = new int[n];
        int[] dMax = new int[n];
//        Arrays.fill(min2, Integer.MAX_VALUE);
//        Arrays.fill(dMin, Integer.MAX_VALUE);
        Arrays.fill(max1, Integer.MIN_VALUE);
        Arrays.fill(dMax, Integer.MIN_VALUE);
        MaxSegmentTree tree = new MaxSegmentTree(n);
        for (int i = 0; i < n; i++) {
            tree.set(i, i);
        }
        for (int v = n - 1; v >= 0; v--) {
            dMax[v] = v;
            max1[v] = v;
            int minimal = Integer.MAX_VALUE;
            for (int i = 0; i < edges[v].size(); i++) {
                Edge e = edges[v].get(i);
                dMax[v] = Math.max(dMax[v], dMax[e.to]);
                if (e.type == 1) {
                    max1[v] = Math.max(max1[v], dMax[e.to]);
                } else {
                    minimal = Math.min(minimal, e.to);
                }
            }
            max1[v] = tree.getMax(v, max1[v] + 1);
            tree.set(v, tree.getMax(v, dMax[v] + 1));
            if (max1[v] >= minimal) {
                out.println("IMPOSSIBLE");
                return;
            }
        }
        ans = new int[n];
        cn = 0;
        go(0, n - 1, max1);
        out.printArray(ans);
    }

    static int[] ans;
    static int cn;

    static void go(int v, int last, int[] max1) {
        if (v + 1 <= max1[v]) go(v + 1, max1[v], max1);
        ans[cn++] = v + 1;
        if (max1[v] <= last - 1) go(max1[v] + 1, last, max1);
    }

}
