package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Task2755 {

    static long[] DP = new long[31];
    static {
        DP[0] = 1;
        DP[1] = 1;
        for (int i = 2; i < DP.length; i++) {
            for (int j = 0; j < i; j++) {
                DP[i] += DP[i - j - 1] * DP[j];
            }
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n == 0) {
            throw new UnknownError();
        }
        out.println(DP[n]);
	}
}
