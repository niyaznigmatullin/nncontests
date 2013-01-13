package ru.ifmo.niyaz.geometry;


/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 19.01.12
 * Time: 3:40
 * To change this template use File | Settings | File Templates.
 */
public class Point2DInteger {
    public int x;
    public int y;

    public Point2DInteger(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public long distanceSquared(Point2DInteger p) {
        long dx = x - p.x;
        long dy = y - p.y;
        return dx * dx + dy * dy;
    }

    public double distance(Point2DInteger p) {
        return Math.sqrt(distanceSquared(p));
    }

    public Point2DInteger add(Point2DInteger p) {
        return new Point2DInteger(x + p.x, y + p.y);
    }

    public Point2DInteger subtract(Point2DInteger p) {
        return new Point2DInteger(x - p.x, y - p.y);
    }

    static public Point2DInteger[] minkovskySum(Point2DInteger[] p, Point2DInteger[] q) {
        p = normalizePolygonArray(p);
        q = normalizePolygonArray(q);
        int n = p.length;
        int m = q.length;
        Point2DInteger[] ret = new Point2DInteger[n + m];
        ret[0] = p[0].add(q[0]);
        for (int i = 0, j = 0; i + j + 1 < n + m; ) {
            int i2 = (i + 1) % n;
            int j2 = (j + 1) % m;
            if (j >= m || i < n &&
                    (long) (p[i2].x - p[i].x) * (q[j2].y - q[j].y) - (long) (p[i2].y - p[i].y) * (q[j2].x - q[j].x) >= 0) {
                ret[i + j + 1] = ret[i + j].add(p[i2]).subtract(p[i]);
                i++;
            } else {
                ret[i + j + 1] = ret[i + j].add(q[j2]).subtract(q[j]);
                j++;
            }
        }
        return ret;
    }

    public long smul(Point2DInteger p) {
        return (long) x * p.x + (long) y * p.y;
    }

    public long vmul(Point2DInteger p) {
        return (long) x * p.y - (long) y * p.x;
    }

    private static Point2DInteger[] normalizePolygonArray(Point2DInteger[] p) {
        long area = 0;
        int n = p.length;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            area += (long) p[i].x * p[j].y - (long) p[i].y * p[j].x;
        }
        if (area < 0) {
            for (int i = 1, j = n - 1; i < j; i++, j--) {
                Point2DInteger tmp = p[i];
                p[i] = p[j];
                p[j] = tmp;
            }
        }
        int minIndex = -1;
        for (int i = 0; i < n; i++) {
            if (minIndex < 0 || p[i].y < p[minIndex].y || p[i].y == p[minIndex].y && p[i].x < p[minIndex].x) {
                minIndex = i;
            }
        }
        Point2DInteger[] ret = new Point2DInteger[n];
        System.arraycopy(p, minIndex, ret, 0, p.length - minIndex);
        System.arraycopy(p, 0, ret, p.length - minIndex, minIndex);
        return ret;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2DInteger that = (Point2DInteger) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


    public boolean isOnSegment(Point2DInteger p, Point2DInteger q) {
        Point2DInteger v1 = subtract(p);
        Point2DInteger v2 = subtract(q);
        if (v1.vmul(v2) != 0) {
            return false;
        }
        return v1.smul(v2) <= 0;
    }

    public long distSquared(int x, int y) {
        long dx = this.x - x;
        long dy = this.y - y;
        return dx * dx + dy * dy;
    }

    public double dist(int x, int y) {
        return Math.sqrt(distSquared(x, y));
    }

    public double distSquared(double x, double y) {
        x -= this.x;
        y -= this.y;
        return x * x + y * y;
    }

    public double dist(double x, double y) {
        return Math.sqrt(distSquared(x, y));
    }
}
