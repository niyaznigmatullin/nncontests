import java.io.*;
import java.util.*;
import java.math.*;

public class C implements Runnable {
	public static void main(String[] args) {
		new C().run();
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

	void solve() {
		int n = nextInt();
		int x = nextInt();
		int m = nextInt();
		int y = nextInt();
		int d = Math.abs(x - y);
		long ans = n + 1;
		if (d == 0) {
			out.println(Math.max(n, m) + 1);
			return;
		}
		for (int i = 1; i <= m; i++) {
			ans += f(d, n, i);
		}
		out.println(ans);
	}

	int f(int d, int n, int c) {
		if (d - c >= n) {
			return 1;
		}
		if (c == 1) {
			return 2;
		}
		int l = Math.abs(d - c) + 1;
		int r = Math.min(d + c - 1, n);
		if (d > c) {
			return (r - l + 1) * 2;
		} else if (d < c) {
			return (r - l + 1) * 2 + 1;
		} else {
			return (r - l + 1) * 2;
		}
	}
}