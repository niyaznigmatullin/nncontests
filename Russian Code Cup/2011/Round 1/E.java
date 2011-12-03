import java.io.*;
import java.util.*;
import java.math.*;

public class E implements Runnable {

	static final int INF = Integer.MAX_VALUE / 10;

	static int go(int x, long n, int k) {
		if (x == 17) {
			if (k == 0 && n == 0) {
				return 0;
			}
			return INF;
		}
		if (n > 1000 && n / D[x] >= k) {
			return INF;
		}
		int ans = INF;
		int q = 0;
		while (true) {
			if (n < 0 || k < 0) {
				break;
			}
			ans = Math.min(ans, go(x + 1, n, k) + q);
			k -= 17 - x;
			n -= D[x];
			q++;
		}
		return ans;
	}

	final static long[] D = new long[17];
	static {
		D[16] = 1;
		for (int i = 15; i >= 0; i--) {
			D[i] = D[i + 1] * 10 + 1;
		}
	}

	static String genTest(int x) {
		Random rand = new Random();
		int y = x;
		long ans = 0;
		while (x > 0) {
			int e = rand.nextInt(17);
			if (17 - e > x) {
				continue;
			}
			x -= 17 - e;
			ans += D[e];
		}
		return ans + " " + y;
	}

	void solve() {
		long n = nextLong();
		int k = nextInt();
		int ans = go(0, n, k);
		out.println(ans > k ? "Impossible" : ans);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
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
		new E().run();
	}
}