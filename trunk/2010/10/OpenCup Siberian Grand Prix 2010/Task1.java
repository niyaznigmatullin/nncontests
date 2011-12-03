import java.io.*;
import java.util.*;
import java.math.*;

public class Task1 implements Runnable {
	final static double EPS = 1e-9;

	public static void main(String[] args) {
		new Task1().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
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

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			sc = new FastScanner("input.txt");
			out = new PrintWriter("output.txt");
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

	class E {
		double x, y, z;
		double r, alpha;

		public E(double x, double y, double z, double alpha, double r) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.r = r;
			this.alpha = alpha / 180. * Math.PI;
		}

	}

	final int ITERATIONS = 400000;

	double union(/*
				 * double x1, double y1, double r1, double x2, double y2, double
				 * r2
				 */) {
		// System.err.println(r1 + " " + r2);
		double dx = x1 - x2;
		double dy = y1 - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		if (compare(d, r1 + r2) >= 0) {
			return Math.PI * (r1 * r1 + r2 * r2);
		}
		if (compare(d + r1, r2) <= 0) {
			return Math.PI * r2 * r2;
		}
		if (compare(d + r2, r1) <= 0) {
			return Math.PI * r1 * r1;
		}
		double difS = r1 * r1 - r2 * r2;
		double d2 = .5 * (d - difS / d);
		double d1 = d - d2;
		double beta = Math.acos(d1 / r1);
		double theta = Math.acos(d2 / r2);
		double area = Math.PI * (r1 * r1 + r2 * r2) - areaSegment(r1, beta)
				- areaSegment(r2, theta);
		return area;
	}

	double areaSegment(double r1, double alpha) {
		double areaSector = r1 * r1 * alpha;
		double areaTriangle = r1 * r1 * Math.sin(2 * alpha) * .5;
		return areaSector - areaTriangle;
	}

	int compare(double a, double b) {
		return Math.abs(a - b) < EPS ? 0 : a < b ? -1 : 1;
	}

	double x1, y1, x2, y2, r1, r2;

	double getIt(E e1, E e2, double curZ) {
		if (curZ < e1.z || curZ > e1.z + e1.r) {
			r1 = 0;
		} else if (curZ <= e1.z + e1.r * Math.cos(e1.alpha)) {
			r1 = (curZ - e1.z) * Math.tan(e1.alpha);
		} else {
			r1 = Math.sqrt(e1.r * e1.r - (curZ - e1.z) * (curZ - e1.z));
		}

		if (curZ < e2.z || curZ > e2.z + e2.r) {
			r2 = 0;
		} else if (curZ <= e2.z + e2.r * Math.cos(e2.alpha)) {
			r2 = (curZ - e2.z) * Math.tan(e2.alpha);
		} else {
			r2 = Math.sqrt(e2.r * e2.r - (curZ - e2.z) * (curZ - e2.z));
		}

		return union();
	}

	void solve() {
		E e1 = new E(nextDouble(), nextDouble(), nextDouble(), nextDouble(),
				nextDouble());
		E e2 = new E(nextDouble(), nextDouble(), nextDouble(), nextDouble(),
				nextDouble());
		x1 = e1.x;
		y1 = e1.y;
		x2 = e2.x;
		y2 = e2.y;
		double minZ = Math.min(e1.z, e2.z);
		double maxZ = Math.max(e1.z + e1.r, e2.z + e2.r);
		double dz = (maxZ - minZ) / ITERATIONS;
		double ans = 0;
		for (int i = 0; i < ITERATIONS; i++) {
			double curZ = minZ + dz * i;
			ans += dz * getIt(e1, e2, curZ);
		}
		out.printf("%.1f", ans);
	}
}