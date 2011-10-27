import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	void solve() {
		test();
		String s = sc.nextToken();
		q = 0;
		long time = currentTimeMillis();
		out.println(solveDumbRecursive(s));
		System.err.println(currentTimeMillis() - time);
		// time = currentTimeMillis();
		// out.println(solveDumb(s));
		// System.err.println(currentTimeMillis() - time);
		System.err.println(q);
	}

	static final Random rand = new Random(234124L);

	static void test() {
		while (true) {
			StringBuilder sb = new StringBuilder();
			char last = '*';
			for (int i = 0; i < 1999; i++) {
				int g = rand.nextInt(3);
				if (g == 0) {
					if (last != '0') {
						sb.append('0');
					} else if (rand.nextBoolean()) {
						sb.append('+');
					} else {
						sb.append('*');
					}
				} else if (g == 1) {
					sb.append('+');
				} else {
					if (last == '0') {
						sb.append('*');
					} else if (rand.nextBoolean()) {
						sb.append('0');
					} else {
						sb.append('+');
					}
				}
				last = sb.charAt(sb.length() - 1);
			}
			if (last != '0') {
				sb.append('0');
			}
			long time = currentTimeMillis();
			System.err.println(solveDumbRecursive(sb.toString()) + " "
					+ (currentTimeMillis() - time));

		}
	}

	static void go(int len, String s) {
		if (len == 0) {
			int got = solveDumb(s);
			if (got != 0) {
				System.out.println(s + " " + got);
			}
			return;
		}
		if (s.isEmpty() || s.charAt(s.length() - 1) != '0') {
			go(len - 1, s + "0");
		}
		go(len - 1, s + "+");
		go(len - 1, s + "*");
	}

	static final int MOD = 1000003;

	static char op(char c) {
		return (c == '+' || c == '-') ? '+' : '*';
	}

	static String processString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length();) {
			if (!Character.isDigit(s.charAt(i))) {
				sb.append(op(s.charAt(i)));
				i++;
				continue;
			}
			int j = i;
			while (j < s.length() && Character.isDigit(s.charAt(j))) {
				j++;
			}
			sb.append('a');
			i = j;
		}
		return sb.toString();
	}

	static int f(int l, int r) {
		if (l > r) {
			return 0;
		}
		if (dp[l][r] != -1) {
			return dp[l][r];
		}
		int ret = 0;
		if (c[l] == '+') {
			ret = (ret + f(l + 1, r)) % MOD;
		}
		for (int i = next[l]; i < r; i = next[i]) {
			if (c[i] == 'a') {
				throw new AssertionError();
			}
			int got1 = f(l, i - 1);
			if (got1 == 0) {
				continue;
			}
			long got2 = f(i + 1, r);
			if (got2 == 0) {
				continue;
			}
			ret = (int) ((ret + got1 * got2) % MOD);
		}
		q++;
		return dp[l][r] = ret;
	}

	static int q;
	static int[][] dp;
	static char[] c;
	static int[] next;

	static int solveDumbRecursive(String s) {
		c = processString(s).toCharArray();
		dp = new int[c.length][c.length];
		for (int[] d : dp) {
			fill(d, -1);
		}
		int n = c.length;
		for (int i = 0; i < n; i++) {
			dp[i][i] = c[i] == 'a' ? 1 : 0;
		}
		for (int i = 0; i < n - 1; i++) {
			if (c[i] != 'a' && c[i + 1] == '*') {
				return 0;
			}
		}
		for (int i = 0; i < n; i++) {
			if (c[i] != 'a') {
				for (int j = 0; j <= i; j++) {
					dp[j][i] = 0;
				}
			}
		}
		for (int i = 0; i < n;) {
			if (c[i] == 'a') {
				i++;
				continue;
			}
			int j = i;
			while (j < n && c[j] != 'a') {
				j++;
			}
			for (int k1 = i; k1 < j; k1++) {
				for (int k2 = k1; k2 < j; k2++) {
					dp[k1][k2] = 0;
				}
			}
			i = j;
		}
		next = new int[n];
		next[n - 1] = n;
		for (int i = n - 2; i >= 0; i--) {
			if (c[i + 1] != 'a') {
				next[i] = i + 1;
			} else {
				next[i] = next[i + 1];
			}
		}
		return f(0, c.length - 1);
	}

	static int solveDumb(String s) {
		char[] c = processString(s).toCharArray();
		int[][] dp = new int[c.length][c.length];
		int n = c.length;
		for (int i = 0; i < n; i++) {
			if (c[i] == 'a') {
				dp[i][i] = 1;
			}
		}
		for (int len = 1; len < n; len++) {
			for (int i = 0, j = len; j < n; i++, j++) {
				if (c[i] == '-' || c[i] == '+') {
					dp[i][j] += dp[i + 1][j];
					if (dp[i][j] >= MOD) {
						dp[i][j] -= MOD;
					}
				}
				for (int k = i + 1; k < j; k++) {
					if (c[k] != 'a') {
						if (dp[i][k - 1] == 0 || dp[k + 1][j] == 0) {
							continue;
						}
						dp[i][j] = (int) ((dp[i][j] + (long) dp[i][k - 1]
								* dp[k + 1][j]) % MOD);
					}
				}
			}
		}
		return dp[0][n - 1];
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			File file = new File("D.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
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
		new D().run();
	}
}