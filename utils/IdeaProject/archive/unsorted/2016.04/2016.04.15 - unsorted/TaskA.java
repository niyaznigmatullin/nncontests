package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
            b[i] = in.nextLong();
        }
        long have = 0;
        for (int i = 0; i < n; i++) {
            have ^= a[i];
            b[i] ^= a[i];
        }
        final int BITS = 61;
        long[] z = new long[BITS];
        Arrays.fill(z, -1);
        for (int i = 0; i < n; i++) {
            long x = b[i];
            for (int j = 0; j < BITS; j++) {
                if (((x >> j) & 1) == 0) continue;
                if (z[j] >= 0) {
                    x ^= z[j];
                } else {
                    z[j] = x;
                    break;
                }
            }
        }
        for (int i = 0; i < BITS; i++) {
            if (((have >> i) & 1) == 0) continue;
            if (z[i] < 0) {
                out.println("1/1");
                return;
            }
            have ^= z[i];
        }
        int rank = 0;
        for (int i = 0; i < BITS; i++) {
            if (z[i] >= 0) {
                rank++;
            }
        }
        out.println((BigInteger.ONE.shiftLeft(rank).subtract(BigInteger.ONE)) + "/" + BigInteger.ONE.shiftLeft(rank));
    }
}
