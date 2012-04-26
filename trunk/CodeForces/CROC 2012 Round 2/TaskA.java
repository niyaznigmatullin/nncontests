package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];
        int[][] c = new int[n][m];
        for (int i = 0; i < n; i++) {
            in.next();
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
                b[i][j] = in.nextInt();
                c[i][j] = in.nextInt();
            }
        }
        boolean[] was = new boolean[m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int all = k;
                Arrays.fill(was, false);
                int profit = 0;
                while (all > 0) {
                    int min = -1;
                    for (int z = 0; z < m; z++) {
                        int val = b[j][z] - a[i][z];
                        if (was[z] || val <= 0) {
                            continue;
                        }
                        if (min < 0 || val > b[j][min] - a[i][min]) {
                            min = z;
                        }
                    }
                    if (min < 0) {
                        break;
                    }
                    int got = Math.min(all, c[i][min]);
                    all -= got;
                    profit += (b[j][min] - a[i][min]) * got;
                    was[min] = true;
                }
                ans = Math.max(ans, profit);
            }
        }
        out.println(ans);
    }
}
