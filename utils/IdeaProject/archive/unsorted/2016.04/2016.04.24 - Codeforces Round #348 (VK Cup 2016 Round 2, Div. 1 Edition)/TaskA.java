package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[][] ans = new int[n][m];
        int[][] id = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                id[i][j] = i * m + j;
            }
        }
        for (int i = 0; i < q; i++) {
            int t = in.nextInt();
            if (t == 1) {
                int row = in.nextInt() - 1;
                int tmp = id[row][0];
                for (int j = 0; j + 1 < m; j++) {
                    id[row][j] = id[row][j + 1];
                }
                id[row][m - 1] = tmp;
            } else if (t == 2) {
                int col = in.nextInt() - 1;
                int tmp = id[0][col];
                for (int j = 0; j + 1 < n; j++) {
                    id[j][col] = id[j + 1][col];
                }
                id[n - 1][col] = tmp;
            } else {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                int z = in.nextInt();
                ans[id[x][y] / m][id[x][y] % m] = z;
            }
        }
        for (int[] e : ans) {
            out.printArray(e);
        }
    }
}
