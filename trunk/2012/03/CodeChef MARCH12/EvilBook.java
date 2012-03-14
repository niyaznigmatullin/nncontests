package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;
import sun.print.PSPrinterJob;

public class EvilBook {

    static final double EPS = 1e-8;

    static class Man {
        double c;
        double m;
        int castsBefore;
        double mAfterCast;
        double cAfterCast;

        Man(double c, double m, int magicalPower) {
            this.c = c;
            this.m = m;
            cAfterCast = c;
            mAfterCast = m;
            while (compare(cAfterCast, magicalPower) > 0) {
                cAfterCast /= 3;
                mAfterCast /= 3;
                castsBefore++;
            }
        }

    }

    static int compare(double a, double b) {
        return Math.abs(a - b) < EPS ? 0 : Double.compare(a, b);
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int magicalPower = in.nextInt();
        Man[] a = new Man[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Man(in.nextInt(), in.nextInt(), magicalPower);
        }
        Man[] sorted = a.clone();
        int z = MathUtils.powerStupid(3, n);
        for (int i = 0; i < z; i++) {

        }
	}
}
