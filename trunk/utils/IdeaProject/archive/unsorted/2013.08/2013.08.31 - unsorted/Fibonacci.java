package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    long[] qa, qb, qc, qd;

    int getFibonacci(long n, int r) {
        if (qa == null) {
            qa = new long[63];
            qb = new long[63];
            qc = new long[63];
            qd = new long[63];
            long a = 0;
            long b = 1;
            long c = 1;
            long d = 1;
            for (int i = 0; i < 63; i++) {
                qa[i] = a;
                qb[i] = b;
                qc[i] = c;
                qd[i] = d;
                long ta = (a * a + b * c) % r;
                long tb = (a * b + b * d) % r;
                long tc = (c * a + d * c) % r;
                long td = (c * b + d * d) % r;
                a = ta;
                b = tb;
                c = tc;
                d = td;
            }
        }
        n--;
        long xa = 1;
        long xb = 0;
        long xc = 0;
        long xd = 1;
        int i = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                long a = qa[i];
                long b = qb[i];
                long c = qc[i];
                long d = qd[i];
                long ta = (xa * a + xb * c) % r;
                long tb = (xa * b + xb * d) % r;
                long tc = (xc * a + xd * c) % r;
                long td = (xc * b + xd * d) % r;
                xa = ta;
                xb = tb;
                xc = tc;
                xd = td;
            }
            n >>= 1;
            ++i;
        }
        return (int) xa;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int r = in.nextInt();
        long m = 6L * r;
        int sm = (int) Math.sqrt(m);
        Map<Long, Long> found = new HashMap<>();
        int cf1 = getFibonacci(10L * m, r);
        for (int i = 0; i <= sm; i++) {
            int f2 = getFibonacci(10L * m + i + 1, r);
            found.put(((long) cf1 << 32) | f2, 10L * m + i);
            cf1 = f2;
        }
        long somePeriod = Long.MAX_VALUE;
        for (long i = 0; i <= m; i += sm) {
            int f1 = getFibonacci(11L * m + i, r);
            int f2 = getFibonacci(11L * m + i + 1, r);
            long cur = (((long) f1 << 32) | f2);
            if (found.containsKey(cur)) {
                long diff = 11L * m + i - found.get(cur);
                if (diff > 0) {
                    somePeriod = diff;
                    break;
                }
            }
        }
        long best = somePeriod;
        for (long i = 1; i * i <= somePeriod; i++) {
            if (somePeriod % i != 0) continue;
            if (i < best) {
                long d = i;
                int f1 = getFibonacci(10L * m, r);
                int f2 = getFibonacci(10L * m + 1, r);
                int f3 = getFibonacci(10L * m + d, r);
                int f4 = getFibonacci(10L * m + d + 1, r);
                if (f1 == f3 && f2 == f4) {
                    best = d;
                }
            }
            if (somePeriod / i < best) {
                long d = somePeriod / i;
                int f1 = getFibonacci(10L * m, r);
                int f2 = getFibonacci(10L * m + 1, r);
                int f3 = getFibonacci(10L * m + d, r);
                int f4 = getFibonacci(10L * m + d + 1, r);
                if (f1 == f3 && f2 == f4) {
                    best = d;
                }
            }
        }
        out.println(best);
    }
}
