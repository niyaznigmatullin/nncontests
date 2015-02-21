import java.io.*;
import java.util.*;

public class D {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			Point[] p = new Point[n];
			for (int j = 0; j < n; j++) {
				p[j] = new Point(nextInt(), nextInt());
			}
			long ans = solve(p);
			for (int j = 0; j < n; j++) {
				p[j].y = -p[j].y;
			}
			ans = Math.min(ans, solve(p));
			for (int j = 0; j < n; j++) {
				p[j].x = -p[j].x;
			}
			ans = Math.min(ans, solve(p));
			// for (int j = 0; j < n; j++) {
			// p[j].y = -p[j].y;
			// }
			// ans = Math.min(ans, solve(p));
			out.println(ans);
		}
	}

	static long sq(int a, int b) {
		long c = Math.max(a, b);
		return c * c;
	}

	static long solve(Point[] p) {
		int n = p.length;
		Point[] byX = p.clone();
		Point[] byY = p.clone();
		Arrays.sort(byX, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return -Integer.compare(o1.x, o2.x);
			}
		});
		Arrays.sort(byY, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return -Integer.compare(o1.y, o2.y);
			}
		});
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (Point e : p) {
			minX = Math.min(minX, e.x);
			minY = Math.min(minY, e.y);
		}
		long ans = sq(byX[0].x - minX, byY[0].y - minY);
		int x1 = Integer.MAX_VALUE;
		int y1 = Integer.MAX_VALUE;
		int x2 = Integer.MIN_VALUE;
		int y2 = Integer.MIN_VALUE;
		for (int i = 0, j = 0; i < n || j < n;) {
			if (j >= n || i < n && byX[i].x - minX > byY[j].y - minY) {
				x1 = Math.min(x1, byX[i].x);
				x2 = Math.max(x2, byX[i].x);
				y1 = Math.min(y1, byX[i].y);
				y2 = Math.max(y2, byX[i].y);
				++i;
			} else {
				x1 = Math.min(x1, byY[j].x);
				x2 = Math.max(x2, byY[j].x);
				y1 = Math.min(y1, byY[j].y);
				y2 = Math.max(y2, byY[j].y);
				++j;
			}
			ans = Math.min(
					ans,
					sq(x2 - x1, y2 - y1)
							+ sq(i >= n ? 0 : byX[i].x - minX, j >= n ? 0
									: byY[j].y - minY));
		}
		return ans;
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
