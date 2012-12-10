package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[] x = new long[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt() * 2;
        }
        ArrayUtils.sort(x);
        long l = -1;
        long r = x[n - 1];
        ans = new long[3];
        while (l < r - 1) {
            long mid = l + r >> 1;
            if (check(mid, x)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        long d = r;
        check(d, x);
        out.println(d * .5);
        out.println(ans[0] * .5 + " " + ans[1] * .5 + " " + ans[2] * .5);
	}

    static boolean check(long d, long[] x) {
        long last = Long.MIN_VALUE;
        int count = 0;
        for (long i : x) {
            if (i > last) {
                if (count == 3) {
                    return false;
                }
                ans[count++] = i + d;
                last = 2 * d + i;
            }
        }
        while (count < 3) {
            long cur = ans[count - 1];
            ans[count++] = cur;
        }
        return true;
    }

    static long[] ans;
}
