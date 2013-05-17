package lib.test.on2013_05.on2013_05_03_.E2;



import ru.ifmo.niyaz.geometry.Point3DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class E2 {
    static final double EPS = 1e-7;
    static final DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) < EPS ? 0 : a < b ? -1 : 1;
        }
    };

    static final Comparator<Point3DDouble> byC = new Comparator<Point3DDouble>() {
        @Override
        public int compare(Point3DDouble o1, Point3DDouble o2) {
            int c = comp.compare(o1.x, o2.x);
            if (c != 0) return c;
            c = comp.compare(o1.y, o2.y);
            if (c != 0) return c;
            c = comp.compare(o1.z, o2.z);
            if (c != 0) return c;
            return 0;
        }
    };

    static final Random rand = new Random(System.nanoTime());

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point3DDouble[] points = new Point3DDouble[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point3DDouble(in.nextDouble(), in.nextDouble(), in.nextDouble());
        }
        all: while (true) {
            {
                double ang = rand.nextDouble();
                double sin = Math.sin(ang);
                double cos = Math.cos(ang);
                for (int i = 0; i < n; i++) {
                    double x = points[i].x * cos - points[i].y * sin;
                    double y = points[i].x * sin + points[i].y * cos;
                    points[i].x = x;
                    points[i].y = y;
                }
            }
            {
                double ang = rand.nextDouble();
                double sin = Math.sin(ang);
                double cos = Math.cos(ang);
                for (int i = 0; i < n; i++) {
                    double x = points[i].x * cos - points[i].z * sin;
                    double z = points[i].x * sin + points[i].z * cos;
                    points[i].x = x;
                    points[i].z = z;
                }
            }
            Point3DDouble[] q = points.clone();
            Arrays.sort(q, byC);
            for (int i = 1; i < n; i++) {
                if (Math.abs(q[i].x - q[i - 1].x) < 1e-5) {
                    continue all;
                }
            }
            break;
        }
        Point3DDouble a = points[0];
        Point3DDouble b = null;
        for (int i = 1; i < n; i++) {
            if (b == null || a.distanceSquared(b) > a.distanceSquared(points[i])) {
                b = points[i];
            }
        }
        Point3DDouble[] s1 = new Point3DDouble[n];
        Point3DDouble[] s2 = points.clone();
        Arrays.sort(s2, byC);
        for (int i = 0; i < n; i++) {
            s1[i] = new Point3DDouble(0, 0, 0);
        }
        int answer = 0;
        Point3DDouble v1 = a;
        Point3DDouble v22 = b;
        Point3DDouble v0 = v1.vmul(v22);
        v0 = v0.multiply(1. / v0.length());
        Point3DDouble v2 = v0.vmul(v1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j /*|| opposite(points[i], points[j])*/) {
                    continue;
                }
                if (Math.abs(a.distanceSquared(b) - points[i].distanceSquared(points[j])) > 1e-10) continue;
                Point3DDouble u1 = points[i];
                Point3DDouble u22 = points[j];
                Point3DDouble u0 = u1.vmul(u22);
                u0 = u0.multiply(1. / u0.length());
                Point3DDouble u2 = u0.vmul(u1);
                for (int k = 0; k < n; k++) {
                    Point3DDouble cp = points[k];
                    s1[k].x = u0.x * cp.smul(v0) + u1.x * cp.smul(v1) + u2.x * cp.smul(v2);
                    s1[k].y = u0.y * cp.smul(v0) + u1.y * cp.smul(v1) + u2.y * cp.smul(v2);
                    s1[k].z = u0.z * cp.smul(v0) + u1.z * cp.smul(v1) + u2.z * cp.smul(v2);
                }
                if (check(s1, s2)) {
                    ++answer;
                }
            }
        }
        out.println(answer);
    }

    static boolean check(Point3DDouble[] a, Point3DDouble[] b) {
        Arrays.sort(a, byC);
        for (int i = 0; i < a.length; i++) {
            if (!eq(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    static boolean eq(Point3DDouble a, Point3DDouble b) {
        return comp.compare(a.x, b.x) == 0 && comp.compare(a.y, b.y) == 0 && comp.compare(a.z, b.z) == 0;
    }


}
