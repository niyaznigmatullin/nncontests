import java.io.*;
import java.util.*;

public class C {

	static void solve() throws IOException {
		int t = nextInt();
		for (int curTest = 0; curTest < t; curTest++) {
			int n = nextInt();
			int[] a = new int[n];
			Fenwick f = new Fenwick(n);
			for (int i = 0; i < n; i++) {
				a[i] = nextInt() - 1;
			}
			int[] ans = new int[n];
			for (int i = n - 1; i >= 0; i--) {
				ans[i] = f.get(a[i]);
				f.add(a[i], 1);
			}
			for (int i = 0; i < n; i++) {
				if (i > 0)
					out.print(' ');
				out.print(ans[i]);
			}
			out.println();
		}
	}

	static class Fenwick {
		int[] a;

		public Fenwick(int n) {
			a = new int[n];
		}

		void add(int x, int y) {
			for (int i = x; i < a.length; i |= i + 1)
				a[i] += y;
		}

		int get(int x) {
			int ret = 0;
			for (int i = x; i >= 0; i = (i & i + 1) - 1) {
				ret += a[i];
			}
			return ret;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("c.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("c.out");
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
