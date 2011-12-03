import java.io.*;
import java.util.*;
import java.math.*;

public class C implements Runnable {

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			int n = nextInt();
			char[][] c = new char[n][];
			for (int j = 0; j < n; j++) {
				c[j] = nextToken().toCharArray();
			}
			out.println("Case " + (i + 1) + ": " + solve(c));
		}
	}

	static final int[] DX = { 1, 0, -1, 0 };
	static final int[] DY = { 0, 1, 0, -1 };

	static int dfs(int x, int y, char[][] c, int n) {
		int ret = c[x][y] == 'x' ? 1 : 0;
		c[x][y] = '.';
		for (int dir = 0; dir < 4; dir++) {
			int xx = x + DX[dir];
			int yy = y + DY[dir];
			if (xx < 0 || yy < 0 || xx >= n || yy >= n || c[xx][yy] == '.') {
				continue;
			}
			ret += dfs(xx, yy, c, n);
		}
		return ret;
	}

	static int solve(char[][] c) {
		int n = c.length;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (c[i][j] != '.') {
					if (dfs(i, j, c, n) > 0) {
						ans++;
					}
				}
			}
		}
		return ans;
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
		new C().run();
	}
}