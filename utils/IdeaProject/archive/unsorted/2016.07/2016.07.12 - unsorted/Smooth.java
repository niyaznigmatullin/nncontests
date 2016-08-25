package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Matrix;

public class Smooth {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[10][10];
        for (int i = 0; i < 10; i++) {
            if (i > 0) a[i][i - 1] = 1;
            a[i][i] = 1;
            if (i + 1 < 10) a[i][i + 1] = 1;
        }
        final int MOD = 1000000007;
        Matrix m = Matrix.powMod(new Matrix(a), n - 1, MOD);
        int ans = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ans += m.get(i, j);
                if (ans >= MOD) ans -= MOD;
            }
        }
        out.println(ans);
    }

}
