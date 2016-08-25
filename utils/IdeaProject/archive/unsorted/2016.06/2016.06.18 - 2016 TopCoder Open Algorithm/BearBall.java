package coding;

import ru.ifmo.niyaz.geometry.GeometryAlgorithms;
import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.HashMap;
import java.util.Map;

public class BearBall {
    public int countThrows(int[] x, int[] y) {
        int n = x.length;
        Point2DInteger[] p = new Point2DInteger[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Point2DInteger(x[i], y[i]);
        }
        boolean sameLine = true;
        for (int i = 2; i < x.length; i++) {
            if (GeometryAlgorithms.vmulFromPoint(p[0], p[1], p[i]) != 0) {
                sameLine = false;
                break;
            }
        }
        if (sameLine) {
            int ans = 0;
            for (int i = 1; i < n; i++) {
                int ways = n - i;
                ans += ways * i * 2;
            }
            return ans;
        }
        Map<Line, Integer> lines = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Line e = new Line(p[i], p[j]);
                Integer got = lines.get(e);
                if (got == null) got = 0;
                lines.put(e, got + 1);
            }
        }
        int ans = 0;
        for (Map.Entry<Line, Integer> e : lines.entrySet()) {
            int f = e.getValue();
            int g = (int) Math.round((1 + Math.sqrt(1 + 8. * f)) * .5);
            if (f == 1) {
                ans += 2;
            } else {
                ans += 4 * (f - g + 1);
                ans += 2 * (g - 1);
            }
        }
        return ans;
    }

    static class Line {
        int a;
        int b;
        int c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (a != line.a) return false;
            if (b != line.b) return false;
            return c == line.c;

        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            result = 31 * result + c;
            return result;
        }

        Line(Point2DInteger p, Point2DInteger q) {
            a = q.y - p.y;
            b = p.x - q.x;
            c = -p.x * a - p.y * b;
            if (a < 0 || (a == 0 && b < 0)) {
                a = -a;
                b = -b;
                c = -c;
            }
            int g = MathUtils.gcd(Math.abs(a), MathUtils.gcd(Math.abs(b), Math.abs(c)));
            a /= g;
            b /= g;
            c /= g;
        }
    }
}
