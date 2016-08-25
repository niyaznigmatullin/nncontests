package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;
import sun.plugin2.main.client.MozillaServiceDelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskC {
    static List<Integer>[] edges;
    static int[] down;
    static int[] all;

    static void dfs(int v, int pv) {
        down[v] = 0;
        for (int it = 0; it < edges[v].size(); it++) {
            int to = edges[v].get(it);
            if (to == pv) continue;
            dfs(to, v);
            down[v] = Math.max(down[v], down[to] + 1);
        }
    }

    static void dfs2(int v, int pv, int up) {
        int mx1 = up;
        int who1 = -1;
        int mx2 = 0;
        all[v] = Math.max(up, down[v]);
        for (int it = 0; it < edges[v].size(); it++) {
            int to = edges[v].get(it);
            if (to == pv) continue;
            if (down[to] + 1 > mx1) {
                mx2 = mx1;
                who1 = to;
                mx1 = down[to] + 1;
            } else if (down[to] + 1 > mx2) {
                mx2 = down[to] + 1;
            }
        }
        for (int it = 0; it < edges[v].size(); it++) {
            int to = edges[v].get(it);
            if (to == pv) continue;
            dfs2(to, v, (who1 == to ? mx2 : mx1) + 1);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] deg = new int[n];
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
            deg[v]++;
            deg[u]++;
        }
        for (int i = 0; i < n; i++) {
            if (deg[i] > 3) {
                out.println(-1);
                return;
            }
        }
        down = new int[n];
        dfs(0, -1);
        all = new int[n];
        dfs2(0, -1, 0);
        int best = -1;
        for (int i = 0; i < n; i++) {
            if (deg[i] > 2) continue;
            if (best < 0 || all[i] < all[best]) {
                best = i;
            }
        }
        final int MOD = 1000000007;
        int ans = MathUtils.modPow(2, all[best] + 1, MOD);
        ans -= n + 1;
        if (ans < 0) {
            ans += MOD;
        }
        out.println((best + 1) + " " + ans);
    }
}
