package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;
import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Line2DDouble;
import ru.ifmo.niyaz.geometry.Point2DDouble;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.awt.geom.Point2D;
import java.util.*;

public class Lazy {

    static Point2DDouble[] points;
    static int cn;
    static int[] deg;

    static final DoubleComparator comp = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            if (Math.abs(a - b) < 1e-8) return 0;
            return a < b ? -1 : 1;
        }
    };

    static int find(Point2DDouble q) {
        int id = -1;
        for (int e = 0; e < cn; e++) {
            if (comp.compare(points[e].x, q.x) == 0 && comp.compare(points[e].y, q.y) == 0) {
                id = e;
                break;
            }
        }
        if (id < 0) {
            id = cn;
            points[cn++] = q;
        }
        return id;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        points = new Point2DDouble[123 * 123];
        deg = new int[points.length];
        DisjointSetUnion dsu = new DisjointSetUnion(points.length);
        cn = 0;
        Point2DInteger[][] segments = new Point2DInteger[n][2];
        Line2DDouble[] lines = new Line2DDouble[n];
        for (int i = 0; i < n; i++) {
            segments[i][0] = new Point2DInteger(in.nextInt(), in.nextInt());
            segments[i][1] = new Point2DInteger(in.nextInt(), in.nextInt());
            lines[i] = Line2DDouble.getThroughTwoPoints(segments[i][0].toPointDouble(), segments[i][1].toPointDouble());
        }
        Set<Integer> was = new HashSet<>();
        for (int i = 0; i < n; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (GeometryAlgorithms.segmentsIntersect(segments[i][0], segments[i][1], segments[j][0], segments[j][0])) {
                    cur.add(find(segments[j][0].toPointDouble()));
                }
                if (GeometryAlgorithms.segmentsIntersect(segments[i][0], segments[i][1], segments[j][1], segments[j][1])) {
                    cur.add(find(segments[j][1].toPointDouble()));
                }
                if (GeometryAlgorithms.segmentsIntersect(segments[i][0], segments[i][1], segments[j][0], segments[j][1])) {
                    Line2DDouble line1 = lines[i];
                    Line2DDouble line2 = lines[j];
                    Point2DDouble q = line1.intersect(line2, comp);
                    if (q == null) {
                        continue;
                    }
                    cur.add(find(q));
                }
            }
            Collections.sort(cur, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int c = comp.compare(points[o1].x, points[o2].x);
                    if (c != 0) return c;
                    return comp.compare(points[o1].y, points[o2].y);
                }
            });
            for (int it = 1; it < cur.size(); it++) {
                int current = cur.get(it);
                int prev = cur.get(it - 1);
                if (prev == current) continue;
                if (prev > current) {
                    int t = prev;
                    prev = current;
                    current = t;
                }
                if (!was.add(prev * points.length + current)) {
                    continue;
                }
                dsu.union(current, prev);
                deg[current]++;
                deg[prev]++;
            }
        }
        int[] size = new int[cn];
        int[] odd = new int[cn];
        for (int i = 0; i < cn; i++) {
            size[dsu.get(i)]++;
            if (deg[i] % 2 == 1) {
                odd[dsu.get(i)]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < cn; i++) {
            if (size[i] > 0) {
                ans++;
                if (k % 2 == 1) {
                    int q = odd[i];
                    if (q == 0) continue;
                    q -= 2;
                    ans += q / 2;
                }
            }
        }
        out.println(ans - 1);
    }
}
