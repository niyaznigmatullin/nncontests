import static java.lang.Math.min;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class E {
	static final int MOD = 1000000007;

	static void solve() throws IOException {
		int m = nextInt();
		int n = nextInt();
		int k = nextInt();
		out.println(solve(n, m, k));
	}

	static int solve(int n, int m, int k) {
		int[][] dp = new int[n + 1][m + 1];
		int[][] sum = new int[n + 1][m + 1];
		dp[0][0] = 1;
		fill(sum[0], 1);
		for (int i = 1; i <= n; i++) {
			int[] d = dp[i];
			int[] s = sum[i];
			int[] s1 = sum[i - 1];
			d[0] = 1;
			s[0] = 1;
			for (int j = 1; j <= m; j++) {
				int left = j - min(j, k);
				int right = j;
				d[j] = s1[right] - (left == 0 ? 0 : s1[left - 1]);
				if (d[j] < 0) {
					d[j] += MOD;
				}
				s[j] = s[j - 1] + d[j];
				if (s[j] >= MOD) {
					s[j] -= MOD;
				}
			}
		}
		return dp[n][m];
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) {
		try {
			long time = System.currentTimeMillis();
			File file = new File("e.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("e.out"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
			br.close();
			System.err.println("Runtime = "
					+ (System.currentTimeMillis() - time) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}