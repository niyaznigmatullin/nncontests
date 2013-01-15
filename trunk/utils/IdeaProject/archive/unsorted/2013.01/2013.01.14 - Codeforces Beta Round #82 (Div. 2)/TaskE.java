package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.geometry.*;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskE {

    static final double EPS = 1e-8;
    final static DoubleComparator dblcmp = new DoubleComparator() {
        public int compare(double a, double b) {
            return Math.abs(a - b) < EPS ? 0 : a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point3DDouble[] p = new Point3DDouble[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point3DDouble(in.nextInt(), in.nextInt(), in.nextInt());
        }
        Circle3DDouble ans = minCircle1(p);
        Point3DDouble center = ans.p;
        out.println(center.x + " " + center.y + " " + center.z);
    }

    static Circle3DDouble minCircle1(Point3DDouble[] p) {
        ArrayUtils.shuffle(p);
        if (p.length == 1) {
            return getCircle(p[0]);
        }
        Circle3DDouble cur = getCircle(p[0], p[1]);
        for (int i = 2; i < p.length; i++) {
            double dist = cur.p.distance(p[i]);
            if (dblcmp.compare(dist, cur.radius) > 0) {
                Point3DDouble[] q = Arrays.copyOf(p, i);
                cur = minCircle2(q, p[i]);
            }
        }
        return cur;
    }

    static Circle3DDouble minCircle2(Point3DDouble[] p, Point3DDouble first) {
        ArrayUtils.shuffle(p);
        if (p.length == 1) {
            return getCircle(first, p[0]);
        }
        Circle3DDouble cur = getCircle(first, p[0]);
        for (int i = 1; i < p.length; i++) {
            double dist = cur.p.distance(p[i]);
            if (dblcmp.compare(dist, cur.radius) > 0) {
                Point3DDouble[] q = Arrays.copyOf(p, i);
                cur = minCircle3(q, first, p[i]);
            }
        }
        return cur;
    }

    static Circle3DDouble minCircle3(Point3DDouble[] p, Point3DDouble first, Point3DDouble second) {
        ArrayUtils.shuffle(p);
        Circle3DDouble cur = getCircle(first, second);
        for (int i = 0; i < p.length; i++) {
            double dist = cur.p.distance(p[i]);
            if (dblcmp.compare(dist, cur.radius) > 0) {
                Point3DDouble[] q = Arrays.copyOf(p, i);
                cur = minCircle4(q, first, second, p[i]);
            }
        }
        return cur;
    }

    static Circle3DDouble minCircle4(Point3DDouble[] p, Point3DDouble first, Point3DDouble second, Point3DDouble third) {
        ArrayUtils.shuffle(p);
        Circle3DDouble cur = getCircle(first, second, third);
        for (int i = 0; i < p.length; i++) {
            double dist = cur.p.distance(p[i]);
            if (dblcmp.compare(dist, cur.radius) > 0) {
                cur = getCircle(first, second, third, p[i]);
            }
        }
        return cur;
    }

    static Circle3DDouble getCircle(Point3DDouble p) {
        return new Circle3DDouble(p, 0);
    }

    static Circle3DDouble getCircle(Point3DDouble p, Point3DDouble q) {
        return new Circle3DDouble(p.add(q).multiply(0.5), p.distance(q) * .5);
    }

    static boolean contains(Circle3DDouble c, Point3DDouble p) {
        return dblcmp.compare(c.p.distance(p), c.radius) <= 0;
    }

    static Circle3DDouble getCircle(Point3DDouble p, Point3DDouble q, Point3DDouble r) {
        Point3DDouble v1 = q.subtract(p);
        Point3DDouble v2 = r.subtract(p);
        Point3DDouble v = v1.vmul(v2);
        Point3DDouble b1 = v1.multiply(1 / v1.length());
        Point3DDouble b2 = b1.vmul(v);
        b2 = b2.multiply(1 / b2.length());
        q = q.subtract(p);
        r = r.subtract(p);
        Point2DDouble p2 = new Point2DDouble(0, 0);
        Point2DDouble q2 = new Point2DDouble(q.smul(b1), q.smul(b2));
        Point2DDouble r2 = new Point2DDouble(r.smul(b1), r.smul(b2));
        Circle2DDouble circle = getCircle(p2, q2, r2);
        Point2DDouble center2d = circle.p;
        Point3DDouble center = p.add(b1.multiply(center2d.x)).add(b2.multiply(center2d.y));
        return new Circle3DDouble(center, center.distance(p));
    }

    static Circle3DDouble getCircleG(Point3DDouble p, Point3DDouble q, Point3DDouble r) {
        Point3DDouble v1 = q.subtract(p);
        Point3DDouble v2 = r.subtract(p);
        Point3DDouble v = v1.vmul(v2);
        Point3DDouble b1 = v1.multiply(1 / v1.length());
        Point3DDouble b2 = b1.vmul(v);
        b2 = b2.multiply(1 / b2.length());
        q = q.subtract(p);
        r = r.subtract(p);
        Point2DDouble p2 = new Point2DDouble(0, 0);
        Point2DDouble q2 = new Point2DDouble(q.smul(b1), q.smul(b2));
        Point2DDouble r2 = new Point2DDouble(r.smul(b1), r.smul(b2));
        Circle2DDouble circle = getCircleG(p2, q2, r2);
        Point2DDouble center2d = circle.p;
        Point3DDouble center = p.add(b1.multiply(center2d.x)).add(b2.multiply(center2d.y));
        return new Circle3DDouble(center, center.distance(p));
    }

    static Circle2DDouble getCircle(Point2DDouble p, Point2DDouble q, Point2DDouble r) {
        Line2DDouble line1 = Line2DDouble.getPerpendicularBisection(p, q);
        Line2DDouble line2 = Line2DDouble.getPerpendicularBisection(p, r);
        Point2DDouble center = line1.intersect(line2, dblcmp);
        double a = p.distanceSquared(q);
        double b = q.distanceSquared(r);
        double c = p.distanceSquared(r);
        if (a + b < c) {
            return new Circle2DDouble(p.add(r).multiply(.5), p.distance(r) * .5);
        }
        if (a + c < b) {
            return new Circle2DDouble(q.add(r).multiply(.5), q.distance(r) * .5);
        }
        if (c + b < a) {
            return new Circle2DDouble(p.add(q).multiply(.5), p.distance(q) * .5);
        }
        if (center == null) {
            throw new AssertionError();
        }
        return new Circle2DDouble(center, center.distance(p));
    }

    static Circle2DDouble getCircleG(Point2DDouble p, Point2DDouble q, Point2DDouble r) {
        Line2DDouble line1 = Line2DDouble.getPerpendicularBisection(p, q);
        Line2DDouble line2 = Line2DDouble.getPerpendicularBisection(p, r);
        Point2DDouble center = line1.intersect(line2, dblcmp);
        return new Circle2DDouble(center, center.distance(p));
    }

    static Circle3DDouble getCircle(Point3DDouble p, Point3DDouble q, Point3DDouble r, Point3DDouble s) {
        Point3DDouble v1 = q.subtract(p);
        Point3DDouble v2 = r.subtract(p);
        Point3DDouble v = v1.vmul(v2);
        Point3DDouble v3 = s.subtract(p);
        Point3DDouble u = v1.vmul(v3);
        List<Circle3DDouble> list = new ArrayList<Circle3DDouble>(1);
        Circle3DDouble circle1 = getCircle(p, q, r);
        if (contains(circle1, s)) {
            list.add(circle1);
        }
        Circle3DDouble circle2 = getCircle(p, q, s);
        if (contains(circle2, r)) {
            list.add(circle2);
        }
        Circle3DDouble circle3 = getCircle(p, r, s);
        if (contains(circle3, q)) {
            list.add(circle3);
        }
        Circle3DDouble circle4 = getCircle(q, r, s);
        if (contains(circle4, p)) {
            list.add(circle4);
        }
        if (dblcmp.compare(s.subtract(r).smul(v), 0) != 0) {
            Point3DDouble w = u.vmul(v);
            Point3DDouble b1 = v.multiply(1 / v.length());
            Point3DDouble b2 = b1.vmul(w);
            b2 = b2.multiply(1 / b2.length());
            Point3DDouble center1 = getCircleG(p, q, r).p;
            Point3DDouble center2 = getCircleG(p, q, s).p;
            Point3DDouble vec1 = p.subtract(q).vmul(r.subtract(q));
            Point3DDouble vec2 = p.subtract(q).vmul(s.subtract(q));
            center2 = center2.subtract(center1);
            Point2DDouble c22d = new Point2DDouble(b1.smul(center2), b2.smul(center2));
            Point2DDouble vec12d = new Point2DDouble(vec1.smul(b1), vec1.smul(b2));
            Point2DDouble vec22d = new Point2DDouble(vec2.smul(b1), vec2.smul(b2));
            Line2DDouble line1 = Line2DDouble.getUsingCodirectedVector(vec12d, new Point2DDouble(0, 0));
            Line2DDouble line2 = Line2DDouble.getUsingCodirectedVector(vec22d, c22d);
            Point2DDouble inter = line1.intersect(line2, dblcmp);
            if (inter == null) {
                throw new AssertionError();
            }
            Point3DDouble center = center1.add(b1.multiply(inter.x)).add(b2.multiply(inter.y));
            list.add(new Circle3DDouble(center, center.distance(p)));
        }
        Circle3DDouble ret = null;
        for (Circle3DDouble e : list) {
            if (ret == null || ret.radius > e.radius) {
                ret = e;
            }
        }
        return ret;
    }

}
