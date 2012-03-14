package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class CosinePartitionFunction {

    static double[] FACT;
    static int MAXF = 40;
    static {
        FACT = new double[MAXF];
        FACT[0] = 1;
        for (int i = 1; i < MAXF; i++) {
            FACT[i] = i * FACT[i - 1];
        }
    }
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        double x = in.nextDouble();
        p = new int[Math.min(m, n)];
        double answer = go(0, n, n, m, x, n);
        out.println(answer);
	}

    static int[] p;

    static double go(int current, int n, int last, int m, double x, int all) {
        if (n == 0) {
            return getIt(m, x, p, current, all);
        }
        if (current == m) {
            return 0;
        }
        double ret = 0;
        for (int i = Math.min(last, n); i > 0; i--) {
            p[current] = i;
            ret += go(current + 1, n - i, i, m, x, all);
        }
        return ret;
    }

    static double getIt(int m, double x, int[] p, int count, int n) {
        double choose = choose(m, count);
        for (int i = 0; i < count;) {
            int j = i;
            while (j < count && p[i] == p[j]) {
                j++;
            }
            choose /= FACT[j - i];
            i = j;
        }
        double product = 1.;
        for (int i = 0; i < count; i++) {
            product *= Math.cos(p[i] * x / n);
        }
        return product * choose;
    }

    static double choose(int n, int k) {
        double ret = 1;
        for (int i = 0; i < k; i++) {
            ret *= n - i;
        }
        return ret;
    }
}
