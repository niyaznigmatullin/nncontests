import java.util.*;
import java.math.*;
import java.io.*;

public class H implements Runnable {
	public static void main(String[] args) {
		new Thread(new H()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
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
			br = new BufferedReader(new FileReader(new File("cell.in")));
			out = new PrintWriter("cell.out");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.close();
	}

	class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		double dist(Point p) {
			return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
		}

		Point subtract(Point p) {
			return new Point(x - p.x, y - p.y);
		}

		double len() {
			return Math.sqrt(x * x + y * y);
		}

		Point norm() {
			double z = len();
			return new Point(x / z, y / z);
		}

		Point add(Point p) {
			return new Point(x + p.x, y + p.y);
		}

		Point multiply(double d) {
			return new Point(x * d, y * d);
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}

	}

	void solve() {
		while (solve2())
			;
	}

	final double EPS = 1e-9;

	class Tower {
		Point p;
		int power;

		public Tower(Point p, int power) {
			this.p = p;
			this.power = power;
		}

		double get(Point e) {
			return power / sqr(e.dist(p));
		}
	}

	double sqr(double x) {
		return x * x;
	}

	boolean solve2() {
		int t = nextInt();
		if (t == 0)
			return false;
		int n = nextInt();
		Tower[] towers = new Tower[t];
		for (int i = 0; i < t; i++) {
			towers[i] = new Tower(new Point(nextInt(), nextInt()), nextInt());
		}
		n++;
		Point[] p = new Point[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Point(nextInt(), nextInt());
		}
		// System.err.println(Arrays.toString(p));
		ArrayList<Point> points = new ArrayList<Point>();
		double r = 1;
		for (int i = 0; i < n - 1; i++) {
			double dist = p[i].dist(p[i + 1]) - (1 - r);
			int e = (int) Math.floor(dist + 1 - EPS);
			Point v = p[i + 1].subtract(p[i]).norm();
			for (int j = 0; j < e; j++) {
				points.add(p[i].add(v.multiply(j).add(v.multiply(1 - r))));
			}
			r = p[i + 1].dist(points.get(points.size() - 1));
		}
		if (p[n - 1].dist(points.get(points.size() - 1)) > 0.5 - EPS) {
			points.add(p[n - 1]);
		}
		// System.err.println(points);
		char last = 0;
		ArrayList<String> ans = new ArrayList<String>();
		for (int i = 0; i < points.size(); i++) {
			double maxP = Integer.MIN_VALUE;
			char here = 0;
			for (int j = 0; j < t; j++) {
				double w = towers[j].get(points.get(i));
				if (maxP < w - EPS) {
					maxP = w;
					here = (char) (j + 'A');
				}
			}
			if (here != last) {
				ans.add("(" + i + "," + here + ")");
			}
			last = here;
		}
		for (int i = 0; i < ans.size(); i++) {
			if (i != 0)
				out.print(" ");
			out.print(ans.get(i));
		}
		out.println();
		return true;
	}
}