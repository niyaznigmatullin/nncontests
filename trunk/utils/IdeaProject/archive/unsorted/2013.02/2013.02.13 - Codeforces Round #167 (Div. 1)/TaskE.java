package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TaskE {

    static final int MOD = 1000000007;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        NavigableMap<Integer, Integer> g = new TreeMap<Integer, Integer>();
        g.put(1, 0);
        g.put(3, 1);
        int lastG = 1;
        for (int d = 3; d < p - 1; ) {
            int a = d / 3;
            int b = d - d / 3;
            Integer na = g.higherKey(a);
            Integer nb = g.higherKey(b);
            long z1 = na * 3L;
            long z2 = 3L * nb / 2;
            while (z2 - z2 / 3 >= nb) {
                z2--;
            }
            while (z2 - z2 / 3 < nb) {
                ++z2;
            }
            long z = Math.min(z1, z2);
            if (z > p - 1) {
                break;
            }
            int zi = (int) z;
            int ga = g.get(g.floorKey(zi / 3));
            int gb = g.get(g.floorKey(zi - zi / 3));
            int ng = -1;
            for (int j = 0; j < 3; j++) {
                if (j == ga || j == gb) {
                    continue;
                }
                ng = j;
                break;
            }
            if (ng != lastG) {
                g.put(zi, ng);
            }
            lastG = ng;
            d = zi;
        }
        long[] count = new long[3];
        for (int i : g.keySet()) {
            Integer ni = g.higherKey(i);
            if (ni == null) {
                ni = p;
            }
            int first = p - i;
            int second = p - ni;
            count[g.get(i)] += s(second + 1, first + 1);
        }
        for (int i = 0; i < 3; i++) {
            count[i] %= MOD;
        }
        int[][] dp = new int[n + 1][4];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 4; y++) {
                    dp[i][x ^ y] = (int) ((dp[i][x ^ y] + (long) dp[i - 1][y] * count[x]) % MOD);
                }
            }
        }
        long ans = 0L + dp[n][1] + dp[n][2] + dp[n][3];
        out.println(ans % MOD);
    }

    static long s(int a, int b) {
        if (a >= b) {
            return 0;
        }
        --b;
        --a;
        long ret = ss(b) - ss(a);
        return ret;
    }

    static long ss(int n) {
        return (long) n * (n + 1) / 2;
    }
}
