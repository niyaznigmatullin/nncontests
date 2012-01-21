package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class TaskE {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        Map<Line, Integer> map = new HashMap<Line, Integer>();
        Line curLine = new Line(1, 1, 2, 2);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                curLine.set(x[i], y[i], x[j], y[j]);
                Integer got = map.get(curLine);
                if (got == null) {
                    map.put(new Line(x[i], y[i], x[j], y[j]), 1);
                } else {
                    map.put(curLine, got + 1);
                }
            }
        }
        int ans = 0;
        for (int e : map.values()) {
            ans = Math.max(ans, e);
        }
        int l = 0;
        int r = ans + 10;
        while (l < r - 1) {
            long mid = l + r >> 1;
            if (mid * (mid - 1) > 2 * ans) {
                r = (int) mid;
            } else {
                l = (int) mid;
            }
        }
        out.println(l);
	}

    static class Line {
        long a;
        long b;
        long c;

        Line(long x1, long y1, long x2, long y2) {
            set(x1, y1, x2, y2);
        }

        void set(long x1, long y1, long x2, long y2) {
            a = y2 - y1;
            b = x1 - x2;
            c = -x1 * a - y1 * b;
            if (a < 0 || a == 0 && b < 0) {
                a = -a;
                b = -b;
                c = -c;
            }
            long g = MathUtils.gcd(Math.abs(c), MathUtils.gcd(Math.abs(a), Math.abs(b)));
            if (g > 1) {
                a /= g;
                b /= g;
                c /= g;
            }
        }

        @Override
        public String toString() {
            return "Line{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Line)) return false;

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
