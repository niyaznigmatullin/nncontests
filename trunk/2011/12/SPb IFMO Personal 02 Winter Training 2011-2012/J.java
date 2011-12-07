import static java.lang.System.currentTimeMillis;
import static java.lang.System.exit;

import java.io.*;
import java.util.*;

public class J {

	static void solve() throws Exception {
		// stress();
		int n = nextInt();
		int[] a = new int[n];
		long s = 0;
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			s += a[i];
		}
		s = -s;
		long[] ans = get(a);
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += ans[i] * a[i];
		}
		if (sum != 0) {
			throw new AssertionError();
		}
		for (long e : ans) {
			out.print(e + " ");
		}
	}

	static void stress() {
		while (true) {
			int n = 50000;
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				do {
					a[i] = rand.nextInt(200001) - 100000;
				} while (a[i] == 0);
			}
			long start = currentTimeMillis();
			long[] ans = get(a);
			long s = 0;
			int i = 0;
			for (long e : ans) {
				if (e < 1 || e > 1000000000000L) {
					throw new AssertionError(e);
				}
				s += e * a[i++];
			}
			if (s != 0) {
				throw new AssertionError();
			}
			start = currentTimeMillis() - start;
			if (start > 1000) {
				System.err.println(start);
			}
		}
	}

	static long[] get(int[] a) {
		final int MAX = 1000000 / a.length;
		while (true) {
			long[] b = new long[a.length];
			long s = 0;
			for (int i = 0; i < b.length; i++) {
				b[i] = rand.nextInt(MAX) + 1;
				s += b[i] * a[i];
			}
			s = -s;
			for (int i = 0; i < a.length; i++) {
				if (s % a[i] == 0) {
					if ((s ^ (long) a[i]) >= 0) {
						b[i] += s / a[i];
						return b;
					}
				}
			}
		}
	}

	static Random rand = new Random(123123L);

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
