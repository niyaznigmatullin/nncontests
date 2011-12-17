import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;

import java.io.*;
import java.util.*;

public class F {
	
	static class Point {
		final double x, y, z;

		Point(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		Point add(Point b) {
			return new Point(x + b.x, y + b.y, z + b.z);
		}

		Point sub(Point b) {
			return new Point(x - b.x, y - b.y, z - b.z);
		}
		
		Point mul(double b) {
			return new Point(x * b, y * b, z * b);
		}
		
		double smul(Point b) {
			return x * b.x + y * b.y + z * b.z;
		}
		
		Point vmul(Point b) {
			return new Point(y * b.z - b.y * z, z * b.x - b.z * x, x * b.y - b.x * y);
		}
		
		public String toString() {
			return String.format(Locale.US, "(%.2f, %.2f, %.2f)", x, y, z);
		}
	}
	
	static double det(Point a, Point b, Point c) {
		return a.vmul(b).smul(c);
	}

	static void solve() throws IOException {
		int tests = nextInt();
		Point a = new Point(-50, 0, nextDouble());
		Point b = new Point(50, 0, nextDouble());
		for (int test = 0; test < tests; test++) {
			Point va, vb;
			{
				double alpha = nextDouble() * PI / 180;
				double beta = nextDouble() * PI / 180;
				double gamma = nextDouble() * PI / 180;
				double delta = nextDouble() * PI / 180;
				va = new Point(cos(alpha) * cos(gamma), cos(alpha) * sin(gamma), sin(alpha));
				vb = new Point(cos(beta) * cos(delta), cos(beta) * sin(delta), sin(beta));
			}
//			System.err.println(a + " " + va + " " + b + " " + vb);
			Point vec = va.vmul(vb);
			// a + da*va + d*vec = b + db*vb
			Point right = b.sub(a);
			double det = det(va, vec, vb);
			double da = det(right, vec, vb) / det;
			double d = det(va, right, vb) / det;
			double db = -det(va, vec, right) / det;
			Point ans = a.add(va.mul(da)).add(vec.mul(d / 2));
//			System.err.println(a.add(va.mul(da)).add(vec.mul(d)).sub(b.add(vb.mul(db))));
//			System.err.println(ans);
			out.println(test + 1 + ": " + round(ans.z));
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("rocket.in"));
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