package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int groups = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        int[][] a = new int[groups][groups];
        for (int i = 0; i < groups; i++) {
            Arrays.fill(a[i], Integer.MAX_VALUE);
            a[i][i] = 0;
        }
        int[] type = new int[n];
        for (int i = 0, j = 0; i < groups; i++) {
            int cn = in.nextInt();
            for (int k = 0; k < cn; k++) {
                type[j++] = i;
            }
        }
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            int w = in.nextInt();
            if (w == 0) {
                dsu.union(v, u);
            }
            int from = type[v];
            int to = type[u];
            a[to][from] = a[from][to] = Math.min(a[from][to], w);
        }
        for (int i = 0; i + 1 < n; i++) {
            if (type[i] == type[i + 1] && dsu.get(i) != dsu.get(i + 1)) {
                out.println("No");
                return;
            }
        }
        for (int k = 0; k < groups; k++) {
            for (int i = 0; i < groups; i++) {
                for (int j = 0; j < groups; j++) {
                    if (a[i][k] != Integer.MAX_VALUE && a[k][j] != Integer.MAX_VALUE) {
                        a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                    }
                }
            }
        }
        out.println("Yes");
        for (int i = 0; i < groups; i++) {
            for (int j = 0; j < groups; j++) {
                if (j > 0) out.print(' ');
                int ans = a[i][j];
                if (ans == Integer.MAX_VALUE) ans = -1;
                out.print(ans);
            }
            out.println();
        }
    }
}
