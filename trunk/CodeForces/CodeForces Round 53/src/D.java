import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	long s(long x) {
		return x * (x + 1) >> 1;
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		long[][] dp = new long[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (i == 1 && j == 1) {
					continue;
				}
				if (i > 1) {
					dp[i][j] = dp[i - 1][j] + s(i + j - 2) - s(i - 2);
				} else {
					dp[i][j] = dp[i][j - 1] + s(i + j - 2) - s(j - 2);
				}
			}
		}
		long sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				{
					int x = m - j;
					sum += dp[n - i][x];
					sum -= s(x - 1);
					sum -= s(n - i - 1);
				}
				{
					int x = j + 1;
					sum += dp[n - i][x];
					sum -= s(x - 1);
					sum -= s(n - i - 1);
				}
				{
					int x = m - j;
					sum += dp[i + 1][x];
					sum -= s(x - 1);
					sum -= s(i);
				}
				{
					int x = j + 1;
					sum += dp[i + 1][x];
					sum -= s(x - 1);
					sum -= s(i);
				}
			}
		}
		long[] d3 = new long[Math.max(n, m) + 1];
		for (int i = 2; i < d3.length; i++) {
			d3[i] = d3[i - 1] + 2 * s(i - 1);
		}
		char[][] c = new char[n][];
		int[] row = new int[n];
		int[] col = new int[m];
		Arrays.fill(row, -1);
		Arrays.fill(col, -1);
		for (int i = 0; i < c.length; i++) {
			c[i] = nextToken().toCharArray();
			for (int j = 0; j < m; j++) {
				if (c[i][j] == 'X') {
					row[i] = j;
					col[j] = i;
				}
			}
		}
		System.err.println(sum);
		for (int i = 0; i < n; i++) {
			if (row[i] != -1) {
				sum += d3[m];
				sum += 4 * (long) (row[i]) * (m - row[i] - 1);
				sum -= 2 * (s(row[i]) + s(m - row[i] - 1));
			} else {
				sum += d3[m];
			}
		}
		System.err.println(sum);
		for (int i = 0; i < m; i++) {
			if (col[i] != -1) {
				sum += d3[n];
				sum += 4 * (long) (col[i]) * (n - col[i] - 1);
				sum -= 2 * (s(col[i]) + s(n - col[i] - 1));
			} else {
				sum += d3[n];
			}
		}
		System.err.println(sum);
		int count = n * m;
		for (int i = 0; i < n; i++) {
			if (row[i] == -1) {
				continue;
			}
			int x = row[i];
			sum -= 2 * dp[n - i][m - x];
			sum -= 2 * dp[n - i][x + 1];
			sum -= 2 * dp[i + 1][m - x];
			sum -= 2 * dp[i + 1][x + 1];
			sum += 4 * (s(n - i - 1) + s(m - x - 1) + s(i) + s(x));
			count--;
		}
		System.err.println(sum);
		for (int i = 0; i < n; i++) {
			if (row[i] == -1) {
				continue;
			}
			for (int j = i + 1; j < n; j++) {
				if (row[j] == -1) {
					continue;
				}
				sum += 2 * (Math.abs(row[i] - row[j]) + Math.abs(i - j));
			}
		}
		System.err.println(sum);
		out.println(1. * sum / ((long) count * count));
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
		new D().run();
	}
}