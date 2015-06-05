package coding;

import ru.ifmo.niyaz.DataStructures.FenwickMultiSum;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

public class ACMMarket {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] primes = MathUtils.genPrimes(151);
        FenwickMultiSum[] f = new FenwickMultiSum[primes.length];
        for (int i = 0; i < f.length; i++) {
            f[i] = new FenwickMultiSum(n);
        }
        for (int i = 0; i < m; i++) {
            int operation = in.nextInt();
            if (operation == 0) {
                int left = in.nextInt() - 1;
                int right = in.nextInt();
                int mod = in.nextInt();
                int ans = 1 % mod;
                for (int j = 0; j < f.length; j++) {
                    long count;
                    if (left >= right) {
                        count = f[j].getSum(left, n) + f[j].getSum(0, right);
                    } else {
                        count = f[j].getSum(left, right);
                    }
                    ans = (int) ((long) ans * MathUtils.modPow(primes[j], count, mod) % mod);
                }
                out.println(ans);
            } else {
                int sign = operation == 1 ? 1 : -1;
                int left = in.nextInt() - 1;
                int right = in.nextInt();
                int x = in.nextInt();
                int id = -1;
                for (int q : primes) {
                    ++id;
                    int cn = 0;
                    while (x % q == 0) {
                        x /= q;
                        ++cn;
                    }
                    if (cn == 0) continue;
                    if (left >= right) {
                        f[id].add(left, n, sign * cn);
                        f[id].add(0, right, sign * cn);
                    } else {
                        f[id].add(left, right, sign * cn);
                    }
                }
            }
        }
    }
}
