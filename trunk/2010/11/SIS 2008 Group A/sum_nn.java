import java.io.*;
import java.util.*;
import java.math.*;

public class sum_nn implements Runnable {
	public static void main(String[] args) {
		new sum_nn().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
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

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner("sum.in");
			out = new PrintWriter("sum.out");
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

	class Fenwick {
		long[] a;

		public Fenwick(int n) {
			a = new long[n];
		}

		void add(int x, long y) {
			while (x < a.length) {
				a[x] += y;
				x |= x + 1;
			}
		}

		private long getSum(int x) {
			long ret = 0;
			while (x >= 0) {
				ret += a[x];
				x = (x & (x + 1)) - 1;
			}
			return ret;
		}

		long getSum(int l, int r) {
			return getSum(r) - getSum(l - 1);
		}
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		Fenwick f = new Fenwick(n);
		for (int i = 0; i < m; i++) {
			String s = nextToken();
			if (s.equals("A")) {
				int x = nextInt() - 1;
				long cur = f.getSum(x, x);
				f.add(x, nextInt() - cur);
			} else {
				out.println(f.getSum(nextInt() - 1, nextInt() - 1));
			}
		}
	}
}