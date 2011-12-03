import java.io.*;
import java.util.*;
import java.math.*;

public class prosto_nn implements Runnable {
	public static void main(String[] args) {
		new prosto_nn().run();
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
			sc = new FastScanner("prosto.in");
			out = new PrintWriter("prosto.out");
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

	long solve2(long n, int k, int[] p) {
		if (n < 0) {
			return 0;
		}
		long ans = 0;
		for (int i = 0; i < 1 << k; i++) {
			long mul = 1;
			for (int j = 0; j < k; j++) {
				if (((i >> j) & 1) == 1) {
					mul *= p[j];
				}
			}
			if (Integer.bitCount(i) % 2 == 0) {
				ans += n / mul;
			} else {
				ans -= n / mul;
			}
		}
		return ans;
	}

	void solve() {
		long a = nextLong();
		long b = nextLong();
		int n = nextInt();
		int[] p = new int[n];
		for (int i = 0; i < p.length; i++) {
			p[i] = nextInt();
		}
		out.println(solve2(b, n, p) - solve2(a - 1, n, p));
	}
}