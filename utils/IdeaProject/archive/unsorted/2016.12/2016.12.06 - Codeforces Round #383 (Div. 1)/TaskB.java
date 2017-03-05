package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int w = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] b = in.readIntArray(n);
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            dsu.union(v, u);
        }
        int[] dp = new int[w + 1];
        int[] vs = new int[n];
        for (int root = 0; root < n; root++) {
            if (dsu.get(root) != root) continue;
            int cn = 0;
            for (int j = 0; j < n; j++) {
                if (dsu.get(j) == root) vs[cn++] = j;
            }
            int[] next = dp.clone();
            int sumA = 0;
            int sumB = 0;
            for (int it = 0; it < cn; it++) {
                int i = vs[it];
                int curA = a[i];
                int curB = b[i];
                sumA += curA;
                sumB += curB;
                for (int cw = w; cw >= curA; cw--) {
                    next[cw] = Math.max(next[cw], dp[cw - curA] + curB);
                }
            }
            for (int cw = w; cw >= sumA; cw--) {
                next[cw] = Math.max(next[cw], dp[cw - sumA] + sumB);
            }
            dp = next;
        }
        out.println(dp[w]);
    }

}
