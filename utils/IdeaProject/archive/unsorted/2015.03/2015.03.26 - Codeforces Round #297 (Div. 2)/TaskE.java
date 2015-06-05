package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long s = in.nextLong();
        int[] numbers = in.readIntArray(n);
        long[][] f = get(Arrays.copyOfRange(numbers, 0, n / 2), k, s);
        long[][] g = get(Arrays.copyOfRange(numbers, n / 2, n), k, s);
        long ans = 0;
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < g.length && j + i <= k; j++) {
                long[] a = f[i];
                long[] b = g[j];
                for (int id = 0, jd = b.length - 1; id < a.length; ) {
                    int id2 = id;
                    while (id2 < a.length && a[id2] == a[id]) ++id2;
                    while (jd >= 0 && a[id] + b[jd] > s) --jd;
                    long count = 0;
                    while (jd >= 0 && a[id] + b[jd] ==s) {
                        --jd;
                        ++count;
                    }
                    ans += count * (id2 - id);
                    id = id2;
                }
            }
        }
        out.println(ans);
    }

    static final long[] fact = new long[20];
    static {
        fact[0] = 1;
        for (int i = 1; i < fact.length; i++) fact[i] = fact[i - 1] * i;
    }

    static long[][] get(int[] a, int k, long s) {
        int n = a.length;
        k = Math.min(k, n);
        int all = 1;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = a[i] >= fact.length ? 2 : 3;
            all *= f[i];
        }
        int[] b = new int[n];
        long[][] ret = new long[k + 1][all];
        int[] cn = new int[k + 1];
        all: for (int mask = 0; mask < all; mask++) {
            int got = 0;
            for (int i = 0, p = mask; i < n; i++) {
                b[i] = p % f[i];
                p /= f[i];
                if (b[i] == 2) {
                    got++;
                }
            }
            if (got > k) continue;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (b[i] == 1) {
                    sum += a[i];
                } else if (b[i] == 2) sum += fact[a[i]];
                if (sum > s) continue all;
            }
            ret[got][cn[got]++] = sum;
        }
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOf(ret[i], cn[i]);
            Arrays.sort(ret[i]);
        }
        return ret;
    }
}
