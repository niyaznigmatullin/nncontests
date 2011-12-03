import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	static class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

	}

	static final int ITER = 3000, SEARCH = 10;

	void solve() {
		int n = nextInt();
		Point[] p = new Point[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Point(nextDouble(), nextDouble());
		}
		Point center = new Point(nextDouble(), nextDouble());
		int m = nextInt();
		double[] angles = new double[m];
		for (int i = 0; i < m; i++) {
			angles[i] = nextDouble() / 180. * Math.PI;
		}
		double ans = Double.POSITIVE_INFINITY;
		for (int i = 0; i < ITER; i++) {
			double angle1 = 2 * Math.PI * i / ITER;
			double angle2 = 2 * Math.PI * (i + 1) / ITER;
			double got = get(center, p, angles, angle1, angle2);
			ans = Math.min(ans, got);
			// out.println(angle1 + " " + angle2 + " " + got);
		}
		out.println(ans);
	}

	static double get(Point center, Point[] p, double[] ang, double l, double r) {
		double[] e = ang.clone();
		double best = Double.POSITIVE_INFINITY;
		;
		for (int i = 0; i < SEARCH; i++) {
			double m1 = (l + l + r) / 3.;
			double m2 = (l + r + r) / 3.;
			for (int j = 0; j < ang.length; j++) {
				e[j] = (ang[j] + m1) % (2 * Math.PI);
			}
			double f1 = f(center, p, e);
			for (int j = 0; j < ang.length; j++) {
				e[j] = (ang[j] + m2) % (2 * Math.PI);
			}
			double f2 = f(center, p, e);
			if (f1 > f2) {
				l = m1;
			} else {
				r = m2;
			}
			best = Math.min(best, Math.min(f1, f2));
		}
		return best;
	}

	static double f(Point center, Point[] p, double[] e) {
		Arrays.sort(e);
		int cur = 0;
		double ans = 0;
		for (double ang : e) {
			double dx = Math.cos(ang);
			double dy = Math.sin(ang);
			double a = -dy;
			double b = dx;
			double c = -a * center.x - b * center.y;
			for (;; cur++) {
				cur %= p.length;
				Point p1 = p[cur];
				Point p2 = p[(cur + 1) % p.length];
				double la = p2.y - p1.y;
				double lb = p1.x - p2.x;
				double lc = -p1.x * la - p1.y * lb;
				double z = a * lb - b * la;
				if (compare(z, 0) == 0) {
					continue;
				}
				double cx = (b * lc - c * lb) / z;
				double cy = (c * la - a * lc) / z;
				if (compare((cx - p1.x) * (cy - p2.y) - (cx - p2.x)
						* (cy - p1.y), 0) != 0) {
					continue;
				}
				if (compare(
						dist(p1.x, p1.y, cx, cy) + dist(p2.x, p2.y, cx, cy),
						dist(p1.x, p1.y, p2.x, p2.y)) != 0) {
					continue;
				}
				double dcx = cx - center.x;
				double dcy = cy - center.y;
				if (compare(dcx, 0) == 0) {
					if (sign(dcy) != sign(dy)) {
						continue;
					}
				} else {
					if (sign(dcx) != sign(dx)) {
						continue;
					}
				}
				ans += Math.sqrt(dcx * dcx + dcy * dcy);
				break;
			}
		}
		return ans;
	}

	static final double EPS = 1e-5;

	static double dist(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	static int sign(double a) {
		if (compare(a, 0) == 0) {
			return 0;
		}
		if (Double.compare(a, 0) < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	static int compare(double a, double b) {
		return Math.abs(a - b) < EPS ? 0 : Double.compare(a, b);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File inputFile = new File("input.txt");
			if (inputFile.canRead()) {
				System.setIn(new FileInputStream(inputFile));
				System.setOut(new PrintStream("output.txt"));
			}
			sc = new FastScanner(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	class FastScanner extends BufferedReader {
		StringTokenizer st;
		boolean eof;
		String buf;
		String curLine;
		boolean createST;

		public FastScanner(String fileName) throws FileNotFoundException {
			this(fileName, true);
		}

		public FastScanner(String fileName, boolean createST)
				throws FileNotFoundException {
			super(new FileReader(fileName));
			this.createST = createST;
			nextToken();
		}

		public FastScanner(InputStream stream) {
			this(stream, true);
		}

		public FastScanner(InputStream stream, boolean createST) {
			super(new InputStreamReader(stream));
			this.createST = createST;
			nextToken();
		}

		String nextLine() {
			String ret = curLine;
			if (createST) {
				st = null;
			}
			nextToken();
			return ret;
		}

		String nextToken() {
			if (!createST) {
				try {
					curLine = readLine();
				} catch (Exception e) {
					eof = true;
				}
				return null;
			}
			while (st == null || !st.hasMoreTokens()) {
				try {
					curLine = readLine();
					st = new StringTokenizer(curLine);
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
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

		public void close() {
			try {
				buf = null;
				st = null;
				curLine = null;
				super.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	public static void main(String[] args) {
		new D().run();
	}
}
