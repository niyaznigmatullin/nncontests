package coding;

import ru.ifmo.niyaz.geometry.Point3DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.DoubleComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskD2 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        double r = in.nextInt();
        Point3DDouble[] p = new Point3DDouble[n];
        Point3DDouble[] v = new Point3DDouble[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point3DDouble(in.nextInt(), in.nextInt(), in.nextInt());
            v[i] = new Point3DDouble(in.nextInt(), in.nextInt(), in.nextInt());
        }
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Point3DDouble toInit = p[i].multiply(-1);
            double smul = toInit.smul(v[i]) / v[i].lengthSquared();
            Point3DDouble p0 = p[i].add(v[i].multiply(smul));
            double dist = p0.length();
            if (dbl.compare(dist, r) <= 0) {
                double z = r * r - dist * dist;
                if (z < 0) z = 0;
                z = Math.sqrt(z);
                z /= v[i].length();
                events.add(new Event(smul - z, -1, -1));
                events.add(new Event(smul + z, 1, -1));
            }
        }
        int m = in.nextInt();
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            events.add(new Event(in.nextInt(), 0, i));
        }
        Collections.sort(events);
        int cnt = 0;
        for (Event e : events) {
            if (e.type == -1) {
                ++cnt;
            }
            if (e.type == 1) {
                --cnt;
            }
            if (e.type == 0) {
                answer[e.id] = cnt;
            }
        }
        for (int i : answer) {
            out.println(i);
        }
    }

    static final double EPS = 1e-9;

    static final DoubleComparator dbl = new DoubleComparator() {
        @Override
        public int compare(double a, double b) {
            double c = Math.max(Math.abs(a), Math.abs(b));
            return Math.abs(a - b) / c < EPS || Math.abs(a - b) < EPS ? 0 : a < b ? -1 : 1;
        }
    };

    static class Event implements Comparable<Event> {
        double x;
        int type;
        int id;

        Event(double x, int type, int id) {
            this.x = x;
            this.type = type;
            this.id = id;
        }

        @Override
        public int compareTo(Event o) {
            int c = dbl.compare(x, o.x);
            if (c != 0) {
                return c;
            }
            return type - o.type;
        }
    }
}
