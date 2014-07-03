package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Path {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        int[] from = new int[m];
        int[] to = new int[m];
        long[] w = new long[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
            w[i] = in.nextLong();
        }
        long[] d = new long[n];
        Arrays.fill(d, Long.MAX_VALUE);
        d[s] = 0;
        for (int it = 0; it < n - 1; it++) {
            for (int e = 0; e < m; e++) {
                if (d[from[e]] == Long.MAX_VALUE) continue;
                if (d[to[e]] > d[from[e]] + w[e]) {
                    d[to[e]] = d[from[e]] + w[e];
                }
            }
        }
        boolean[] was = new boolean[n];
        int[] q = new int[n];
        int head = 0;
        int tail = 0;
        for (int e = 0; e < m; e++) {
            if (d[from[e]] == Long.MAX_VALUE) continue;
            if (d[to[e]] > d[from[e]] + w[e] && !was[to[e]]) {
                q[tail++] = to[e];
                was[to[e]] = true;
            }
        }
        while (head < tail) {
            int v = q[head++];
            for (int e = 0; e < m; e++) {
                if (from[e] != v) continue;
                if (!was[to[e]]) {
                    was[to[e]] = true;
                    q[tail++] = to[e];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (was[i]) out.println("-"); else
                if (d[i] == Long.MAX_VALUE) out.println("*"); else
                    out.println(d[i]);
        }
    }
}
