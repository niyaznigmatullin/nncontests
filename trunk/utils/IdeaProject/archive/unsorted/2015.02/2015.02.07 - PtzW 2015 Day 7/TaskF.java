package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;
import ru.ifmo.niyaz.math.Matrix;

import java.util.Arrays;

public class TaskF {

    static final int MOD = 7340033;
    static int[] P = {9, -9, 6, -16, 5, 2, 0, -9, -1, -1, 20, 2, 6, -3, -15, -13, 15, 20, 15, -26, -28, 7, 6, 26, -27, -4, 9, -15, 3, 2, 8, 43, 9, -39, -24, -2, -24, 28, 9, 13, 13, -18, -12, -16, 14, 13, 16, 8, -36, 1, -6, -8, 15, 1, 14, 3, -6, -7, -3, 2, -2, 2, 2, 0, -1, -2, -1, 3, 3, -1, -1, -1};
    static int[] Q = {-6, 9, -9, 18, -16, 11, -14, 8, -1, 5, -7, -2, -8, 14, 5, 5, -19, -3, 6, 7, 6, -16, 7, -8, 22, -17, 12, -7, -5, -7, 8, -4, 7, 9, -13, 4, 6, -14, 14, -19, 7, 13, -2, 4, -18, 0, 1, 4, 12, -8, 5, 0, -8, -1, -7, 8, 5, 2, -3, -3, 0, 0, 0, 0, 2, 1, 0, -3, -1, 1, 1, 1, -1};

    static {
        for (int i = 0; i < P.length - i - 1; i++) {
            int t = P[i];
            P[i] = P[P.length - i - 1];
            P[P.length - i - 1] = t;
        }
        for (int i = 0; i < Q.length - i - 1; i++) {
            int t = Q[i];
            Q[i] = Q[Q.length - i - 1];
            Q[Q.length - i - 1] = t;
        }
        for (int i = 0; i < P.length; i++) {
            P[i] = (P[i] + MOD) % MOD;
        }
        for (int i = 0; i < Q.length; i++) {
            Q[i] = (Q[i] + MOD) % MOD;
        }
        int divide = modInverse(Q[0]);
        for (int i = 0; i < P.length; i++) {
            P[i] = mul(P[i], divide);
        }
        for (int i = 0; i < Q.length; i++) {
            Q[i] = mul(Q[i], divide);
        }
    }

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int modInverse(int a) {
        return MathUtils.modPow(a, MOD - 2, MOD);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int[] x = new int[100];
        long n = in.nextLong();
        x[0] = 1;
        for (int i = 1; i < x.length; i++) {
            int cur = 0;
            for (int j = 1; i - j >= 0 && j < Q.length; j++) {
                cur = add(cur, mul(x[i - j], MOD - Q[j]));
            }
            if (i < P.length) {
                cur = add(cur, P[i]);
            }
            x[i] = cur;
        }
//        System.out.println(Arrays.toString(x));
        if (n < x.length) {
            out.println(x[(int) n]);
        } else {
            int[][] a = new int[Q.length][Q.length];
            for (int i = 0; i + 1 < Q.length; i++) {
                a[i][i + 1] = 1;
            }
            for (int i = 1; i < Q.length; i++) {
                a[Q.length - 1][Q.length - i] = MOD - Q[i];
            }
            Matrix m = new Matrix(a);
            m = Matrix.powMod(m, n - x.length + 1, MOD);
            int ans = 0;
            for (int i = 0; i < Q.length; i++) {
                ans = add(ans, mul(x[x.length - Q.length + i], m.get(Q.length - 1, i)));
            }
//            System.out.println(Arrays.deepToString(m.toArray()));
            out.println(ans);
        }
    }

    //1774464, 5030371, 3213868, 3673656, 2253480, 162153, 6967672, 6212357, 1488891, 5272969, 2288129, 3900589, 6122846, 6177603]
    //4338517
}
