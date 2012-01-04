package mypackage;

import niyazio.FastScanner;
import java.io.PrintWriter;

public class TaskA {
	public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        int R = in.nextInt();
        int r = in.nextInt();
        R -= r;
        if (n == 1) {
            out.println(R >= 0 ? "YES" : "NO");
            return;
        }
        if (R < r) {
            out.println("NO");
            return;
        }
        double angle = Math.asin(1. * r / R) * n;
        if (angle > Math.PI + 1e-7) {
            out.println("NO");
        } else {
            out.println("YES");
        }
	}
}
