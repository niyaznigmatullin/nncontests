package coding;

import ru.ifmo.niyaz.geometry.Point2DInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TrianglePainting {

    static class Point extends Point2DInteger {
        int id;

        Point(int x, int y, int id) {
            super(x, y);
            this.id = id;
        }
    }

    public double expectedArea(int[] x1, int[] y1, int[] x2, int[] y2, int[] prob) {
        int n = prob.length;
        Point[] v = new Point[3 * n];
        double ans = 0;
        for (int i = 0; i < n; i++) {
            if (x1[i] * y2[i] - x2[i] * y1[i] < 0) {
                int t = x1[i];
                x1[i] = x2[i];
                x2[i] = t;
                t = y1[i];
                y1[i] = y2[i];
                y2[i] = t;
            }
            ans += (x1[i] * y2[i] - x2[i] * y1[i]) * .01 * prob[i];
            v[i] = new Point(x1[i], y1[i], i);
            v[i + n] = new Point(x2[i] - x1[i], y2[i] - y1[i], i);
            v[i + n + n] = new Point(-x2[i], -y2[i], i);
        }
        Arrays.sort(v, new Comparator<Point>() {

            int get(int x, int y) {
                if (x < 0) {
                    return y > 0 ? 4 : y < 0 ? 6 : 5;
                } else if (x > 0) {
                    return y > 0 ? 2 : y < 0 ? 8 : 1;
                } else {
                    return y > 0 ? 3 : y < 0 ? 7 : 0;
                }
            }

            @Override
            public int compare(Point o1, Point o2) {
                int c = get(o1.x, o1.y) - get(o2.x, o2.y);
                if (c != 0) return c;
                return -Long.signum(o1.vmul(o2));
            }
        });
        List<Point>[] was = new ArrayList[n];
        for (int i = 0; i < n; i++) was[i] = new ArrayList<>();
        double dx = 0;
        double dy = 0;
        for (Point e : v) {
            double curDX = dx;
            double curDY = dy;
            for (Point f : was[e.id]) {
                curDX -= f.x * prob[e.id] * .01;
                curDY -= f.y * prob[e.id] * .01;
            }
            ans += ((-e.x * curDY) + e.y * curDX) * .01 * prob[e.id];
            dx += e.x * prob[e.id] * .01;
            dy += e.y * prob[e.id] * .01;
            was[e.id].add(e);
        }
        return ans * .5;
    }
}
