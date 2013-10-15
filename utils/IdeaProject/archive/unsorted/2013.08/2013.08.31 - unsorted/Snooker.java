package coding;

import ru.ifmo.niyaz.geometry.Circle2DDouble;
import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Snooker {

    static final DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) < 1e-6 ? 0 : a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int redCount = in.nextInt();
        int coloredCount = in.nextInt();
        String op = in.next();
        Point2DDouble[] reds = new Point2DDouble[redCount];
        Point2DDouble[] colors = new Point2DDouble[coloredCount];
        Point2DDouble center = new Point2DDouble(in.nextDouble(), in.nextDouble());
        for (int i = 0; i < redCount; i++) {
            reds[i] = new Point2DDouble(in.nextDouble(), in.nextDouble()).subtract(center);
        }
        for (int i = 0; i < coloredCount; i++) {
            colors[i] = new Point2DDouble(in.nextDouble(), in.nextDouble()).subtract(center);
        }
        boolean ans = op.equals("C") ? getAnswer(reds, colors) : getAnswer(colors, reds);
        out.println(ans ? "NO" : "YES");
    }

    static List<Element> getElements(Point2DDouble[] p) {
        List<Element> ret = new ArrayList<>();
        for (Point2DDouble e : p) {
            Circle2DDouble c = new Circle2DDouble(e, 2);
            Point2DDouble[] t = c.getTangents(center, comp);
            double angle1 = Math.atan2(t[0].y, t[0].x);
            double angle2 = Math.atan2(t[1].y, t[1].x);
            if (angle1 > angle2) {
                ret.add(new Element(angle1, Math.PI, c));
                ret.add(new Element(-Math.PI, angle2, c));
            } else {
                ret.add(new Element(angle1, angle2, c));
            }
        }
        return ret;
    }

    static final Point2DDouble center = new Point2DDouble(0, 0);

    static boolean getAnswer(Point2DDouble[] p, Point2DDouble[] q) {
        Circle2DDouble[] cp = getCircle2DDoubles(p);
        Circle2DDouble[] cq = getCircle2DDoubles(q);
        List<Element> pe = getElements(p);
        List<Element> qe = getElements(q);
        List<Double> allAngles = new ArrayList<>();
        allAngles.add(-Math.PI);
        allAngles.add(Math.PI);
        for (Element z : qe) {
            allAngles.add(z.left);
            allAngles.add(z.right);
        }
        for (Element z : pe) {
            allAngles.add(z.left);
            allAngles.add(z.right);
        }
        for (Circle2DDouble e : cp) {
            for (Circle2DDouble f : cq) {
                Point2DDouble[] z = e.intersect(f, comp);
                if (z != null) {
                    for (Point2DDouble g : z) {
                        allAngles.add(Math.atan2(g.y, g.x));
                    }
                }
            }
        }
        Collections.sort(allAngles);
        for (int it = 1; it < allAngles.size(); it++) {
            double left = allAngles.get(it - 1);
            double right = allAngles.get(it);
            if (comp.compare(left, right) >= 0) continue;
            double best = Double.POSITIVE_INFINITY;
            double angle = (left + right) * .5;
            Point2DDouble ea = new Point2DDouble(Math.cos(angle), Math.sin(angle));
            for (Element e : pe) {
                if (comp.compare(e.left, left) <= 0 && comp.compare(right, e.right) <= 0) {
                    best = Math.min(best, getDistToCircle(ea, e.c));
                }
            }
            for (Element e : qe) {
                if (comp.compare(e.left, left) <= 0 && comp.compare(right, e.right) <= 0) {
                    double cur = getDistToCircle(ea, e.c);
                    if (comp.compare(cur, best) < 0) {
                        return true;
                    }
                }
            }
        }
        return false;
//        for (Element z : qe) {
//            List<Event> events = new ArrayList<>();
//            for (Element f : pe) {
//                double angLeft = Math.max(z.left, f.left);
//                double angRight = Math.min(z.right, f.right);
//                if (comp.compare(angLeft, angRight) > 0) {
//                    continue;
//                }
//                Point2DDouble e = new Point2DDouble(Math.cos(angLeft), Math.sin(angLeft));
//                double d1 = getDistToCircle(e, z.c);
//                double d2 = getDistToCircle(e, f.c);
//                if (comp.compare(d1, d2) < 0) {
//                    continue;
//                }
//                events.add(new Event(angLeft, 0));
//                events.add(new Event(angRight, -1));
//            }
//            Collections.sort(events, new Comparator<Event>() {
//                @Override
//                public int compare(Event o1, Event o2) {
//                    return comp.compare(o1.angle, o2.angle);
//                }
//            });
//            int count = 0;
//            if (events.isEmpty()) {
//                return true;
//            }
//            double first = events.get(0).angle;
//            double last = events.get(events.size() - 1).angle;
//            if (comp.compare(first, z.left) != 0 || comp.compare(last, z.right) != 0) return true;
//            for (int i = 0; i < events.size(); ) {
//                int j = i;
//                while (j < events.size() && comp.compare(events.get(i).angle, events.get(j).angle) == 0) {
//                    int type = events.get(j).type;
//                    if (type < 0) {
//                        --count;
//                    } else {
//                        ++count;
//                    }
//                    ++j;
//                }
//                if (j != events.size() && count == 0) {
//                    return true;
//                }
//                i = j;
//            }
//        }
//        return false;
    }

    private static Circle2DDouble[] getCircle2DDoubles(Point2DDouble[] p) {
        Circle2DDouble[] cp = new Circle2DDouble[p.length];
        for (int i = 0; i < p.length; i++) {
            cp[i] = new Circle2DDouble(p[i], 2);
        }
        return cp;
    }

    static double getDistToCircle(Point2DDouble e, Circle2DDouble c) {
        double a = -e.y;
        double b = e.x;
        double d = a * c.p.x + b * c.p.y;
        double z = c.radius * c.radius - d * d;
        if (z < 0) z = 0;
        z = Math.sqrt(z);
        return e.smul(c.p) - z;
    }

    static class Element {
        double left;
        double right;
        Circle2DDouble c;

        Element(double left, double right, Circle2DDouble c) {
            this.left = left;
            this.right = right;
            this.c = c;
        }
    }

    static class Event {
        double angle;
        int type;

        Event(double angle, int type) {
            this.angle = angle;
            this.type = type;
        }
    }
}
