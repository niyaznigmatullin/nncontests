package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.graphalgorithms.CondensationGraph;
import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class GreenHackenbush {

    static int[] he, ne;
    static int[] from, to, up, depth;
    static boolean[] bridge;
    static DisjointSetUnion dsu;

    static void dfs(int v, int p) {
        up[v] = depth[v];
        for (int e = he[v]; e >= 0; e = ne[e]) {
            int i = to[e];
            if (i == p) {
                continue;
            }
            if (depth[i] >= 0) {
                up[v] = Math.min(up[v], depth[i]);
            } else {
                depth[i] = depth[v] + 1;
                dfs(i, v);
                if (up[i] > depth[v]) {
                    bridge[e] = true;
                }
                up[v] = Math.min(up[v], up[i]);
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        he = new int[n];
        from = new int[m + m];
        to = new int[m + m];
        ne = new int[m + m];
        Arrays.fill(he, -1);
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            from[i + m] = to[i];
            to[i + m] = from[i];
        }
        for (int i = 0; i < m + m; i++) {
            ne[i] = he[from[i]];
            he[from[i]] = i;
        }
        dsu = new DisjointSetUnion(n);
        bridge = new boolean[m + m];
        up = new int[n];
        depth = new int[n];
        Arrays.fill(depth, -1);
        depth[0] = 0;
        dfs(0, 0);
        for (int i = 0; i < m; i++) {
            if (!bridge[i] && !bridge[i + m]) {
                dsu.union(from[i], to[i]);
            }
        }
        int[] color = new int[n];
        Arrays.fill(color, -1);
        int cntColor = 0;
        for (int i = 0; i < n; i++) {
            if (depth[i] >= 0 && dsu.get(i) == i) {
                color[i] = cntColor++;
            }
        }
        int root = color[dsu.get(0)];
        int[] ss = new int[m];
        int[] ff = new int[m];
        int cntEdges = 0;
        for (int i = 0; i < m; i++) {
            if (depth[from[i]] < 0) continue;
            ss[cntEdges] = color[dsu.get(from[i])];
            ff[cntEdges] = color[dsu.get(to[i])];
            ++cntEdges;
        }
        m = cntEdges;
        from = Arrays.copyOf(ss, 2 * cntEdges);
        to = Arrays.copyOf(ff, 2 * cntEdges);
        for (int i = 0; i < m; i++) {
            from[i + m] = to[i];
            to[i + m] = from[i];
        }
        ne = new int[m + m];
        he = new int[cntColor];
        Arrays.fill(he, -1);
        for (int i = 0; i < m + m; i++) {
            ne[i] = he[from[i]];
            he[from[i]] = i;
        }
        int ans = grundy(root, -1);
        out.println(ans == 0 ? "Second" : "First");
    }

    static int grundy(int v, int p) {
        int ret = 0;
        int cntLoops = 0;
        for (int e = he[v]; e >= 0; e = ne[e]) {
            int i = to[e];
            if (i == p) {
                continue;
            }
            if (i == v) {
                ++cntLoops;
            } else {
                ret ^= 1 + grundy(to[e], v);
            }
        }
        cntLoops >>= 1;
        ret ^= cntLoops & 1;
        return ret;
    }
}
