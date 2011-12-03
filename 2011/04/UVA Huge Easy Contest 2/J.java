import java.io.*;
import java.util.*;
import java.math.*;

public class J implements Runnable {

	final static int[] p;
	final static int[] max;
	final static int[] d;
	final static int MAXN = 1000001;

	static {
		p = new int[MAXN];
		for (int i = 2; i * i < MAXN; i++) {
			if (p[i] == 0) {
				for (int j = i * i; j < MAXN; j += i) {
					p[j] = i;
				}
			}
		}
		max = new int[MAXN];
		d = new int[MAXN];
		max[1] = 1;
		d[1] = 1;
		for (int i = 2; i < MAXN; i++) {
			if (p[i] == 0) {
				d[i] = 2;
			} else {
				int prime = p[i];
				int cur = i;
				int count = 1;
				int pow = 1;
				while (cur % prime == 0) {
					cur /= prime;
					count++;
					pow *= prime;
				}
				if (cur == 1) {
					d[i] = count;
				} else {
					d[i] = d[cur] * d[pow];
				}
			}	
			if (d[max[i - 1]] <= d[i]) {
				max[i] = i;
			} else {
				max[i] = max[i - 1];
			}
		}
	}

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			out.println(max[n]);
		}
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
		new J().run();
	}
}