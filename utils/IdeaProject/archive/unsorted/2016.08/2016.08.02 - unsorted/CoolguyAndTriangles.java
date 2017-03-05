package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Line2DDouble;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.*;

public class CoolguyAndTriangles {
    private DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) < 1e-6 ? 0 : a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Point2DInteger[][] p = new Point2DInteger[n][3];
        final int MAXX = 1000000;
        if (n < 2 || n > 100) {
            throw new AssertionError();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x < -MAXX || x > MAXX || y < -MAXX || y > MAXX) {
                    throw new AssertionError();
                }
                p[i][j] = new Point2DInteger(x, y);
            }
            if (GeometryAlgorithms.vmulFromPoint(p[i][0], p[i][1], p[i][2]) == 0) {
                throw new AssertionError(i + 1);
            }
        }
        List<Double> listX = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                listX.add((double) p[i][j].x);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                Point2DInteger from1 = p[i][j];
                Point2DInteger to1 = p[i][(j + 1) % 3];
                for (int i2 = i + 1; i2 < n; i2++) {
                    for (int j2 = 0; j2 < 3; j2++) {
                        Point2DInteger from2 = p[i2][j2];
                        Point2DInteger to2 = p[i2][(j2 + 1) % 3];
                        if (!GeometryAlgorithms.segmentsIntersect(from1, to1, from2, to2)) {
                            continue;
                        }
                        Line2DDouble l1 = Line2DDouble.getThroughTwoPoints(new Point2DDouble(from1.x, from1.y), new Point2DDouble(to1.x, to1.y));
                        Line2DDouble l2 = Line2DDouble.getThroughTwoPoints(new Point2DDouble(from2.x, from2.y), new Point2DDouble(to2.x, to2.y));
                        Point2DDouble z = l1.intersect(l2, comp);
                        if (z == null) continue;
                        listX.add(z.x);
                    }
                }
            }
        }
        Collections.sort(listX);
        double ans = 0;
        for (int it = 0; it + 1 < listX.size(); it++) {
            double fromX = listX.get(it);
            double toX = listX.get(it + 1);
            if (comp.compare(fromX, toX) == 0) continue;
            List<Segment> segments = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    Point2DInteger from = p[i][j];
                    Point2DInteger to = p[i][(j + 1) % 3];
                    if (from.x > to.x) {
                        Point2DInteger t = from;
                        from = to;
                        to = t;
                    }
                    if (from.x == to.x) continue;
                    if (comp.compare(from.x, fromX) <= 0 && comp.compare(toX, to.x) <= 0) {
                        segments.add(new Segment(getY(from, to, fromX), getY(from, to, toX), i));
                    }
                }
            }
            Collections.sort(segments, new Comparator<Segment>() {
                @Override
                public int compare(Segment o1, Segment o2) {
                    return Double.compare(o1.y1 + o1.y2, o2.y1 + o2.y2);
                }
            });
            boolean[] was = new boolean[n];
            double dx = toX - fromX;
            double curArea = 0;
            int cur = 0;
            double lastY = 0;
            for (Segment e : segments) {
                double curY = .5 * (e.y1 + e.y2);
                if (cur > 0) {
                    curArea += (curY - lastY) * dx;
                }
                lastY = curY;
                if (was[e.id]) {
                    was[e.id] = false;
                    --cur;
                } else {
                    was[e.id] = true;
                    ++cur;
                }
            }
            for (boolean e : was) if (e) throw new AssertionError();
            ans += curArea;
        }
        out.println(ans);
    }

    static class Segment {
        double y1;
        double y2;
        int id;

        public Segment(double y1, double y2, int id) {
            this.y1 = y1;
            this.y2 = y2;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "y1=" + y1 +
                    ", y2=" + y2 +
                    ", id=" + id +
                    '}';
        }
    }

    static double getY(Point2DInteger from, Point2DInteger to, double x) {
        return from.y + (1. * (to.y - from.y) / (to.x - from.x) * (x - from.x));
    }
}
