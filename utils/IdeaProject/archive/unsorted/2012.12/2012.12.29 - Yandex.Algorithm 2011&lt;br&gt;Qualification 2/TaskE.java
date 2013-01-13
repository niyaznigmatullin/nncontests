package coding;

import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int h = in.nextInt();
        int f = in.nextInt();
        Line[] z = new Line[4 * n];
        for (int i = 0; i < n; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            z[4 * i] = new Line(true, new Point2DInteger(0, f), new Point2DInteger(left, h));
            z[4 * i + 1] = new Line(false, new Point2DInteger(0, f), new Point2DInteger(right, h));
            z[4 * i + 2] = new Line(true, new Point2DInteger(0, -f), new Point2DInteger(left, -h));
            z[4 * i + 3] = new Line(false, new Point2DInteger(0, -f), new Point2DInteger(right, -h));
        }
        int iter = 2000;
        Event[] s = new Event[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            s[i] = new Event(0, false);
        }
        double answer = 0;
        for (int it = 0; it < iter; it++) {
            double y = 2. * h * it / iter - h;
            for (int i = 0; i < 4 * n; i++) {
                s[i].x = z[i].get(y);
                s[i].begin = z[i].begin;
            }
            Arrays.sort(s);
            double last = Double.NEGATIVE_INFINITY;
            double sum = 0;
            int count = 0;
            for (int i = 0; i < 4 * n; i++) {
                if (count > 0) {
                    sum += s[i].x - last;
                }
                if (s[i].begin) {
                    ++count;
                } else {
                    --count;
                }
                last = s[i].x;
            }
            answer += sum;
        }
        out.println(2 * h * answer / iter);
    }

    static class Event implements Comparable<Event> {
        double x;
        boolean begin;

        Event(double x, boolean begin) {
            this.x = x;
            this.begin = begin;
        }

        public int compareTo(Event o) {
            return Double.compare(x, o.x);
        }
    }

    static class Line {
        boolean begin;
        Point2DInteger p, q;

        Line(boolean begin, Point2DInteger p, Point2DInteger q) {
            this.begin = begin;
            this.p = p;
            this.q = q;
        }

        double get(double y) {
            return p.x + 1. * (q.x - p.x) / (q.y - p.y) * (y - p.y);
        }
    }
}
