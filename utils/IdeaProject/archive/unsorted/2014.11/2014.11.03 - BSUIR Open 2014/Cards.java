package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class Cards {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        BigInteger best = BigInteger.ZERO;
        int ans = -1;
        BigInteger[][] C = new BigInteger[n + 1][n + 1];
        for (BigInteger[] e : C) {
            Arrays.fill(e, BigInteger.ZERO);
        }
        for (int i = 0; i <= n; i++) {
            C[i][0] = BigInteger.ONE;
            for (int j = 1; j <= i; j++) C[i][j] = C[i - 1][j - 1].add(C[i - 1][j]);
        }
        BigInteger[] F = new BigInteger[n + 1];
        F[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            F[i] = F[i - 1].multiply(BigInteger.valueOf(i));
        }
        for (int x = 0; x < n; x++) {
            BigInteger cur = BigInteger.ZERO;
            BigInteger X = BigInteger.valueOf(x + 1);
            for (int m = 0; m + 1 < n; m++) {
                for (int f = x + 1; f < n; f++) {
                    cur = cur.add(X.multiply(C[m][f - 1]).multiply(F[f - 1]).multiply(F[n - f - 1]));
                }
            }
            if (cur.compareTo(best) > 0) {
                best = cur;
                ans = x;
            }
        }
        out.println(ans + 1);
    }
}
