package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Matrix;

import java.util.Arrays;

public class TaskC {

    static final int[][] T = {
            {0, 1, 2, 3},
            {1, 4, 3, 6},
            {2, 7, 4, 1},
            {3, 2, 5, 4},
    };

    static int multiply(int a, int b) {
        int sign = (a ^ b) >> 2;
        a &= ~4;
        b &= ~4;
        int ret = T[a][b];
        if (sign == 1) {
            ret ^= 4;
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println(testNumber);
        int[][][] ms = new int[3][][];
        for (int i = 0; i < 3; i++) {
            int[][] a = new int[32][32];
            for (int have = 0; have < 4; have++) {
                for (int val = 0; val < 8; val++) {
                    int newVal = multiply(val, i + 1);
                    if (have < 3 && newVal == have + 1) {
                        a[have * 8 + val][(have + 1) * 8] = 1;
                    }
                    a[have * 8 + val][have * 8 + newVal] = 1;
                }
            }
            ms[i] = a;
        }
        in.nextInt();
        long repeat = in.nextLong();
        char[] c = in.next().toCharArray();
        int[][] m = new int[32][32];
        for (int i = 0; i < 32; i++) m[i][i] = 1;
        for (char e : c) {
            m = multiply(m, ms[e - 'i']);
        }
        m = pow(m, repeat);
        boolean answer = m[0][3 * 8] == 1;
        out.println("Case #" + testNumber + ": " + (answer ? "YES" : "NO"));
    }

    static int[][] pow(int[][] a, long b) {
        int[][] ret = new int[32][32];
        for (int i = 0; i < 32; i++) {
            ret[i][i] = 1;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                ret = multiply(ret, a);
            }
            a = multiply(a, a);
            b >>= 1;
        }
        return ret;
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[32][32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                for (int k = 0; k < 32; k++) {
                    c[i][j] |= a[i][k] & b[k][j];
                }
            }
        }
        return c;
    }
}
