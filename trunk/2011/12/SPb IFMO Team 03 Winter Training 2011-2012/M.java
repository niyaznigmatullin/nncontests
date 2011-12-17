import static java.lang.Math.max;

import java.io.*;
import java.util.*;

public class M {

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			String s = br.readLine();
			out.println((i + 1) + " " + solve(s));
		}
	}
	
	static int solve(String s) {
		int depth = 0;
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '[') {
				depth++;
			} else {
				depth--;
			}
			ans = max(ans, depth);
		}
		return 1 << ans;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("monkey.in"));
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