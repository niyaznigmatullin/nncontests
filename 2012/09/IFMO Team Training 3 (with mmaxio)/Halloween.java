package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class Halloween {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int mod = in.nextInt();
        int n = in.nextInt();
        if (mod == 0 && n == 0)
            throw new UnknownError();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt() % mod;
        int[] pos = new int[mod];
        Arrays.fill(pos, -1);
        pos[0] = 0;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += a[i];
            if (sum >= mod)
                sum -= mod;
            if (pos[sum] == -1) {
                pos[sum] = i + 1;
            } else {

                //System.err.println(i + " " + sum + " " + Arrays.toString(pos));

                for (int j = pos[sum]; j <= i; j++) {
                    out.print((j + 1) + " ");
                }
                out.println();
                return;
            }
        }
        throw new AssertionError();
	}
}
