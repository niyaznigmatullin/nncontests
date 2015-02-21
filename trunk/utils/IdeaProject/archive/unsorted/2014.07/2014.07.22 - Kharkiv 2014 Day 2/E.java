import java.io.*;
import java.util.*;

public class E {

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
		final int K = 450;
		int b = nextInt();
		int p = nextInt();
		int k = nextInt();
		int s = nextInt();
		int ways1 = 1;
//		long time = System.currentTimeMillis();
		if (b > 0) {
			int[] rev = new int[b + 1];
			rev[1] = 1;
			for (int i = 2; i <= b; i++) {
				rev[i] = mul(rev[MOD % i], MOD - MOD / i);

			}
			for (int i = 0; i < b; i++) {
				ways1 = mul(ways1, p - i);
				ways1 = mul(ways1, rev[i + 1]);
			}
		}
		int waysAll = 1;
		for (int i = 0; i < p; i++) {
			waysAll = mul(waysAll, 2);
		}
		int ways2 = add(waysAll, MOD - ways1);
		int[] dp = new int[s + 1];
		int[] next = new int[s + 1];
		dp[1] = ways1;
		int ans = 0;
		for (int curK = 1; curK < K; curK++) {
			Arrays.fill(next, 0);
			for (int i = 1; i <= s; i++) {
				int val = dp[i];
				if (val == 0 || i + curK > s)
					continue;
				{
					int a = dp[i + curK];
					a += mul(val, ways2);
					if (a >= MOD) {
						a -= MOD;
					}
					dp[i + curK] = a;
				}
				int nextK = Math.min(k, curK + 1);
				int[] z = nextK == curK ? dp : next;
				if (i + nextK <= s) {
					int a1 = z[i + nextK];
					a1 += mul(val, ways1);
					if (a1 >= MOD) {
						a1 -= MOD;
					}
					z[i + nextK] = a1;
				}
			}
			ans = add(ans, dp[s]);
			int[] t = dp;
			dp = next;
			next = t;
		}
		out.println(ans);
		// System.err.println(System.currentTimeMillis() - time);
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
