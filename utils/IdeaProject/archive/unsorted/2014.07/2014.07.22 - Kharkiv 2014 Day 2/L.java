import java.io.*;
import java.util.*;

public class L {

	static final int MOD = 1000000007;

	static int mul(int a, int b) {
		return (int) ((long) a * b % MOD);
	}

	static int add(int a, int b) {
		a += b;
		if (a >= MOD) {
			a -= MOD;
		}
		return a;
	}

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int k = nextInt();
		int maxLen = Math.min(k, n);
		int maxCur = Math.min(k, m);
		int[][][] dp = new int[maxCur + 1][maxLen + 1][k + 1];
		dp[maxCur][1][0] = 1;
		for (int cur = maxCur - 1; cur >= 0; cur--) {
			int[][] cdp = new int[maxLen + 2][k + 1];
			cdp[0][0] = 1;
			cdp[1][0] = 1;
			for (int len = 0; len <= maxLen; len++) {
				for (int spent = 0; spent < k; spent++) {
					int val = cdp[len][spent];
					if (val == 0)
						continue;
					for (int get = 1; 1 + get + len <= maxLen + 1; get++) {
						for (int toSpend = 0; 1 + toSpend + spent <= k; toSpend++) {
							cdp[get + len + 1][1 + spent + toSpend] = add(
									cdp[get + len + 1][1 + spent + toSpend],
									mul(val, dp[cur + 1][get][toSpend]));
						}
					}
				}
			}
			for (int len = 1; len <= maxLen; len++) {
				for (int spent = 0; spent <= k; spent++) {
					if (cur > 0) {
						int nVal = dp[cur + 1][len][spent];
						if (spent > 0) {
							if (spent > 1) {
								int fh = add(cdp[len + 1][spent - 1], MOD
										- dp[cur + 1][len][spent - 2]);
								nVal = add(nVal, fh);
							}
							nVal = add(nVal, cdp[len][spent - 1]);

						}
						dp[cur][len][spent] = nVal;
					} else {
						int nVal = dp[cur + 1][len][spent];
						if (spent > 0) {
							int fh = add(cdp[len + 1][spent], MOD
									- dp[cur + 1][len][spent - 1]);
							nVal = add(nVal, fh);
							nVal = add(nVal, cdp[len][spent]);
						}
						dp[cur][len][spent] = nVal;
					}
					System.err.println("f(" + cur + ", " + len + ", " + spent
							+ ") = " + dp[cur][len][spent]);
				}
			}
		}
		int ans = 0;
		for (int len = 1; len <= k && len <= n; len++) {
			for (int spent = 0; spent <= k; spent++) {
				System.err.println(len + " " + spent + " " + dp[0][len][spent]
						+ " " + f(n - len, len));
				ans = add(ans, mul(dp[0][len][spent], f(n - len, len)));
			}
		}
		out.println(ans);
	}

	static final int MAXN = 12345;
	static final int[] rev = new int[MAXN];

	static {
		rev[1] = 1;
		for (int i = 2; i < MAXN; i++) {
			rev[i] = mul(MOD - MOD / i, rev[MOD % i]);
		}
	}

	static int f(int n, int k) {
		--k;
		n += k;
		int ret = 1;
		for (int i = 0; i < k; i++) {
			ret = mul(ret, n - i);
			ret = mul(ret, rev[i + 1]);
		}
		return ret;
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("l.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("l.out");
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
