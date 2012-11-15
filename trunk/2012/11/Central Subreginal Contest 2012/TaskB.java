package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class TaskB {

    final int[] DX = {1, 0, -1, 0};
    final int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '-') {
                    continue;
                }
                boolean isCoast = false;
                for (int dir = 0; dir < 4; dir++) {
                    int x = i + DX[dir];
                    int y = j + DY[dir];
                    if (c[x][y] == '-') {
                        isCoast = true;
                    }
                }
                if (isCoast) {
                    ++answer;
                }
            }
        }
        out.println(answer);
    }
}
