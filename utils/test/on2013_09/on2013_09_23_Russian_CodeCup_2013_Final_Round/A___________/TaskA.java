package lib.test.on2013_09.on2013_09_23_Russian_CodeCup_2013_Final_Round.A___________;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {

    static long solve(long n, long k) {
        if ((n & 1) == 1) {
            if (k > n / 2) {
                return -1;
            } else {
                return k * 2;
            }
        }
        long half = n / 2;
        long m1 = half / 2;
        long m2 = (half + 1) / 2;
        long cur = count(m1);
        if (cur >= k) {
            return solve(m1, k);
        }
        k -= cur;
        if (m2 >= k) {
            return m1 + k;
        }
        k -= m2;
        if (m1 >= k) {
            long z = m1 - k;
            return n - 2 * z - 1;
        }
        return -1;
    }

    static long count(long n) {
        if (n == 0) return 0;
        if ((n & 1) == 1) return n / 2;
        long m = n / 4;
        return count(m) + (n / 2 + 1) / 2 + m;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t = in.nextInt();
        for (int test = 0; test < t; test++) {
            long n = in.nextLong();
            long k = in.nextLong();
            out.println(solve(n, k));
//            if (solve(n, k) != stupid(n, k)) {
//                throw new AssertionError();
//            }
        }
    }

    static int stupid(long nn, long kk) {
        int n = (int) nn;
        int k = (int) kk;
        boolean[] win = new boolean[n + 1];
        for (int i = n; i > 0; i--) {
            win[i] = false;
            if (i * 2 <= n && !win[i * 2] || i + 1 <= n && !win[i + 1]) {
                win[i] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (!win[i]) continue;
            if (k == 1) return i;
            --k;
        }
        return -1;
    }
}
