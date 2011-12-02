import java.io.*;
import java.util.*;
import java.math.*;

public class E implements Runnable {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		int dist(Point p) {
			return Math.abs(x - p.x) + Math.abs(y - p.y);
		}
	}

	void solve() {
		int n = nextInt();
		Point[] p = new Point[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Point(nextInt(), nextInt());
		}
		int l = -1;
		int r = 20000;
		boolean[] was = new boolean[n];
		while (l < r - 1) {
			int m = (l + r) >> 1;
			Arrays.fill(was, false);
			for (int i = 1; i < n; i++) {
				if (p[0].dist(p[i]) <= m) {
					was[i] = true;
				}
			}
			boolean ok = true;
			all: for (int i = 1; i < n; i++) {
				if (was[i]) {
					continue;
				}
				for (int j = i + 1; j < n; j++) {
					if (was[j]) {
						continue;
					}
					if (p[i].dist(p[j]) > m) {
						ok = false;
						break all;
					}
				}
			}
			if (ok) {
				r = m;
			} else {
				l = m;
			}
		}
		for (int i = 0; i < n; i++) {
			boolean ok = false;
			for (int j = 0; j < n; j++) {
				if (p[i].dist(p[j]) > r) {
					ok = true;
					break;
				}
			}
			if (!ok) {
				continue;
			}
			boolean[] can = new boolean[n];
			for (int j = 0; j < n; j++) {
				can[j] = (p[i].dist(p[j]) > r);

			}
			int count = 0;
			for (int j = 0; j < n; j++) {
				if (i == j) {
					continue;
				}
				if (can[j]) {
					int min = Integer.MAX_VALUE;
					for (int k = 0; k < n; k++) {
						if (can[k]) {
							continue;
						}
						min = Math.min(min, p[j].dist(p[k]));
					}
					if (min <= r) {
						count++;
					}
				}
			}
			out.println(r);
			out.println(modPow(2, count + 1, MOD));
			return;
		}
		out.println(r);
		out.println(modPow(2, n, MOD));
	}

	static int modPow(int a, int b, int mod) {
		int ret = 1;
		while (b > 0) {
			if ((b & 1) == 1) {
				ret = (int) ((long) ret * a % mod);
			}
			a = (int) ((long) a * a % mod);
			b >>= 1;
		}
		return ret;
	}

	static final int MOD = 1000000007;

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
		new E().run();
	}
}