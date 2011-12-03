import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class N extends Thread {
	static final int MOD = 1000000007;

	static void solve() throws Exception {
		int n = nextInt() - 1;
		int k = nextInt();
		if (n > 20 || 1 << n > k) {
			out.println(0);
			return;
		}
		k -= 1 << n;
		int[] dp = new int[k + 1];
		dp[0] = 1;
		int c = 1;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j + c <= k; j++) {
				dp[j + c] += dp[j];
				if (dp[j + c] >= MOD) {
					dp[j + c] -= MOD;
				}
			}
			c = c * (n - i) / (i + 1);
		}
		out.println(dp[k]);
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
		new Thread(null, new N(), "", 1 << 24).start();
	}

	public void run() {
		try {
			long time = System.currentTimeMillis();
			File file = new File("n.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("n.out"));
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