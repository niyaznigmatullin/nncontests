package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskD {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += in.nextDouble();
        }
        out.println(1. * sum / n);
	}
}
