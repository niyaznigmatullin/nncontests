package coding;

import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class BikingDuck {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        v1 = in.nextInt();
        v2 = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        q = new Point2DDouble[]{new Point2DDouble(x1, y1), new Point2DDouble(x2, y1), new Point2DDouble(x2, y2), new Point2DDouble(x1, y2)};
        start = new Point2DDouble(in.nextInt(), in.nextInt());
        finish = new Point2DDouble(in.nextInt(), in.nextInt());
        int n = in.nextInt();
        p = new Point2DDouble[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DDouble(in.nextInt(), in.nextInt());
        }
        double ans = start.distance(finish) / v1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.min(ans, (start.distance(p[i]) + p[j].distance(finish)) / v1 + p[i].distance(p[j]) / v2);
            }
        }
        for (int zz = 0; zz < 2; zz++) {
            for (int i = 0; i < n; i++) {
                f[0].x = p[i].x;
                f[0].y = p[i].y;
                for (int dir = 0; dir < 4; dir++) {
                    dd[1] = dir;
                    ans = Math.min(ans, go(1));
                }
            }
            Point2DDouble t = start;
            start = finish;
            finish = t;
        }
        for (int i = 0; i < 4; i++) {
            dd[0] = i;
            for (int j = 0; j < 4; j++) {
                dd[1] = j;
                ans = Math.min(ans, go(0));
            }
        }
        out.println(ans);
    }

    static Point2DDouble[] f = new Point2DDouble[]{new Point2DDouble(0, 0), new Point2DDouble(0, 0)};
    static int[] dd = new int[2];
    static Point2DDouble[] p;
    static Point2DDouble[] q;
    static Point2DDouble start;
    static Point2DDouble finish;

    static double v1;
    static double v2;

    static double go(int z) {
        if (z == 2) {
            return (start.distance(f[0]) + finish.distance(f[1])) / v1 + f[0].distance(f[1]) / v2;
        }
        Point2DDouble a = q[dd[z]];
        Point2DDouble b = q[dd[z] + 1 & 3];
        double left = 0;
        double right = 1.;
        for (int it = 0; it < 100; it++) {
            double m1 = (left + left + right) / 3.;
            f[z].x = a.x * m1 + b.x * (1 - m1);
            f[z].y = a.y * m1 + b.y * (1 - m1);
            double f1 = go(z + 1);
            double m2 = (left + right + right) / 3.;
            f[z].x = a.x * m2 + b.x * (1 - m2);
            f[z].y = a.y * m2 + b.y * (1 - m2);
            double f2 = go(z + 1);
            if (f1 > f2) {
                left = m1;
            } else {
                right = m2;
            }
        }
        double m = (left + right) * .5;
        f[z].x = a.x * m + b.x * (1 - m);
        f[z].y = a.y * m + b.y * (1 - m);
        return go(z + 1);
    }
}
