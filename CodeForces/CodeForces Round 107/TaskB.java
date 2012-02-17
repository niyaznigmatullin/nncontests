package mypackage;

import DataStructures.DisjointSetUnion;
import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        for (int i = 0; i + k <= n; i++) {
            for (int j = i, t = j + k - 1; j < t; j++, t--) {
                dsu.union(j, t);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i == dsu.get(i)) {
                ans++;
            }
        }
        out.println(MathUtils.modPow(m, ans, MOD));
	}

    static final int MOD = 1000000007;
}
