package coding;

import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashSet;
import java.util.Set;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point2DInteger[] p = getIntegers(in, n);
        int m = in.nextInt();
        boolean[] ans = new boolean[n];
        for (int i = 0; i < m; i++) {
            Point2DInteger e = new Point2DInteger(in.nextInt(), in.nextInt());
            for (int j = 0; j < n; j++) {
                int dx = Math.abs(e.x- p[j].x);
                int dy = Math.abs(e.y- p[j].y);
                if (dx <= 50 && dy <= 50) {
                    ans[j] = true;
                }
            }
        }
        int a = 0;
        for (boolean e : ans) {
            a += e ? 1 : 0;
        }
        out.println(a);
    }

    private Point2DInteger[] getIntegers(FastScanner in, int n) {
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        return p;
    }
}
