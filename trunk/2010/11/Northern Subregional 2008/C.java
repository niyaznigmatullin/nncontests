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
			sc = new FastScanner("class.in");
			out = new PrintWriter("class.out");
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
		int r = nextInt();
		int c = nextInt();
		char[][] ans = new char[r][c];
		for (int i = 0; i < ans.length; i++) {
			Arrays.fill(ans[i], '.');
		}
		int m;
		if (Math.min(r, c) * 2 - 1 > n) {
			m = (n + 1) / 2;
		} else {
			m = Math.min(r, c);
		}
		out.println(m);
		for (int i = 0; i < m; i++) {
			if (ans[0][i] != '#') {
				ans[0][i] = '#';
				--n;
			}
			if (ans[i][0] != '#') {
				ans[i][0] = '#';
				--n;
			}
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (n > 0 && ans[i][j] == '.') {
					ans[i][j] = '#';
					n--;
				}
			}
		}
		for (int i = 0; i < ans.length; i++) {
			out.println(new String(ans[i]));
		}
	}
}