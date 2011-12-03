import static java.util.Arrays.deepToString;

import java.io.*;
import java.util.*;

public class F {

	static void solve() throws Exception {
		int n = nextInt();
		int k = nextInt();
		boolean[] win = new boolean[n + 1];
		win[0] = true;
		for (int i = 1; i <= n; i++) {
			int max = Math.min((i * i) % k + 1, i);
			for (int j = 1; j <= max; j++) {
				win[i] |= !win[i - j];
			}
		}
		int[] dp = new int[n + 1];
		boolean[] can = new boolean[n + 1];
		can[0] = true;
		for (int i = 2; i < n; i++) {
			if (win[i]) {
				continue;
			}
			for (int j = n - 1; j >= 0; j--) {
				if (j + i > n) {
					continue;
				}
				if (can[j + i] || !can[j]) {
					continue;
				}
				can[j + i] = true;
				dp[j + i] = i;
			}
		}
		if (!can[n]) {
			out.println(0);
		} else {
			List<Integer> ans = new ArrayList<Integer>();
			for (int i = n; i > 0; i -= dp[i]) {
				ans.add(dp[i]);
			}
			Collections.sort(ans);
			out.println(ans.size());
			for (int i : ans) {
				out.print(i + " ");
			}
		}
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
			File file = new File("f.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("f.out"));
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