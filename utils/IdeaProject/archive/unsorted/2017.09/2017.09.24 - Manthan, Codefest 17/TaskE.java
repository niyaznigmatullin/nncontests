package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {

    static final int MAXN = 62;

    static long[][][] dp = new long[11][][];

    static long solve(int b, long n) {
        if (n == 0) {
            return 0;
        }
        int[] d = new int[MAXN];
        int cn = 0;
        while (n > 0) {
            d[cn++] = (int) (n % b);
            n /= b;
        }
        long ans = 0;
        int curMask = 0;
        long[][] f = dp[b];
        for (int length = 1; length < cn; length++) {
            for (int first = 1; first < b; first++) {
                ans += f[length - 1][1 << first];
            }
        }
        for (int i = cn - 1; i >= 0; i--) {
            for (int cur = 0; cur < d[i]; cur++) {
                if (i == cn - 1 && cur == 0) continue;
                ans += f[i][curMask ^ (1 << cur)];
            }
            curMask ^= 1 << d[i];
        }
        return ans;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        for (int digits = 2; digits <= 10; digits++) {
            long[][] f = new long[MAXN][1 << digits];
            f[0][0] = 1;
            long N = 1000000000000000000L;
            int log = 0;
            while (N > 0) {
                N /= digits;
                log++;
            }
            for (int i = 0; i < log; i++) {
                for (int mask = 0; mask < 1 << digits; mask++) {
                    long value = f[i][mask];
                    if (value == 0) continue;
                    for (int d = 0; d < digits; d++) {
                        f[i + 1][mask ^ (1 << d)] += f[i][mask];
                    }
                }
            }
            dp[digits] = f;
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int b = in.nextInt();
            long l = in.nextLong();
            long r = in.nextLong();
            out.println(solve(b, r + 1) - solve(b, l));
        }
    }
}
