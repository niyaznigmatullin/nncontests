package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskD {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        boolean[][] dp = new boolean[n][m];
        for (int i = n - 2; i >= 1; i--) {
            for (int j = m - 2; j >= 1; j--) {
                if (c[i][j] == '#') {
                    continue;
                }
                if (i + 1 < n && c[i + 1][j] != '#' && !dp[i + 1][j]) {
                    dp[i][j] = true;
                }
                if (j + 1 < m && c[i][j + 1] != '#' && !dp[i][j + 1]) {
                    dp[i][j] = true;
                }
            }
        }
        out.println(!dp[1][1] ? "Petr" : "Gennady");
	}
}
