package ru.ifmo.niyaz.geometry;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 14.01.13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public class GeometryAlgorithms {

    public static long vmulFromPoint(Point2DInteger from, Point2DInteger v, Point2DInteger u) {
        long x1 = v.x - from.x;
        long y1 = v.y - from.y;
        long x2 = u.x - from.x;
        long y2 = u.y - from.y;
        return x1 * y2 - x2 * y1;
    }

    public static double vmulFromPoint(Point2DDouble from, Point2DDouble v, Point2DDouble u) {
        double x1 = v.x - from.x;
        double y1 = v.y - from.y;
        double x2 = u.x - from.x;
        double y2 = u.y - from.y;
        return x1 * y2 - x2 * y1;
    }

    public static long smulFromPoint(Point2DInteger from, Point2DInteger v, Point2DInteger u) {
        long x1 = v.x - from.x;
        long y1 = v.y - from.y;
        long x2 = u.x - from.x;
        long y2 = u.y - from.y;
        return x1 * x2 + y1 * y2;
    }

    public static double smulFromPoint(Point2DDouble from, Point2DDouble v, Point2DDouble u) {
        double x1 = v.x - from.x;
        double y1 = v.y - from.y;
        double x2 = u.x - from.x;
        double y2 = u.y - from.y;
        return x1 * x2 + y1 * y2;
    }

    public static boolean isInsidePolygon(Point2DInteger[] p, Point2DInteger q) {
        long area = doubledArea(p);
        long area2 = 0;
        for (int i = 0; i < p.length; i++) {
            int j = i + 1;
            if (j == p.length) {
                j = 0;
            }
            area2 += Math.abs(vmulFromPoint(q, p[i], p[j]));
        }
        return area == area2;
    }

    public static boolean isStrictlyInsidePolygon(Point2DInteger[] p, Point2DInteger q) {
        if (doubledArea(p) == 0) {
            return false;
        }
        if (!isInsidePolygon(p, q)) {
            return false;
        }
        for (int i = 0; i < p.length; i++) {
            int j = i + 1;
            if (j == p.length) {
                j = 0;
            }
            if (GeometryAlgorithms.isOnLineSegment(q, p[i], p[j])) {
                return false;
            }
        }
        return true;
    }

    static public boolean isOnLineSegment(Point2DInteger q, Point2DInteger p1, Point2DInteger p2) {
        if (vmulFromPoint(q, p1, p2) != 0) {
            return false;
        }
        return (smulFromPoint(q, p1, p2) <= 0);
    }

    public static long doubledArea(Point2DInteger... p) {
        return Math.abs(signedDoubledArea(p));
    }

    public static long signedDoubledArea(Point2DInteger... p) {
        long ret = 0;
        for (int i = 0; i < p.length; i++) {
            int j = i + 1;
            if (j == p.length) {
                j = 0;
            }
            ret += p[i].vmul(p[j]);
        }
        return ret;
    }

    public static Point2DInteger[] convexHull(Point2DInteger[] p) {
        p = p.clone();
        if (p.length < 3) {
            return p;
        }
        for (int i = 1; i < p.length; i++) {
            if (p[i].x < p[0].x || p[i].x == p[0].x && p[i].y < p[0].y) {
                Point2DInteger t = p[i];
                p[i] = p[0];
                p[0] = t;
            }
        }
        final Point2DInteger first = p[0];
        Arrays.sort(p, 1, p.length, new Comparator<Point2DInteger>() {
            public int compare(Point2DInteger o1, Point2DInteger o2) {
                long d = vmulFromPoint(first, o1, o2);
                if (d == 0) {
                    return Long.signum(first.distanceSquared(o1) - first.distanceSquared(o2));
                }
                return -Long.signum(d);
            }
        });
        int cur = 2;
        for (int i = 2; i < p.length; i++) {
            Point2DInteger add = p[i];
            while (cur > 1) {
                if (vmulFromPoint(p[cur - 2], p[cur - 1], add) <= 0) {
                    --cur;
                    continue;
                }
                break;
            }
            p[cur++] = add;
        }
        return Arrays.copyOf(p, cur);
    }

    public static boolean segmentsIntersect(Point2DInteger p1, Point2DInteger p2, Point2DInteger q1, Point2DInteger q2) {
        long v1 = vmulFromPoint(p1, q1, q2);
        long v2 = vmulFromPoint(p2, q1, q2);
        long u1 = vmulFromPoint(q1, p1, p2);
        long u2 = vmulFromPoint(q2, p1, p2);
        if (v1 == 0 && v2 == 0 && u1 == 0 && u2 == 0) {
            return Math.max(Math.min(p1.x, p2.x), Math.min(q1.x, q2.x)) <= Math.min(Math.max(p1.x, p2.x), Math.max(q1.x, q2.x)) &&
                    Math.max(Math.min(p1.y, p2.y), Math.min(q1.y, q2.y)) <= Math.min(Math.max(p1.y, p2.y), Math.max(q1.y, q2.y));
        }
        return Long.signum(v1) != Long.signum(v2) && Long.signum(u1) != Long.signum(u2);
    }

    public static double distanceToSegment(Point2DDouble p1, Point2DDouble p2, Point2DDouble q) {
        return Math.sqrt(distanceToSegmentSquared(p1, p2, q));
    }

    public static double distanceToSegmentSquared(Point2DDouble p1, Point2DDouble p2, Point2DDouble q) {
        {
            double a = q.distanceSquared(p1);
            double b = q.distanceSquared(p2);
            double c = p1.distanceSquared(p2);
            if (a + c < b) {
                return a;
            }
            if (b + c < a) {
                return b;
            }
        }
        {
            double la = p2.y - p1.y;
            double lb = p1.x - p2.x;
            double lc = -p1.x * la - p1.y * lb;
            double ld = la * la + lb * lb;
            double d = la * q.x + lb * q.y + lc;
            return d * d / ld;
        }
    }

    public static double distanceToLine(Point2DDouble p1, Point2DDouble p2, Point2DDouble q) {
        double la = p2.y - p1.y;
        double lb = p1.x - p2.x;
        double lc = -p1.x * la - p1.y * lb;
        double ld = la * la + lb * lb;
        double d = la * q.x + lb * q.y + lc;
        return Math.abs(d) / Math.sqrt(ld);
    }
}
