package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskF {
    static List<Integer>[] edges;
    static int[] parent;

    static void dfs(int v, int p) {
        parent[v] = p;
        for (int i : edges[v]) {
            if (i == p) continue;
            dfs(i, v);
        }
    }

    static int[] color;
    static boolean[] inTree;

    static int lca(int v, int c) {
        color[v] = c;
        while (v >= 0 && !inTree[v]) {
            inTree[v] = true;
            v = parent[v];
        }
        if (v < 0 || color[v] >= 0) {
            for (int i = 0; i < color.length; i++) {
                if (inTree[i] && color[i] < 0) {
                    return i;
                }
            }
            for (int i = 0; i < color.length; i++) {
                if (color[i] < 0 && color[parent[i]] >= 0) {
                    return i;
                }
            }
            return -1;
        } else {
            return v;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        color = new int[n];
        parent = new int[n];
        dfs(0, -1);
        Arrays.fill(color, -1);
        inTree = new boolean[n];
        out.println("1 1");
        lca(0, 0);
        out.flush();
        while (true) {
            int v = in.nextInt() - 1;
            int c = in.nextInt() - 1;
            if (v < 0 && c < 0) break;
            int u = lca(v, c);
            int mask = 0;
            for (int i : edges[u]) {
                if (color[i] >= 0) mask |= 1 << color[i];
            }
            int paint = -1;
            for (int j = 0; j < 4; j++) {
                if (((mask >> j) & 1) == 0) {
                    paint = j;
                    break;
                }
            }
            lca(u, paint);
            out.println((u + 1) + " " + (paint + 1));
            out.flush();
        }
    }
}
