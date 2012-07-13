package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class ProblemA {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = 12;
        int[] a = new int[n];
        boolean isZero = true;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            isZero &= a[i] == 0;
        }
        if (isZero) {
            throw new UnknownError();
        }
        Arrays.sort(a);
        out.println(a[0] == a[3] && a[4] == a[7] && a[8] == a[11] ? "yes" : "no");
	}
}
