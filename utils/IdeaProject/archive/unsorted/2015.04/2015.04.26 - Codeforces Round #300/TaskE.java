package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
    static List<Integer>[] edges;
    static int[] size;
    static int[] dpMax;
    static int[] dpMin;

    void dfs(int v, int p, int level) {
        size[v] = 0;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (to == p) continue;
            dfs(to, v, level ^ 1);
            size[v] += size[to];
        }
        if (size[v] == 0) {
            ++size[v];
            return;
        }
        if (level == 0) {
            int mx = Integer.MAX_VALUE;
            int mn = 0;
            for (int i = 0; i < edges[v].size(); i++) {
                int to = edges[v].get(i);
                if (to == p) continue;
                mx = Math.min(mx, size[to] - dpMax[to]);
                mn += size[to] - dpMin[to] - 1;
            }
            dpMax[v] = size[v] - mx;
            dpMin[v] = size[v] - mn - 1;
        } else {
            int mx = 0;
            int mn = Integer.MAX_VALUE;
            for (int i = 0; i < edges[v].size(); i++) {
                int to = edges[v].get(i);
                if (to == p) continue;
                mx += dpMax[to];
                mn = Math.min(mn, dpMin[to]);
            }
            dpMax[v] = mx;
            dpMin[v] = mn;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        dpMax = new int[n];
        dpMin = new int[n];
        size = new int[n];
        dfs(0, -1, 0);
        out.println((dpMax[0] + 1) + " " + (dpMin[0] + 1));
    }
}
