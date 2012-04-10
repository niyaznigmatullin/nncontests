package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n == 1) {
            out.println(1);
            return;
        }
        long ans = 1;
        for (int i = 2; i <= n; i++) {
            ans += 12 * (i - 1);
        }
        out.println(ans);
	}
}
