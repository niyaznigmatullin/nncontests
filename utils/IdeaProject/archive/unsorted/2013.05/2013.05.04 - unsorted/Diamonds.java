package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Diamonds {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        out.println("Case #" + testNumber + ": " + solve(n, x, y));
    }

    static double solve(int n, int x, int y) {
        int i;
        for (i = 0; 2 * i + 1 <= n; i += 2) {
            n -= 2 * i + 1;
        }
        if (x + y < i && y - x < i) {
            return 1.;
        }
        if (x + y != i && y - x != i) {
            return 0.;
        }
        int z = Math.abs(Math.abs(x) - i) + 1;
        if (z == i + 1 || n < z) {
            return 0.;
        }
        double ans = 0;
        double[][] dp = getDP(i);
        for (int l = 0; l <= i; l++) {
            for (int r = 0; r <= i; r++) {
                if (l >= z && l + r == n) ans += dp[l][r];
            }
        }
        return ans;
    }


    static double[][] getDP(int n) {
        double[][] ret = new double[n + 1][n + 1];
        ret[0][0] = 1.;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                double cur = ret[i][j];
                if (cur == 0) continue;
                if (i + 1 <= n && j + 1 <= n) {
                    ret[i + 1][j] += cur * .5;
                    ret[i][j + 1] += cur * .5;
                } else if (i + 1 <= n) {
                    ret[i + 1][j] += cur;
                } else if (j + 1 <= n) {
                    ret[i][j + 1] += cur;
                }
            }
        }
        return ret;
    }

}
