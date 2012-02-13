package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Task4 {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        int MOD = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int p = MathUtils.modPow(n, k, MOD - 1);
            out.println(MathUtils.modPow(3, p, MOD));
        }
	}
}
