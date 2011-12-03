import static java.lang.System.exit;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class E {

	static class Answer {
		int bestX;
		int bestY;
		char[][] ans;

		public Answer(int bestX, int bestY, char[][] ans) {
			super();
			this.bestX = bestX;
			this.bestY = bestY;
			this.ans = ans;
		}

	}

	static final int n = 16;

	static void solve() throws Exception {
		char[][] c = new char[n][];
		for (int i = 0; i < n; i++) {
			c[i] = next().toCharArray();
			for (char ch : c[i]) {
				if (ch != '#') {
					continue;
				}
			}
		}
		Answer answer = flop(n, c);
		out.println(answer.bestY + " " + answer.bestX);
		for (char[] e : answer.ans) {
			out.println(e);
		}
	}

	static final Random rand = new Random(231323L);

	static void stress() {
		while (true) {
			char[][] c = new char[3][3];
			for (int i = 0; i < c.length; i++) {
				for (int j = 0; j < c[0].length; j++) {
					c[i][j] = rand.nextBoolean() ? '#' : '.';
				}
			}
			c[rand.nextInt(c.length)][rand.nextInt(c[0].length)] = '#';
			int vx = rand.nextInt(3);
			int vy = rand.nextInt(3);
			char[][] d = shift(c, vx, vy);
			Answer ans = flop(d.length, d);
			char[][] e = ans.ans;
			char[][] f = shift(e, ans.bestX, ans.bestY);
			if (!Arrays.deepEquals(d, f)) {
				for (char[] fs : d) {
					System.err.println(fs);
				}
				System.err.println();
				for (char[] fs : e) {
					System.err.println(fs);
				}
				System.err.println();
				for (char[] fs : f) {
					System.err.println(fs);
				}
				System.err.println();
				throw new AssertionError();
			}

		}
	}

	private static Answer flop(final int n, char[][] c) {
		int ans = Integer.MAX_VALUE;
		int bestX = Integer.MAX_VALUE;
		int bestY = Integer.MAX_VALUE;
		for (int vx = -n + 1; vx < n; vx++) {
			for (int vy = -n + 1; vy < n; vy++) {
				int got = get(c, vx, vy);
				// if (vx == 4 && vy == 0) {
				// System.err.println(got);
				// }
				if (got >= ans) {
					continue;
				}
				// System.err.println(got + " " + vx + " " + vy);
				ans = got;
				bestX = vx;
				bestY = vy;
			}
		}
		char[][] got = tryToGet(c, bestX, bestY);
		return new Answer(bestX, bestY, got);
	}

	// private static char[][] crop(char[][] c) {
	// {
	// int minX = Integer.MAX_VALUE;
	// int maxX = Integer.MIN_VALUE;
	// int minY = Integer.MAX_VALUE;
	// int maxY = Integer.MIN_VALUE;
	// for (int i = 0; i < c.length; i++) {
	// for (int j = 0; j < c[i].length; j++) {
	// if (c[i][j] != '#') {
	// continue;
	// }
	// minX = min(minX, i);
	// minY = min(minY, j);
	// maxX = max(maxX, i);
	// maxY = max(maxY, j);
	// }
	// }
	// char[][] d = new char[maxX - minX + 1][maxY - minY + 1];
	// for (int i = minX; i <= maxX; i++) {
	// for (int j = minY; j <= maxY; j++) {
	// d[i - minX][j - minY] = c[i][j];
	// }
	// }
	// c = d;
	// }
	// return c;
	// }

	static int get(char[][] c, int vx, int vy) {
		char[][] d = tryToGet(c, vx, vy);
		char[][] e = shift(d, vx, vy);
		if (e == null || !Arrays.deepEquals(c, e)) {
			return Integer.MAX_VALUE;
		}
		int count = 0;
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d[i].length; j++) {
				if (d[i][j] == '#') {
					count++;
				}
			}
		}
		return count;
	}

	private static char[][] tryToGet(char[][] c, int vx, int vy) {
		char[][] d = init(n, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i + vx >= 0 && i + vx < n && j + vy < n && j + vy >= 0) {
					if (c[i][j] == '#' && c[i + vx][j + vy] == '#') {
						d[i][j] = '#';
					}
				}
			}
		}
		if (vx == 0 && vy == 0) {
			return d;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (d[i][j] != '#') {
					continue;
				}
				int x1 = i - vx;
				int y1 = j - vy;
				int x2 = i + vx;
				int y2 = j + vy;
				if (x1 < 0 || y1 < 0 || x2 >= n || y2 >= n || x2 < 0 || y2 < 0
						|| x1 >= n || y1 >= n) {
					continue;
				}
				if (d[x1][y1] == '#' && d[x2][y2] == '#') {
					d[i][j] = '.';
				}
			}
		}
		return d;
	}

	static char[][] init(int n, int m) {
		char[][] ret = new char[n][m];
		for (char[] e : ret) {
			fill(e, '.');
		}
		return ret;
	}

	static char[][] shift(char[][] c, int vx, int vy) {
		char[][] ret = init(n, n);
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				if (c[i][j] == '#') {
					int x = i + vx;
					int y = j + vy;
					ret[i][j] = ret[x][y] = '#';
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			InputStream input = System.in;
			PrintStream output = System.out;
			if (System.getProperty("ONLINE_JUDGE") == null && file.canRead()) {
				input = new FileInputStream(file);
				output = new PrintStream(new File("output.txt"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			exit(239);
		}
	}

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}
