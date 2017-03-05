package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.graphalgorithms.HungarianAlgorithm;
import ru.ifmo.niyaz.graphalgorithms.MinCostMaxFlowGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Salesman {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(n);
        long[][] f = new long[n][n];
        for (long[] e : f) Arrays.fill(e, Long.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cost = in.nextInt();
            f[from][to] = Math.min(f[from][to], cost);
        }
        for (int k = 0; k < n; k++) {
            long[] fk = f[k];
            for (int i = 0; i < n; i++) {
                long[] fi = f[i];
                for (int j = 0; j < n; j++) {
                    if (fi[k] != Long.MAX_VALUE && fk[j] != Long.MAX_VALUE) {
                        fi[j] = Math.min(fi[j], fi[k] + fk[j]);
                    }
                }
            }
        }
        final long INF = 1L << 50;
        long[][] b = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    b[i][j] = a[i];
                } else if (f[i][j] != Long.MAX_VALUE) {
                    b[i][j] = f[i][j];
                } else {
                    b[i][j] = INF;
                }
            }
        }
        ArrayUtils.shuffle(b);
        int[] p = HungarianAlgorithm.getMatching(b);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += b[i][p[i]];
        }
        out.println(ans);
    }
}
