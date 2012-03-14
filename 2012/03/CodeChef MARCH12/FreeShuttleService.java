package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class FreeShuttleService {

    static int[] phi;
    static final int MAXN = 10001;
    static {
        phi = new int[MAXN];
        for (int i = 2; i < MAXN; i++) {
            phi[i] = i;
        }
        for (int i = 2; i < MAXN; i++) {
            if (phi[i] == i) {
                for (int j = i; j < MAXN; j += i) {
                    phi[j] -= phi[j] / i;
                }
            }
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.println(phi[in.nextInt()]);
	}
}
