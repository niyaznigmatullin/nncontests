import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.deepToString;

import java.io.*;
import java.util.*;

public class K extends Thread {

	static int n, m;

	static void solve() throws Exception {
		n = nextInt() << 1;
		m = nextInt() << 1;
		char[][] a = new char[n][];
		for (int i = 0; i < n; i++) {
			a[i] = next().toCharArray();
		}
		boolean[][] ans = solve(a);
		int count = 0;
		boolean answer = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ans[i][j]) {
					count++;
				}
			}
		}
		if (count * 2 > n * m) {
			answer = false;
			count = n * m - count;
		}
		out.println(count);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ans[i][j] == answer) {
					out.println((i + 1) + " " + (j + 1));
				}
			}
		}
	}

	static boolean[][] solve(char[][] c) {
		int n = c.length;
		int m = c[0].length;
		boolean[][] ans = new boolean[n][m];
		boolean[] rows = new boolean[n];
		boolean[] cols = new boolean[m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (c[i][j] == '0') {
					ans[i][j] ^= true;
					rows[i] ^= true;
					cols[j] ^= true;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ans[i][j] ^= rows[i];
				ans[i][j] ^= cols[j];
			}
		}
		return ans;
	}

	static int get(BitSet e, int n, int m) {
		if (e.last == n * m) {
			return e.prevSetBit(e.last);
		} else {
			return e.last;
		}
	}

	static class BitSet {
		private static final int BITS = 32;
		private static final int LOG = 5;

		int[] a;
		int first;
		int last;

		public BitSet(int n) {
			a = new int[(n + BITS - 1) >> LOG];
			first = n + 1;
			last = -1;
		}

		void set(int x) {
			a[(x >> LOG)] |= 1 << (x & (BITS - 1));
			first = min(first, x);
			last = max(last, x);
		}

		boolean get(int x) {
			return ((a[(x >> LOG)] >>> (x & (BITS - 1))) & 1) == 1;
		}

		void or(BitSet bs) {
			int left = min(first, bs.first) >> LOG;
			int right = max(last, bs.last) >> LOG;
			first = -1;
			for (int i = left; i <= right; i++) {
				a[i] |= bs.a[i];
				if (first < 0 && a[i] != 0) {
					first = (i << LOG) + Integer.numberOfTrailingZeros(a[i]);
				}
			}
			setLast();
		}

		void setLast() {
			int i = a.length - 1;
			while (i >= 0 && a[i] == 0) {
				i--;
			}
			if (i >= 0) {
				last = (i << LOG)
						+ Integer.numberOfTrailingZeros(Integer
								.highestOneBit(a[i]));
			} else {
				last = -1;
			}
		}

		void xor(BitSet bs) {
			int left = min(first, bs.first) >> LOG;
			int right = max(last, bs.last) >> LOG;
			first = -1;
			for (int i = left; i <= right; i++) {
				a[i] ^= bs.a[i];
				if (first < 0 && a[i] != 0) {
					first = (i << LOG) + Integer.numberOfTrailingZeros(a[i]);
				}
			}
			setLast();
		}

		int prevSetBit(int x) {
			int mask = (1 << (x & (BITS - 1))) - 1;
			if ((a[x >> LOG] & mask) != 0) {
				return ((x >> LOG) << LOG)
						+ Integer.numberOfTrailingZeros(Integer
								.highestOneBit(a[x >> LOG] & mask));
			} else {
				int i = (x >> LOG) - 1;
				while (i >= 0 && a[i] == 0) {
					i--;
				}
				if (i >= 0) {
					return (i << LOG)
							+ Integer.numberOfTrailingZeros(Integer
									.highestOneBit(a[i]));
				} else {
					return -1;
				}
			}
		}

		@Override
		public String toString() {
			Set<Integer> set = new TreeSet<Integer>();
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < BITS; j++) {
					if (((a[i] >>> j) & 1) == 1) {
						set.add((i << LOG) | j);
					}
				}
			}
			return set.toString();
		}
	}

	static class Element {
		int code;
		BitSet set;

		public Element(int code, int n) {
			this.code = code;
			this.set = new BitSet(n);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("[code=");
			builder.append(code);
			builder.append(", set=");
			builder.append(set);
			builder.append("]");
			return builder.toString();
		}

	}

	static int code(int i, int j) {
		return i * m + j;
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) {
		new Thread(null, new K(), "", 1 << 24).start();
	}

	public void run() {
		try {
			long time = System.currentTimeMillis();
			File file = new File("k.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("k.out"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
			br.close();
			System.err.println("Runtime = "
					+ (System.currentTimeMillis() - time) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}