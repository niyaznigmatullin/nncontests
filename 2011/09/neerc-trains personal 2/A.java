import java.io.*;
import java.util.*;
import java.math.*;

public class A {

	void solve() {
		int n = nextInt();
		double r = nextDouble();
		double x1 = nextDouble();
		double y1 = nextDouble();
		double x2 = nextDouble();
		double y2 = nextDouble();
		if (x1 > x2) {
			double t = x1;
			x1 = x2;
			x2 = t;
		}
		if (y1 > y2) {
			double t = y1;
			y1 = y2;
			y2 = t;
		}
		double[] x = new double[n];
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			x[i] = nextDouble() - x1;
			y[i] = nextDouble() - y1;
		}
		solve(x1, y1, x, y);
		solve(x1, (y1 + y2) * .5, x, y);
		solve((x1 + x2) * .5, y1, x, y);
		solve((x1 + x2) * .5, (y1 + y2) * .5, x, y);
	}

	void solve(double x1, double y1, double[] x, double[] y) {
		int n = x.length;
		for (int i = 0; i < n; i++) {
			out.println((x1 + x[i] * .5) + " " + (y1 + y[i] * .5));
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("a.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream("a.out"));
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
		new A().run();
	}
}