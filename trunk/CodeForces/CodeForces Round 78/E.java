import java.io.*;
import java.util.*;
import java.math.*;

public class E implements Runnable {

	void solve() {
		int n = nextInt();
		int m = nextInt();
		dp1 = new double[n + 1][m + 1];
		dp2 = new double[n + 1][m + 1];
		for (double[] d : dp1) {
			Arrays.fill(d, Double.NaN);
		}
		for (double[] d : dp2) {
			Arrays.fill(d, Double.NaN);
		}
		double ans = go1(n, m);
		out.println(ans + " " + (1 - ans));
	}

	static double go1(int n, int m) {
		if (m == 0) {
			return 1.;
		}
		if (n == 0) {
			return 1. / (m + 1);
		}
		if (!Double.isNaN(dp1[n][m])) {
			return dp1[n][m];
		}
		double p1 = (1. * m / (m + 1) * (1 - go2(n, m - 1)) + 1. / (m + 1) - (1. / (m + 1))
				* n / (n + 1) * (1 - go1(n - 1, m)))
				/ (1. + 1. / (n + 1) / (m + 1));
		if (p1 / (n + 1) + 1. * n / (n + 1) * (1 - go1(n - 1, m)) < 1. / (n + 1)) {
			p1 = 1. * m / (m + 1) * (1 - go2(n, m - 1)) + 1. * n / (m + 1)
					/ (n + 1);
		}
		if (p1 < 1. / (m + 1)) {
			p1 = 1. / (m + 1);
		}
		return dp1[n][m] = p1;
	}

	static double go2(int n, int m) {
		if (n == 0) {
			return 1.;
		}
		if (m == 0) {
			return 1. / (n + 1);
		}
		if (!Double.isNaN(dp2[n][m])) {
			return dp2[n][m];
		}
		double p1 = (1. * n / (n + 1) * (1 - go1(n - 1, m)) + 1. / (n + 1) - (1. / (n + 1))
				* m / (m + 1) * (1 - go2(n, m - 1)))
				/ (1. + 1. / (n + 1) / (m + 1));
		if (p1 / (m + 1) + 1. * m / (m + 1) * (1 - go2(n, m - 1)) < 1. / (m + 1)) {
			p1 = 1. * n / (n + 1) * (1 - go1(n - 1, m)) + 1. * m / (m + 1)
					/ (n + 1);
		}
		if (p1 < 1. / (n + 1)) {
			p1 = 1. / (n + 1);
		}
		return dp2[n][m] = p1;
	}

	static double[][] dp1;
	static double[][] dp2;

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
		new E().run();
	}
}