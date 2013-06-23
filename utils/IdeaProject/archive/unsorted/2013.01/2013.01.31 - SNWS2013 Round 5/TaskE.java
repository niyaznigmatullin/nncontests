package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        c = in.next().toCharArray();
        if ((c.length & 1) == 1) {
            out.println("No");
            return;
        }
        int cnt0 = 0;
        int cnt1 = 0;
        for (char d : c) {
            if (d == '1') {
                ++cnt1;
            }
            if (d == '0') {
                ++cnt0;
            }
        }
        if (cnt1 * 2 > c.length || cnt0 * 2 > c.length) {
            out.println("No");
            return;
        }
        for (int i = 0; i + 2 < c.length; i++) {
            int c1 = 0;
            int c0 = 0;
            for (int j = 0; j < 3; j++) {
                if (c[i + j] == '1') {
                    ++c1;
                }
                if (c[i + j] == '0') {
                    c0++;
                }
            }
            if (c0 == 3 || c1 == 3) {
                out.println("No");
                return;
            }
        }
        out.println(go(0, c.length, c.length / 2 - cnt0, c.length / 2 - cnt1) ? "Yes" : "No");
    }

    static char[] c;

    boolean go(int v, int n, int left0, int left1) {
        if (v > 2) {
            int cnt0 = 0;
            for (int j = -3; j < 0; j++) {
                if (c[v + j] == '0') {
                    ++cnt0;
                }
            }
            if (cnt0 == 0 || cnt0 == 3) {
                return false;
            }
        }
        if (v == n) {
            return true;
        }
        if (c[v] == '0' || c[v] == '1') {
            return go(v + 1, n, left0, left1);
        }
        if (left0 < left1) {
            c[v] = '1';
            if (go(v + 1, n, left0, left1 - 1)) {
                return true;
            }
            if (left0 > 0) {
                c[v] = '0';
                if (go(v + 1, n, left0 - 1, left1)) {
                    return true;
                }
            }
            c[v] = '.';
        } else {
            if (left0 > 0) {
                c[v] = '0';
                if (go(v + 1, n, left0 - 1, left1)) {
                    return true;
                }
            }
            if (left1 > 0) {
                c[v] = '1';
                if (go(v + 1, n, left0, left1 - 1)) {
                    return true;
                }
            }
            c[v] = '.';
        }
        return false;
    }
}
