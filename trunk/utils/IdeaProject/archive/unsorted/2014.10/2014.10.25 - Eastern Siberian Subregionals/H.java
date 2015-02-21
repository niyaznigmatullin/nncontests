import java.io.*;
import java.util.*;

public class H {

	static void solve() throws IOException {
		int t = nextInt();
		for (int ct = 0; ct < t; ct++) {
			int n = nextInt();
			int ans = 0;
			int mx = 0;
			for (int i = 0; i < n; i++) {
				int x = nextInt();
				int c = 0;
				while (x > 0) {
					ans += x % 10 > 0 ? 1 : 0;
					x -= x % 10;
					if (x > 0)
						++c;
					x /= 10;
				}
				mx = Math.max(mx, c);
			}
			out.println(ans + mx);
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
