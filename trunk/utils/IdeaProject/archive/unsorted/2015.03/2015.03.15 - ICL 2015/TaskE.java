package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskE {

    static double f(Point2DInteger[] p, double ang) {
        double sin = Math.sin(ang);
        double cos = Math.cos(ang);
        double maxX = Double.NEGATIVE_INFINITY;
        double minX = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        for (Point2DInteger e : p) {
            double x = e.x * cos - e.y * sin;
            double y = e.x * sin + e.y * cos;
            maxX = Math.max(maxX, x);
            minX = Math.min(minX, x);
            maxY = Math.max(maxY, y);
            minY = Math.min(minY, y);
        }
//        if ((maxX - minX) * (maxY - minY) < 25) System.out.println((maxX - minX) * (maxY - minY));
        return (maxX - minX) * (maxY - minY);
    }

    static Point2DDouble[] restore(Point2DInteger[] p, double ang) {
        double sin = Math.sin(ang);
        double cos = Math.cos(ang);
        double maxX = Double.NEGATIVE_INFINITY;
        double minX = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        for (Point2DInteger e : p) {
            double x = e.x * cos - e.y * sin;
            double y = e.x * sin + e.y * cos;
            maxX = Math.max(maxX, x);
            minX = Math.min(minX, x);
            maxY = Math.max(maxY, y);
            minY = Math.min(minY, y);
        }
        double[] ax = new double[]{minX, minX, maxX, maxX};
        double[] ay = new double[]{maxY, minY, minY, maxY};
        Point2DDouble[] ret = new Point2DDouble[4];
        sin = -sin;
        for (int i = 0; i < 4; i++) {
            ret[i] = new Point2DDouble(ax[i] * cos - ay[i] * sin, ax[i] * sin + ay[i] * cos);
        }
        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
        }
        p = GeometryAlgorithms.convexHull(p);
        final int IT = 50;
        double ans = Double.POSITIVE_INFINITY;
        double best = 0;
        for (int it = 0; it < IT; it++) {
            double left = Math.PI * .5 * it / IT;
            double right = Math.PI * .5 * (it + 1) / IT;
            for (int it2 = 0; it2 < 100; it2++) {
                double m1 = (left + left + right) / 3;
                double m2 = (left + right + right) / 3;
                if (f(p, m1) > f(p, m2)) {
                    left = m1;
                } else {
                    right = m2;
                }
            }
            double ang = (left + right) * .5;
            double cur = f(p, ang);
            if (cur < ans) {
                ans = cur;
                best = ang;
            }
        }
//        System.out.println(ans);
        for (Point2DDouble e : restore(p, best)) {
            out.println(e.x + " " + e.y);
        }
    }
}
