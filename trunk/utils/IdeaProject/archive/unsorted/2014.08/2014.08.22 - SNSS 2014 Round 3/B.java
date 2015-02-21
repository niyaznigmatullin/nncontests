import java.io.*;
import java.util.*;

public class B {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int[] from = new int[m];
		int[] to = new int[m];
		int[] w = new int[m];
		for (int i = 0; i < m; i++) {
			from[i] = nextInt() - 1;
			to[i] = nextInt() - 1;
			w[i] = nextInt();
		}
		int[][] d = new int[n + 1][n];
		Arrays.fill(d[0], 0);
		for (int i = 1; i <= n; i++) {
			for (int e = 0; e < m; e++) {
				if (d[i][to[e]] < d[i - 1][from[e]] + w[e]) {
					d[i][to[e]] = d[i - 1][from[e]] + w[e];
				}
			}
		}
		double ans = Double.NEGATIVE_INFINITY;
		for (int v = 0; v < n; v++) {
			double cur = Double.POSITIVE_INFINITY;
			for (int i = 0; i < n; i++) {
				cur = Math.min(cur, 1. * (d[n][v] - d[i][v]) / (n - i));
			}
			ans = Math.max(ans, cur);
		}
		out.println(ans);
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
