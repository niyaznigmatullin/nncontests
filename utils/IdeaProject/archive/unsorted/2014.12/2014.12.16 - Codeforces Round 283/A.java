import java.io.*;
import java.util.*;

public class A {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		char[][] c = new char[n][];
		for (int i = 0; i < n; i++) {
			c[i] = next().toCharArray();
		}
		boolean[] eq = new boolean[n - 1];
		Arrays.fill(eq, true);
		int ans = 0;
		for (int j = 0; j < m; j++) {
			boolean bad = false;
			for (int i = 0; i + 1 < n; i++) {
				if (eq[i] && c[i][j] > c[i + 1][j]) {
					bad = true;
					break;
				}
			}
			if (bad) {
				++ans;
				continue;
			}
			for (int i = 0; i + 1 < n; i++) {
				if (c[i][j] < c[i + 1][j]) {
					eq[i] = false;
				}
			}
		}
		out.println(ans);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("a.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("a.out");
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
