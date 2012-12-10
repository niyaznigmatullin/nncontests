package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        if (isGeometric(a)) {
            out.println(0);
            return;
        }
        int[] b = Arrays.copyOfRange(a, 1, n);
        int[] c = b.clone();
        b[0] = a[0];
        if (isGeometric(b) || isGeometric(c)) {
            out.println(1);
            return;
        }
        int bad = 0;
        int last = 1;
        int prev = 0;
        if (a[0] == 0) {
            for (int i = 0; i < n; i++) {
                if (a[i] != 0) {
                    ++bad;
                }
            }
            if (bad == 1) {
                out.println(1);
            } else {
                out.println(2);
            }
            return;
        }
        for (int i = 2; i < n; i++) {
            if (a[last] * a[last] != a[prev] * a[i] || a[last] == 0 && a[i] != 0) {
                bad++;
            } else {
                prev = last;
                last = i;
            }
        }
        if (bad == 1) {
            out.println(1);
            return;
        }
        out.println(2);
    }

    private boolean isGeometric(int[] a) {
        boolean ret = true;
        int n = a.length;
        if (a[0] == 0) {
            for (int i = 1; i < n; i++) {
                if (a[i] != 0) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 1; i + 1 < n; i++) {
            if (a[i] * a[i] != a[i - 1] * a[i + 1] || a[i] == 0 && a[i + 1] != 0) {
                ret = false;
            }
        }
        return ret;
    }
}
