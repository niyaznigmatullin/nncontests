import static java.lang.Math.min;
import static java.lang.System.exit;

import java.io.*;
import java.util.*;

public class I {

	static void solve() throws Exception {
		String s = next();
		String t = next();
		out.println(getMin(s, t) + " " + getMax(s, t));
	}

	static int getMin(String s, String t) {
		boolean[] can = new boolean[t.length() - s.length() + 1];
		for (int i = t.indexOf(s); i >= 0; i = t.indexOf(s, i + 1)) {
			can[i] = true;
		}
		int[] dp = new int[t.length() + 1];
		int[] dp2 = new int[t.length() + 1];
		dp[0] = 0;
		dp2[0] = 0;
		for (int i = 1; i <= t.length(); i++) {			
			dp2[i] = Integer.MAX_VALUE;			
			if (i >= s.length() && can[i - s.length()]) {
				int j = i;
				for (int k = 1; k < s.length(); k++) {
					if (j - k < 0) {
						break;
					}
					dp2[i] = min(dp2[i], dp[j - k]);
				}
				dp[i] = dp2[i - s.length()] + 1;
				dp2[i] = min(dp2[i], dp[i]);
			} else {
				int j = i - 1;
				while (dp[j] == Integer.MAX_VALUE) {
					j--;
				}
				dp2[i] = min(dp2[i], dp[j]);
				for (int k = 1; k < s.length(); k++) {
					if (j - k < 0) {
						break;
					}
					dp2[i] = min(dp2[i], dp[j - k]);
				}
				dp[i] = Integer.MAX_VALUE;
			}
		}
		return dp2[t.length()];
	}

	static int getMax(String s, String t) {
		int ret = 0;
		for (int i = t.indexOf(s); i >= 0; i = t.indexOf(s, i + s.length())) {
			ret++;
		}
		return ret;
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
			}
			st = new StringTokenizer(s);
		}
		return st.nextToken();
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
			}
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			br.close();
			out.close();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			List<Void> list = new ArrayList<Void>();
			while (true) {
				list.add(null);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}
