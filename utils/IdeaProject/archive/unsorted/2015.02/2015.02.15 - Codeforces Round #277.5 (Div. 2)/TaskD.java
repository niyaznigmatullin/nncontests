package coding;

import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        int[][] edges = GraphUtils.getEdgesDirectedUnweighted(n, from, to);
        int ans = 0;
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j : edges[i]) {
                for (int k : edges[j]) {
                    if (k != i) {
                        ans += a[i][k];
                        a[i][k]++;
                    }
                }
            }
        }
        out.println(ans);
    }
}
