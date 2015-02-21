import java.io.*;
import java.util.*;

public class H {

	static final int MOD = 1000000007;

	static int solve(int n, int p) {
		if (n >= p) {
			return 0;
		}
		int ans = 1;
		for (int i = 1; i <= n; i++) {
			ans = (int) ((long) ans * (p - i) % MOD);
		}
		return ans;
	}

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			int p = nextInt();
			out.println(solve(n, p));
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("h.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("h.out");
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
