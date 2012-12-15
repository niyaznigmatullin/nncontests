package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TheForbiddenPath {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int x = 0;
        int y = in.nextInt() - 1;
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        int[][] was = new int[n][m];
        for (int[] d : was) {
            Arrays.fill(d, -1);
        }
        for (int currentStep = 0; ; currentStep++) {
            if (x < 0 || y < 0 || x >= n || y >= m) {
                out.println(currentStep + "E");
                return;
            }
            if (was[x][y] >= 0) {
                out.println(currentStep - was[x][y] + "L");
                return;
            }
            was[x][y] = currentStep;
            int dir = DIRS.indexOf(c[x][y]);
            x += DX[dir];
            y += DY[dir];
        }
	}

    static final String DIRS = "SENW";
    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};
}
