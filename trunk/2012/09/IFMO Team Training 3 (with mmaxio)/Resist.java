package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Resist {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int ans1 = 1;
        int ans2 = 1;
        for (int i = 0; i + 1 < n; i++) {
            ans1 = (ans1 + ans2);
            if (ans1 >= m) {
                ans1 -= m;
            }
            ans2 = (ans1 + ans2);
            if (ans2 >= m) {
                ans2 -= m;
            }
        }
        out.println(ans1 + "/" + ans2);
	}
}
