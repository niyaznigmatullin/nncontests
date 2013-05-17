package lib.test.on2013_03.on2013_03_03_.C;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Matrix;

import java.util.Arrays;

public class C {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        int c = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[c][c];
        for (int[] d : a) {
            Arrays.fill(d, 1);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            a[x][y] = 0;
            a[y][x] = 0;
        }
        Matrix matrix = new Matrix(a);
        final int MOD = 1000000007;
        matrix = Matrix.powMod(matrix, n - 1, MOD);
        int[] ones = new int[c];
        Arrays.fill(ones, 1);
        Matrix x = new Matrix(new int[][]{ones});
        x = x.multiplyMod(matrix, MOD);
        long ans = 0;
        for (int i = 0; i < x.n; i++) {
            for (int j = 0; j < x.m; j++) {
                ans += x.get(i, j);
                ans %= MOD;
            }
        }
        out.println(ans);
    }
}
