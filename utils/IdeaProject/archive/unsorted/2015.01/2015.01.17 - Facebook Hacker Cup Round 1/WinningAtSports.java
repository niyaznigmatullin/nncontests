package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class WinningAtSports {

    static int[][] F;
    static final int MAXN = 2222;
    static final int MOD = 1000000007;
    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static {
        F = new int[MAXN][MAXN];
        F[1][0] = 1;
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < i; j++) {
                if (j + 1 < i) {
                    F[i][j + 1] = add(F[i][j + 1], F[i][j]);
                }
                if (i + 1 < MAXN) {
                    F[i + 1][j] = add(F[i + 1][j], F[i][j]);
                }
            }
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String[] line = in.next().split("-");
        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[1]);
        out.println("Case #" + testNumber + ": " + F[a][b] + " " + F[b + 1][b]);
    }
}
