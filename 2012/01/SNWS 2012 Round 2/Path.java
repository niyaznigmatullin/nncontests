package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Path {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] left = new int[n];
        int[] right = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            left[i] = in.nextInt() - 1;
            right[i] = in.nextInt() - 1;
            ans += right[i] - left[i];
        }
        int dp1 = left[0];
        int dp2 = right[0];
        for (int i = 1; i < n; i++) {
            int new1 = 1 + Math.min(dp1 + Math.abs(right[i - 1] - left[i]), dp2 + Math.abs(left[i - 1] - left[i]));
            int new2 = 1 + Math.min(dp1 + Math.abs(right[i - 1] - right[i]), dp2 + Math.abs(left[i - 1] - right[i]));
            dp1 = new1;
            dp2 = new2;
        }
        out.println(ans + Math.min(dp1 + n - 1 - right[n - 1], dp2 + n - 1 - left[n - 1]));
	}
}
