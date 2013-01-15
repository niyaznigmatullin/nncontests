package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        final int MOD = 1000000007;
        x = Math.abs(x - in.nextInt());
        y = Math.abs(y - in.nextInt());
        z = Math.abs(z - in.nextInt());
        int max = x + y + z;
        int[][] c = new int[max + 1][max + 1];
        for (int i = 0; i < c.length; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
                if (c[i][j] >= MOD) {
                    c[i][j] -= MOD;
                }
            }
        }
        long answer = (long) c[x + y + z][x] * c[y + z][y];
        out.println(answer % MOD);
    }
}
