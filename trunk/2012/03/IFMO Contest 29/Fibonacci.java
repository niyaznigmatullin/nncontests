package mypackage;

import math.Factor;
import math.MathUtils;
import math.Matrix;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Fibonacci {

    static final long SHIFT = 1000000000L * 1000000000;
    static Matrix fibMatrix = new Matrix(new int[][]{{0, 1}, {1, 1}});

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n <= 20) {
            out.println(stupid(n));
            return;
        }
        Factor[] f = MathUtils.factorize(n);
        long[] pi = new long[f.length];
        for (int i = 0; i < f.length; i++) {
            int prime = (int) f[i].prime;
            pi[i] = getPizzano(prime);
        }
        long ans = 1;
        for (int i = 0; i < f.length; i++) {
            long z = 1;
            for (int j = 1; j < f[i].pow; j++) {
                z *= f[i].prime;
            }
            z *= pi[i];
            ans = MathUtils.lcm(ans, z);
        }
        out.println(ans);
	}

    static int stupid(int n) {
        int[][] was = new int[n][n];
        int f1 = 0;
        int f2 = 1 % n;
        was[f1][f2] = 1;
        int q = 1;
        while (true) {
            ++q;
            int f3 = (f1 + f2) % n;
            f1 = f2;
            f2 = f3;
            if (was[f1][f2] > 0) {
                return q - was[f1][f2];
            }
            was[f1][f2] = q;
        }
    }

    static long getPizzano(int p) {
        if (p <= 20) {
            return stupid(p);
        }
        int mod = p % 5;
        mod = (mod * mod) % 5;
        if (mod == 1) {
            return getBest(p, p - 1);
        } else {
            return getBest(p, 2L * p + 2);
        }
    }

    static long getBest(int mod, long d) {
        long answer = Long.MAX_VALUE;
        for (long i = 1; i * i <= d; i++) {
            if (d % i == 0) {
                if (check(mod, i)) {
                    answer = Math.min(answer, i);
                }
                if (check(mod, d / i)) {
                    answer = Math.min(answer, d / i);
                }
            }
        }
        if (answer == Long.MAX_VALUE) {
            throw new AssertionError();
        }
        return answer;
    }


    static boolean check(int mod, long period) {
        return getFibonacci(SHIFT, mod) == getFibonacci(SHIFT + period, mod) && getFibonacci(SHIFT + 1, mod) == getFibonacci(1 + SHIFT + period, mod);
    }

    static int getFibonacci(long n, int mod) {
        return Matrix.powMod(fibMatrix, n, mod).get(0, 0);
    }
}
