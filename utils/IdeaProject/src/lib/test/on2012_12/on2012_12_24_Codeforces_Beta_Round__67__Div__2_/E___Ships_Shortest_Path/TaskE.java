package lib.test.on2012_12.on2012_12_24_Codeforces_Beta_Round__67__Div__2_.E___Ships_Shortest_Path;



import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.io.FastScanner;

public class TaskE {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double dist(Point p) {
            double dx = x - p.x;
            double dy = y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        double dist(PointDouble p) {
            double dx = x - p.x;
            double dy = y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Point start = getPoint(in);
        Point finish = getPoint(in);
        int n = in.nextInt();
        double len = 0;
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i] = getPoint(in);
        }
        int id1 = -1;
        int id2 = -1;
        PointDouble p1 = null;
        PointDouble p2 = null;
        for (int i = 0; i < n; i++) {
            len += p[i].dist(p[(i + 1) % n]);
            PointDouble cur = intersect(start, finish, p[i], p[(i + 1) % n]);
            if (cur == null) {
                continue;
            }
            if (p1 == null) {
                p1 = cur;
                id1 = i;
            } else if (p2 == null) {
                p2 = cur;
                id2 = i;
            } else {
                throw new AssertionError();
            }
        }
        if (p2 == null) {
            out.println(start.dist(finish));
            return;
        }
        double len1 = 0;
        for (int i = id1; ; i = (i + 1) % n) {
            if (i == id1) {
                len1 += p[(i + 1) % n].dist(p1);
            } else if (i == id2) {
                len1 += p[i].dist(p2);
                break;
            } else {
                len1 += p[i].dist(p[(i + 1) % n]);
            }
        }
        double len2 = len - len1;
        out.println(start.dist(finish) - p1.dist(p2) + Math.min(p1.dist(p2) * 2, Math.min(len1, len2)));
    }

    static int vmulFromPoint(Point from, Point p, Point q) {
        int x1 = p.x - from.x;
        int y1 = p.y - from.y;
        int x2 = q.x - from.x;
        int y2 = q.y - from.y;
        return x1 * y2 - x2 * y1;
    }

    static PointDouble intersect(Point p1, Point p2, Point q1, Point q2) {
        int v1 = vmulFromPoint(p1, q1, q2);
        int v2 = vmulFromPoint(p2, q1, q2);
        int u1 = vmulFromPoint(q1, p1, p2);
        int u2 = vmulFromPoint(q2, p1, p2);
//        System.out.println(v1 + " " + v2 + " " +u1 + " " + u2);
        if (v1 == 0 && v2 == 0 && u1 == 0 && u2 == 0 || u2 == 0) {
            return new PointDouble(q2.x, q2.y);
        }
        if (u1 == 0) {
            return null;
        }
        if (Integer.signum(v1) != Integer.signum(v2) && Integer.signum(u1) != Integer.signum(u2)) {
            return new Line(p1, p2).intersect(new Line(q1, q2));
        } else {
            return null;
        }
    }

    static class Line {
        int a;
        int b;
        int c;

        Line(Point p, Point q) {
            a = q.y - p.y;
            b = p.x - q.x;
            c = -p.x * a - p.y * b;
        }

        PointDouble intersect(Line line) {
            int z = a * line.b - b * line.a;
            if (z == 0) {
                return null;
            }
            return new PointDouble(1. * (b * line.c - c * line.b) / z, 1. * (c * line.a - a * line.c) / z);
        }
    }

    static class PointDouble {
        double x, y;

        PointDouble(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double dist(Point p) {
            double dx = x - p.x;
            double dy = y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }

        double dist(PointDouble p) {
            double dx = x - p.x;
            double dy = y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }

    private Point getPoint(FastScanner in) {
        return new Point(in.nextInt(), in.nextInt());
    }
}
