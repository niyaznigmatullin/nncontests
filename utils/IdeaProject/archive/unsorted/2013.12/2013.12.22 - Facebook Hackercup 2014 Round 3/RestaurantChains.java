package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class RestaurantChains {

    static int[] from, to, he, next;
    static int[][] dist;
    static int n, m;

    static boolean bipartite() {
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int start = 0; start < n; start++) {
            if (color[start] >= 0) continue;
            q[tail++] = start;
            color[start] = 0;
            while (head < tail) {
                int v = q[head++];
                for (int e = he[v]; e >= 0; e = next[e]) {
                    int u = to[e];
                    if (color[u] >= 0 && color[u] == color[v]) {
                        return false;
                    }
                    if (color[u] < 0) {
                        color[u] = color[v] ^ 1;
                        q[tail++] = u;
                    }
                }
            }
        }
        return true;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        out.print("Case #" + testNumber + ": ");
        n = in.nextInt();
        m = in.nextInt();
        from = new int[2 * m];
        to = new int[2 * m];
        he = new int[n];
        Arrays.fill(he, -1);
        next = new int[2 * m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt();
            to[i] = in.nextInt();
            from[i + m] = to[i];
            to[i + m] = from[i];
        }
        for (int i = 0; i < 2 * m; i++) {
            next[i] = he[from[i]];
            he[from[i]] = i;
        }
        if (!bipartite()) {
            out.println(-1);
            return;
        }
        int[] q = new int[n];
        dist = new int[n][];
        for (int i = 0; i < n; i++) {
            int head = 0;
            int tail = 0;
            int[] d = new int[n];
            Arrays.fill(d, Integer.MAX_VALUE);
            q[tail++] = i;
            d[i] = 0;
            while (head < tail) {
                int v = q[head++];
                for (int e = he[v]; e >= 0; e = next[e]) {
                    int u = to[e];
                    if (d[u] == Integer.MAX_VALUE) {
                        d[u] = d[v] + 1;
                        q[tail++] = u;
                    }
                }
            }
            dist[i] = d;
        }
        boolean[] inTree = new boolean[m];
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < m; i++) {
            if (dsu.get(from[i]) != dsu.get(to[i])) {
                dsu.union(from[i], to[i]);
                inTree[i] = true;
            }
        }
        DisjointSetUnion dsuEdges = new DisjointSetUnion(m);
        for (int i = 0; i < m; i++) {
            if (!inTree[i]) continue;
            int u1 = from[i];
            int v1 = to[i];
            for (int j = 0; j < m; j++) {
                int u2 = from[j];
                int v2 = to[j];
                int dd = dist[u1][u2] + dist[v1][v2];
                if (dd != dist[u1][v2] + dist[u2][v1]) {
                    dsuEdges.union(i, j);
                }
            }
        }
        int ans = 0;
        boolean[] was = new boolean[n];
        for (int i = 0; i < m; i++) {
            if (dsuEdges.get(i) != i) {
                continue;
            }
            ++ans;
            int head = 0;
            int tail = 0;
            int countComponents = 0;
            Arrays.fill(was, false);
            for (int start = 0; start < n; start++) {
                if (was[start]) continue;
                ++countComponents;
                if (countComponents > 2) {
                    System.err.println("components more than 2");
                    out.println(-1);
                    return;
                }
                q[tail++] = start;
                was[start] = true;
                while (head < tail) {
                    int v = q[head++];
                    for (int e = he[v]; e >= 0; e = next[e]) {
                        int ne = e >= m ? e - m : e;
                        if (dsuEdges.get(ne) == i) {
                            continue;
                        }
                        int u = to[e];
                        if (!was[u]) {
                            q[tail++] = u;
                            was[u] = true;
                        }
                    }
                }
            }
        }
        out.println(ans);
    }
}
