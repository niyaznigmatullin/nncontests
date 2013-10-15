package lib.test.on2013_07.on2013_07_17_.TaskC1;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC1 {

    static final int N = 1000002;

    static int[] dp = new int[N];
    static int[] last = new int[N];

    static {
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int x = i;
            dp[i] = Integer.MAX_VALUE;
            int maxD = 0;
            while (x > 0) {
                int d = x % 10;
                maxD = Math.max(maxD, d);
                if (d != 0) {
                    if (dp[i] > dp[i - d] + 1) {
                        dp[i] = dp[i - d] + 1;
                        last[i] = d;
                    }
                }
                x /= 10;
            }
            if (dp[i] != dp[i - maxD] + 1) throw new AssertionError();
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        for (int i = 1; i < 10; i++) {
            Matrix z = new Matrix();
            for (int j = 0; j < 9; j++) {
                z.a[j][j + 1] = 0;
            }
            z.a[9][10 - i] = 1;
            all[0][i] = z;
        }
        {
            Matrix z = new Matrix();
            for (int j = 0; j < 10; j++) {
                z.a[j][j] = 0;
            }
            all[0][0] = z;
        }
        for (int i = 1; i < all.length; i++) {
            for (int j = 0; j < 10; j++) {
                Matrix f = new Matrix();
                for (int k = 0; k < 10; k++) f.a[k][k] = 0;
                for (int k = 0; k < 10; k++) {
                    Matrix on = all[i - 1][Math.max(k, j)];
                        f = on.mul(f);
                }
                all[i][j] = f;
            }
        }
        long n = in.nextLong();
        int[] d = new int[(n + "").length()];
        {
            long m = n;
            for (int i = 0; i < d.length; i++) {
                d[d.length - i - 1] = (int) (m % 10);
                m /= 10;
            }
        }
        Matrix z = new Matrix();
        for (int i = 0; i < 10; i++) z.a[i][i] = 0;
        int maxD = 0;
        for (int i = 0; i < d.length; i++) {
            int length = d.length - i - 1;
            for (int j = 0; j < d[i]; j++) {
                Matrix on = all[length][Math.max(j, maxD)];
                    z = on.mul(z);
            }
            maxD = Math.max(maxD, d[i]);
        }
        z = all[0][maxD].mul(z);
        out.println(z.a[9][9]);
    }

    static Matrix[][] all = new Matrix[20][10];

    static class Matrix {
        long[][] a;

        Matrix() {
            a = new long[10][10];
            for (long[] e : a) {
                Arrays.fill(e, Long.MAX_VALUE);
            }
        }

        public Matrix mul(Matrix b) {
            Matrix ret = new Matrix();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    long best = Long.MAX_VALUE;
                    for (int k = 0; k < 10; k++) {
                        if (a[i][k] != Long.MAX_VALUE && b.a[k][j] != Long.MAX_VALUE) {
                            best = Math.min(best, a[i][k] + b.a[k][j]);
                        }
                    }
                    ret.a[i][j] = best;
                }
            }
            return ret;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (long[] e : a) {
                sb.append(Arrays.toString(e)).append('\n');
            }
            return sb.toString();
        }
    }
}
