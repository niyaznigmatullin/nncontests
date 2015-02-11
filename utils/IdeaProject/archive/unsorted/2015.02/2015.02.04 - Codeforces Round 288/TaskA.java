package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        boolean[][] a = new boolean[n + 2][m + 2];
        boolean ans = true;
        for (int i = 0; i < k; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            a[x][y] = true;
            for (int dx = -1; dx <= 0; dx++) {
                for (int dy = -1; dy <= 0; dy++) {
                    if (a[x + dx][y + dy] && a[x + dx + 1][y + dy] && a[x + dx][y + dy + 1] && a[x + dx + 1][y + dy + 1]) {
                        ans = false;
                    }
                }
            }
            if (!ans) {
                out.println(i + 1);
                return;
            }
        }
        out.println(0);
    }
}
