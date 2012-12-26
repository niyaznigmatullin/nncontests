package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskD {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        long s = 1;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            s *= a[i];
        }
        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer += s / a[i];
        }
        out.println(answer * 2);
	}
}
