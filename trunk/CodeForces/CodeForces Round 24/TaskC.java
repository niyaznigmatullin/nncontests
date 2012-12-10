package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long m = in.nextLong();
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        long addX = get(x, 2 * n);
        long addY = get(y, 2 * n);
        addX *= m / (2 * n);
        addY *= m / (2 * n);
        addX += x0;
        addY += y0;
        int z = (int) (m % (2 * n));
        long x2 = get(x, z);
        long y2 = get(y, z);
        if ((z & 1) == 1) {
            x2 -= addX;
            y2 -= addY;
        } else {
            x2 += addX;
            y2 += addY;
        }
        out.println(x2 + " " + y2);
    }

    static long get(int[] x, int m) {
        long ret = 0;
        for (int i = 0; i < m; i++) {
            ret = 2 * x[i % x.length] - ret;
        }
        return ret;
    }
}
