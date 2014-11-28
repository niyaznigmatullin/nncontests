package coding;

import ru.ifmo.niyaz.graphalgorithms.CondensationGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Salary {

    static List<Edge>[] edges;
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int u = from[i];
            int v = to[i];
            boolean eq = false;
            boolean neq = false;
            for (int s1 = 0; s1 < 2; s1++) {
                for (int s2 = 0; s2 < 2; s2++) {
                    if ((x[u] > x[v] || y[u] > y[v])) {
                        if (s1 != s2) neq = true;
                        if (s1 == s2) eq = true;
                    }
                    {
                        int t = x[v];
                        x[v] = y[v];
                        y[v] = t;
                    }
                }
                {
                    int t = x[u];
                    x[u] = y[u];
                    y[u] = t;
                }
            }
            if (!eq && !neq) {
                out.println(-1);
                return;
            }
            if (eq && neq) {
            } else {
                edges[u].add(new Edge(u, v, eq ? 0 : 1));
                edges[v].add(new Edge(v, u, eq ? 0 : 1));
            }
        }
        one = new ArrayList<>();
        zero = new ArrayList<>();
        int[] color = new int[n];
        was = new int[n];
        Arrays.fill(was, -1);
        Arrays.fill(color, -1);
        for (int i = 0; i < n; i++) {
            if (color[i] >= 0) continue;
            zero.clear();
            one.clear();
            if (!dfs(i, 0)) {
                out.println(-1);
                return;
            }
            for (int v : (one.size() < zero.size() ? one : zero)) {
                color[v] = 1;
            }
            for (int v : (one.size() < zero.size() ? zero : one)) {
                color[v] = 0;
            }
        }
        int[] ans = new int[n];
        int ac = 0;
        for (int i = 0; i < n; i++) if (color[i] == 1) {
            ans[ac++] = i;
        }
        out.print(ac);
        for (int i = 0; i < ac; i++) out.print(" " + (ans[i] + 1));
        out.println();
    }

    static boolean dfs(int v, int c) {
        (c == 0 ? zero : one).add(v);
        was[v] = c;
        for (int i = 0; i < edges[v].size(); i++) {
            Edge e = edges[v].get(i);
            if (was[e.to] >= 0 && was[e.to] != (was[v] ^ e.change)) {
                return false;
            }
            if (was[e.to] < 0) {
                if (!dfs(e.to, c ^ e.change)) return false;
            }
        }
        return true;
    }

    static int[] was;
    static List<Integer> one;
    static List<Integer> zero;

    static class Edge {
        int from;
        int to;
        int change;

        Edge(int from, int to, int change) {
            this.from = from;
            this.to = to;
            this.change = change;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", change=" + change +
                    '}';
        }
    }
}
