package lib.test.on2013_02.on2013_02_22_Volume_4._1351___Good_Gnusmas___Dead_Gnusmas;



import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Comparator;

public class Task1351 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int d = in.nextInt();
        d *= d;
        Point2DInteger p1 = readPoint(in);
        Point2DInteger p2 = readPoint(in);
        int n = in.nextInt();
        Comparator<Point2DInteger> comp = new Comparator<Point2DInteger>() {
            @Override
            public int compare(Point2DInteger o1, Point2DInteger o2) {
                if (get(o1) != get(o2)) {
                    return get(o1) - get(o2);
                }
                return -Long.signum(o1.vmul(o2));
            }
        };
        for (int i = 0; i < n; i++) {
            Point2DInteger q = readPoint(in);
            if (q.squaredLength() <= d) {
                if (comp.compare(p1, p2) > 0) {
                    if (comp.compare(p2, q) >= 0 || comp.compare(q, p1) >= 0) {
                        out.println("YES");
                        continue;
                    }
                } else if (comp.compare(p1, q) <= 0 && comp.compare(q, p2) <= 0) {
                    out.println("YES");
                    continue;
                }
            }
            out.println("NO");
        }
    }

    Point2DInteger readPoint(FastScanner in) {
        return new Point2DInteger(in.nextInt(), in.nextInt());
    }

    static int get(Point2DInteger p) {
        int x = p.x;
        int y = p.y;
        if (x > 0) {
            return y > 0 ? 2 : y < 0 ? 8 : 1;
        } else if (x < 0) {
            return y > 0 ? 4 : y < 0 ? 6 : 5;
        } else {
            return y > 0 ? 3 : y < 0 ? 7 : 0;
        }
    }

}
