import java.io.*;
import java.util.*;

public class I {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int k = nextInt();
		int[] count = new int[m];
		for (int i = 0; i < n; i++) {
			int x = nextInt();
			count[x - 1]++;
		}
		int mm = 0;
		for (int i = 0; i < m; i++) {
			mm = Math.max(mm, count[i]);
		}
		++mm;
		int[] z = new int[mm];
		for (int i : count) {
			z[i] += i;
		}
		int ans = 1;
		for (int i = 0; i < mm; i++) {
			if (k >= z[i]) {
				k -= z[i];
			} else {
				ans = c(z[i], k);
				break;
			}
		}
		out.println(ans);
	}

	static final int MOD = 1000000007;

	static int mul(int a, int b) {
		return (int) ((long) a * b % MOD);
	}

	static int c(int n, int k) {
		if (k == 0)
			return 1;
		int[] rev = new int[k + 1];
		rev[1] = 1;
		for (int i = 2; i <= k; i++) {
			rev[i] = mul(rev[MOD % i], MOD - MOD / i);
		}
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
		File file = new File("i.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("i.out");
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
