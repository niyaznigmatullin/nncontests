package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Help {

    int n;
    int[] l;
    int[] r;

    long[] ans;

    static int[] f = new int[50];
    static int POW = 5;
    static double Z = 1.53;
    static int[] ZP;

    static {
        ZP = new int[51];
        ZP[0] = 0;
        ZP[1] = 1;
        ZP[2] = 2;
        for (int i = 3; i <= 50; i++) {
            ZP[i] = (int) Math.round(ZP[i - 1] * Z);
        }
        for (int i = 0; i < 50; i++) {
            f[i] = 1;
            for (int j = 0; j < POW; j++)
                f[i] = f[i] * (i + 1);
        }
    }

    int cur;

    int dfs(int v, int depth) {
        int ret = 0;
        if (l[v] != -2)
            ret = Math.max(ret, dfs(l[v], depth + 1) + 1);
        if (r[v] != -2)
            ret = Math.max(ret, dfs(r[v], depth + 1) + 1);
        if (l[v] != -2 && r[v] != -2) {
            ++ret;
        }
        ans[v] = 1L << ret;
        return ret;
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        if (n == 0)
            throw new UnknownError();
        l = new int[n];
        r = new int[n];
        boolean[] hasIn = new boolean[n];
        for (int i = 0; i < n; i++) {
            l[i] = in.nextInt() - 1;
            if (l[i] != -2)
                hasIn[l[i]] = true;
            r[i] = in.nextInt() - 1;
            if (r[i] != -2)
                hasIn[r[i]] = true;
        }

        ans = new long[n];
        cur = n - 1;

        for (int i = 0; i < n; i++)
            if (!hasIn[i]) {
                dfs(i, 0);
            }

        for (int i = 0; i < n; i++)
            out.print(ans[i] + " ");
        out.println();

	}
}
