package mypackage;

import DataStructures.Fenwick2D;
import niyazio.FastScanner;
import niyazio.FastPrinter;

public class Annoying {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int r = in.nextInt();
        int c = in.nextInt();
        if (n == 0 && m == 0 && r == 0 && c == 0) {
            throw new UnknownError();
        }
        char[][] f = new char[n][];
        for (int i = 0; i < n; i++)
            f[i] = in.next().toCharArray();

        int ans = 0;
        for (int i = 0; i <= n - r; i++)
            for (int j = 0; j <= m - c; j++) {
                if (f[i][j] == '1') {
                    ans++;
                    for (int x = i; x < i + r; x++)
                        for (int y = j; y < j + c; y++) {
                            f[x][y] = (char) ('0' ^ '1' ^ f[x][y]);
                        }
                }
            }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (f[i][j] == '1') {
                    out.println(-1);
                    return;
                }

        out.println(ans);

	}
}
