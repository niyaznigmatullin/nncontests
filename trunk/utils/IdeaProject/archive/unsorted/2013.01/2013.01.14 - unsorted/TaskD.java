package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskD {

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        int xh = in.nextInt();
        int yh = in.nextInt();
        int moves = in.nextInt();
        if (x0 == xh && y0 == yh) {
            out.println(1);
            return;
        }
        double[][] p = new double[n][m];
        p[x0][y0] = 1.;
        double[][] np = new double[n][m];
        double answer = 0;
        for (int move = 0; move < moves; move++) {
            for (double[] d : np) {
                Arrays.fill(d, 0);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int count = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int x = i + DX[dir];
                        int y = j + DY[dir];
                        if (x < 0 || y < 0 || x >= n || y >= m) {
                            continue;
                        }
                        ++count;
                    }
                    double add = p[i][j] / count;
                    for (int dir = 0; dir < 4; dir++) {
                        int x = i + DX[dir];
                        int y = j + DY[dir];
                        if (x < 0 || y < 0 || x >= n || y >= m) {
                            continue;
                        }
                        np[x][y] += add;
                    }
                }
            }
            answer += np[xh][yh];
            np[xh][yh] = 0;
            double[][] t = p;
            p = np;
            np = t;
        }
        out.println(answer);
    }
}
