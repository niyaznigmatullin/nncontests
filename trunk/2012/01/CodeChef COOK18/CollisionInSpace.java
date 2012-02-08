package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

public class CollisionInSpace {


    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};

    static final String DIRS = "RULD";

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int xe = in.nextInt();
        int ye = in.nextInt();
        int dire = DIRS.indexOf(in.next());
        int n = in.nextInt();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int dir = DIRS.indexOf(in.next());
            if (dir == dire) {
                continue;
            }
            if (dir == ((dire + 2) & 3)) {
                int vx = x - xe;
                int vy = y - ye;
                if (Integer.signum(vx) == Integer.signum(DX[dire]) && Integer.signum(vy) == Integer.signum(DY[dire])) {
                    if (DX[dire] == 0) {
                        ans = Math.min(ans, Math.abs(vy));
                    } else {
                        ans = Math.min(ans, Math.abs(vx));
                    }
                }
                continue;
            }
            int xc = DX[dire] == 0 ? xe : x;
            int yc = DY[dire] == 0 ? ye : y;
            int dist1 = getDist(xe, ye, xc, yc, dire);
            int dist2 = getDist(x, y, xc, yc, dir);
            if (dist1 != dist2 || dist1 == Integer.MAX_VALUE || dist2 == Integer.MAX_VALUE) {
                continue;
            }
            ans = Math.min(ans, dist1 * 2);
        }
        out.println(ans == Integer.MAX_VALUE ? "SAFE" : (ans / 2 + "." + (ans % 2 == 0 ? 0 : 5)));
    }

    static int getDist(int x1, int y1, int x2, int y2, int dir) {
        int vx = x2 - x1;
        int vy = y2 - y1;
        if (Integer.signum(DX[dir]) != Integer.signum(vx) || Integer.signum(DY[dir]) != Integer.signum(vy)) {
            return Integer.MAX_VALUE;
        }
        return Math.abs(vx) + Math.abs(vy);
    }
}
