package coding;



import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Point2DInteger joe = getPoint2DInteger(in);
        Point2DInteger goal = getPoint2DInteger(in);
        int n = in.nextInt();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = getPoint2DInteger(in);
        }
        p = GeometryAlgorithms.convexHull(p);
        n = p.length;
        if (GeometryAlgorithms.isStrictlyInsidePolygon(p, joe) || GeometryAlgorithms.isStrictlyInsidePolygon(p, goal)) {
            out.println(-1);
            return;
        }
        boolean inter = false;
        for (int i = 0; i < n; i++) {
            if (segmentsIntersect(joe, goal, p[i], p[(i + 1) % n])) {
                inter = true;
                break;
            }
        }
        if (!inter) {
            out.println(joe.distance(goal));
            return;
        }
        int[] a = getIt(joe, p);
        int[] b = getIt(goal, p);
        double perimeter = 0;
        for (int i = 0; i < p.length; i++) {
            perimeter += p[(i + 1) % p.length].distance(p[i]);
        }
        double answer = Double.POSITIVE_INFINITY;
        for (int i : a) {
            for (int j : b) {
                double dist = 0;
                for (int k = i; k != j; ) {
                    int next = k + 1;
                    if (next == p.length) {
                        next = 0;
                    }
                    dist += p[k].distance(p[next]);
                    k = next;
                }
                dist = Math.min(dist, perimeter - dist);
                answer = Math.min(answer, dist + joe.distance(p[i]) + goal.distance(p[j]));
            }
        }
        out.println(answer);
    }

    static int[] getIt(Point2DInteger a, Point2DInteger[] p) {
        int minID = -1;
        int maxID = -1;
        for (int i = 0; i < p.length; i++) {
            if (minID < 0 || GeometryAlgorithms.vmulFromPoint(a, p[i], p[minID]) < 0) {
                minID = i;
            }
            if (maxID < 0 || GeometryAlgorithms.vmulFromPoint(a, p[i], p[maxID]) > 0) {
                maxID = i;
            }
        }
        return new int[]{minID, maxID};
    }

    public static boolean segmentsIntersect(Point2DInteger p1, Point2DInteger p2,
                                            Point2DInteger q1, Point2DInteger q2) {
        long v1 = GeometryAlgorithms.vmulFromPoint(p1, q1, q2);
        long v2 = GeometryAlgorithms.vmulFromPoint(p2, q1, q2);
        long u1 = GeometryAlgorithms.vmulFromPoint(q1, p1, p2);
        long u2 = GeometryAlgorithms.vmulFromPoint(q2, p1, p2);
        if (v1 == 0 || v2 == 0) {
            return false;
        }
        return Long.signum(v1) != Long.signum(v2) && Long.signum(u1) != Long.signum(u2);
    }


    private Point2DInteger getPoint2DInteger(FastScanner in) {
        return new Point2DInteger(in.nextInt(), in.nextInt());
    }
}
