import static java.lang.Math.max;

import java.io.*;
import java.util.*;

public class I {

	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	static void solve() throws IOException {
		int t = nextInt();
		int[] ans = new int[1001];
		for (int i = 0; i <= 1000; i++) {
			for (int j = 0; j <= 1000; j++) {
				if (gcd(i, j) == 1) {
					ans[max(i, j)]++;
				}
			}
		}
		for (int i = 1; i < ans.length; i++) {
			ans[i] += ans[i - 1];
		}
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			out.println((i + 1) + " " + n + " " + ans[n]);			
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("visible.in"));
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