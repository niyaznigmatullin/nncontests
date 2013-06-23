package coding;

import ru.ifmo.niyaz.graphalgorithms.DijkstraGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println("[" + testNumber + "]");
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        int[] a = new int[m];
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }
        int[] path = new int[p];
        for (int i = 0; i < p; i++) {
            path[i] = in.nextInt() - 1;
        }
        int[] pathV = new int[p + 1];
        pathV[0] = 0;
        for (int i = 0; i < p; i++) {
            pathV[i + 1] = to[path[i]];
        }
        int l = 0;
        int r = p + 1;
        while (l < r - 1) {
            int mid = (l + r) >>> 1;
            boolean ok = true;
            boolean[] was = new boolean[n];
            boolean[] wasEdge = new boolean[m];
            was[pathV[0]] = true;
            for (int i = 0; i < mid; i++) {
                if (was[pathV[i + 1]]) {
                    ok = false;
                    break;
                }
                was[pathV[i + 1]] = true;
                wasEdge[path[i]] = true;
            }
            if (ok) {
                ok = check(was, wasEdge, from, to, a, b, n, mid, m, path, pathV);
            }
            if (ok) l = mid;
            else r = mid;
        }
        out.println(l == p ? "Looks Good To Me" : (path[l] + 1));
    }

    boolean check(boolean[] was, boolean[] wasEdge, int[] from, int[] to, int[] a, int[] b, int n, int mid, int m, int[] path, int[] pathV) {
        DijkstraGraph g1 = new DijkstraGraph(n);
        for (int i = 0; i < m; i++) {
            if (wasEdge[i]) {
                g1.addEdge(from[i], to[i], a[i]);
            } else {
                g1.addEdge(from[i], to[i], b[i]);
            }
        }
        long[] d1 = g1.dijkstra(0);
        for (int i = 0; i < mid; i++) {
            if (d1[pathV[i + 1]] == Long.MAX_VALUE || a[path[i]] != d1[pathV[i + 1]] - d1[pathV[i]]) {
                return false;
            }
        }
        DijkstraGraph g2 = new DijkstraGraph(n);
        for (int i = 0; i < m; i++) {
            if (from[i] == pathV[mid] || to[i] == pathV[mid]) continue;
            if (wasEdge[i]) {
                g2.addEdge(from[i], to[i], a[i]);
            } else {
                g2.addEdge(from[i], to[i], b[i]);
            }
        }
        long[] d2 = g2.dijkstra(0);
        DijkstraGraph g3 = new DijkstraGraph(n);
        for (int i = 0; i < m; i++) {
            if (was[to[i]]) {
                continue;
            }
            if (from[i] == pathV[mid]) {
                g3.addEdge(to[i], from[i], a[i]);
            } else if (d2[to[i]] == Long.MAX_VALUE || d2[from[i]] == Long.MAX_VALUE) {
                g3.addEdge(to[i], from[i], b[i]);
            } else {
                g3.addEdge(to[i], from[i], (int) Math.max(a[i], d2[to[i]] - d2[from[i]]));
            }
        }
        long[] d3 = g3.dijkstra(1);
        return d3[pathV[mid]] != Long.MAX_VALUE && d3[pathV[mid]] + d1[pathV[mid]] == d1[1];
    }
}
