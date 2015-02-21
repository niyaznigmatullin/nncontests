package coding;

import ru.ifmo.niyaz.geometry.Line2DDouble;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.lang.annotation.Retention;
import java.util.HashMap;

public class Map {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt() * 2;
            y[i] = in.nextInt() * 2;
            r[i] = in.nextInt();
        }
        HashMap<Line, Integer> f = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Line l = Line.getThroughTwoPoints(x[i], y[i], x[j], y[j]);
                if (!f.containsKey(l)) f.put(l, 0);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if (r[i] != r[j]) continue;
                Line l = Line.getMid(x[i], y[i], x[j], y[j]);
                Integer got = f.get(l);
                if (got == null) got = 0;
                f.put(l, got + 1);
            }
        }
        int ans = 0;
        for (Line e : f.keySet()) {
            int on = 0;
            for (int i = 0; i < n; i++) {
                if (e.a * x[i] + e.b * y[i] + e.c == 0) ++on;
            }
            if (f.get(e) * 2 + on == n) ++ans;
        }
        out.println(ans);
    }

    static class Line {
        long a;
        long b;
        long c;

        Line(long a, long b, long c) {
            long g = MathUtils.gcd(MathUtils.gcd(a, b), c);
            a /= g;
            b /= g;
            c /= g;
            if (a < 0 || a == 0 && b < 0) {
                a = -a;
                b = -b;
                c = -c;
            }
            this.a = a;
            this.b = b;
            this.c = c;
        }

        static Line getThroughTwoPoints(int x1, int y1, int x2, int y2) {
            long a = y2 - y1;
            long b = x1 - x2;
            long c = -a * x1 - b * y1;
            return new Line(a, b, c);
        }

        static Line getMid(int x1, int y1, int x2, int y2) {
            long a = x2 - x1;
            long b = y2 - y1;
            long c = -(x1 + x2) / 2L * a - (y1 + y2) / 2L * b;
            return new Line(a, b, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (a != line.a) return false;
            if (b != line.b) return false;
            if (c != line.c) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (a ^ (a >>> 32));
            result = 31 * result + (int) (b ^ (b >>> 32));
            result = 31 * result + (int) (c ^ (c >>> 32));
            return result;
        }
    }
}
