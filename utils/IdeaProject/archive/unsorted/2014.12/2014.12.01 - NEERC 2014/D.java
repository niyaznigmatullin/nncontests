import java.util.*;
import java.io.*;

public class D {

	final int MAX = 2_000_000;

	double d, l, r, t, h;
	double cap;
	double y0, tan;

	double getAngle(double a, double b, double c) {
		if (a == 0 || b == 0)
			return 0;
		return Math.acos(Math.max(-1,
				Math.min(1, (a * a + b * b - c * c) / 2 / a / b)));
	}

	double capSize() {
		double angle = getAngle(r, r, d);
		return r - r * Math.cos(angle / 2);
	}

	void setY0Tan() {
		Point init = new Point(Math.sqrt(l * l - t * t), t);
		Point vec = new Point(init.x, init.y);
		vec.norm();
		vec.rotate();
		vec.mul(d);

		Point start = new Point(vec.x, vec.y);
		Point end = init.add(vec);

		Line line = new Line(start, end);
		Line line2 = new Line(0, 1, -h);

		Point inter = line.intersect(line2);
		if (inter != null) {
			double dist = inter.distTo(start);
			if (scalMul(start, end, inter) > 0) {
				this.y0 = dist;
			} else {
				this.y0 = -dist;
			}

			double alpha = Math.asin(t / l);
			double omega = Math.PI / 2 - alpha;
			this.tan = Math.tan(omega);
		}
	}

	double scalMul(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.x - a.x) + (b.y - a.y) * (c.y - a.y);
	}

	double getX(double y) {
		if (t == l) {
			if (y > h) {
				return 1e6;
			} else {
				return -1e6;
			}
		}
		if (t == 0) {
			return d - h;
		}

		return (y - y0) / tan;
	}

	double getRadius(double y) {
		if (y < 0) {
			double centerY = r - cap;
			return Math.sqrt(r * r - (y - centerY) * (y - centerY));
		}
		if (y > l) {
			double centerY = l + cap - r;
			return Math.sqrt(r * r - (y - centerY) * (y - centerY));
		}
		return d / 2;
	}

	double getF(double curX) {
		double curY = getX(curX);
		double rad = getRadius(curX);
		double O = d / 2;

		if (curY < O - rad) {
			return Math.PI * rad * rad;
		}

		if (curY > O + rad) {
			return 0;
		}

		double alpha = getAngle(rad, rad,
				2 * Math.sqrt(Math.max(0, rad * rad - (curY - O) * (curY - O))));

		if (curY <= O) {
			// System.err.println(Math.PI * rad * rad - segmentArea(rad,
			// alpha));
			return Math.PI * rad * rad - segmentArea(rad, alpha);
		} else {
			return segmentArea(rad, alpha);
		}

	}

	private double segmentArea(double rad, double alpha) {
		return rad * rad / 2 * (alpha - Math.sin(alpha));
	}

	void solve() {
		this.d = in.nextDouble() / 1000;
		this.l = in.nextDouble() / 1000;
		this.r = in.nextDouble() / 1000;
		this.t = in.nextDouble() / 1000;
		this.h = in.nextDouble() / 1000;

		this.cap = capSize();
		setY0Tan();

		double minY = -cap, maxY = l + cap;
		double step = (maxY - minY) / 2 / MAX;
		double[] xs = new double[MAX * 2 + 1];
		double[] ys = new double[MAX * 2 + 1];

		for (int i = 0; i <= 2 * MAX; i++) {
			xs[i] = minY + step * i;
			ys[i] = getF(xs[i]);
		}

		double ans = 0;
		for (int i = 0; i + 2 <= xs.length; i += 2) {
			double f1 = ys[i], f2 = ys[i + 1], f3 = ys[i + 2];
			double dist = 2 * step;
			ans += dist * (f1 + 4 * f2 + f3) / 6;
		}

		ans *= 1000;
		out.println(ans);
	}

	class Line {
		double a, b, c;

		public Line(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public Line(Point a, Point b) {
			this.a = b.y - a.y;
			this.b = a.x - b.x;
			this.c = -(this.a * a.x + this.b * a.y);
		}

		@Override
		public String toString() {
			return "Line [a=" + a + ", b=" + b + ", c=" + c + "]";
		}

		Point intersect(Line other) {
			double d = a * other.b - b * other.a;
			if (Math.abs(d) < eps) {
				return null;
			}
			double dx = (-c) * other.b - b * (-other.c);
			double dy = a * (-other.c) - (-c) * other.a;
			return new Point(dx / d, dy / d);
		}
	}

	final double eps = 1e-9;

	class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		Point add(Point other) {
			return new Point(x + other.x, y + other.y);
		}

		double len() {
			return Math.sqrt(x * x + y * y);
		}

		double distTo(Point other) {
			double dx = x - other.x, dy = y - other.y;
			return Math.sqrt(dx * dx + dy * dy);
		}

		void mul(double c) {
			x *= c;
			y *= c;
		}

		void norm() {
			mul(1 / len());
		}

		void rotate() {
			double newX = -y;
			double newY = x;
			this.x = newX;
			this.y = newY;
		}
	}

	FastScanner in;
	PrintWriter out;

	void run() {
		try {
			in = new FastScanner("damage.in");
			out = new PrintWriter("damage.out");
			solve();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}
	}

	public static void main(String[] args) {
		new D().run();
	}
}