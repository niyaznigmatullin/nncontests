package lib.test.on2013_08.on2013_08_27_.Crypto;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Matrix;

import java.util.Arrays;

public class Crypto {

    static int MOD;
    static int N;
    static Matrix[] t;

    static void set(int x, Matrix y) {
        x += N;
        t[x] = y;
        while (x > 1) {
            x >>= 1;
            t[x] = t[x * 2].multiplyMod(t[x * 2 + 1], MOD);
        }
    }

    static final Matrix ONE = new Matrix(new int[][]{{1, 0}, {0, 1}});

    static Matrix get(int l, int r) {
        --r;
        l += N;
        r += N;
        Matrix left = ONE;
        Matrix right = ONE;
        while (l <= r) {
            if ((l & 1) == 1) {
                left = left.multiplyMod(t[l], MOD);
                ++l;
            }
            if ((r & 1) == 0) {
                right = t[r].multiplyMod(right, MOD);
                --r;
            }
            l >>= 1;
            r >>= 1;
        }
        return left.multiplyMod(right, MOD);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        MOD = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        N = Integer.highestOneBit(n) << 1;
        t = new Matrix[2 * N];
        Arrays.fill(t, ONE);
        for (int i = 0; i < n; i++) {
            int[][] a = new int[2][2];
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    a[j][k] = in.nextInt();
                }
            }
            set(i, new Matrix(a));
        }
        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            Matrix got = get(l, r);
            for (int j = 0; j < 2; j++) {
                out.println(got.get(j, 0) + " " + got.get(j, 1));
            }
            out.println();
        }
    }
}
