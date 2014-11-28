package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class RollingDice {

    static long[][] mul(long[][] a, long[][] b) {
        int n = a.length;
        long[][] ret = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    ret[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ret;
    }

    static long[][] pow(long[][] a, long b) {
        int n = a.length;
        long[][] ret = new long[n][n];
        for (int i = 0; i < n; i++) ret[i][i] = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ret = mul(ret, a);
            }
            a = mul(a, a);
            b >>= 1;
        }
        return ret;
    }

    // FURLDB
    // 012345

    static long[][] up() {
        long[][] ret = new long[7][7];
        ret[4][5] = 1;
        ret[5][1] = 1;
        ret[1][0] = 1;
        ret[0][4] = 1;
        ret[3][3] = 1;
        ret[2][2] = 1;
        ret[6][6] = 1;
        ret[6][5] = 1;
        return ret;
    }

    static long[][] right() {
        long[][] ret = new long[7][7];
        ret[4][2] = 1;
        ret[2][1] = 1;
        ret[1][3] = 1;
        ret[3][4] = 1;
        ret[0][0] = 1;
        ret[5][5] = 1;
        ret[6][6] = 1;
        ret[6][2] = 1;
        return ret;
    }

    static long[][] solve(long a, long b, long[][] right, long[][] up) {
        if (b == 1) {
            return pow(right, a - 1);
        }
        return mul(pow(right, a / b), solve(b, a % b, mul(up, pow(right, a / b)), right));
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        int[] p = new int[6];
        for (int i = 0; i < 6; i++) {
            p[i] = in.nextInt();
        }
        long[][] m = solve(a, b, right(), up());
        long ans = p[4];
        for (int i = 0; i < 6; i++) {
            ans += m[6][i] * p[i];
        }
        out.println(ans);
    }
}
