import java.io.*;
import java.util.*;
import java.math.*;

public class E implements Runnable {

	void solve() {
		int n = nextInt();
		for (int i = 0; i < n; i++) {
			out.println("Case " + (i + 1) + ": " + solve(nextToken()));
		}
	}

	final static int MAXN = 51;
	final static long[][] C = new long[MAXN][MAXN];
	static {
		for (int i = 0; i < MAXN; i++) {
			C[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
			}
		}
	}

	static String solve(String s) {
		int whereT = s.indexOf('^');
		int n = Integer.parseInt(s.substring(whereT + 1));
		s = s.substring(0, whereT);
		s = s.substring(1, s.length() - 1);
		int plus = s.indexOf('+');
		String s1 = s.substring(0, plus);
		String s2 = s.substring(plus + 1);
		StringBuilder sb = new StringBuilder();
		for (int i = n; i >= 0; i--) {
			if (i < n) {
				sb.append('+');
			}
			if (C[n][i] > 1) {
				sb.append(C[n][i]).append('*');
			}
			String p1 = pow(s1, i);
			String p2 = pow(s2, n - i);
			if (!p1.equals("")) {
				sb.append(p1);
			}
			if (!p2.equals("")) {
				if (!p1.equals("")) {
					sb.append('*');
				}
				sb.append(p2);
			}

		}
		return sb.toString();
	}

	static String pow(String s, int e) {
		if (e == 0) {
			return "";
		}
		if (e == 1) {
			return s;
		}
		return s + "^" + e;
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
		new E().run();
	}
}