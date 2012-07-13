package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {
    static int[] a;
    static int[] b;
    static int[] k;
    static int[] p;
    static int[] tmp;
    static int n;
    static int add;

    static long go(int last, int left) {
        long ret = Long.MIN_VALUE;
        if ((left & 1) == 0) {
            long got = 0;
            for (int i = 0; i < n; i++) {
                got += (long) a[i] * k[i];
            }
            ret = Math.max(ret, got);
        }
        if (left == 0) {
            return ret;
        }
        if (last != 0) {
            for (int i = 0; i < n; i++) {
                a[i] ^= b[i];
            }
            ret = Math.max(ret, go(0, left - 1));
            for (int i = 0; i < n; i++) {
                a[i] ^= b[i];
            }
        }
        for (int i = 0; i < n; i++) {
            tmp[i] = a[p[i]] + add;
        }
        for (int i = 0; i < n; i++) {
            a[i] = tmp[i];
        }
        ret = Math.max(ret, go(1, left - 1));
        for (int i = 0; i < n; i++) {
            tmp[p[i]] = a[i] - add;
        }
        for (int i = 0; i < n; i++) {
            a[i] = tmp[i];
        }
        return ret;
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        int m = in.nextInt();
        add = in.nextInt();
        a = new int[n];
        b = new int[n];
        k = new int[n];
        p = new int[n];
        tmp = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            k[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt() - 1;
        }
        out.println(go(1, m));
	}
}
