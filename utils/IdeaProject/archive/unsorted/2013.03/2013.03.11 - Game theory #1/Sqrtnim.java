package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Sqrtnim {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        map = new HashMap<>();
        int n = in.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long m = in.nextLong();
            ans ^= snim(m);
        }
        out.println(ans == 0 ? "Second" : "First");
    }

    static int sqrt(long n) {
        int v = (int) Math.sqrt(n);
        while ((long) v * v < n) {
            v++;
        }
        while ((long) v * v > n) {
            v--;
        }
        return v;
    }


    static long[] sqr;

    static {
        sqr = new long[111111];
        for (int i = 1; i < sqr.length; i++) {
            sqr[i] = (long) i * i;
        }
    }

    static Map<Long, Integer> map;

    static int snim(long n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        long m = n;
        int v = sqr.length - 1;
        while (n > 0) {
            while (sqr[v] > n) --v;
            int u = v;
            if ((long) u * u > n - 1) --u;
            if (v == u) {
                n = n - v - 1;
            } else {
                map.put(m, v);
                return v;
            }
        }
        map.put(m, 0);
        return 0;
    }
}
