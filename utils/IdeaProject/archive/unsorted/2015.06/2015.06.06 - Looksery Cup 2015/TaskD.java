package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = in.readCharacterFieldTokens(n, m);
        long[][] ans = new long[n][m];
        int ansC = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                long need = c[i][j] == 'W' ? 1 : -1;
                need = need - ans[i][j];
                if (need != 0) {
                    for (int x = i; x >= 0; x--)
                        for (int y = j; y >= 0; y--) {
                            ans[x][y] += need;
                        }
                    ++ansC;
                }
            }
        }
        out.println(ansC);
    }
}
