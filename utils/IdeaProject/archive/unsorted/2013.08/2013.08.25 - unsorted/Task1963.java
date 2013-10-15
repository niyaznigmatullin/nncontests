package coding;

import ru.ifmo.niyaz.geometry.Point2DInteger;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.HashMap;
import java.util.Map;

public class Task1963 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Point2DInteger[] p = new Point2DInteger[4];
        for (int i = 0; i < 4; i++) {
            int x = in.nextInt() << 1;
            int y = in.nextInt() << 1;
            p[i] = new Point2DInteger(x, y);
        }
        Map<Line, Integer> lines = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                Line line = new Line(p[i], p[j]);
                Integer z = lines.get(line);
                if (z == null) {
                    z = 0;
                }
                lines.put(line, z + 1);
            }
        }
        int ans = 0;
        for (Line e : lines.keySet()) {
            int got = lines.get(e) * 2;
            for (int i = 0; i < 4; i++) {
                int v = p[i].x * e.a + p[i].y * e.b + e.c;
                if (v == 0) {
                    got++;
                }
            }
            if (got == 4) {
                ++ans;
            }
        }
        out.println(ans * 2);
    }

    static class Line {
        int a;
        int b;
        int c;

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Line)) return false;

            Line line = (Line) o;

            if (a != line.a) return false;
            if (b != line.b) return false;
            if (c != line.c) return false;

            return true;
        }

        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            result = 31 * result + c;
            return result;
        }

        Line(Point2DInteger p1, Point2DInteger p2) {
            Point2DInteger p0 = p1.add(p2);
            p0.x /= 2;
            p0.y /= 2;
            a = p2.x - p1.x;
            b = p2.y - p1.y;
            c = -p0.x * a - p0.y * b;
            int g = MathUtils.gcd(MathUtils.gcd(a, b), c);
            a /= g;
            b /= g;
            c /= g;
            if (a < 0 || a == 0 && b < 0 || a == 0 && b == 0 && c < 0) {
                a = -a;
                b = -b;
                c = -c;
            }
        }
    }
}
