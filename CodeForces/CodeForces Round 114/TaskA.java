package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int d = in.nextInt();
        double[] answer = new double[n];
        double last = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            int start = in.nextInt();
            int velocity = in.nextInt();
            last = answer[i] = Math.max(last, start + getTime(velocity, a, d));
        }
        for (double e : answer) {
            out.println(e);
        }
	}

    static double getTime(double v, double a, double s) {
        double time = v / a;
        double acc = a * time * time * .5;
        if (acc >= s) {
            return Math.sqrt(2 * s / a);
        } else {
            return time + (s - acc) / v;
        }
    }
}
