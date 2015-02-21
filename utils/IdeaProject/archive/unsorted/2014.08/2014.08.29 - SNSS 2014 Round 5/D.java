import java.io.*;
import java.util.*;

public class D {

	static void solve() throws IOException {
		Point center = new Point(nextInt(), nextInt());
		int radius = nextInt();
		Point p1 = new Point(nextInt(), nextInt());
		Point p2 = new Point(nextInt(), nextInt());
		Point q1 = new Point(nextInt(), nextInt());
		Point q2 = new Point(nextInt(), nextInt());
		Point q3 = new Point(nextInt(), nextInt());
		Point start = new Point(0, 0);
		Point finish = new Point(1000, 1000);
		int ans = 0;
		if (center.distanceSquared(start) < radius * radius) {
			++ans;
		}
		if (Math.abs(q1.vmul(q2)) + Math.abs(q1.vmul(q3))
				+ Math.abs(q2.vmul(q3)) == Math.abs(q1.vmul(q2) + q2.vmul(q3)
				+ q3.vmul(q1))) {
			++ans;
		}
		if (p1.x <= 0 && p2.x >= 0 && p1.y <= 0 && p2.y >= 0) {
			++ans;
		}
		out.println(ans);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("d.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("d.out");
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

class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public long distanceSquared(Point p) {
		long dx = x - p.x;
		long dy = y - p.y;
		return dx * dx + dy * dy;
	}

	public double distance(Point p) {
		return Math.sqrt(distanceSquared(p));
	}

	public Point add(Point p) {
		return new Point(x + p.x, y + p.y);
	}

	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y);
	}

	static public Point[] minkovskySum(Point[] p, Point[] q) {
		p = normalizePolygonArray(p);
		q = normalizePolygonArray(q);
		int n = p.length;
		int m = q.length;
		Point[] ret = new Point[n + m];
		ret[0] = p[0].add(q[0]);
		for (int i = 0, j = 0; i + j + 1 < n + m;) {
			int i2 = (i + 1) % n;
			int j2 = (j + 1) % m;
			if (j >= m
					|| i < n
					&& (long) (p[i2].x - p[i].x) * (q[j2].y - q[j].y)
							- (long) (p[i2].y - p[i].y) * (q[j2].x - q[j].x) >= 0) {
				ret[i + j + 1] = ret[i + j].add(p[i2]).subtract(p[i]);
				i++;
			} else {
				ret[i + j + 1] = ret[i + j].add(q[j2]).subtract(q[j]);
				j++;
			}
		}
		return ret;
	}

	public long smul(Point p) {
		return (long) x * p.x + (long) y * p.y;
	}

	public long vmul(Point p) {
		return (long) x * p.y - (long) y * p.x;
	}

	private static Point[] normalizePolygonArray(Point[] p) {
		long area = 0;
		int n = p.length;
		for (int i = 0; i < n; i++) {
			int j = (i + 1) % n;
			area += (long) p[i].x * p[j].y - (long) p[i].y * p[j].x;
		}
		if (area < 0) {
			for (int i = 1, j = n - 1; i < j; i++, j--) {
				Point tmp = p[i];
				p[i] = p[j];
				p[j] = tmp;
			}
		}
		int minIndex = -1;
		for (int i = 0; i < n; i++) {
			if (minIndex < 0 || p[i].y < p[minIndex].y
					|| p[i].y == p[minIndex].y && p[i].x < p[minIndex].x) {
				minIndex = i;
			}
		}
		Point[] ret = new Point[n];
		System.arraycopy(p, minIndex, ret, 0, p.length - minIndex);
		System.arraycopy(p, 0, ret, p.length - minIndex, minIndex);
		return ret;
	}

	@Override
	public String toString() {
		return "{" + "x=" + x + ", y=" + y + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Point that = (Point) o;

		if (x != that.x)
			return false;
		if (y != that.y)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	public boolean isOnSegment(Point p, Point q) {
		Point v1 = subtract(p);
		Point v2 = subtract(q);
		if (v1.vmul(v2) != 0) {
			return false;
		}
		return v1.smul(v2) <= 0;
	}

	public long distSquared(int x, int y) {
		long dx = this.x - x;
		long dy = this.y - y;
		return dx * dx + dy * dy;
	}

	public double dist(int x, int y) {
		return Math.sqrt(distSquared(x, y));
	}

	public double distSquared(double x, double y) {
		x -= this.x;
		y -= this.y;
		return x * x + y * y;
	}

	public double dist(double x, double y) {
		return Math.sqrt(distSquared(x, y));
	}

	public long squaredLength() {
		return (long) x * x + (long) y * y;
	}

	public double length() {
		return Math.sqrt(squaredLength());
	}

}
