package lib.test.on2013_03.on2013_03_15_Game_Theory_Lab_1.Smith;



import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Smith {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        int[][] ed = GraphUtils.getEdgesDirectedUnweighted(n, from, to);
        int[][] rev = GraphUtils.getEdgesDirectedUnweighted(n, to, from);
        int[] g = new int[n];
        Arrays.fill(g, -1);
        int[] set = new int[n];
        int cur = 0;
        boolean[][] has = new boolean[n][n];
        all:
        while (true) {
            for (int i = 0; i < n; i++) {
                if (g[i] >= 0) {
                    continue;
                }
                ++cur;
                for (int v : ed[i]) {
                    if (g[v] >= 0) {
                        set[g[v]] = cur;
                    }
                }
                int best;
                for (int j = 0; ; j++) {
                    if (set[j] != cur) {
                        best = j;
                        break;
                    }
                }
                boolean ok = true;
                for (int v : ed[i]) {
                    if (g[v] < 0) {
                        if (!has[v][best]) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    g[i] = best;
                    for (int v : rev[i]) {
                        has[v][best] = true;
                    }
                    continue all;
                }
            }
            break;
        }
        for (int i = 0; i < n; i++) {
            out.print(g[i]);
            if (g[i] < 0) {
                int cn = 0;
                for (int j = 0; j < n; j++) {
                    if (has[i][j]) {
                        ++cn;
                    }
                }
                out.print(" " + cn);
                for (int j = 0; j < n; j++) {
                    if (has[i][j]) {
                        out.print(" " + j);
                    }
                }
            }
            out.println();
        }
    }
}
