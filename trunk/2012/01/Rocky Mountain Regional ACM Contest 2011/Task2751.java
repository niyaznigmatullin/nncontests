package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Task2751 {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n == 0) {
            throw new UnknownError();
        }
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.print("Case " + testNumber + ": ");
        for (int it = 0; it <= 1000; it++) {
            boolean ok = true;
            for (int i = 1; i < n; i++) {
                if (a[i] != a[i - 1]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                out.println(it + " iterations");
                return;
            }
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = Math.abs(a[i] - a[(i + 1) % n]);
            }
            a = b;
        }
        out.println("not attained");
	}
}
