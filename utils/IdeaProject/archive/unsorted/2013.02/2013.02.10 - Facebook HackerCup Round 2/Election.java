package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Election {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        long k = in.nextLong();
        out.print("Case #" + testNumber + ": ");
        int p = in.nextInt();
        if (p == 100) {
            out.println((n + k - 1) / k);
            return;
        }
        long m = n % k;
        long l = (100 * k - p * m) / (p * k);
        if (l * k + m >= n) {
            out.println(1);
            return;
        }
        long z = l * k + m;
        while (true) {
            long den = ((100 - p) * k);
            long nl = (p * z + (p - 100) * k + den - 1) / den + 1;
            long newZ = z + nl * k;
            if (newZ > n) {
                out.println((n - z + k) / k);
                return;
            }
            if (newZ == n) {
                out.println(1);
                return;
            }
            z = newZ;
        }
    }
}
