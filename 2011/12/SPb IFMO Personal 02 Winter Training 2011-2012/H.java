import static java.lang.Math.max;
import static java.lang.System.exit;

import java.io.*;
import java.util.*;

public class H {

	static void solve() throws Exception {
		int n = nextInt();
		int m = nextInt();
		int[] a = new int[n];
		int best = 0;
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			if (i > 0) {
				best = max(best, a[i] - a[i - 1]);
			}
		}
		int l = best - 1;
		int r = Integer.MAX_VALUE / 2;
		while (l < r - 1) {
			int mid = l + r >> 1;
			int last = a[0];
			int removed = 0;
			for (int i = 1; i + 1 < a.length; i++) {
				if (last + mid >= a[i + 1]) {
					removed++;
				} else {
					last = a[i];
				}
			}
			if (removed >= m) {
				r = mid;
			} else {
				l = mid;
			}
		}
		out.println(r);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
			}
			st = new StringTokenizer(s);
		}
		return st.nextToken();
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
			}
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			br.close();
			out.close();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			List<Void> list = new ArrayList<Void>();
			while (true) {
				list.add(null);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}
