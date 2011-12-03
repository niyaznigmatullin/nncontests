import java.io.*;
import java.util.*;
import java.math.*;

public class F implements Runnable {
	public static void main(String[] args) {
		new F().run();
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
			sc = new FastScanner("fenwick.in");
			out = new PrintWriter("fenwick.out");
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

		void update(int x, long y) {
			for (int i = x; i < a.length; i |= (i + 1)) {
				a[i] += y;
			}
		}

		long getSum(int x) {
			long ret = 0;
			for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
				ret += a[i];
			}
			return ret;
		}

		long sum(int l, int r) {
			if (l > r) {
				return 0;
			}
			return getSum(r) - getSum(l - 1);
		}
	}

	void solve() {
		int n = nextInt();
		long[] a = new long[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = nextInt();
		}
		long[] b = a.clone();
		Fenwick f = new Fenwick(n);
		for (int i = 0; i < n; i++) {
			f.update(i, a[i]);
		}
		for (int i = 1; 1 << i <= n; i++) {
			int j = 1 << i;
			for (int k = j - 2; k < n; k += j) {
				long e = f.sum(k - j + 2, k - 1);
				f.update(k, -e - a[k]);
				a[k] = -e;
			}
		}		
		for (int i = 0; i < a.length; i++) {
			if ((i | (i + 1)) >= a.length) {
				a[i] = b[i];
			}
		}
		for (int i = 0; i < a.length; i++) {
			out.print(a[i] + " ");
		}
		// Fenwick ff = new Fenwick(n);
		// for (int i = 0; i < a.length; i++) {
		// ff.update(i, a[i]);
		// }
		// if (!Arrays.equals(ff.a, a)) {
		// throw new AssertionError(Arrays.toString(ff.a) + " "
		// + Arrays.toString(a));
		// }
	}
}