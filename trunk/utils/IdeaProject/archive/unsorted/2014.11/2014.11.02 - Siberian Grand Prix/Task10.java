import java.io.*;
import java.util.*;

public class Task10 {

	static class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public Point sub(Point q) {
			return new Point(x - q.x, y - q.y);
		}

		public double len() {
			return Math.sqrt(x * x + y * y);
		}

		double ang() {
			return Math.atan2(y, x);
		}

		double smul(Point p) {
			return x * p.x + y * p.y;
		}

	}

	static void solve() throws IOException {
		Point p1 = new Point(nextDouble(), nextDouble());
		Point p2 = new Point(nextDouble(), nextDouble());
		Point q = new Point(nextDouble(), nextDouble());
		p1 = p1.sub(q);
		p2 = p2.sub(q);
		double r1 = p1.len();
		double r2 = p2.len();
		if (r1 > r2) {
			Point t = p1;
			p1 = p2;
			p2 = t;
			double tt = r1;
			r1 = r2;
			r2 = tt;
		}
		final double EPS = 1e-8;
		double d = Math.log(r2) - Math.log(r1);
		double ang = Math
				.acos(Math.min(1, Math.max(-1, p1.smul(p2) / r1 / r2)));
		if (Math.abs(ang) < EPS) {
			out.println(Math.abs(r1 - r2));
		} else if (r2 - r1 < EPS) {
			out.println(r1 * ang);
		} else {
			double k = d / ang;
			System.err.println(k);
			out.println(Math.sqrt(1 + k * k) / k * (r2 - r1));
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("10.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("10.out");
		}
		br = new BufferedReader(new InputStreamReader(input));
		out = new PrintWriter(output);
		solve();
		out.close();
		br.close();
	}

	static boolean hasNext() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return false;
			}
			st = new StringTokenizer(line);
		}
		return true;
	}

	static String next() throws IOException {
		return hasNext() ? st.nextToken() : null;
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
