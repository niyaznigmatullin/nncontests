package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {

    static final int MOD = 1000000007;


    static int solve(int n) {
        int ans = 9;
        n = (n + 1) / 2 - 1;
        for (int i = 0; i < n; i++) {
            ans = (int) ((long) ans * 10 % MOD);
        }
        return ans;
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int ans = 0;
        while (n > 0) {
            ans += solve(n);
            n--;
            if (ans >= MOD) {
                ans -= MOD;
            }
        }
        out.println(ans);
	}
}
