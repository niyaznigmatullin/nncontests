package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int k1 = in.nextInt();
        int k2 = in.nextInt();
        boolean[][][] win = new boolean[2][n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                for (int e = 1; e <= k1 && e <= i; e++) {
                    win[0][i][j] |= !win[1][i - e][j];
                }
                for (int e = 1; e <= k2 && e <= j; e++) {
                    win[1][i][j] |= !win[0][i][j - e];
                }
            }
        }
        out.println(win[0][n1][n2] ? "First" : "Second");
    }
}
