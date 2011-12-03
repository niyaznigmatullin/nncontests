import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			int m = nextInt();
			int x1 = nextInt() - 1;
			int y1 = nextInt() - 1;
			int x2 = nextInt() - 1;
			int y2 = nextInt() - 1;
			char[][] ans = solve(n, m, x1, y1, x2, y2);
			if (ans != null) {
				if (ans.length != n || ans[0].length != m || ans[x1][y1] != 'R'
						|| ans[x2][y2] != 'B') {
					System.err.println(n + " " + m + " " + x1 + " " + y1 + " "
							+ x2 + " " + y2 + "\n" + printAns(ans));
					throw new AssertionError();
				}
			}
			out.println(printAns(ans));
		}
	}

	static char[][] solve(int n, int m, int x1, int y1, int x2, int y2) {
		if ((n & 1) == 1 && (m & 1) == 1) {
			return null;
		}
		boolean rev = false;
		if ((n & 1) == 1) {
			rev = true;
			{
				int t = n;
				n = m;
				m = t;
			}
			{
				int t = x1;
				x1 = y1;
				y1 = t;
			}
			{
				int t = x2;
				x2 = y2;
				y2 = t;
			}
		}
		if (x1 < n / 2 && x2 >= n / 2) {
			char[][] ans = new char[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (i < n / 2) {
						ans[i][j] = 'R';
					} else {
						ans[i][j] = 'B';
					}
				}
			}
			return getAns(ans, rev);
		}
		if (x1 >= n / 2 && x2 < n / 2) {
			char[][] ans = new char[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (i >= n / 2) {
						ans[i][j] = 'R';
					} else {
						ans[i][j] = 'B';
					}
				}
			}
			return getAns(ans, rev);
		}		
		boolean inv = false;
		if (x1 >= n / 2) {
			x1 = n - x1 - 1;
			x2 = n - x2 - 1;
			y1 = m - y1 - 1;
			y2 = m - y2 - 1;
			inv = !inv;
		}
		if (y1 == y2) {
			if (m == 1) {
				return null;
				// char[][] ans = new char[n][m];
				// for (int i = 0; i < n / 2; i++) {
				// ans[i][0] = 'B';
				// }
				// ans[x1][0] = 'R';
				// for (int i = n / 2; i < n; i++) {
				// ans[i][0] = rev(ans[n - i - 1][0]);
				// }
				// inv(ans, inv);
				// return getAns(ans, rev);
			}
			if (x1 > x2) {
				{
					int t = x1;
					x1 = x2;
					x2 = t;
				}
				{
					int t = y1;
					y1 = y2;
					y2 = t;
				}
				inv = !inv;
			}
			char[][] ans = new char[n][m];
			for (int i = 0; i < n / 2; i++) {
				Arrays.fill(ans[i], 'R');
			}
			for (int i = x2; i < n / 2; i++) {
				if (y2 < m / 2) {
					Arrays.fill(ans[i], 0, y2 + 1, 'B');
				} else {
					Arrays.fill(ans[i], y2, m, 'B');
				}
			}
			for (int i = n / 2; i < n; i++) {
				for (int j = 0; j < m; j++) {
					ans[i][j] = rev(ans[n - i - 1][m - j - 1]);
				}
			}
			inv(ans, inv);
			return getAns(ans, rev);
		}
		if (y1 > y2) {
			{
				int t = x1;
				x1 = x2;
				x2 = t;
			}
			{
				int t = y1;
				y1 = y2;
				y2 = t;
			}
			inv = !inv;
		}
		char[][] ans = new char[n][m];
		for (int i = 0; i < n / 2; i++) {
			Arrays.fill(ans[i], 'R');
		}
		for (int i = 0; i < n / 2; i++) {
			Arrays.fill(ans[i], y2, m, 'B');
		}
		for (int i = n / 2; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ans[i][j] = rev(ans[n - i - 1][m - j - 1]);
			}
		}
		inv(ans, inv);
		return getAns(ans, rev);
	}

	static char rev(char c) {
		if (c == 'R') {
			return 'B';
		}
		if (c == 'B') {
			return 'R';
		}
		throw new AssertionError();
	}

	static void inv(char[][] ans, boolean inv) {
		if (!inv) {
			return;
		}
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[i].length; j++) {
				if (ans[i][j] == 'R') {
					ans[i][j] = 'B';
				} else {
					ans[i][j] = 'R';
				}
			}
		}
	}

	static char[][] getAns(char[][] ans, boolean rev) {
		if (rev) {
			char[][] ans2 = new char[ans[0].length][ans.length];
			for (int i = 0; i < ans.length; i++) {
				for (int j = 0; j < ans[i].length; j++) {
					ans2[j][i] = ans[i][j];
				}
			}
			ans = ans2;
		}
		return ans;
	}

	static String printAns(char[][] ans) {
		if (ans == null) {
			return "IMPOSSIBLE\n";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ans.length; i++) {
			sb.append(new String(ans[i])).append('\n');
		}
		return sb.toString();
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			sc = new FastScanner("d.in");
			out = new PrintWriter("d.out");
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
		new D().run();
	}
}