package coding;

import com.sun.media.sound.MidiOutDeviceProvider;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkiResortPlanning {
    List<Integer>[] edges;
    static final int MOD = 1000000007;

    int dfs(int v, int s) {
        if (v >= s) return 0;
        int ret = 1;
        for (int i = 0; i < edges[v].size(); i++) {
            int to = edges[v].get(i);
            ret += dfs(to, s);
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        System.err.println(testNumber);
        int n = in.nextInt();
        edges = new List[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int x = in.nextInt();
            p[i] = x;
            edges[x].add(i);
        }
        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = pow2[i - 1] * 2 % MOD;
        }
        long ans = 1;
        for (int i = 1; i < n; i++) {
            int all = 0;
            long bad = 1;
            for (int j : edges[p[i]]) {
                int cc = dfs(j, i);
                bad = (bad + pow2[cc] - 1) % MOD;
                all += cc;
            }
            long cur = ((2 * pow2[all] - bad) % MOD + MOD) % MOD;
            ans = (ans * cur) % MOD;
        }
        out.println((ans % MOD + MOD) % MOD);
    }
}
