import java.io.*;
import java.util.*;
import java.math.*;

public class SlowD implements Runnable {

	static int BLOCK = 400;

	static class Query implements Comparable<Query> {
		int a;
		int b;
		long answer;

		public Query(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Query o) {
			if (b != o.b) {
				return b - o.b;
			}
			return a - o.a;
		}

	}

	// void maxTest() {
	// Random rand = new Random();
	// out.println(300000);
	// for (int i = 0; i < 300000; i++) {
	// out.println(rand.nextInt(1000000000) + 1);
	// }
	// out.println(300000);
	// for (int i = 0; i < 300000; i++) {
	// out.print(rand.nextInt(300000) + 1 + " ");
	// if (rand.nextBoolean()) {
	// out.print(rand.nextInt(BLOCK) + 1);
	// } else {
	// out.print(rand.nextInt(300000 - BLOCK) + BLOCK);
	// }
	// out.println();
	// }
	// }

	void solve() {
		long time = System.currentTimeMillis();
		int n = nextInt();
		int[] w = new int[n];
		for (int i = 0; i < n; i++) {
			w[i] = nextInt();
		}
		int m = nextInt();
		Query[] q = new Query[m];
		for (int i = 0; i < m; i++) {
			q[i] = new Query(nextInt() - 1, nextInt());
		}
		System.err.println(System.currentTimeMillis() - time);
		Query[] sq = q.clone();
		Arrays.sort(sq);
		Query last = null;
		long[] sum = new long[n];
		for (int i = 0; i < m;) {
			Query e = sq[i];
			if (last != null && last.compareTo(e) == 0) {
				e.answer = last.answer;
				i++;
				continue;
			}
			if (e.b >= BLOCK) {
				e.answer = stupid(e.a, e.b, w, n);
				last = e;
				i++;
			} else {
				int j = i;
				while (j < m && sq[j].b == e.b) {
					j++;
				}
				int dif = j - i;
				if (dif < e.b) {
					for (int k = i; k < j; k++) {
						Query d = sq[k];
						d.answer = stupid(d.a, d.b, w, n);
					}
				} else {
					for (int cur = n - 1; cur >= 0; cur--) {
						sum[cur] = w[cur];
						if (cur + e.b < n) {
							sum[cur] += sum[cur + e.b];
						}
					}
					for (int k = i; k < j; k++) {
						Query d = sq[k];
						d.answer = sum[d.a];
					}
				}
				i = j;
			}
		}
		for (Query e : q) {
			out.println(e.answer);
		}
		System.err.println(System.currentTimeMillis() - time);
	}

	static long stupid(int a, int b, int[] w, int n) {
		long ans = 0;
		for (int j = a; j < n; j += b) {
			ans += w[j];
		}
		return ans;
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
			}
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
		new SlowD().run();
	}
}