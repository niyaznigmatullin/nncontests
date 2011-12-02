import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;
import java.math.*;

public class C implements Runnable {

	static final Random rand = new Random(123413L);

	static void test() {
		int q = 0;
		while (true) {
			if (++q == 1000) {
				System.err.println("TEST");
				q = 0;
			}
			int n = 10;
			char[][] c = new char[n][n];
			for (char[] d : c) {
				fill(d, '0');
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) {
						continue;
					}
					if (rand.nextBoolean()) {
						c[i][j] = '1';
					} else {
						c[j][i] = '1';
					}
				}
			}
			int[] ans = solve(n, c);
			if (ans != null) {
				int i = ans[0];
				int j = ans[1];
				int k = ans[2];
				if (c[i][j] != '1' || c[j][k] != '1' || c[k][i] != '1') {
					throw new AssertionError();
				}
			}
			if (ans == null) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (i == j) {
							continue;
						}
						for (int k = 0; k < n; k++) {
							if (i == k || j == k) {
								continue;
							}
							if (c[i][j] == '1' && c[j][k] == '1'
									&& c[k][i] == '1') {
								throw new AssertionError();
							}
						}
					}
				}
			}
		}
	}

	void solve() {
		test();
		int n = nextInt();
		char[][] c = new char[n][];
		for (int i = 0; i < n; i++) {
			c[i] = sc.nextToken().toCharArray();
		}
		int[] ans = solve(n, c);
		if (ans == null) {
			out.println(-1);
		} else {
			out.println((1 + ans[0]) + " " + (1 + ans[1]) + " " + (1 + ans[2]));
		}
		// BitSet[] from = new BitSet[n];
		// BitSet[] to = new BitSet[n];
		// for (int i = 0; i < n; i++) {
		// from[i] = new BitSet(n);
		// to[i] = new BitSet(n);
		// }
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < n; j++) {
		// if (c[i][j] == '1') {
		// from[i].set(j);
		// to[j].set(i);
		// }
		// }
		// }
		// BitSet tmp = new BitSet(n);
		// for (int i = 0; i < n; i++) {
		// tmp.clear();
		// for (int j = from[i].nextSetBit(0); j >= 0; j = from[i]
		// .nextSetBit(j + 1)) {
		// tmp.or(from[j]);
		// }
		// tmp.andNot(from[i]);
		// tmp.and(to[i]);
		// if (tmp.cardinality() > 0) {
		// int got = tmp.nextSetBit(0);
		// for (int j = from[i].nextSetBit(0); j >= 0; j = from[i]
		// .nextSetBit(j + 1)) {
		// if (from[j].get(got)) {
		// out.println((1 + i) + " " + (1 + j) + " " + (1 + got));
		// return;
		// }
		// }
		// }
		// }
		// out.println(-1);

	}

	static int[] solve(int n, char[][] c) {
		int[] count = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (c[i][j] == '1') {
					count[i]++;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (c[i][j] == '1') {
					if (count[j] >= count[i]) {
						for (int k = 0; k < n; k++) {
							if (k == i || k == j) {
								continue;
							}
							if (c[j][k] == '1' && c[k][i] == '1') {
								return new int[] { i, j, k };
							}
						}
					}
				}
			}
		}
		return null;
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	class FastScanner extends BufferedReader {
		StringTokenizer st;
		boolean eof;
		String buf;
		String curLine;
		boolean createST;

		public FastScanner(String fileName) throws FileNotFoundException {
			this(fileName, true);
		}

		public FastScanner(String fileName, boolean createST)
				throws FileNotFoundException {
			super(new FileReader(fileName));
			this.createST = createST;
			nextToken();
		}

		public FastScanner(InputStream stream) {
			this(stream, true);
		}

		public FastScanner(InputStream stream, boolean createST) {
			super(new InputStreamReader(stream));
			this.createST = createST;
			nextToken();
		}

		String nextLine() {
			String ret = curLine;
			if (createST) {
				st = null;
			}
			nextToken();
			return ret;
		}

		String nextToken() {
			if (!createST) {
				try {
					curLine = readLine();
				} catch (Exception e) {
					eof = true;
				}
				return null;
			}
			while (st == null || !st.hasMoreTokens()) {
				try {
					curLine = readLine();
					st = new StringTokenizer(curLine);
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}

		BigInteger nextBigInteger() {
			return new BigInteger(nextToken());
		}

		public void close() {
			try {
				buf = null;
				st = null;
				curLine = null;
				super.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	public static void main(String[] args) {
		new C().run();
	}
}