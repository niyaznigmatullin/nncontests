package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {

    static final String ALL = "RPS";
    static int[][] BEAT = {{0, -1, 1}, {1, 0, -1}, {-1, 1, 0}};

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        char[] c = in.next().toCharArray();
        char[] d = in.next().toCharArray();
        int m = c.length;
        int k = d.length;
        int all = (int) MathUtils.lcm(m, k);
        int period1 = 0;
        int period2 = 0;
        for (int i = 0; i < all; i++) {
            int e = BEAT[ALL.indexOf(c[i % m])][ALL.indexOf(d[i % k])];
            if (e > 0) {
                period1++;
            } else if (e < 0) {
                period2++;
            }
        }
        int ans1 = period1 * (n / all);
        int ans2 = period2 * (n / all);
        n %= all;
        for (int i = 0; i < n; i++) {
            int e = BEAT[ALL.indexOf(c[i % m])][ALL.indexOf(d[i % k])];
            if (e > 0) {
                ans1++;
            } else if (e < 0) {
                ans2++;
            }
        }
        out.println(ans2 + " " + ans1);
	}
}
