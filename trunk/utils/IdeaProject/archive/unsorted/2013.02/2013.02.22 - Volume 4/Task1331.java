package coding;

import ru.ifmo.niyaz.geometry.Point3DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task1331 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        double r = in.nextDouble();
        Point3DDouble[] p = new Point3DDouble[m];
        for (int i = 0; i < m; i++) {
            p[i] = readPoint(in);
        }
        for (int i = 0; i < n; i++) {
            Point3DDouble q = readPoint(in);
            double best = Double.POSITIVE_INFINITY;
            for (int j = 0; j < m; j++) {
                double dist = q.distanceSquared(p[j]);
                if (dist < best) {
                    best = dist;
                }
            }
            double d = Math.sqrt(best);
            out.println(2 * asin(d / 2) * r);
        }
    }

    static double asin(double x) {
        return x >= 1 ? Math.asin(1) : x <= -1 ? Math.asin(-1) : Math.asin(x);
    }

    private Point3DDouble readPoint(FastScanner in) {
        double lat = in.nextDouble() * Math.PI / 180;
        double lon = in.nextDouble() * Math.PI / 180;
        double z = Math.sin(lat);
        double x = Math.cos(lon) * Math.cos(lat);
        double y = Math.sin(lon) * Math.cos(lat);
        return new Point3DDouble(x, y, z);
    }
}
