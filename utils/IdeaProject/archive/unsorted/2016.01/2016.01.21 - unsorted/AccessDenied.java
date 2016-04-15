package coding;

import ru.ifmo.niyaz.geometry.Circle2DInteger;
import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class AccessDenied {

    static Point2DInteger readPoint(FastScanner in) {
        return new Point2DInteger((int) Math.round(in.nextDouble() * 100), (int) Math.round(in.nextDouble() * 100));
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int c = in.nextInt();
        int r = in.nextInt();
        int k = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = readPoint(in);
        }
        Point2DInteger[] workingPlaces = new Point2DInteger[c];
        for (int i = 0; i < c; i++) {
            workingPlaces[i] = readPoint(in);
        }
        Circle2DInteger[] wifi = new Circle2DInteger[r];
        for (int i = 0; i < r; i++) {
            wifi[i] = new Circle2DInteger(readPoint(in), (int) Math.round(in.nextDouble() * 100));
        }
        Circle2DInteger[] badDevices = new Circle2DInteger[k];
        for (int i = 0; i < k; i++) {
            badDevices[i] = new Circle2DInteger(readPoint(in), (int) Math.round(in.nextDouble() * 100));
        }
        int[] has = new int[c];
        for (Circle2DInteger w : wifi) {
            Point2DInteger v = new Point2DInteger(w.p.x + 1000000000, w.p.y + 1);
            int intersections = 0;
            for (int i = 0; i < n; i++) {
                if (GeometryAlgorithms.segmentsIntersect(w.p, v, p[i], p[(i + 1) % n])) {
                    ++intersections;
                }
            }
            if ((intersections & 1) == 1) {
                continue;
            }
            for (int i = 0; i < c; i++) {
                if (w.p.distanceSquared(workingPlaces[i]) < (long) w.radius * w.radius) {
                    has[i] |= 1;
                }
            }
        }
        for (Circle2DInteger e : badDevices) {
            for (int i = 0; i < c; i++) {
                if (e.p.distanceSquared(workingPlaces[i]) <= (long) e.radius * e.radius) {
                    has[i] |= 2;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < c; i++) {
            if (has[i] == 1) {
                ++ans;
            }
        }
        out.println(ans);
    }
}
