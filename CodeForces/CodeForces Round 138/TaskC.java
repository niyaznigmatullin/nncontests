package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;

public class TaskC {

    static final int MOD = 1000000007;
    static final int[] inv;
    static {
        inv = new int[10000];
        for (int i = 1; i < inv.length; i++) {
            inv[i] = MathUtils.modPow(i, MOD - 2, MOD);
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt() - 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = c(k + i, i);
        }
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int current = 0;
            for (int j = 0; j <= i; j++) {
                current += modMul(c[j], a[i - j], MOD);
                if (current >= MOD) {
                    current -= MOD;
                }
            }
            answer[i] = current;
        }
        out.printArray(answer);
	}

    static int c(int n, int k) {
        int ret = 1;
        for (int i = 0; i < k; i++) {
            ret = modMul(ret, (n - i) % MOD, MOD);
            ret = modMul(ret, inv[i + 1], MOD);
        }
        return ret;
    }

    static int modMul(int a, int b, int mod) {
        return (int) ((long) a * b % mod);
    }
}
