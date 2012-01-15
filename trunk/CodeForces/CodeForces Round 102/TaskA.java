package mypackage;

import niyazio.FastScanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskA {

    static long ansMin;
    static long ansMax;

	public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        ansMax = Long.MIN_VALUE;
        ansMin = Long.MAX_VALUE;
        List<Integer> divs = new ArrayList<Integer>();
        for (long ca = 1; ca * ca <= n; ca++) {
            if (n % ca != 0) {
                continue;
            }
            divs.add((int) ca);
            if (ca * ca != n) {
                divs.add(n / (int) ca);
            }
        }
        for (long a : divs) {
            for (long b : divs) {
                if (n % (a * b) != 0) {
                    continue;
                }
                long c = n / a / b;
                long volume = (a + 1) * (b + 2) * (c + 2);
                ansMin = Math.min(ansMin, volume);
                ansMax = Math.max(ansMax, volume);
            }
        }
        out.println((ansMin - n) + " " + (ansMax - n));
	}

}
