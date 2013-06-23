package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        n >>= Integer.numberOfTrailingZeros(n);
        m >>= Integer.numberOfTrailingZeros(m);
        int cn = Integer.bitCount(n);
        int cm = Integer.bitCount(m);
        long[][] dp = new long[cn][cm];
        for (long[] e : dp) {
            Arrays.fill(e, Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        int[] an = getA(n, cn);
        int[] bn = getA(m, cm);
        for (int i = 0; i < cn; i++) {
            for (int j = 0; j < cm; j++) {
                long val = dp[i][j];
                if (val == Long.MAX_VALUE) {
                    continue;
                }
                if (i + 1 < cn && dp[i + 1][j] > val + bn[j]) {
                    dp[i + 1][j] = val + bn[j];
                }
                if (j + 1 < cm && dp[i][j + 1] > val + an[i]) {
                    dp[i][j + 1] = val + an[i];
                }
            }
        }
        out.println(dp[cn - 1][cm - 1]);
    }

    private int[] getA(int n, int cn) {
        int[] an = new int[cn];
        an[0] = n;
        for (int i = 1; i < cn; i++) {
            an[i] = an[i - 1] >> 1;
            an[i] >>= Integer.numberOfTrailingZeros(an[i]);
        }
        return an;
    }
}
