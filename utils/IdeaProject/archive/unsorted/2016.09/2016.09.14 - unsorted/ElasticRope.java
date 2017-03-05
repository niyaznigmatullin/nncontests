package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElasticRope {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int pa = in.nextInt() - 1;
        int pb = in.nextInt() - 1;
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt() * 2, in.nextInt() * 2);
        }
        if (GeometryAlgorithms.signedDoubledArea(p) < 0) {
            ArrayUtils.reverse(p);
            pa = p.length - pa - 1;
            pb = p.length - pb - 1;
        }
        Point2DInteger[] h = GeometryAlgorithms.convexHull(p);
        for (int i = 0; i < n; i++) {
            if (p[i] == h[0]) {
                Point2DInteger[] np = new Point2DInteger[n];
                System.arraycopy(p, i, np, 0, p.length - i);
                System.arraycopy(p, 0, np, p.length - i, i);
                pa = (pa + p.length - i) % p.length;
                pb = (pb + p.length - i) % p.length;
                p = np;
                break;
            }
        }
        int[] lastHull = new int[n];
        for (int i = 0, j = 0; i < h.length; i++) {
            if (p[j] != h[i]) {
                throw new AssertionError();
            }
            Point2DInteger nextH = h[(i + 1) % h.length];
            while (p[j] != nextH) {
                lastHull[j] = i;
                j = (j + 1) % p.length;
            }
        }
        Solver solver = new Solver(p, h, lastHull);
        out.println(Math.max(solver.getPathBetween(pa, pb), Math.max(solver.solve(pa, pb), solver.solve(pb, pa))) * .5);
    }

    private static class Solver {
        Point2DInteger[] p, h;
        int[] lastHull;

        public Solver(Point2DInteger[] p, Point2DInteger[] h, int[] lastHull) {
            this.p = p;
            this.h = h;
            this.lastHull = lastHull;
        }

        boolean isOnHull(int pa) {
            return h[lastHull[pa]] == p[pa];
        }

        double solve(int pa, int pb) {
            int ha = isOnHull(pa) ? lastHull[pa] : ((lastHull[pa] + 1) % h.length);
            int hb = lastHull[pb];
            List<Point2DInteger> pathBetween = new ArrayList<>();
            for (int i = ha; i != hb; i = (i + 1) % h.length) {
                pathBetween.add(h[i]);
            }
            pathBetween.add(h[hb]);
            List<Point2DInteger> list = concat(concat(getPath(pa, false), pathBetween), getPath(pb, true));
            for (int i = 0; i + 1 < list.size(); i++) {
                Point2DInteger ai = list.get(i);
                Point2DInteger ai2 = list.get(i + 1);
                for (int j = i + 1; j + 1 < list.size(); j++) {
                    Point2DInteger bj = list.get(j);
                    Point2DInteger bj2 = list.get(j + 1);
                    if (ai == bj) {
                        return Double.NEGATIVE_INFINITY;
                    }
                    if (ai != bj && ai != bj2 && bj != ai2 && ai2 != bj2) {
                        if (GeometryAlgorithms.segmentsIntersect(ai, ai2, bj, bj2)) {
                            return Double.NEGATIVE_INFINITY;
                        }
                    }
                }
            }
            return getLength(list);
        }

        private void checkPath(List<Point2DInteger> list) {
            for (int i = 0; i + 1 < list.size(); i++) {
                Point2DInteger aa = list.get(i);
                Point2DInteger bb = list.get(i + 1);
                checkSegment(aa, bb);
            }
        }

        private void checkSegment(Point2DInteger aa, Point2DInteger bb) {
            Point2DInteger mm = new Point2DInteger((aa.x + bb.x) / 2, (aa.y + bb.y) / 2);
            for (int j = 0; j < p.length; j++) {
                if (GeometryAlgorithms.isOnLineSegment(mm, p[j], p[(j + 1) % p.length])) {
                    return;
                }
            }
            if (inside(p, mm)) {
                System.out.println(mm);
                throw new AssertionError();
            }
        }

        List<Point2DInteger> getPath(int pa, boolean toStart) {
            if (isOnHull(pa)) {
                return new ArrayList<>(Arrays.asList(p[pa]));
            }
            Point2DInteger[] polygon = getHolePolygon(pa);
            int where = getWhere(pa, polygon);
            List<Point2DInteger> ret;
            if (toStart) {
                ret = shortest(polygon, polygon.length - 1, where);
            } else {
                ret = shortest(polygon, where, 0);
            }
            return ret;
        }

        private int getWhere(int pa, Point2DInteger[] polygon) {
            int where = -1;
            for (int cur = 0; cur < polygon.length; cur++) {
                if (polygon[cur] == p[pa]) {
                    where = cur;
                    break;
                }
            }
            return where;
        }

        private Point2DInteger[] getHolePolygon(int pa) {
            List<Point2DInteger> list = new ArrayList<>();
            int i = pa;
            while (lastHull[(i + 1) % p.length] == lastHull[pa]) {
                ++i;
            }
            list.add(p[(i + 1) % p.length]);
            while (lastHull[i] == lastHull[pa]) {
                list.add(p[i]);
                i = (i + p.length - 1) % p.length;
            }
            return list.toArray(new Point2DInteger[list.size()]);
        }

        double getPathBetween(int pa, int pb) {
            if (isOnHull(pa) || isOnHull(pb) || lastHull[pa] != lastHull[pb]) {
                return Double.NEGATIVE_INFINITY;
            }
            Point2DInteger[] polygon = getHolePolygon(pa);
            return getLength(shortest(polygon, getWhere(pa, polygon), getWhere(pb, polygon)));
        }

        List<Point2DInteger> shortest(Point2DInteger[] p, int from, int to) {
            int n = p.length;
            double[][] len = new double[n][n];
            for (double[] e : len) {
                Arrays.fill(e, Double.POSITIVE_INFINITY);
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (j - i == 1 || j - i == n - 1) {
                        len[i][j] = len[j][i] = p[i].distance(p[j]);
                    }
                    boolean ok = true;
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j || (k + 1) % n == i || (k + 1) % n == j) continue;
                        if (GeometryAlgorithms.segmentsIntersect(p[i], p[j], p[k], p[(k + 1) % n])) {
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) continue;
                    Point2DInteger f = new Point2DInteger((p[i].x + p[j].x) / 2, (p[i].y + p[j].y) / 2);
                    if (!inside(p, f)) {
                        continue;
                    }
                    len[i][j] = len[j][i] = p[i].distance(p[j]);
                }
            }
            double[] d = new double[n];
            boolean[] was = new boolean[n];
            int[] cameFrom = new int[n];
            Arrays.fill(d, Double.POSITIVE_INFINITY);
            d[from] = 0;
            while (true) {
                int min = -1;
                for (int i = 0; i < n; i++) {
                    if (was[i] || d[i] == Double.POSITIVE_INFINITY) {
                        continue;
                    }
                    if (min < 0 || d[min] > d[i]) {
                        min = i;
                    }
                }
                if (min < 0) break;
                was[min] = true;
                double[] lMin = len[min];
                for (int i = 0; i < n; i++) {
                    if (d[i] > d[min] + lMin[i]) {
                        d[i] = d[min] + lMin[i];
                        cameFrom[i] = min;
                    }
                }
            }
            if (d[to] == Double.POSITIVE_INFINITY) {
                throw new AssertionError();
            }
            List<Point2DInteger> ret = new ArrayList<>();
            for (int i = to; i != from; i = cameFrom[i]) {
                ret.add(p[i]);
            }
            ret.add(p[from]);
            Collections.reverse(ret);
            return ret;
        }

    }

    static double getLength(List<Point2DInteger> a) {
        double ret = 0;
        for (int i = 0; i + 1 < a.size(); i++) {
            ret += a.get(i).distance(a.get(i + 1));
        }
        return ret;
    }

    static boolean inside(Point2DInteger[] p, Point2DInteger q) {
        Point2DInteger q2 = q.add(new Point2DInteger(123456, 1));
        int c = 0;
        int n = p.length;
        for (int i = 0; i < n; i++) {
            if (GeometryAlgorithms.segmentsIntersect(q, q2, p[i], p[(i + 1) % n])) {
                ++c;
            }
        }
        return (c & 1) == 1;
    }

    static List<Point2DInteger> concat(List<Point2DInteger> a, List<Point2DInteger> b) {
        if (a.get(a.size() - 1) != b.get(0)) {
            throw new AssertionError();
        }
        List<Point2DInteger> ret = new ArrayList<>(a);
        for (int i = 1; i < b.size(); i++) {
            ret.add(b.get(i));
        }
        return ret;
    }
}
