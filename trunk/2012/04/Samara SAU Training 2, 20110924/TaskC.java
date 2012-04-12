package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskC {
    static int[] t;
    static int n;

    static void init(int n) {
        TaskC.n = Integer.highestOneBit(n) << 1;
        t = new int[TaskC.n << 1];
        Arrays.fill(t, 1);
    }

    static final int MOD = 10007;

    static int getMul(int l, int r) {
        l += n;
        r += n;
        int ret = 1;
        while (l <= r) {
            if ((l & 1) == 1) {
                ret = mul(ret, t[l++]);
            }
            if ((r & 1) == 0) {
                ret = mul(ret, t[r--]);
            }
            l >>= 1;
            r >>= 1;
        }
        return ret;
    }

    static int mul(int a, int b) {
        return a * b % MOD;
    }

    static void set(int x, int y) {
        x += n;
        t[x] = y;
        while (x > 1) {
            x >>= 1;
            t[x] = mul(t[x * 2], t[x * 2 + 1]);
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        init(n);
        for (int i = 0; i < n; i++) {
            set(i, in.nextInt());
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            out.println(getMul(l, r));
        }
	}
}
