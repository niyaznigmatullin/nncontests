package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;

public class TaskF {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int ph = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        out.println(choose(m, a).multiply(choose(ph, b)));
	}

    static BigInteger choose(int n, int k) {
        BigInteger ans = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            ans = ans.multiply(BigInteger.valueOf(n - i)).divide(BigInteger.valueOf(i + 1));
        }
        return ans;
    }
}
