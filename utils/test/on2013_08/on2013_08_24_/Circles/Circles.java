package lib.test.on2013_08.on2013_08_24_.Circles;



import com.sun.org.apache.bcel.internal.generic.DSUB;
import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.geometry.Circle2DDouble;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.*;

public class Circles {
    static final DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            return Math.abs(a - b) < 1e-8 ? 0 : a < b ? -1 : 1;
        }
    };

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Circle2DDouble[] c = new Circle2DDouble[n];
        for (int i = 0; i < n; i++) {
            c[i] = new Circle2DDouble(new Point2DDouble(in.nextInt(), in.nextInt()), in.nextInt());
        }
        boolean[] bad = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pointsEqual(c[i].p, c[j].p) && comp.compare(c[i].radius, c[j].radius) == 0) {
                    bad[j] = true;
                }
            }
        }
        {
            int nn = 0;
            for (int i = 0; i < n; i++) {
                if (!bad[i]) ++nn;
            }
            Circle2DDouble[] cc = new Circle2DDouble[nn];
            for (int i = 0, j = 0; i < n; i++) {
                if (!bad[i]) {
                    cc[j++] = c[i];
                }
            }
            n = nn;
            c = cc;
        }
//        Random rand = new Random();
//        double sin = rand.nextDouble();
//        double cos = Math.sqrt(1. - sin * sin);
//        for (int i = 0; i < n; i++) {
//            c[i].p = new Point2DDouble(c[i].p.x * cos - c[i].p.y * sin, c[i].p.x * sin + c[i].p.y * cos);
//        }
        List<Double> xs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            xs.add(c[i].p.x);
            xs.add(c[i].p.x - c[i].radius);
            xs.add(c[i].p.x + c[i].radius);
            for (int j = i + 1; j < n; j++) {
                Point2DDouble[] p = c[i].intersect(c[j], comp);
                if (p != null) {
                    for (Point2DDouble e : p) {
                        xs.add(e.x);
                    }
                }
            }
        }
        Collections.sort(xs);
        List<List<Arc>> allArcs = new ArrayList<>();
        Arc first, last;
        {
            Point2DDouble left = new Point2DDouble(0, -10000);
            Point2DDouble right = new Point2DDouble(0, -10000);
            first = new Arc(left, right, left.add(right).multiply(0.5));
            left = new Point2DDouble(0, 10000);
            right = new Point2DDouble(0, 10000);
            last = new Arc(left, right, left.add(right).multiply(0.5));
        }
        {
            List<Arc> f = new ArrayList<>();
            f.add(first);
            f.add(last);
            allArcs.add(f);
        }
        for (int it = 1; it < xs.size(); it++) {
            double x1 = xs.get(it - 1);
            double x2 = xs.get(it);
            if (comp.compare(x1, x2) == 0) continue;
            List<Arc> arcs = new ArrayList<>();
            arcs.add(first);
            arcs.add(last);
            for (int i = 0; i < n; i++) {
                if (comp.compare(c[i].p.x - c[i].radius, x1) <= 0 && comp.compare(c[i].p.x + c[i].radius, x2) >= 0) {
                    {
                        Point2DDouble left = new Point2DDouble(x1, c[i].p.y - getSecondC(c[i].radius, c[i].p.x - x1));
                        Point2DDouble right = new Point2DDouble(x2, c[i].p.y - getSecondC(c[i].radius, x2 - c[i].p.x));
                        Point2DDouble center = left.add(right).multiply(.5);
                        arcs.add(new Arc(left, right, center));
                    }
                    {
                        Point2DDouble left = new Point2DDouble(x1, c[i].p.y + getSecondC(c[i].radius, c[i].p.x - x1));
                        Point2DDouble right = new Point2DDouble(x2, c[i].p.y + getSecondC(c[i].radius, x2 - c[i].p.x));
                        Point2DDouble center = left.add(right).multiply(.5);
                        arcs.add(new Arc(left, right, center));
                    }
                }
            }
            Collections.sort(arcs);
            allArcs.add(arcs);
//            System.out.println(arcs);
        }
        {
            List<Arc> f = new ArrayList<>();
            f.add(first);
            f.add(last);
            allArcs.add(f);
        }
        int countArcs = 0;
        for (List<Arc> e : allArcs) {
            countArcs += e.size() - 1;
        }
        DisjointSetUnion dsu = new DisjointSetUnion(countArcs);
        List<Integer> all = new ArrayList<>();
        for (int i = 1, j = 0; i < allArcs.size(); i++) {
            List<Arc> f1 = allArcs.get(i - 1);
            List<Arc> f2 = allArcs.get(i);
            int cur = 0;
            for (int k = 0; k + 1 < f2.size(); k++) {
                Arc a1 = f2.get(k);
                Arc a2 = f2.get(k + 1);
                if (pointsEqual(a1.left, a2.left)) continue;
                for (int e = cur; e + 1 < f1.size(); e++) {
                    Arc b1 = f1.get(e);
                    Arc b2 = f1.get(e + 1);
//                    if (b2.right.y < a1.left.y) ++cur;
//                    if (b1.right.y > a2.left.y) break;
                    if (pointsEqual(b1.right, b2.right)) continue;
                    if (!(comp.compare(b2.right.y, a1.left.y) <= 0 || comp.compare(b1.right.y, a2.left.y) >= 0)) {
//                        dsu.union(j + e, j + f1.size() - 1 + k);
                        all.add((j + e) * countArcs + (j + f1.size() - 1 + k));
//                        System.out.println(j + e + " " + (j + f1.size() - 1 + k));
                    }
                }
            }
            j += f1.size() - 1;
        }
        for (int i : all) {
            dsu.union(i / countArcs, i %
                    countArcs);
        }
        int ans = 0;
        for (int i = 0; i < countArcs; i++) {
            if (dsu.get(i) == i) ++ans;
        }
        out.println(ans);
    }

    static boolean pointsEqual(Point2DDouble p, Point2DDouble q) {
        return comp.compare(p.x, q.x) == 0 && comp.compare(p.y, q.y) == 0;
    }

    static double getSecondC(double c, double a) {
        double b = c * c - a * a;
        if (b < 0) b = 0;
        return Math.sqrt(b);
    }

    static class Arc implements Comparable<Arc> {
        Point2DDouble left;
        Point2DDouble right;
        Point2DDouble center;

        Arc(Point2DDouble left, Point2DDouble right, Point2DDouble center) {
            this.left = left;
            this.right = right;
            this.center = center;
        }

        @Override
        public String toString() {
            return "Arc{" +
                    "left=" + left +
                    ", right=" + right +
                    ", center=" + center +
                    '}';
        }

        @Override
        public int compareTo(Arc o) {
            return Double.compare(center.y, o.center.y);
        }
    }
}
