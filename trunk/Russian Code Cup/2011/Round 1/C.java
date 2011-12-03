import java.io.*;
import java.util.*;
import java.math.*;

public class C implements Runnable {

	void solve() {
		int n = nextInt();
		int m = nextInt();
		if (n < m) {
			solve1(n, m);
		} else if (n > m) {
			solve2(n, m);
		} else if (n == m) {
			solve3(n);
		}
	}

	private void solve1(int n, int m) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append("EE");
		}
		m -= (n + 1);
		if (m > 0) {
			sb.append("W");
			if (m > 2 * n) {
				throw new AssertionError();
			}
			while (m > 0) {
				sb.append("NS");
				m--;
				if (m == 0) {
					break;
				}
				sb.append("SN");
				m--;
				if (m == 0) {
					break;
				}
				sb.append("WW");
			}
		}
		print(sb.toString());
	}

	private void solve2(int n, int m) {
		StringBuilder sb = new StringBuilder();
		sb.append("W");
		for (int i = 0; i < m; i++) {
			sb.append("EE");
		}
		n -= m + 1;
		if (n > 0) {
			if (n > 2 * m) {
				throw new AssertionError();
			}
			m = n;
			sb.append("W");
			while (m > 0) {
				sb.append("NS");
				m--;
				if (m == 0) {
					break;
				}
				sb.append("SN");
				m--;
				if (m == 0) {
					break;
				}
				sb.append("WW");
			}
		}
		print(sb.toString());
	}

	void solve3(int n) {
		StringBuilder sb = new StringBuilder();
		sb.append("E");
		for (int i = 1; i < n; i++) {
			sb.append("EE");
		}
		print(sb.toString());
	}

	void print(String s) {
		out.println(s.length());
		out.println(s);
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
		new C().run();
	}
}