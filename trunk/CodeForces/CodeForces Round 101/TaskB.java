package mypackage;

import niyazio.FastScanner;
import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int a = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        if (y % a == 0) {
            out.println(-1);
            return;
        }
        int v = y / a;
        if (v == 0 || v % 2 == 1) {
            x *= 2;
            if (x > -a && x < a) {
                out.println(getFor(v) + 1);
            } else {
                out.println(-1);
            }
        } else {
            if (x == 0 || x <= -a || x >= a) {
                out.println(-1);
            } else if (x < 0) {
                out.println(getFor(v) + 1);
            } else {
                out.println(getFor(v) + 2);
            }
        }
	}

    static int getFor(int v) {
        int ret = 0;
        for (int i = 0; i < v; i++) {
            ret += (i == 0 || (i & 1) == 1) ? 1 : 2;
        }
        return ret;
    }
}
