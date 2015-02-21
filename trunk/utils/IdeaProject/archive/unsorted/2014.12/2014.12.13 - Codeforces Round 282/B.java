import java.io.*;
import java.util.*;

public class B {

	static final int MOD = 1000000007;

	static int add(int a, int b) {
		a += b;
		if (a >= MOD)
			a -= MOD;
		return a;
	}

	static void solve() throws IOException {
		char[] s = next().toCharArray();
		char[] t = next().toCharArray();
		int[] p = new int[t.length];
		int k = -1;
		p[0] = -1;
		for (int i = 1; i < t.length; i++) {
			while (k > -1 && t[k + 1] != t[i]) {
				k = p[k];
			}
			if (t[k + 1] == t[i])
				++k;
			p[i] = k;
		}
		int[] dp = new int[s.length + 1];
		int[] dpSum = new int[s.length + 1];
		dp[0] = 1;
		dpSum[0] = 1;
		int last = -1;
		k = -1;
		for (int i = 0; i < s.length; i++) {
			while (k > -1 && (k + 1 >= t.length || t[k + 1] != s[i])) {
				k = p[k];
			}
			if (k + 1 < t.length && t[k + 1] == s[i]) {
				++k;
			}
			if (k == t.length - 1) {
				last = i;
			}
			if (last >= 0) {
				dp[i + 1] = dpSum[last - t.length + 1];
			}
			dp[i + 1] = add(dp[i + 1], dp[i]);
			dpSum[i + 1] = add(dpSum[i], dp[i + 1]);
		}
		out.println(add(dp[s.length], MOD - 1));
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("b.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("b.out");
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
