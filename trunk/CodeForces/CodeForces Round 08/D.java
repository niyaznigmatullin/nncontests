import java.io.*;
import java.math.*;
import java.util.*;

public class D {
	public static void main(String[] args) {
		new D().run();
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

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	double t1, t2, cx, cy, hx, hy, sx, sy, ans, shopD, strD;

	double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	final double EPS = 1e-6;

	int cmp(double x, double y) {
		if (Double.isNaN(x) || Double.isNaN(y)) {
			throw new AssertionError("NaN, x = " + x + ", y = " + y);
		}
		if (x < y - EPS) {
			return -1;
		}
		if (x > y + EPS) {
			return 1;
		}
		return 0;
	}

	int cmp(double x, double y, double EPS) {
		if (Double.isNaN(x) || Double.isNaN(y)) {
			throw new AssertionError("NaN, x = " + x + ", y = " + y);
		}
		if (x < y - EPS) {
			return -1;
		}
		if (x > y + EPS) {
			return 1;
		}
		return 0;
	}

	double check(double x, double y) {
		double d1 = dist(cx, cy, x, y) + dist(x, y, sx, sy)
				+ dist(sx, sy, hx, hy);
		double d2 = dist(cx, cy, x, y) + dist(x, y, hx, hy);
		if (cmp(d1, shopD + t1) > 0 || cmp(d2, strD + t2) > 0) {
			return Double.POSITIVE_INFINITY;
		}
		double ret = Math.min(shopD + t1 - d1, strD + t2 - d2);
		ans = Math.max(ans, ret);
		return ret;
	}

	void go(double x1, double y1, double x2, double y2) {
		double d1 = check(x1, y1);
		double d2 = check(x2, y2);
		double d3 = check(x1, y2);
		double d4 = check(x2, y1);
		if (cmp(x1, x2, 1e-1) == 0 || cmp(y1, y2, 1e-1) == 0) {
			return;
		}
		double mx = (x1 + x2) * .5;
		double my = (y1 + y2) * .5;
		go(x1, y1, mx, my);
		go(x1, my, mx, y2);
		go(mx, my, x2, y2);
		go(mx, y1, x2, my);
	}

	void solve() {
		t1 = nextDouble();
		t2 = nextDouble();
		cx = nextDouble();
		cy = nextDouble();
		hx = nextDouble();
		hy = nextDouble();
		sx = nextDouble();
		sy = nextDouble();
		ans = Double.NEGATIVE_INFINITY;
		shopD = dist(cx, cy, sx, sy) + dist(sx, sy, hx, hy);
		strD = dist(cx, cy, hx, hy);
		if (cmp(shopD, strD + t2) < 1) {
			ans = dist(cx, cy, hx, hy) + t2;
		}
		go(-100, -100, 100, 100);
		out.println(ans);
		int n = nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		System.err.println(Arrays.toString(a));
	}
}