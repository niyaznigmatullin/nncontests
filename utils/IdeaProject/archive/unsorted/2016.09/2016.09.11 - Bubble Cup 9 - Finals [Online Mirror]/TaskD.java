package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int x = in.nextInt();
        final int MAX = 128;
        double[] prob = new double[x + 1];
        for (int i = 0; i <= x; i++) {
            prob[i] = in.nextDouble();
        }
        double[][] a = new double[MAX][MAX];
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j <= x; j++) {
                a[i][i ^ j] = prob[j];
            }
        }
        double[][] res = new double[MAX][MAX];
        for (int i = 0; i < MAX; i++) res[i][i] = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = mul(res, a);
            }
            a = mul(a, a);
            n >>= 1;
        }
        out.println(1. - res[0][0]);
    }

    static double[][] mul(double[][] a, double[][] b) {
        int n = a.length;
        double[][] res = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double v = 0;
                for (int k = 0; k < n; k++) {
                    v += a[i][k] * b[k][j];
                }
                if (v < 1e-30) v = 0;
                res[i][j] = v;
            }
        }
        return res;
    }
}
