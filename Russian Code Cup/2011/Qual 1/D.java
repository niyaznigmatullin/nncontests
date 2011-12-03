import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class Line {
		int a;
		int b;
		int c;

		Line(Point p1, Point p2) {
			a = p2.y - p1.y;
			b = p1.x - p2.x;
			c = -p1.x * a - p1.y * b;
			if (a < 0 || a == 0 && b < 0 || a == 0 && b == 0 && c < 0) {
				a = -a;
				b = -b;
				c = -c;
			}
			int d = gcd(a, gcd(b, c));
			if (d > 1) {
				a /= d;
				b /= d;
				c /= d;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			return result;
		}

		@Override
		public String toString() {
			return "Line [a=" + a + ", b=" + b + ", c=" + c + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Line other = (Line) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}

		double dist(Line l) {
			return Math.abs(c - l.c) / Math.sqrt(a * a + b * b);
		}
	}

	static int gcd(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);
		return b == 0 ? a : gcd(b, a % b);
	}

	void solve() {
		int n = nextInt() << 1;
		Point[] p1 = new Point[n];
		Point[] p2 = new Point[n];
		for (int i = 0; i < n; i++) {
			p1[i] = new Point(nextInt(), nextInt());
			p2[i] = new Point(nextInt(), nextInt());
		}
		out.println(solve(n, p1, p2));
	}

	static Comparator<Line> byC = new Comparator<D.Line>() {
		@Override
		public int compare(Line o1, Line o2) {
			return o1.c - o2.c;
		}
	};

	private String solve(int n, Point[] p1, Point[] p2) {
		Line[] lines = new Line[n];
		for (int i = 0; i < n; i++) {
			lines[i] = new Line(p1[i], p2[i]);
		}
		Map<Line, List<Line>> map = new HashMap<Line, List<Line>>();
		for (Line l : lines) {
			if (!map.containsKey(l)) {
				map.put(l, new ArrayList<Line>(2));
			}
			map.get(l).add(l);
		}
		for (Line l : map.keySet()) {
			if (map.get(l).size() % 2 == 1) {
				return "-1";
			}
			Collections.sort(map.get(l), byC);
		}
		// System.err.println(map);
		List<Line> firstList = map.get(lines[0]);
		Line[] firstArray = firstList.toArray(new Line[firstList.size()]);
		all: for (int curDist = 1; curDist < firstArray.length; curDist++) {
			double dist = firstArray[0].dist(firstArray[curDist]);
			for (List<Line> curList : map.values()) {
				Line[] cur = curList.toArray(new Line[curList.size()]);
				// System.err.println(curList);
				boolean[] was = new boolean[cur.length];
				for (int i = 0, j = 0; i < cur.length; i++) {
					if (was[i]) {
						continue;
					}
					while (j < cur.length
							&& compare(dist, cur[i].dist(cur[j])) > 0) {
						j++;
					}
					if (j == cur.length
							|| compare(dist, cur[i].dist(cur[j])) < 0) {
						continue all;
					}
					was[j] = true;
					j++;
				}
			}
			return dist + "";
		}
		return "-1";
	}

	static final double EPS = 1e-9;

	static int compare(double a, double b) {
		return Math.abs(a - b) < EPS ? 0 : Double.compare(a, b);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
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