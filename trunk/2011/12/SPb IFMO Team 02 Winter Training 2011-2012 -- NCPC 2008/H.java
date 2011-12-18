import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.*;
import java.util.*;

public class H {

	static class Point {
		final double x, y;

		private Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		double abs() {
			return Math.hypot(x, y);
		}

		double smul(Point p) {
			return x * p.x + y * p.y;
		}

		double vmul(Point p) {
			return x * p.y - y * p.x;
		}

		Point sub(Point p) {
			return new Point(x - p.x, y - p.y);
		}

		public String toString() {
			return String.format(Locale.US, "(%.2f, %.2f)", x, y);
		}
	}

	static class Line {
		final double a, b, c;

		private Line(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		static Line lineForTwoPoints(Point u, Point v) {
			double a = u.y - v.y;
			double b = -u.x + v.x;
			double c = a * u.x + b * u.y;
			return new Line(a, b, c);
		}

		static Line lineForPointAndNorm(Point p, Point norm) {
			double a = norm.x;
			double b = norm.y;
			double c = p.x * a + p.y * b;
			return new Line(a, b, c);
		}

		boolean contains(Point p) {
			return abs(a * p.x + b * p.y - c) < 1e-6;
		}
	}

	static Point intersect(Line l1, Line l2) {
		double det = l1.a * l2.b - l1.b * l2.a;
		double det1 = l1.c * l2.b - l1.b * l2.c;
		double det2 = l1.a * l2.c - l1.c * l2.a;
		double x = det1 / det;
		double y = det2 / det;
		return new Point(x, y);
	}

	static void solve() throws IOException {
		int n = nextInt();
		double r = nextDouble();
		Point[] p = new Point[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Point(nextDouble(), nextDouble());
		}
		double best = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				best = max(best, bestAngle(p[i], p[j], r));
			}
		}
		best = max(best, 0);
		best = min(best, 2 * Math.PI);
		out.println(best);
	}

	static boolean onLineWithZero(Point a, Point b) {
		return Math.abs(a.vmul(b)) < 1e-6;
	}

	static double bestAngle(Point a, Point b, double r) {
		if (a.abs() < b.abs()) {
			Point t = a;
			a = b;
			b = t;
		}
		Point c = new Point((a.x + b.x) * .5, (a.y + b.y) * .5);
		Point norm = new Point(b.y - a.y, -b.x + a.x);
		double halfAB;
		{
			double abs = norm.abs();
			halfAB = abs * .5;
			norm = new Point(norm.x / abs, norm.y / abs);
		}
		double low = 0, high = 10 * r;
		{
			Point goodPoint;
			if (onLineWithZero(a, b)) {
				goodPoint = c;
			} else {
				Line line1 = Line.lineForTwoPoints(new Point(0, 0), a);
				Line line2 = Line.lineForPointAndNorm(c, b.sub(a));
				goodPoint = intersect(line1, line2);
			}
			// System.err.println("test");
			// System.err.println(c + " " + norm);
			// System.err.println(a + " " + b + " -> " + goodPoint);
			Point ok = goodPoint.sub(a);
			double mul = ok.smul(norm);
			if (mul > 0) {
				norm = new Point(-norm.x, -norm.y);
				mul = -mul;
			}
			low = mul;
		}
		for (int it = 0; it < 50; it++) {
			double mid = (low + high) * .5;
			double xx = c.x + mid * norm.x;
			double yy = c.y + mid * norm.y;
			double abs = Math.hypot(xx, yy);
			double d1 = Math.hypot(mid, halfAB);
			double d2 = r - abs;
			if (d1 > d2) {
				high = mid;
			} else {
				low = mid;
			}
		}
		return Math.atan2(halfAB, low);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}