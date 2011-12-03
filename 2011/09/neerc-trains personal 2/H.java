import static java.math.BigInteger.valueOf;

import java.io.*;
import java.util.*;
import java.math.*;

public class H {

	static final int MOD = 1000000007;

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int[] from = new int[m];
		int[] to = new int[m];
		int[] deg = new int[n];
		for (int i = 0; i < m; i++) {
			from[i] = nextInt() - 1;
			to[i] = nextInt() - 1;
			deg[from[i]]++;
			deg[to[i]]++;
		}
		int ans = 0;
		for (int i = 0; i < m; i++) {
			int a = from[i];
			int b = to[i];
			ans = (int) ((ans + (long) (deg[a] - 3) * (deg[a] - 1) % MOD
					* (deg[a] - 2) % MOD * (deg[b] - 1) % MOD * (deg[b] - 2)
					% MOD) % MOD);
			ans = (int) ((ans + (long) (deg[b] - 3) * (deg[b] - 1) % MOD
					* (deg[b] - 2) % MOD * (deg[a] - 1) % MOD * (deg[a] - 2)
					% MOD) % MOD);
		}
		out.println((ans * valueOf(12).modInverse(valueOf(MOD)).longValue())
				% MOD);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("h.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream("h.out"));
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
		new H().run();
	}
}