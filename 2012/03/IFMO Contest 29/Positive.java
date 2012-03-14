package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Positive {

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] min = new int[n + 1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += a[i - 1];
            min[i] = Math.min(min[i - 1], sum);
        }
        sum = 0;
        int[] max = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            sum += a[i];
            max[i] = Math.max(max[i + 1], sum);
        }
        int ans = 0;
        for (int i = 0; i < n; sum -= a[i], i++) {
            if (sum + min[i] > 0 && sum - max[i + 1] > 0) {
                ans++;
            }
        }
        out.println(ans);
	}
}
