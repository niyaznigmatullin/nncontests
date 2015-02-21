import java.io.*;
import java.util.*;

public class K {

	static final int MOD = 1000000007;

	static int mul(int a, int b) {
		return (int) ((long) a * b % MOD);
	}

	static void solve() throws IOException {
		int n = nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		for (int i = 0; i < n; i++) {
			b[i] = nextInt();
		}
		int[][][] dp = new int[n][n][n];
		for (int len = 0; len < n; len++) {
			for (int i = 0; i + len < n; i++) {
				int first = a[i];
				for (int j = 0; j + len < n; j++) {
					int[] ls = i + 1 < n ? dp[i + 1][j] : null;
					int cur = 0;
					for (int k = 0; k <= len; k++) {
						int c = b[j + k];
						if (c >= 0 && c != first)
							continue;
						int ways;
						if (k > 0 && len > k) {
							ways = mul(ls[k - 1], dp[i + k + 1][j + k + 1][len
									- k - 1]);
						} else if (k > 0) {
							ways = ls[k - 1];
						} else if (len > k) {
							ways = dp[i + k + 1][j + k + 1][len - k - 1];
						} else {
							ways = 1;
						}
						cur += ways;
						if (cur >= MOD) {
							cur -= MOD;
						}
					}
					dp[i][j][len] = cur;
				}
			}
		}
		out.println(dp[0][0][n - 1]);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("k.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("k.out");
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
