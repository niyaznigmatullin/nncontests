package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskA {

    static char[] d;

    static boolean hanoi(int a, int b, int c, int n) {
        if (n == 0) {
            return true;
        }
        if (d[n - 1] == a) {
            return hanoi(a, c, b, n - 1);
        } else if (d[n - 1] == b) {
            return hanoi(c, b, a, n - 1);
        } else {
            return false;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        in.next();
        d = in.next().toCharArray();
        if (hanoi('A', 'B', 'C', d.length)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
