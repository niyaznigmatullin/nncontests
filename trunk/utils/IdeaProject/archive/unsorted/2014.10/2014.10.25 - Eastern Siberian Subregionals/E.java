import java.io.*;
import java.util.*;

public class E {

	static final int MOD = 1000000000;

	static void solve() throws IOException {
		int t = nextInt();
		for (int ct = 0; ct < t; ct++) {
			int n = nextInt();
			int m = nextInt();
			int k = nextInt();
			int[] a = new int[k];
			for (int i = 0; i < k; i++)
				a[i] = nextInt();
			Arrays.sort(a);
			int s = nextInt();
			int[] b = new int[s];
			for (int i = 0; i < s; i++)
				b[i] = nextInt();
			Arrays.sort(b);
			int[] dp = new int[m];
			dp[n] = 1;
			for (int i = n; i < m; i++) {
				int val = dp[i];
				if (val == 0)
					continue;
				for (int j = 0; j < k; j++) {
					if (j > 0 && a[j] == a[j - 1])
						continue;
					int ni = i + a[j];
					if (ni < m) {
						dp[ni] += val;
						if (dp[ni] >= MOD) {
							dp[ni] -= MOD;
						}
					}
				}
				for (int j = 0; j < s; j++) {
					if (j > 0 && b[j] == b[j - 1])
						continue;
					int ni = i * b[j];
					if (ni < m) {
						dp[ni] += val;
						if (dp[ni] >= MOD) {
							dp[ni] -= MOD;
						}
					}
				}
			}
			int ans = 0;
			for (int i = 0; i < s; i++) {
				if (i > 0 && b[i] == b[i - 1])
					continue;
				if (m % b[i] == 0) {
					ans += dp[m / b[i]];
					if (ans >= MOD)
						ans -= MOD;
				}
			}
			out.println(ans);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("e.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("e.out");
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
