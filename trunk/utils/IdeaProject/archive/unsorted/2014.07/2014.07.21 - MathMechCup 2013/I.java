import java.io.*;
import java.util.*;

public class I {

	static class Rectangle {
		int r1;
		int c1;
		int r2;
		int c2;

		public Rectangle(int r1, int c1, int r2, int c2) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
		}

		@Override
		public String toString() {
			return "Rectangle [r1=" + r1 + ", c1=" + c1 + ", r2=" + r2
					+ ", c2=" + c2 + "]";
		}

		Rectangle intersect(Rectangle r) {
			return new Rectangle(Math.max(r1, r.r1), Math.max(c1, r.c1),
					Math.min(r2, r.r2), Math.min(c2, r.c2));
		}

	}

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		Rectangle[][] a = new Rectangle[n][m];
		final int K = 10;
		Rectangle[][][][] table = new Rectangle[K][K][n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int r1 = nextInt();
				int c1 = nextInt();
				int r2 = nextInt();
				int c2 = nextInt();
				if (r1 > r2) {
					int t = r1;
					r1 = r2;
					r2 = t;
				}
				if (c1 > c2) {
					int t = c1;
					c1 = c2;
					c2 = t;
				}
				table[0][0][i][j] = a[i][j] = new Rectangle(r1, c1, r2, c2);
			}
		}
		for (int u = 1; u < K; u++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					table[0][u][i][j] = (j + (1 << u - 1) < m) ? table[0][u - 1][i][j]
							.intersect(table[0][u - 1][i][j + (1 << u - 1)])
							: table[0][u - 1][i][j];
				}
			}
		}
		for (int v = 1; v < K; v++) {
			for (int u = 0; u < K; u++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						table[v][u][i][j] = (i + (1 << v - 1) < n) ? table[v - 1][u][i][j]
								.intersect(table[v - 1][u][i + (1 << v - 1)][j])
								: table[v - 1][u][i][j];
					}
				}
			}
		}
		int q = nextInt();
		A = nextInt();
		B = nextInt();
		v0 = nextInt();
		int[] d = new int[Math.max(n, m) + 1];
		for (int i = 1; i < d.length; i++) {
			int j = 0;
			while ((1 << j + 1) <= i) {
				++j;
			}
			d[i] = j;
		}
		int ans = 0;
		for (int i = 0; i < q; i++) {
			int x1 = nextNumber() % n;
			int y1 = nextNumber() % m;
			int x2 = nextNumber() % n;
			int y2 = nextNumber() % m;
			if (x1 > x2) {
				int t = x1;
				x1 = x2;
				x2 = t;
			}
			if (y1 > y2) {
				int t = y1;
				y1 = y2;
				y2 = t;
			}
			int v = d[x2 - x1 + 1];
			int u = d[y2 - y1 + 1];
			int r1 = Math.max(Math.max(table[v][u][x1][y1].r1, table[v][u][x2
					- (1 << v) + 1][y1].r1), Math.max(table[v][u][x1][y2
					- (1 << u) + 1].r1, table[v][u][x2 - (1 << v) + 1][y2
					- (1 << u) + 1].r1));
			int c1 = Math.max(Math.max(table[v][u][x1][y1].c1, table[v][u][x2
					- (1 << v) + 1][y1].c1), Math.max(table[v][u][x1][y2
					- (1 << u) + 1].c1, table[v][u][x2 - (1 << v) + 1][y2
					- (1 << u) + 1].c1));
			int r2 = Math.min(Math.min(table[v][u][x1][y1].r2, table[v][u][x2
					- (1 << v) + 1][y1].r2), Math.min(table[v][u][x1][y2
					- (1 << u) + 1].r2, table[v][u][x2 - (1 << v) + 1][y2
					- (1 << u) + 1].r2));
			int c2 = Math.min(Math.min(table[v][u][x1][y1].c2, table[v][u][x2
					- (1 << v) + 1][y1].c2), Math.min(table[v][u][x1][y2
					- (1 << u) + 1].c2, table[v][u][x2 - (1 << v) + 1][y2
					- (1 << u) + 1].c2));
			if (r1 >= r2 || c1 >= c2) {
				continue;
			}
			ans = (int) ((ans + (long) (r2 - r1) * (c2 - c1)) % MOD);
		}
		out.println(ans);
	}

	static int v0, A, B;
	static final int MOD = 1000000007;

	static int nextNumber() {
		return v0 = (int) (((long) A * v0 + B) % MOD);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("pail.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("pail.out");
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
