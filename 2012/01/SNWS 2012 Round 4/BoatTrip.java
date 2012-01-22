package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class BoatTrip {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] deg = new int[n];
        int[] a = new int[n - 1];
        int[] b = new int[n - 1];
        int[] c = new int[n - 1];
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            a[i] = in.nextInt() - 1;
            b[i] = in.nextInt() - 1;
            c[i] = in.nextInt();
            deg[a[i]]++;
            deg[b[i]]++;
            ans += 2 * c[i];
        }
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            if (deg[a[i]] == 1 || deg[b[i]] == 1) {
                max = Math.max(max, c[i]);
            }
        }
        out.println(ans - 2 * max);
	}
}
