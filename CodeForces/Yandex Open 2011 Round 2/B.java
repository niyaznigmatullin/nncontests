import java.io.*;
import java.util.*;
import java.math.*;

public class B implements Runnable {

	static int[][] ans;
	static boolean[][] was;
	static int count;
	static final int[] DX = { 1, 0, -1, 0 };
	static final int[] DY = { 0, 1, 0, -1 };

	static int dfs(int x, int y, boolean isRoot, char[][] c) {
		was[x][y] = true;
		int got = 1;
		int color = -1;
		int direction = -1;
		for (int dir = 0; dir < 4; dir++) {
			int xx = x + DX[dir];
			int yy = y + DY[dir];
			if (xx < 0 || yy < 0 || xx >= was.length || yy >= was[xx].length
					|| was[xx][yy] || c[xx][yy] == '#') {
				continue;
			}
			int d = dfs(xx, yy, false, c);
			if (d == 1) {
				if (color == -1) {
					color = count++;
					ans[x][y] = color;
				}
				ans[xx][yy] = color;
				got++;
			} else if (isRoot && d < 4) {
				direction = dir;
			}
		}
		if (ans[x][y] == -1 && isRoot) {
			if (direction == -1)
				throw new AnswerNotFoundException();
			int xx = x + DX[direction];
			int yy = y + DY[direction];
			ans[x][y] = ans[xx][yy];
		}
		return got;
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		char[][] c = new char[n][];
		ans = new int[n][m];
		for (int i = 0; i < n; i++) {
			c[i] = nextToken().toCharArray();
			for (int j = 0; j < m; j++) {
				if (c[i][j] == '#') {
					ans[i][j] = -2;
				} else {
					ans[i][j] = -1;
				}
			}
		}
		count = 0;
		was = new boolean[n][m];
		try {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (c[i][j] == '#') {
						continue;
					}
					dfs(i, j, true, c);
				}
			}
		} catch (AnswerNotFoundException e) {
			out.println(-1);
			return;
		}
		char[][] d = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				d[i][j] = c[i][j];
			}
		}
		was = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (d[i][j] == '.') {
					int x = ~getMask(i, j, d, ans);
					fill(i, j, d, ans,
							(char) (Integer.numberOfTrailingZeros(x) + '0'));
				}
			}
		}
		for (int i = 0; i < n; i++) {
			out.println(new String(d[i]));
		}
	}

	static void fill(int x, int y, char[][] d, int[][] e, char c) {
		d[x][y] = c;
		for (int dir = 0; dir < 4; dir++) {
			int xx = x + DX[dir];
			int yy = y + DY[dir];
			if (xx < 0 || yy < 0 || xx >= d.length || yy >= d[xx].length) {
				continue;
			}
			if (e[xx][yy] == e[x][y] && d[xx][yy] == '.') {
				fill(xx, yy, d, e, c);
			}
		}
	}

	static int getMask(int x, int y, char[][] d, int[][] e) {
		was[x][y] = true;
		int ret = 0;
		for (int dir = 0; dir < 4; dir++) {
			int xx = x + DX[dir];
			int yy = y + DY[dir];
			if (xx < 0 || yy < 0 || xx >= d.length || yy >= d[xx].length) {
				continue;
			}
			if (e[xx][yy] == e[x][y]) {
				if (!was[xx][yy])
					ret |= getMask(xx, yy, d, e);
			} else {
				if (d[xx][yy] >= '0' && d[xx][yy] <= '9') {
					ret |= 1 << d[xx][yy] - '0';
				}
			}
		}
		return ret;
	}

	static class AnswerNotFoundException extends RuntimeException {

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
		new B().run();
	}
}