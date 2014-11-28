package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TheDividingLine {

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        while (true) {
//            System.err.println("TEST");
//            Random rand = new Random(21323L);
        int n = in.nextInt();
//            int n = rand.nextInt(5);
        Map<Point2DInteger, Integer> set = new HashMap<>();
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(in.nextInt(), in.nextInt());
//                p[i] = new Point2DInteger(rand.nextInt(10), rand.nextInt(10));
            set.put(p[i], i);
        }
        Point2DInteger[] q = p.clone();
        p = GeometryAlgorithms.convexHull(p);
        n = p.length;
        Point2DInteger[] v = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            v[i] = p[(i + 1) % n].subtract(p[i]);
        }
        int m = in.nextInt();
//            int m = rand.nextInt(5);
        for (int i = 0; i < m; i++) {
            if (n < 2) {
                out.println(0);
                continue;
//                    return;
            }
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();
//                int x1 = rand.nextInt(20);
//                int y1 = rand.nextInt(20);
//                int x2 = rand.nextInt(20);
//                int y2 = rand.nextInt(20);
            long a = y2 - y1;
            long b = x1 - x2;
            int first = 0;
            int l = 0;
            int r = n;
            Point2DInteger cur = new Point2DInteger((int) a, (int) b);
            while (first < n && v[first].smul(cur) == 0) {
                first++;
            }
            if (first == n) {
                out.println(0);
                continue;
            }
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                Point2DInteger vmid = v[(mid + first) % n];
                if ((vmid.smul(cur) >= 0) == (v[first].smul(cur) >= 0)) {
                    if (vmid.vmul(v[first]) >= 0) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                } else {
                    r = mid;
                }
            }
            Point2DInteger p1 = p[(l + first + 1) % n];
            l = 0;
            r = n;
            while (l < r - 1) {
                int mid = (l + r) >> 1;
                Point2DInteger vmid = v[(mid + first) % n];
                Point2DInteger vfirst = v[first];
                if ((vmid.smul(cur) >= 0) == (vfirst.smul(cur) >= 0)) {
                    if (vmid.vmul(vfirst) >= 0) {
                        r = mid;
                    } else {
                        l = mid;
                    }
                } else {
                    l = mid;
                }
            }
            Point2DInteger p2 = p[(l + first + 1) % n];
            long c = -a * x1 - b * y1;
            long v1 = p1.x * a + p1.y * b + c;
            long v2 = p2.x * a + p2.y * b + c;
            if (v1 < 0 && v2 > 0 || v1 > 0 && v2 < 0) {
                int id1 = set.get(p1);
                int id2 = set.get(p2);
                {
                    long u1 = q[id1].x * a + q[id1].y * b + c;
                    long u2 = q[id2].x * a + q[id2].y * b + c;
                    if (u1 <= 0 && u2 <= 0 || u1 >= 0 && u2 >= 0) {
                        throw new AssertionError();
                    }
                }
                out.println((id1 + 1) + " " + (id2 + 1));
            } else {
//                long minV = 0;
//                long maxV = 0;
//                for (Point2DInteger e : q) {
//                    minV = Math.min(minV, e.x * a + e.y * b + c);
//                    maxV = Math.max(maxV, e.x * a + e.y * b + c);
//                }
//                if (minV < 0 && maxV > 0) throw new AssertionError();
                out.println(0);
            }
        }
//    }
    }
}
