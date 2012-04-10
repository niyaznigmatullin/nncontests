package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CielNum1 {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long[] answer = new long[50000];
        int count = 0;
        long[] q = new long[1000000];
        int head = 0;
        int tail = 0;
        q[tail++] = 3;
        q[tail++] = 5;
        q[tail++] = 8;
        while (count < 50000) {
            long v = q[head++];
            q[tail++] = (v * 10 + 3);
            q[tail++] = (v * 10 + 5);
            q[tail++] = (v * 10 + 8);
            if (good(v)) {
                answer[count++] = v;
            }
        }
        for (long e : answer) {
            out.println(e);
        }
	}

    static boolean good(long v) {
        int c3 = 0;
        int c5 = 0;
        int c8 = 0;
        while (v > 0) {
            int d = (int) (v % 10);
            if (d == 3) {
                c3++;
            } else if (d == 5) {
                c5++;
            } else if (d == 8) {
                c8++;
            } else {
                throw new AssertionError();
            }
            v /= 10;
        }
        return c3 <= c5 && c5 <= c8;
    }
}
