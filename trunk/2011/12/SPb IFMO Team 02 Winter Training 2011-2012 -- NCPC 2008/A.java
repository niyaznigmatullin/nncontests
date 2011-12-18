import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

import java.io.*;
import java.util.*;

public class A {

	static void solve() throws IOException {
		int n = nextInt();
		int length = nextInt();
		int width = nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		n >>= 1;
		sort(a);
		double[][] dp = new double[n + 1][n + 1];
		for (double[] d : dp) {
			fill(d, Double.POSITIVE_INFINITY);
		}
		dp[0][0] = 0;
		double space = 1. * length / (n - 1);
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				if (i > 0) {
					dp[i][j] = min(dp[i][j],
							dp[i - 1][j] + dist(a[i + j - 1], i - 1, space));
				}
				if (j > 0) {
					dp[i][j] = min(dp[i][j],
							dp[i][j - 1] + dist2(a[i + j - 1], j - 1, space, width));
				}
			}
		}
		out.println(dp[n][n]);
	}

	static double dist(double a, int k, double length) {
		double z = length * k;
		return abs(a - z);
	}

	static double dist2(double a, int k, double length, int width) {
		double z = length * k;
		double dx = abs(a - z);
		double dy = width;
		return sqrt(dx * dx + dy * dy);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
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
