package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskD {

    static List<Integer>[] edges;
    static int[] a;
    static final int MOD = 1000000007;

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int dfs(int v, int p, int minA, int maxA, int root) {
        if (a[v] < minA || a[v] > maxA || a[v] == minA && v < root) return 1;
        int ret = 1;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            if (to == p) continue;
            ret = mul(ret, dfs(to, v, minA, maxA, root));
        }
        if (p >= 0) {
            ret = (ret + 1) % MOD;
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int d = in.nextInt();
        int n = in.nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        for (int i = 0; i + 1 < n; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            edges[v].add(u);
            edges[u].add(v);
        }
        int ways = 0;
        for (int i = 0; i < n; i++) {
            ways = (ways + dfs(i, -1, a[i], a[i] + d, i)) % MOD;
        }
        out.println(ways);
    }
}
