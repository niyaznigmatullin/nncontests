package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskE {

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int two = 0;
        int one = 0;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            if (s.indexOf("B") < 0) {
                continue;
            }
            if (s.indexOf("R") < 0) {
                ++two;
            } else {
                ++one;
            }
        }
        int num = two * 2;
        int den = two * 2 + one;
        int g = MathUtils.gcd(num, den);
        num /= g;
        den /= g;
        out.println(num + "/" + den);
	}
}
