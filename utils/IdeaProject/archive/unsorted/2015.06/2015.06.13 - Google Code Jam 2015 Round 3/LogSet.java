package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class LogSet {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int p = in.nextInt();
        a = in.readLongArray(p);
        long[] f = in.readLongArray(p);
        long sum = 0;
        for (long e : f) sum += e;
        int n = Long.numberOfTrailingZeros(sum);
        {
            final long[] cn = new long[p];
            final long INF = 1L << 60;
            for (int i = 0; i < p; i++) {
                int q = 0;
                for (int j = 0; j < p; j++) {
                    while (q < p && a[q] < a[j] - a[i]) ++q;
                    int id = q < p && a[q] == a[j] - a[i] ? q : -1;
                    if (id < 0) continue;
                    cn[id] += 1. * f[i] * f[j] > 3e18 ? INF : Math.min(INF, f[i] * f[j]);
                    cn[id] = Math.min(cn[id], INF);
                }
            }
            candidates = new ArrayList<>();
//            for (long e : a) candidates.add(e);
            for (int i = 0; i < p; i++) {
                if (cn[i] >= (1L << (n - 1))) {
                    if (check(a[i], f.clone())) {
                        candidates.add(a[i]);
                    }
                }
            }
        }
        Collections.sort(candidates);
        System.err.println(p + " " + candidates.size());
        fs = new long[n + 1][p];
        fs[0] = f.clone();
        got = new long[n];
        if (!go(0, n, 0)) {
            throw new AssertionError();
        }
        out.print("Case #" + testNumber + ": ");
        out.printArray(got);
        out.flush();
    }

    static boolean go(int depth, int n, int last) {
        if (depth == n) {
            return true;
        }
        for (int i = last; i < candidates.size(); i++) {
            long cur = candidates.get(i);
            long[] cfs = fs[depth + 1];
            System.arraycopy(fs[depth], 0, cfs, 0, fs[depth].length);
            got[depth] = cur;
            boolean ok = check(cur, cfs);
            if (ok) {
                if (go(depth + 1, n, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check(long cur, long[] cfs) {
        boolean ok = true;
        if (cur > 0) {
            for (int j = 0, k = 0; j < cfs.length; j++) {
                if (a[j] == 0 && cfs[j] == 0) {
                    ok = false;
                    break;
                }
                while (k < cfs.length && a[j] + cur > a[k]) ++k;
                if (cfs[j] == 0) continue;
                if (k >= cfs.length || a[j] + cur != a[k] || cfs[k] < cfs[j]) {
                    ok = false;
                    break;
                }
                cfs[k] -= cfs[j];
            }
        } else if (cur == 0) {
            for (int j = 0; j < cfs.length; j++) {
                if (a[j] == 0 && cfs[j] == 0) {
                    ok = false;
                    break;
                }
                if (cfs[j] % 2 != 0) {
                    ok = false;
                    break;
                }
                cfs[j] /= 2;
            }
        } else {
            for (int j = cfs.length - 1, k = cfs.length - 1; j >= 0; j--) {
                if (a[j] == 0 && cfs[j] == 0) {
                    ok = false;
                    break;
                }
                while (k >= 0 && a[j] + cur < a[k]) --k;
                if (cfs[j] == 0) continue;
                if (k < 0 || a[j] + cur != a[k] || cfs[k] < cfs[j]) {
                    ok = false;
                    break;
                }
                cfs[k] -= cfs[j];
            }
        }
        return ok;
    }

    static long[] a;
    static long[] got;
    static List<Long> candidates;
    static long[][] fs;
}
