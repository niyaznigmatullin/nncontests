package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskA {

    static final int[] DX = {1, 0, 0, 0, 0, -1};
    static final int[] DY = {0, 1, -1, 0, 0, 0};
    static final int[] DZ = {0, 0, 0, 1, -1, 0};
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        char[][][] a = new char[k][n][m];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.next().toCharArray();
            }
        }
        int answer = 0;
        for (int i = 1; i + 1 < k; i++) {
            for (int j = 1; j + 1 < n; j++) {
                for (int t = 1; t + 1 < m; t++) {
                    if (a[i][j][t] != '1') {
                        continue;
                    }
                    boolean ok = true;
                    for (int dir = 0; dir < 6; dir++) {
                        if (a[i + DX[dir]][j + DY[dir]][t + DZ[dir]] != '1') {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        ++answer;
                    }
                }
            }
        }
        out.println(answer);
	}
}
