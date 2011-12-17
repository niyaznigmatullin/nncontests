import static java.lang.Math.sqrt;

import java.io.*;
import java.util.*;

public class E {

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			double[] d = new double[n];
			for (int j = 0; j < n; j++) {
				d[j] = nextDouble();
			}
			Point ans = solve(d);
			out.printf(Locale.US, (i + 1) + ": %.4f %.4f\n", ans.x, ans.y);
		}
	}

	static Point solve(double[] x) {
		int n = x.length;
		Point[][] p = new Point[n][];
		p[0] = new Point[n];
		for (int i = 0; i < n; i++) {
			p[0][i] = new Point(x[i], 1);
		}
		if (n == 1) {
			return p[0][0];
		}
		p[1] = new Point[n - 1];
		for (int i = 0; i < n - 1; i++) {
			double d = x[i + 1] - x[i];
			p[1][i] = new Point((x[i] + x[i + 1]) * .5, 1 + sqrt(4 - d * d
					* .25));
		}
		for (int i = 2; i < n; i++) {
			p[i] = new Point[p[i - 1].length - 1];
			for (int j = 0; j < p[i].length; j++) {
				p[i][j] = p[i - 1][j].add(p[i - 1][j + 1]).subtract(
						p[i - 2][j + 1]);
			}
		}
		return p[n - 1][0];
	}

	static class Point {
		double x;
		double y;

		private Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		Point add(Point p) {
			return new Point(x + p.x, y + p.y);
		}

		Point subtract(Point p) {
			return new Point(x - p.x, y - p.y);
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("cylinders.in"));
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