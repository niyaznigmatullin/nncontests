package lib.test.on2013_02.on2013_02_07_.TaskC;



import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Point2DInteger[] p = new Point2DInteger[4];
        for (int i = 0; i < 4; i++) {
            p[i] = new Point2DInteger(in.nextInt() * 2, in.nextInt() * 2);
        }
        p = GeometryAlgorithms.convexHull(p);
        Point2DInteger[] q = new Point2DInteger[4];
        for (int i = 0; i < 4; i++) {
            q[i] = p[i + 1 & 3].add(p[i]);
            q[i].x /= 2;
            q[i].y /= 2;
            Point2DInteger v = p[i + 1 & 3].subtract(p[i]);
            v = new Point2DInteger(v.y, -v.x);
            v.x /= 2;
            v.y /= 2;
            q[i] = q[i].add(v);
        }
        Point2DInteger[] vq =new Point2DInteger[4];
        for (int i = 0; i < 4; i++) {
            vq[i] = q[i + 1 & 3].subtract(q[i]);
        }
        if (vq[0].vmul(vq[2]) != 0 || vq[1].vmul(vq[3]) != 0) {
            out.println("OTHERWISE");
            return;
        }
        if (vq[0].smul(vq[1]) != 0 || vq[1].smul(vq[2]) != 0 || vq[2].smul(vq[3]) != 0 || vq[3].smul(vq[0]) != 0) {
            out.println("PARALLELOGRAM");
            return;
        }
        if (vq[0].squaredLength() != vq[2].squaredLength() || vq[1].squaredLength() != vq[2].squaredLength()) {
            out.println("RECTANGLE");
            return;
        }
        out.println("SQUARE");
    }
}
