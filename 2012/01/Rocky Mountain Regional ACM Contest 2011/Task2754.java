package mypackage;

import arrayutils.ArrayUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task2754 {

    static final int[] DX = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] DY = {-1, -1, 0, 1, 1, 1, 0, -1};

    static boolean[][] was;
    static char[][] c;
    static int n;
    static int m;

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        m = in.nextInt();
        if (n == 0 && m == 0) {
            throw new UnknownError();
        }
        c = new char[n][m];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        List<Integer> answer = new ArrayList<Integer>();
        was = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (was[i][j] || c[i][j] == '0') {
                    continue;
                }
                int got = dfs(i, j);
                if (got < 5) {
                    continue;
                }
                answer.add(doIt(i, j));
            }
        }
        Collections.sort(answer);
        out.println("Case " + testNumber);
        if (answer.isEmpty()) {
            out.println("no objects found");
        } else {
            out.printArray(ArrayUtils.toPrimitiveArray(answer));
        }
	}

    static int dfs(int i, int j) {
        was[i][j] = true;
        int ret = 1;
        for (int dir = 0; dir < DX.length; dir++) {
            int x = i + DX[dir];
            int y = j + DY[dir];
            if (x < 0 || y < 0 || x >= n || y >= m || was[x][y] || c[x][y] == '0') {
                continue;
            }
            ret += dfs(x, y);
        }
        return ret;
    }

    static int doIt(int b0x, int b0y) {
        int c0x = b0x + DX[0];
        int c0y = b0y + DY[0];
        int b1x = -1;
        int b1y = -1;
        int c1x = -1;
        int c1y = -1;
        for (int dir = 0; dir < DX.length; dir++) {
            if (c[DX[dir] + b0x][DY[dir] + b0y] == '1') {
                b1x = DX[dir] + b0x;
                b1y = DY[dir] + b0y;
                c1x = DX[dir - 1] + b0x;
                c1y = DY[dir - 1] + b0y;
                break;
            }
            if (dir == DX.length - 1) {
                throw new AssertionError();
            }
        }
        int ans = 2;
        int curBx = b1x;
        int curBy = b1y;
        int curCx = c1x;
        int curCy = c1y;
        while (true) {
            int firstDir = 0;
            while (curCx != DX[firstDir] + curBx || DY[firstDir] + curBy != curCy) {
                firstDir++;
            }
            boolean ok = curBx == b0x && curBy == b0y;
            for (int sh = 0; sh < DX.length; sh++) {
                int dir = (firstDir + sh) % DX.length;
                int x = DX[dir] + curBx;
                int y = DY[dir] + curBy;
                if (c[x][y] == '1') {
                    ans++;
                    curCx = DX[(dir + DX.length - 1) % DX.length] + curBx;
                    curCy = DY[(dir + DX.length - 1) % DX.length] + curBy;
                    curBx = x;
                    curBy = y;
                    break;
                }
            }
            if (ok && curBx == b1x && curBy == b1y) {
                ans -= 2;
                break;
            }
        }
        return ans;
    }
}
