package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class ProblemE {

    static boolean check(int d1, int d2, int d3) {
        long z = d1 + d2;
        return (z * z - 1) % d3 == 0;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        final int MAXN = 10000000;
        boolean[] isPrime = MathUtils.genPrimesBoolean(MAXN);
        int[] primes = MathUtils.genPrimes(MAXN);
        int k = in.nextInt();
        for (int i : primes) {
            {
                int val = i * 2 - 1;
                if (isPrime[val] && check(i, i, val) && check(i, val, i)) {
                    --k;
                    if (k == 0) {
                        out.println(i + " " + i + " " + val);
                        return;
                    }
                }
            }
            {
                int val = i * 2 + 1;
                if (isPrime[val] && check(i, i, val) && check(i, val, i)) {
                    --k;
                    if (k == 0) {
                        out.println(i + " " + i + " " + val);
                        return;
                    }
                }
            }
        }
    }
}
