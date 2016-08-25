package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Matrix;

import java.util.Arrays;

public class LeastCommonMultiple {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        long n = in.nextLong();
        int mod = in.nextInt();
        int NMOD = 4 * 7 * 9;
        int[][] a = new int[NMOD][NMOD];
        for (int curMod = 0; curMod < NMOD; curMod++) {
            for (int dig = 1; dig < 10; dig++) {
                int nMod = curMod * 10 + dig;
                if (dig != 5) {
                    if (nMod % dig != 0) continue;
                }
                nMod %= NMOD;
                a[curMod][nMod]++;
                a[curMod][nMod] %= mod;
            }
        }
        for (int i = 0; i < NMOD; i++) {
            boolean ok = true;
            for (int j = 0; j < NMOD; j++) {
                if (a[i][j] != 0 || a[j][i] != 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) System.out.println(i);
        }
        Matrix m = new Matrix(a);
        m = Matrix.powMod(m, n, mod);
        int ans = 0;
        for (int i = 0; i < NMOD; i++) {
            ans = (ans + m.get(0, i)) % mod;
        }
        out.println(ans);
    }
}
