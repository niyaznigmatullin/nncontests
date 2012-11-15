package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        if (n == 0) {
            throw new UnknownError();
        }
        long l = 0;
        long r = Integer.MAX_VALUE;
        while (l < r - 1) {
            long m = (l + r) >> 1;
            if (m * (m + 1) <= 2 * n) {
                l = m;
            } else {
                r = m;
            }
        }
        out.println(r + " " + (r * (r + 1) / 2 - n));
	}
}
