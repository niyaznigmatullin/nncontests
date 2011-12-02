import java.io.*;
import java.util.*;

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

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int a = nextInt();
		int b = nextInt();
		int c = nextInt();
		char[][] ans = new char[n][m];
		if ((n & 1) == 1 && (m & 1) == 1) {
			out.println("IMPOSSIBLE");
			return;
		}
		if ((n & 1) == 1) {
			if (m / 2 > a) {
				out.println("IMPOSSIBLE");
				return;
			}
			for (int i = 0; i < m / 2; i++) {
				ans[n - 1][2 * i + 1] = ans[n - 1][2 * i] = (char) ('z' - (i & 1));
			}
			a -= m / 2;
			--n;
		}
		if ((m & 1) == 1) {
			if (n / 2 > b) {
				out.println("IMPOSSIBLE");
				return;
			}
			b -= n / 2;
			--m;
			for (int i = 0; i < n / 2; i++) {
				ans[2 * i + 1][m - 1] = ans[2 * i][m - 1] = (char) ('z' - (i & 1));
			}
		}
		a /= 2;
		b /= 2;
		if (a + b + c < n * m / 4) {
			out.println("IMPOSSIBLE");
			return;
		}
		char[] ccd = new char[] { 'a', 'b', 'c', 'd' };
		char[] cce = new char[] { 'e', 'f', 'g', 'h' };
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < m / 2; j++) {
				int x = 2 * (i & 1) + (j & 1);
				if (c > 0) {
					ans[2 * i][2 * j] = ans[2 * i][2 * j + 1] = ans[2 * i + 1][2 * j] = ans[2 * i + 1][2 * j + 1] = ccd[x];
					--c;
				} else if (b > 0) {
					ans[2 * i][2 * j] = ans[2 * i + 1][2 * j] = ccd[x];
					ans[2 * i][2 * j + 1] = ans[2 * i + 1][2 * j + 1] = cce[x];
					--b;
				} else if (a > 0) {
					ans[2 * i][2 * j] = ans[2 * i][2 * j + 1] = ccd[x];
					ans[2 * i + 1][2 * j] = ans[2 * i + 1][2 * j + 1] = cce[x];
					--a;
				} else {
					throw new AssertionError();
				}
			}
		}
		for (char[] cc : ans) {
			out.println(new String(cc));
		}
	}
}