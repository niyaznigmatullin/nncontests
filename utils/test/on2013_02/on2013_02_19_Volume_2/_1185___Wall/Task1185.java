package lib.test.on2013_02.on2013_02_19_Volume_2._1185___Wall;



import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task1185 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int r = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            int x0 = in.nextInt();
            int y0 = in.nextInt();
            p[i] = new Point2DInteger(x0, y0);
        }
        p = GeometryAlgorithms.convexHull(p);
        double ans = 0;
        for (int i = 0; i < p.length; i++) {
            ans += p[i].distance(p[(i + 1) % p.length]);
        }
        out.println(Math.round(ans + 2 * Math.PI * r));
    }
}
