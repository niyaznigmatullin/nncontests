package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class Indoorienteering {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        len = in.nextLong();
        d = new long[n][];
        for (int i = 0; i < n; i++) {
            d[i] = in.readLongArray(n);
        }
//        int[] p = new int[n];
//        for (int i = 0; i < n; i++) {
//            p[i] = i;
//        }
//        do {
//            long sum = 0;
//            for (int i = 0; i < n; i++) {
//                int j = i + 1;
//                if (j == n) j = 0;
//                sum += d[p[i]][p[j]];
//            }
//            if (sum == len) {
//                out.println("possible");
//                return;
//            }
//        } while (ArrayUtils.nextPermutation(p) && p[0] == 0);
//        out.println("impossible");
        was = new boolean[n];
        if (go(1, n, 0, 0)) {
            out.println("possible");
        } else {
            out.println("impossible");
        }
//        all: for (int i = 900; i < 950; i++) {
//            for (int j = 2; j < i; j++) {
//                if (i % j == 0) continue all;
//            }
//            if (!check(d, (int) (len % i), i)) {
//                out.println("impossible");
//                return;
//            }
//        }
//        out.println("possible");
    }

    static boolean[] was;
    static long len;
    static long[][] d;

    static boolean go(int x, int n, int last, long sum) {
        if (sum > len) {
            return false;
        }
        if (x == n) {
            if (sum + d[last][0] == len) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (was[i]) continue;
            was[i] = true;
            if (go(x + 1, n, i, sum + d[last][i])) {
                return true;
            }
            was[i] = false;
        }
        return false;
    }

    static boolean check(long[][] d_, int f, int mod) {
        int n = d_.length;
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = (int) (d_[i][j] % mod);
            }
        }
        boolean[][][] dp = new boolean[n][mod][1 << n];
        dp[0][0][1] = true;
        for (int mask = 0; mask < 1 << n; mask++) {
            if (((mask - 1) & mask) == 0) {
                continue;
            }
            for (int last = 0; last < n; last++) {
                if (((mask >> last) & 1) == 0) continue;
                for (int prev = 0; prev < n; prev++) {
                    if (((mask >> prev) & 1) == 0 || last == prev) continue;
                    for (int z = 0; z < mod; z++) {
                        if (!dp[prev][z][mask & ~(1 << last)]) {
                            continue;
                        }
                        int nz = (z + d[prev][last]) % mod;
                        dp[last][nz][mask] = true;
                    }
                }
            }
        }
        for (int i = 1; i < n; i++) {
            int nf = (f + mod - d[i][0]) % mod;
            if (dp[i][nf][(1 << n) - 1]) return true;
        }
        return false;
    }
}
