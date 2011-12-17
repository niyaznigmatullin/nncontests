import static java.lang.Math.min;

import java.io.*;
import java.util.*;

public class E {

	static void solve() throws IOException {
		int n = nextInt();
		int b = nextInt();
		int h = nextInt();
		int w = nextInt();
		int best = Integer.MAX_VALUE;
		for (int i = 0; i < h; i++) {
			int p = nextInt();
			int max = 0;
			for (int j = 0; j < w; j++) {
				max = Math.max(max, nextInt());
			}
			if (max >= n && p * n <= b) {
				best = min(best, p * n);
			}
		}
		out.println(best == Integer.MAX_VALUE ? "stay home" : best);
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