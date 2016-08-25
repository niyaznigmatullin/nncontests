package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {

    static class Edge {
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static boolean[][] was;
    static List<Edge>[] edges;
    static List<Integer> path;

    static boolean dfs(int v, int par, int t) {
        if (v == t && par != 0) {
            return true;
        }
        if (was[par][v] || was[1 ^ par][v]) return false;
        was[par][v] = true;
        for (int it = 0; it < edges[v].size(); it++) {
            Edge e = edges[v].get(it);
            if (dfs(e.to, par ^ e.w, t)) {
                path.add(v);
                return true;
            }
        }
        return false;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        int r0 = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        Point2DInteger[] p = new Point2DInteger[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            p[i] = new Point2DInteger(x[i], y[i]);
            r[i] = r0 + in.nextInt();
        }
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long dx = x[i] - x[j];
                long dy = y[i] - y[j];
                long dr = r[i] + r[j];
                if (dx * dx + dy * dy < dr * dr) {
                    int y1 = y[i];
                    int y2 = y[j];
                    if (y1 > y2) {
                        int t = y1;
                        y1 = y2;
                        y2 = t;
                    }
                    int w;
                    if (y1 == y2) {
                        w = 0;
                    } else {
                        w = y1 <= y0 && y0 < y2 ? 1 : 0;
                        long den = y[j] - y[i];
                        long num = x[i] * den + (long) (x[j] - x[i]) * (y0 - y[i]);
                        if (den < 0) {
                            num = -num;
                            den = -den;
                        }
//                        System.out.println(x0 + " " + 1. * num / den + " " + num + " " + den);
                        if (x0 * den >= num) {
                            w = 0;
                        }
                    }
//                    System.out.println(i + " " + j + " " + w);
                    edges[i].add(new Edge(i, j, w));
                }
            }
        }
        path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            was = new boolean[2][n];
            if (dfs(i, 0, i)) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }
}
