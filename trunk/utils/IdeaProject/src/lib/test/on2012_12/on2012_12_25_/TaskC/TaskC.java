package lib.test.on2012_12.on2012_12_25_.TaskC;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class TaskC {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        long dist2(Point p) {
            long dx = x - p.x;
            long dy = y - p.y;
            return dx * dx + dy * dy;
        }

        long abs2() {
            return smul(this);
        }

        long smul(Point p) {
            return (long) x * p.x + (long) y * p.y;
        }

        Point sub(Point p) {
            return new Point(x - p.x, y - p.y);
        }
    }

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        Point[] p1 = new Point[4];
        Point[] p2 = new Point[4];
        for (int i = 0; i < 4; i++) {
            p1[i] = new Point(in.nextInt(), in.nextInt());
            p2[i] = new Point(in.nextInt(), in.nextInt());
        }
        int[] first = new int[4];
        int[] second = new int[4];
        Arrays.fill(first, -1);
        Arrays.fill(second, -1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    continue;
                }
                if (p1[i].equals(p2[j]) || p1[i].equals(p1[j]) || p2[i].equals(p1[j]) || p2[i].equals(p2[j])) {
                    if (first[i] < 0) {
                        first[i] = j;
                    } else if (second[i] < 0) {
                        second[i] = j;
                    } else {
                        out.println("NO");
                        return;
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (second[i] < 0) {
                out.println("NO");
                return;
            }
        }
        int[] p = new int[4];
        p[1] = first[0];
        for (int i = 2; i < 4; i++) {
            p[i] = p[i - 2] ^ first[p[i - 1]] ^ second[p[i - 1]];
        }
        Point[] v = new Point[4];
        for (int i = 0; i < 4; i++) {
            v[i] = p2[p[i]].sub(p1[p[i]]);
        }
        for (int i = 0; i < 4; i++) {
            if (v[i].smul(v[(i + 1) & 3]) != 0 || v[i].abs2() != v[i + 2 & 3].abs2() || v[i].abs2() == 0 || (v[i].x != 0 && v[i].y != 0)) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }
}
