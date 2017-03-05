package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class MegaprimeNumbers {

    long left, right;

    long get(int len, long n, int d) {
        while (len > 0) {
            n = n * 10 + d;
            len--;
        }
        return n;
    }

    boolean isPrime(long n) {
        return BigInteger.valueOf(n).isProbablePrime(10);
    }

    int go(int x, int len, long n) {
        if (get(len - x, n, 7) < left) return 0;
        if (get(len - x, n, 2) > right) return 0;
        if (x == len) {
            if (isPrime(n))
                return 1;
            else return 0;
        }
        n *= 10;
        int ret = 0;
        ret += go(x + 1, len, n + 2);
        ret += go(x + 1, len, n + 3);
        ret += go(x + 1, len, n + 5);
        ret += go(x + 1, len, n + 7);
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        left = in.nextLong();
        right = in.nextLong();
        int ans = 0;
        for (int i = 1; i <= 15; i++) {
            ans += (go(0, i, 0));
        }
        out.println(ans);
    }
}
