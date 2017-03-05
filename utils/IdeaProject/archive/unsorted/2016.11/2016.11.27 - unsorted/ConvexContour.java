package coding;

import ru.ifmo.niyaz.geometry.*;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.Arrays;

public class ConvexContour {

//    static double getF(Point2DDouble p, Point2DDouble q, double angle) {
//        double y = q.y + Math.cos(angle) * .5;
//        double x = q.x - Math.sin(angle) * .5;
//        x -= p.x;
//        y -= p.y;
//        return Math.sqrt(x * x + y * y) + angle * .5;
//    }

    static DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) < 1e-7 ? 0 : a < b ? -1 : 1;
        }
    };

    static double getDistance(Point2DDouble p, Point2DDouble q) {
        Circle2DDouble c = new Circle2DDouble(q, 0.5);
        Point2DDouble[] ps = c.getTangents(p, comp);
        if (ps.length > 1) {
            if (ps[0].y < ps[1].y) {
                ps[0] = ps[1];
            }
            ps = Arrays.copyOf(ps, 1);
        }
        Point2DDouble need = ps[0];
        double angle = Math.atan2(need.y - q.y, need.x - q.x) - Math.PI * .5;
        return need.distance(p) + angle * .5;
//        double left = 0;
//        double right = Math.PI * .5;
//        for (int it = 0; it < 100; it++) {
//            double m1 = (left + left + right) / 3.;
//            double m2 = (left + right + right) / 3.;
//            if (getF(p, q, m1) < getF(p, q, m2)) right = m2; else left = m1;
//        }
//        double ret = getF(p, q, (left + right) * .5);
//        return ret;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        char[] c = in.next().toCharArray();
        int first = -1;
        int last = -1;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'T') continue;
            if (first < 0) first = i;
            last = i;
        }
        if (first < 0) {
            out.println(n + 2 + (n - 1));
            return;
        }
        double ans = 0;
        if (first > 0) {
            Point2DDouble top = new Point2DDouble(0.5, Math.sqrt(3.) * .5);
            if (c[first] == 'S') {
                ans += top.distance(new Point2DDouble(first, 1.));
            } else {
                ans += getDistance(top, new Point2DDouble(first + 0.5, 0.5));
            }
        }
        if (last + 1 < n) {
            Point2DDouble top = new Point2DDouble(-(n - 0.5), Math.sqrt(3) * .5);
            if (c[last] == 'S') {
                ans += top.distance(new Point2DDouble(-(last + 1), 1.));
            } else {
                ans += getDistance(top, new Point2DDouble(-(last + 0.5), 0.5));
            }
        }
        double bottom = n;
        if (c[0] == 'C') bottom -= 0.5;
        if (c[n - 1] == 'C') bottom -= 0.5;
        double side = 0;
        side += c[0] == 'C' ? Math.PI * .5 : 1;
        side += c[n - 1] == 'C' ? Math.PI * .5 : 1;
        if (c[first] == 'S') ans += 0.5;
        if (c[last] == 'S') ans += 0.5;
        ans += last - first;
        ans += side;
        out.println(ans + bottom);
    }
}
