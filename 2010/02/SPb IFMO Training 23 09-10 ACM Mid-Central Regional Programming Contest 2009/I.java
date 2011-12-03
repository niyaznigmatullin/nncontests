import java.util.*;
import java.math.*;
import java.io.*;

public class I implements Runnable {
	public static void main(String[] args) {
		new Thread(new I()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return "0";
			}
		}
		return st.nextToken();
	}

	String nextLine() {
		String ret = "";
		try {
			ret = br.readLine();
		} catch (Exception e) {
			ret = "";
		}
		if (ret == null) {
			eof = true;
			return "$";
		}
		return ret;
	}

	String nextString() {
		return nextToken();
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

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new FileReader(new File("ripoff.in")));
			out = new PrintWriter("ripoff.out");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.close();
	}

	void solve() {
		while (solve2())
			;
	}

	boolean solve2() {
		int n = nextInt();
		if (n == 0)
			return false;
		int s = nextInt();
		int t = nextInt();
		int[] a = new int[n + 1];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		a[n] = 0;
		int[][] dp = new int[t + 1][n + 2];
		final int INF = Integer.MIN_VALUE / 10;
		for (int[] d : dp) {
			Arrays.fill(d, INF);
		}
		dp[0][0] = 0;
		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= t; i++) {
			for (int j = 1; j <= n + 1; j++) {
				for (int k = j - 1; k >= j - s && k >= 0; k--) {
					dp[i][j] = Math.max(dp[i - 1][k] + a[j - 1], dp[i][j]);
				}
			}
			ans = Math.max(ans, dp[i][n + 1]);
		}
		out.println(ans);
		return true;
	}
}