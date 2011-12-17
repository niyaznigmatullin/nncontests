import java.io.*;
import java.util.*;

public class H {

	static void solve() throws IOException {
		long[] dp = new long[1 << 11];
		dp[0] = 1;
		for (int i = 0; i < 1 << 11; i++) {
			for (int j = i; j > 0; j = (i & (j - 1))) {
				dp[i] += dp[i ^ j];
			}
		}
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			long ans = 0;
			for (int j = 1; j < 1 << n; j++) {
				ans += dp[j];
			}
			out.println((i + 1) + " " + n + " " + ans);
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("frobozz.in"));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}