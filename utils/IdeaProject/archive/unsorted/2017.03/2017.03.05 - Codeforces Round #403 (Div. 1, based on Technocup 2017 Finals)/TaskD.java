package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.BitSet;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        final int BITS = 64;
        BitSet[][][] can = new BitSet[2][BITS][];
        can[0][0] = new BitSet[n];
        can[1][0] = new BitSet[n];
        for (int i = 0; i < n; i++) {
            can[0][0][i] = new BitSet(n);
            can[1][0][i] = new BitSet(n);
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int type = in.nextInt();
            can[type][0][from].set(to);
        }
        for (int i = 1; i < BITS; i++) {
            for (int type = 0; type < 2; type++) {
                can[type][i] = merge(n, can[type][i - 1], can[type ^ 1][i - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (can[0][BITS - 1][i].get(j)) {
                    out.println("-1");
                    return;
                }
            }
        }
        long[][] val = new long[2][n];
        boolean[][] was = new boolean[2][n];
        for (int i = 0; i < BITS; i++) {
            for (int v = 0; v < n; v++) {
                for (int type = 0; type < 2; type++) {
                    if (was[type][v]) continue;
                    if (can[type][i][v].nextSetBit(0) >= 0) continue;
                    was[type][v] = true;
                    if (i == 0) {
                        continue;
                    }
                    long best = 0;
                    BitSet z = can[type][i - 1][v];
                    for (int u = z.nextSetBit(0); u >= 0; u = z.nextSetBit(u + 1)) {
                        best = Math.max(best, val[type ^ 1][u]);
                    }
                    val[type][v] = (1L << (i - 1)) + best;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 1; i++) ans = Math.max(ans, val[0][i]);
        if (ans > 1000000000000000000L) out.println(-1);
        else
            out.println(ans);
    }

    static BitSet[] merge(int n, BitSet[] a, BitSet[] bb) {
        BitSet[] b = new BitSet[n];
        for (int i = 0; i < n; i++) {
            b[i] = new BitSet(n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bb[i].get(j)) {
                    b[j].set(i);
                }
            }
        }
        BitSet[] ret = new BitSet[n];
        for (int i = 0; i < n; i++) {
            ret[i] = new BitSet(n);
        }
        BitSet tmp = new BitSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp.or(a[i]);
                tmp.and(b[j]);
                if (tmp.nextSetBit(0) >= 0) {
                    ret[i].set(j);
                }
            }
            tmp.clear();
        }
        return ret;
    }
}
