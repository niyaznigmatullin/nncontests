import java.io.*;
import java.util.*;

public class D implements Runnable {
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

	class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return x < o.x ? -1 : x > o.x ? 1 : 0;
		}

		double dist(Point p) {
			double dx = x - p.x;
			double dy = y - p.y;
			return Math.sqrt(dx * dx + dy * dy);
		}
	}

	void solve() {
		int n = nextInt();
		int k = nextInt() - 1;
		Point[] p = new Point[n + 1];
		for (int i = 0; i < n; i++) {
			p[i] = new Point(nextInt(), 0);
		}
		Point start = p[k];
		p[n] = new Point(nextInt(), nextInt());
		Arrays.sort(p, 0, n);
		if (k == n) {
			double ans = p[n - 1].x - p[0].x
					+ Math.min(p[n].dist(p[0]), p[n].dist(p[n - 1]));
			out.println(ans);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (start == p[i]) {
				k = i;
				break;
			}
		}
		double ans = p[k].dist(p[n - 1]) + p[n - 1].dist(p[n])
				+ (k == 0 ? 0 : p[n].dist(p[0]) + p[0].dist(p[k - 1]));
		ans = Math.min(
				ans,
				p[k].dist(p[0])
						+ p[0].dist(p[n])
						+ (k == n - 1 ? 0 : p[n].dist(p[n - 1])
								+ p[n - 1].dist(p[k + 1])));
		ans = Math.min(ans,
				p[0].dist(p[n]) + 2 * p[k].dist(p[n - 1]) + p[k].dist(p[0]));
		ans = Math
				.min(ans,
						p[n - 1].dist(p[n]) + 2 * p[k].dist(p[0])
								+ p[k].dist(p[n - 1]));
		ans = Math.min(ans,
				p[n - 1].dist(p[0]) + p[k].dist(p[n]) + p[n].dist(p[0]));
		ans = Math.min(ans,
				p[n - 1].dist(p[0]) + p[k].dist(p[n]) + p[n].dist(p[n - 1]));
		ans = Math.min(ans, Math.min(p[n].dist(p[0]), p[n].dist(p[n - 1]))
				+ p[k].dist(p[n]) + p[0].dist(p[n - 1]));
		
		out.println(ans);
	}
}