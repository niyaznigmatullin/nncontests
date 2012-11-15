package mypackage;

import geometry.Point2DInteger;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class TaskF {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double len() {
            return Math.sqrt(x * x + y * y);
        }

        int dist2(Point p) {
            int dx = x - p.x;
            int dy = y - p.y;
            return dx * dx + dy * dy;
        }

        int vect(Point p) {
            return x * p.y - y * p.x;
        }

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
        }

        int scalar(Point p) {
            return x * p.x + y * p.y;
        }

        long vect(Point p1, Point p2) {
            return (p1.x - x) * (p2.y - y) - (p1.y - y) * (p2.x - x);
        }


    }

    static class ByAngle implements Comparator<Point> {
        Point p;

        public ByAngle(Point p) {
            super();
            this.p = p;
        }

        public int compare(Point o1, Point o2) {
            long d = p.vect(o1, o2);
            long d1 = p.dist2(o1);
            long d2 = p.dist2(o2);
            return d > 0 ? -1 : d < 0 ? 1 : d1 < d2 ? -1 : d1 > d2 ? 1 : 0;
        }
    }

    static Point[] convexHull(Point[] p) {
        if (p.length < 3) {
            throw new AssertionError("not enough points");
        }
        Stack<Point> stack = new Stack<Point>();
        int min = -1;
        for (int i = 1; i < p.length; i++) {
            if (min < 0 || (p[i].x < p[min].x || (p[i].x == p[min].x && p[i].y < p[min].y))) {
                min = i;
            }
        }
        Point[] newP = new Point[p.length];
        System.arraycopy(p, min, newP, 0, p.length - min);
        System.arraycopy(p, 0, newP, p.length - min, min);
        p = newP;
        stack.add(p[0]);
        stack.add(p[1]);
        for (int i = 2; i < p.length; i++) {
            while (true) {
                Point last2 = stack.pop();
                Point last = stack.peek();
                if (last.vect(last2, p[i]) >= 0) {
                    stack.add(last2);
                    break;
                }
            }
            stack.add(p[i]);
        }
        Point[] hull = new Point[stack.size()];
        int cnt = 0;
        for (Point e : stack) {
            hull[cnt++] = e;
        }
        return hull;
    }


    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point[] inner = new Point[n];
        for (int i = 0; i < n; i++) {
            inner[i] = new Point(in.nextInt(), in.nextInt());
        }
        int m = in.nextInt();
        Point[] outer = new Point[m];
        for (int i = 0; i < m; i++) {
            outer[i] = new Point(in.nextInt(), in.nextInt());
        }
        Point[] h = convexHull(inner);
        double xc = 0;
        double yc = 0;
        for (Point e : h) {
            xc += e.x;
            yc += e.y;
        }
        xc /= h.length;
        yc /= h.length;
//        System.out.println(xc + " " + yc);
        xc -= EPS;
        yc += EPS;
        double[][] dist = new double[n + m][n + m];
        for (double[] e : dist) {
            Arrays.fill(e, Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                Point p1 = inner[i];
                Point p2 = inner[j];
                if (doesIntersect(p1, p2, inner) || doesIntersect(p1, p2, outer)) {
                    continue;
                }
                Point v = p2.sub(p1);
                Point v1 = inner[(i + 1) % n].sub(p1);
                Point v2 = p1.sub(inner[(i + n - 1) % n]);
                if ((!inside(v, v1, v2) || v.vect(v1) == 0 && v.scalar(v1) > 0) && isGood(v, p1, xc, yc)) {
                    dist[i][j] = v.len();
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    continue;
                }
                Point p1 = outer[i];
                Point p2 = outer[j];
                if (doesIntersect(p1, p2, inner) || doesIntersect(p1, p2, outer)) {
                    continue;
                }
                Point v = p2.sub(p1);
                Point v1 = outer[(i + 1) % m].sub(p1);
                Point v2 = p1.sub(outer[(i + m - 1) % m]);
                Point v3 = inner[0].sub(p1);
//                System.err.println(v1.vect(v) + " " + v.vect(v2) + " " + v.vect(v3));
                if (inside(v, v1, v2) &&  isGood(v, p1, xc, yc)) {
                    dist[i + n][j + n] = v.len();
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Point p1 = inner[i];
                Point p2 = outer[j];
                if (doesIntersect(p1, p2, inner) || doesIntersect(p1, p2, outer)) {
                    continue;
                }
                Point v = p2.sub(p1);
                Point v1 = inner[(i + 1) % n].sub(p1);
//                if (v.scalar(v1) >= 0 && v.vect(v1) >= 0) {
                if (isGood(v, p1, xc, yc)) {
                    dist[i][j + n] = v.len();
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Point p1 = outer[i];
                Point p2 = inner[j];
                if (doesIntersect(p1, p2, inner) || doesIntersect(p1, p2, outer)) {
                    continue;
                }
                Point v = p2.sub(p1);
                Point v1 = inner[(j + 1) % n].sub(p2);
                Point v2 = p2.sub(inner[(j + n - 1) % n]);
//                if (/*v1.scalar(v) >= 0 && v1.scalar(v) >= 0 || */v2.scalar(v) >= 0 && v2.scalar(v) >= 0) {

                if (isGood(v, p1, xc, yc)) {
                    dist[i + n][j] = v.len();
                }
            }
        }
//        for (double[] e : dist) {
//            System.out.println(Arrays.toString(e));
//        }
        for (int k = 0; k < n + m; k++) {
            for (int i = 0; i < n + m; i++) {
                for (int j = 0; j < n + m; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
//        System.out.println();
//        for (double[] e : dist) {
//            System.out.println(Arrays.toString(e));
//        }
        double answer = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n + m; i++) {
            answer = Math.min(answer, dist[i][i]);
        }
        out.println(answer);
    }

    static boolean isGood(Point v, Point p1, double xc, double yc) {
        double vx = xc - p1.x;
        double vy = yc - p1.y;
        double vect = v.x * vy - v.y * vx;
        return compare(vect, 0) > 0;
    }

    static final double EPS = 1e-8;

    static int compare(double a, double b) {
        return Math.abs(a - b) < EPS ? 0 : Double.compare(a, b);
    }

    static boolean inside(Point v, Point v1, Point v2) {
        if (less(v1, v2)) {
            return less(v1, v) && less(v, v2);
        } else {
            return less(v1, v) || less(v, v2);
        }
    }


    static boolean less(Point v1, Point v2) {
        int d = get(v1) - get(v2);
        if (d != 0) {
            return d < 0;
        }
        return v1.vect(v2) <= 0;
    }

    static int get(Point v) {
        int x = v.x;
        int y = v.y;
        if (x > 0) {
            if (y > 0) {
                return 2;
            } else if (y < 0) {
                return 8;
            } else {
                return 1;
            }
        } else if (x < 0) {
            if (y > 0) {
                return 4;
            } else if (y < 0) {
                return 6;
            } else {
                return 5;
            }
        } else {
            if (y > 0) {
                return 3;
            } else if (y < 0) {
                return 7;
            } else {
                return 0;
            }
        }
    }

    static boolean doesIntersect(Point p1, Point p2, Point[] q) {
        for (int i = 0; i < q.length; i++) {
            int j = (i + 1) % q.length;
            if (p1 == q[i] || p2 == q[i] || p1 == q[j] || p2 == q[j]) {
                continue;
            }
            if (intersect(p1, p2, q[i], q[j])) {
                return true;
            }
        }
        return false;
    }

    static boolean intersect(Point p1, Point p2, Point q1, Point q2) {
        int v1 = p1.sub(q1).vect(p1.sub(q2));
        int v2 = p2.sub(q1).vect(p2.sub(q2));
        int v3 = q1.sub(p1).vect(q1.sub(p2));
        int v4 = q2.sub(p1).vect(q2.sub(p2));
        if (v1 > v2) {
            int t = v1;
            v1 = v2;
            v2 = t;
        }
        if (v3 > v4) {
            int t = v3;
            v3 = v4;
            v4 = t;
        }
        if (v1 == 0 && v2 == 0 && v3 == 0 && v4 == 0) {
            return Math.max(Math.min(p1.x, p2.x), Math.min(q1.x, q2.x)) <= Math.min(Math.max(p1.x, p2.x), Math.max(q1.x, q2.x)) &&
                    Math.max(Math.min(p1.y, p2.y), Math.min(q1.y, q2.y)) <= Math.min(Math.max(p1.y, p2.y), Math.max(q1.y, q2.y));
        }
        return v1 <= 0 && v2 >= 0 && v3 <= 0 && v4 >= 0;
    }
}
