package mypackage;

import niyazio.FastScanner;
import sun.jvm.hotspot.debugger.dbx.x86.DbxX86Thread;

import java.io.PrintWriter;

public class TaskB {

    static int n;
    static int m;
    static int ans;

    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        if (n > m) {
            int t = n;
            n = m;
            m = t;
        }
        if (n == 1) {
            out.println(m);
            return;
        }
        if (n == 2) {
            int x = m / 4;
            int y = Math.min(m % 4, 2);
            out.println(2 * (x * 2 + y));
            return;
        }
        out.println((n * m + 1) / 2);
    }

    static int[][] can;

    static void go(int current, int got) {
        ans = Math.max(ans, got);
        if (current == n * m) {
            return;
        }
        int x = current / m;
        int y = current % m;
        go(current + 1, got);
        if (can[x][y] > 0) {
            return;
        }
        for (int dir = 0; dir < 8; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }
            can[nx][ny]++;
        }
        go(current + 1, got + 1);
        for (int dir = 0; dir < 8; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }
            can[nx][ny]--;
        }
    }

    static int[] DX = {1, 2, 1, 2, -1, -2, -1, -2};
    static int[] DY = {2, 1, -2, -1, 2, 1, -2, -1};
}
