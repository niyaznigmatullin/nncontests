package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class TaskC {
    static final double EPS = 1e-8;
    static final double RADIUS_2 = 6.25;

    static class Point {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        List<Point> h = new ArrayList<Point>();
        while (true) {
            String first = in.nextToken();
            if (first == null) {
                break;
            }
            h.add(new Point(Double.parseDouble(first), in.nextDouble()));
        }
        int answer = 1;
        for (Point p : h) {
            for (Point s : h) {
                if (p == s) {
                    continue;
                }
                double dx = p.x - s.x;
                double dy = p.y - s.y;
                double d = dx * dx + dy * dy;
                if (compare(d, 4 * RADIUS_2) > 0) {
                    continue;
                }
                double z = Math.sqrt(RADIUS_2 - d * .25);
                d = Math.sqrt(d);
                dx *= z;
                dy *= z;
                dx /= d;
                dy /= d;
                answer = Math.max(tryToDo((p.x + s.x) * .5 - dy, (p.y + s.y) * .5 + dx, h), answer);
                answer = Math.max(tryToDo((p.x + s.x) * .5 + dy, (p.y + s.y) * .5 - dx, h), answer);
            }
        }
        out.println(answer);
    }


    static int tryToDo(double cx, double cy, List<Point> h) {
        int ret = 0;
        for (Point e : h) {
            double dx = cx - e.x;
            double dy = cy - e.y;
            if (compare(dx * dx + dy * dy, RADIUS_2) <= 0) {
                ret++;
            }
        }
        return ret;
    }

    static int compare(double a, double b) {
        return Math.abs(a - b) < EPS ? 0 : Double.compare(a, b);
    }
}
