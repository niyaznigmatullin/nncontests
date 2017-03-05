package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.math.BigInteger;

public class Phiphiphi {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        long k = in.nextLong();
        while (k > 0 && n > 1) {
            if (BigInteger.valueOf(n).isProbablePrime(30)) {
                n--;
                k--;
                continue;
            }
            long m = n;
            for (long i = 2; i * i * i <= m; i++) {
                if (m % i == 0) {
                    while (m % i == 0) {
                        m /= i;
                    }
                    n -= n / i;
                }
            }
            if (m > 1 && BigInteger.valueOf(m).isProbablePrime(30)) {
                n -= n / m;
                m = 1;
            }
            if (m > 1) {
                long d = (long) Math.sqrt(m);
                while (d * d > m) --d;
                while (d * d < m) ++d;
                if (d * d == m) {
                    n -= n / d;
                } else {
                    d = getFactor(m);
                    n -= n / d;
                    n -= n / (m / d);
                }
            }
            k--;
        }
        out.println(n);
    }

    private long getFactor(long n) {
        int start = 2;
        while (true) {
            long x = start;
            long y = start;
            long factor = 1;
            while (factor == 1) {
                x = g(x, n);
                y = g(g(y, n), n);
                factor = MathUtils.gcd(Math.abs(x - y), n);
            }
            if (factor != n) {
                return factor;
            }
            ++start;
        }
    }

    static long g(long x, long n) {
        return (mul(x, x, n) + 1) % n;
    }

    static long mul(long a, long b, long mod) {
        long ret = 0;
        while (b > 0) {
            if ((b & 1) == 1) {
                ret += a;
                if (ret >= mod) ret -= mod;
            }
            a += a;
            if (a >= mod) a -= mod;
            b >>= 1;
        }
        return ret;
    }
}
