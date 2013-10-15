package coding;

import ru.ifmo.niyaz.geometry.Circle2DDouble;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Mammoth {

    static final DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) < 1e-6 ? 0 : a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Circle2DDouble c = new Circle2DDouble(new Point2DDouble(in.nextInt(), in.nextInt()), in.nextInt());
        Point2DDouble p1 = new Point2DDouble(in.nextInt(), in.nextInt());
        Point2DDouble p2 = new Point2DDouble(in.nextInt(), in.nextInt());
        if (p1.x > p2.x) {
            double t = p1.x;
            p1.x = p2.x;
            p2.x = t;
        }
        if (p1.y > p2.y) {
            double t = p1.y;
            p1.y = p2.y;
            p2.y = t;
        }
        List<Point2DDouble> allPoints = new ArrayList<>();
        allPoints.add(p1);
        allPoints.add(p2);
        allPoints.add(new Point2DDouble(p1.x, p2.y));
        allPoints.add(new Point2DDouble(p2.x, p1.y));
        allPoints.add(new Point2DDouble(c.p.x - c.radius, c.p.y));
        allPoints.add(new Point2DDouble(c.p.x + c.radius, c.p.y));
        allPoints.add(new Point2DDouble(c.p.x, c.p.y - c.radius));
        allPoints.add(new Point2DDouble(c.p.x, c.p.y + c.radius));
        for (double x : new double[]{p1.x, p2.x}) {
            if (comp.compare(x, c.p.x - c.radius) > 0 && comp.compare(x, c.p.x + c.radius) < 0) {
                double dx = c.p.x - x;
                double dy = Math.sqrt(c.radius * c.radius - dx * dx);
                allPoints.add(new Point2DDouble(x, c.p.y - dy));
                allPoints.add(new Point2DDouble(x, c.p.y + dy));
            }
        }
        for (double y : new double[]{p1.y, p2.y}) {
            if (comp.compare(y, c.p.y - c.radius) > 0 && comp.compare(y, c.p.y + c.radius) < 0) {
                double dy = c.p.y - y;
                double dx = Math.sqrt(c.radius * c.radius - dy * dy);
                allPoints.add(new Point2DDouble(c.p.x - dx, y));
                allPoints.add(new Point2DDouble(c.p.x + dx, y));
            }
        }
        List<Point2DDouble> onRect = new ArrayList<>();
        for (Point2DDouble e : allPoints) {
            if (inside(p1, p2, e)) {
                if (comp.compare(p1.x, e.x) < 0 && comp.compare(e.x, p2.x) < 0 && comp.compare(p1.y, e.y) < 0 && comp.compare(e.y, p2.y) < 0) {
                    continue;
                }
                onRect.add(e);
            }
        }
        List<Point2DDouble> onCircle = new ArrayList<>();
        for (Point2DDouble e : allPoints) {
            if (comp.compare(e.distance(c.p), c.radius) == 0) {
                onCircle.add(e);
            }
        }
        final Point2DDouble centerRect = p1.add(p2).multiply(0.5);
        Collections.sort(onRect, new Comparator<Point2DDouble>() {

            int get(Point2DDouble e) {
                int x = comp.compare(e.x, 0);
                int y = comp.compare(e.y, 0);
                if (x > 0) {
                    return y > 0 ? 2 : y < 0 ? 8 : 1;
                } else if (x < 0) {
                    return y > 0 ? 4 : y < 0 ? 6 : 5;
                } else {
                    return y > 0 ? 3 : y < 0 ? 7 : 0;
                }
            }

            @Override
            public int compare(Point2DDouble o1, Point2DDouble o2) {
                o1 = o1.subtract(centerRect);
                o2 = o2.subtract(centerRect);
                int c = get(o1) - get(o2);
                if (c != 0) return c;
                return comp.compare(o2.vmul(o1), 0);
            }
        });
        final Point2DDouble circleCenter = c.p;
        Collections.sort(onCircle, new Comparator<Point2DDouble>() {

            int get(Point2DDouble e) {
                int x = comp.compare(e.x, 0);
                int y = comp.compare(e.y, 0);
                if (x > 0) {
                    return y > 0 ? 2 : y < 0 ? 8 : 1;
                } else if (x < 0) {
                    return y > 0 ? 4 : y < 0 ? 6 : 5;
                } else {
                    return y > 0 ? 3 : y < 0 ? 7 : 0;
                }
            }

            @Override
            public int compare(Point2DDouble o1, Point2DDouble o2) {
                o1 = o1.subtract(circleCenter);
                o2 = o2.subtract(circleCenter);
                int c = get(o1) - get(o2);
                if (c != 0) return c;
                return comp.compare(o2.vmul(o1), 0);
            }
        });
        List<Segment> allSegments = new ArrayList<>();
        for (int i = 0; i < onRect.size(); i++) {
            Point2DDouble r1 = onRect.get(i);
            Point2DDouble r2 = onRect.get((i + 1) % onRect.size());
            if (pointsEqual(r1, r2)) continue;
            Point2DDouble r0 = r1.add(r2).multiply(0.5);
            if (comp.compare(r0.distance(c.p), c.radius) < 0) {
                allSegments.add(new Segment(r1, r2, 0));
            }
        }
        for (int i = 0; i < onCircle.size(); i++) {
            Point2DDouble r1 = onCircle.get(i);
            Point2DDouble r2 = onCircle.get((i + 1) % onCircle.size());
            if (pointsEqual(r1, r2)) continue;
            r1 = r1.subtract(c.p);
            r2 = r2.subtract(c.p);
            Point2DDouble r0 = r1.add(r2);
            r0 = r0.multiply(c.radius / r0.length());
            r0 = r0.add(c.p);
            if (inside(p1, p2, r0)) {
                allSegments.add(new Segment(r1.add(c.p), r2.add(c.p), c.radius));
            }
        }
        if (allSegments.isEmpty()) {
            out.println(0);
            return;
        }
        boolean[] was = new boolean[allSegments.size()];
        List<Segment> sortedSegments = new ArrayList<>();
        sortedSegments.add(allSegments.get(0));
        was[0] = true;
        Point2DDouble last = sortedSegments.get(sortedSegments.size() - 1).q;
//        System.out.println(allSegments);
        while (!pointsEqual(sortedSegments.get(0).p, last)) {
            int id = -1;
            for (int i = 0; i < allSegments.size(); i++) {
                if (was[i]) continue;
                Segment e = allSegments.get(i);
                if (pointsEqual(e.p, last)) {
                    id = i;
                    break;
                }
            }
            if (id < 0) throw new AssertionError();
            was[id] = true;
            sortedSegments.add(allSegments.get(id));
            last = allSegments.get(id).q;
        }
        double area = 0;
        for (Segment e : sortedSegments) {
            area += e.p.vmul(e.q);
        }
        area = Math.abs(area) * .5;
        for (Segment e : sortedSegments) {
            if (comp.compare(e.r, 0) > 0) {
                double dist = e.p.distance(e.q);
                double sin = dist * .5 / c.radius;
                if (sin > 1) sin = 1; else if (sin < -1) sin = -1;
                double ang = Math.asin(sin) * 2;
                area += c.radius * c.radius * .5 * (ang - Math.sin(ang));
            }
        }
        out.println(area);
    }

    static boolean pointsEqual(Point2DDouble p, Point2DDouble q) {
        return comp.compare(p.x, q.x) == 0 && comp.compare(p.y, q.y) == 0;
    }

    private boolean inside(Point2DDouble p1, Point2DDouble p2, Point2DDouble e) {
        return comp.compare(p1.x, e.x) <= 0 && comp.compare(e.x, p2.x) <= 0 && comp.compare(p1.y, e.y) <= 0 && comp.compare(e.y, p2.y) <= 0;
    }

    static class Segment {
        Point2DDouble p;
        Point2DDouble q;
        double r;

        Segment(Point2DDouble p, Point2DDouble q, double r) {
            this.p = p;
            this.q = q;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "p=" + p +
                    ", q=" + q +
                    ", r=" + r +
                    '}' + "\n";
        }
    }
}


