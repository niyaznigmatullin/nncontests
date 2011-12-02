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

	final int INF = Integer.MAX_VALUE / 10;

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = i == j ? 0 : INF;
			}
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1;
			int y = nextInt() - 1;
			int z = nextInt();
			a[x][y] = a[y][x] = Math.min(a[x][y], z);
		}
		int[][] dp = new int[n][1 << (n - 1)];
		for (int[] d : dp) {
			Arrays.fill(d, INF);
		}
		dp[0][0] = 0;
		for (int i = 1; i < 1 << (n - 1); i++) {
			if ((i & (i - 1)) == 0) {
				int j = Integer.numberOfTrailingZeros(i) + 1;
				dp[j][i] = a[0][j];
				continue;
			}
			for (int j = 1; j < n; j++) {
				if (((i >> (j - 1)) & 1) == 0) {
					continue;
				}
				for (int k = 1; k < n; k++) {
					if (((i >> (k - 1)) & 1) == 0) {
						continue;
					}
					dp[k][i] = Math.min(dp[k][i], dp[j][i ^ (1 << (k - 1))]
							+ a[j][k]);
				}
			}
		}
		int ans = INF;
		for (int i = 1; i < n; i++) {
			ans = Math.min(ans, dp[i][(1 << (n - 1)) - 1] + a[0][i]);
		}
		if (ans == INF) {
			out.println(-1);
		} else {
			out.println(ans);
		}
	}
}