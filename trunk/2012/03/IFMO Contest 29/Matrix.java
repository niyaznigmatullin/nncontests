package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Matrix {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] deg = new int[n];
        for (int i = 0; i < 2 * m; i++) {
            ++deg[in.nextInt() - 1];
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) deg[i] * deg[i];
        }
        out.println(ans);
	}
}
