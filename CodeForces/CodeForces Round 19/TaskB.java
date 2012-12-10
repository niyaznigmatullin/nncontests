package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskB {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[] cost = new long[n + 1];
        Arrays.fill(cost, Long.MAX_VALUE);
        cost[0] = 0;
        for (int i = 0; i < n; i++) {
            int t = in.nextInt() + 1;
            int c = in.nextInt();
            for (int j = n; j >= 0; j--) {
                long val = cost[j];
                if (val == Long.MAX_VALUE) {
                    continue;
                }
                int k = Math.min(j + t, n);
                cost[k] = Math.min(cost[k], val + c);
            }
        }
        out.println(cost[n]);
	}
}
