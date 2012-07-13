package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class ProblemD {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        int m = in.nextInt();
        int k = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            a[x][y] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                a[i][j] += a[i][j - 1];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] += a[i - 1][j];
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int l = 0;
                int r = Math.min(n - i, n - j) + 1;
                while (l < r - 1) {
                    int mid = l + r >> 1;
                    int count = a[i + mid - 1][j + mid - 1];
                    if (i > 0) {
                        count -= a[i - 1][j + mid - 1];
                    }
                    if (j > 0) {
                        count -= a[i + mid - 1][j - 1];
                    }
                    if (i > 0 && j > 0) {
                        count += a[i - 1][j - 1];
                    }
                    if (count <= k) {
                        l = mid;
                    } else {
                        r = mid;
                    }
                }
                answer = Math.max(answer, l);
            }
        }
        out.println(answer * answer);
	}
}
