import java.util.*;
import java.math.*;
import java.io.*;

public class I implements Runnable {
	public static void main(String[] args) {
		new Thread(new I()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine(), " ,");
			} catch (Exception e) {
				eof = true;
				return "0";
			}
		}
		return st.nextToken();
	}

	String nextLine() {
		String ret = "";
		try {
			ret = br.readLine();
		} catch (Exception e) {
			ret = "";
		}
		if (ret == null) {
			eof = true;
			return "$";
		}
		return ret;
	}

	String nextString() {
		return nextToken();
	}

	int nextInt() {
		return Integer.parseInt(nextToken());
	}

	long nextLong() {
		return Long.parseLong(nextToken());
	}

	double nextDouble() {
		return Double.parseDouble(nextToken());
	}

	BigInteger nextBigInteger() {
		return new BigInteger(nextToken());
	}

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			// br = new BufferedReader(new FileReader("input.txt"));
			// out = new PrintWriter("output.txt");
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
	}

	void solve() {
		int n = nextInt();
		for (int i = 0; i < n; i++) {
			solve2();
		}
	}

	final double EPS = 1e-3;
	final double sqrt3 = Math.sqrt(3.);

	class Point implements Comparable<Point> {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(Point o) {
			if (x < o.x - EPS) {
				return -1;
			}
			if (x > o.x + EPS) {
				return 1;
			}
			if (y < o.y - EPS) {
				return -1;
			}
			if (y > o.y + EPS) {
				return 1;
			}
			return 0;
		}

		Point add(Point p) {
			return new Point(x + p.x, y + p.y);
		}

		Point subtract(Point p) {
			return new Point(x - p.x, y - p.y);
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

		Point rotate(double v) {
			return new Point(Math.cos(v) * x - Math.sin(v) * y, Math.sin(v) * x
					+ Math.cos(v) * y);
		}

		Point multiply(double v) {
			return new Point(x * v, y * v);
		}
	}

	class Segment implements Comparable<Segment> {
		Point p1, p2;

		public Segment(Point p1, Point p2) {
			if (p1.compareTo(p2) > 0) {
				Point temp = p1;
				p1 = p2;
				p2 = temp;
			}
			this.p1 = p1;
			this.p2 = p2;
		}

		boolean isHorizontal() {
			return Math.abs(p2.y - p1.y) < EPS;
		}

		@Override
		public int compareTo(Segment o) {
			int y = p1.compareTo(o.p1);
			if (y != 0)
				return y;
			return p2.compareTo(o.p2);
		}

		@Override
		public String toString() {
			return "Segment [p1=" + p1 + ", p2=" + p2 + "]";
		}
	}

	void solve2() {
		int n = nextInt();
		Segment[] seg = new Segment[n];
		TreeSet<Segment> segments = new TreeSet<Segment>();
		for (int i = 0; i < n; i++) {
			seg[i] = new Segment(new Point(nextDouble(), nextDouble()),
					new Point(nextDouble(), nextDouble()));
			segments.add(seg[i]);
			// System.err.println(seg[i]);
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (!seg[i].isHorizontal()) {
				continue;
			}
			Segment num1 = new Segment(seg[i].p1.add(new Point(0, -sqrt3)),
					seg[i].p2.add(new Point(0, -sqrt3)));
			// System.err.println(num1);
			if (!segments.contains(num1)) {
				continue;
			}
			Point d = seg[i].p2.subtract(seg[i].p1);
			Point v = d.rotate(4 * Math.PI / 3.);
			Segment num2 = new Segment(seg[i].p1, seg[i].p1.add(v));
			if (!segments.contains(num2)) {
				continue;
			}
			num2 = new Segment(num1.p2, num1.p2.add(v.multiply(-1)));
			if (!segments.contains(num2)) {
				continue;
			}
			v = d.rotate(2 * Math.PI - Math.PI / 3);
			num2 = new Segment(seg[i].p2, seg[i].p2.add(v));
			if (!segments.contains(num2)) {
				continue;
			}
			num2 = new Segment(num1.p1, num1.p1.add(v.multiply(-1)));
			if (!segments.contains(num2)) {
				continue;
			}
			ans++;
		}
		out.println(ans);
	}
}