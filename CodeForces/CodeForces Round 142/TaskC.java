package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] deg = new int[n];
        for (int i = 0; i < 2 * m; i++) {
            deg[in.nextInt() - 1]++;
        }
        long c1 = 0;
        long c2 = 0;
        for (int i : deg) {
            c1 += (long) i * (i - 1) / 2;
            c2 += (long)  i * i;
        }
//        System.err.println(c1 + " " + c2);
        long all = (long) n * (n - 1) * (n - 2) / 6;
        out.println(all - ((long) m * n + c1 - c2));
	}
}
