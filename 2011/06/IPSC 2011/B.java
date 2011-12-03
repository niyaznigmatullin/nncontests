import java.io.*;
import java.util.*;
import java.math.*;

public class B implements Runnable {

	void solve() {
		int q = 0;
		while (true) {
			if (++q == 10) {
				System.err.println("TRY");
				q = 0;
			}
			char[][] a = gen(600, 600);
			int got = bfs(a);
			System.err.println(got);
			if (got >= 5000) {
				for (char[] c : a) {
					out.println(new String(c));
				}
				return;
			}
		}
	}

	final static Random RAND = new Random();

	static char[][] gen(int n, int m) {
		char[][] c = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				c[i][j] = RAND.nextInt(4) == 0 ? '#' : '.';
			}
		}
		c[RAND.nextInt(n)][RAND.nextInt(m)] = 'S';
		c[RAND.nextInt(n)][RAND.nextInt(m)] = 'T';
		return c;
	}

	static int bfs(char[][] c) {
		int sx = -1;
		int sy = -1;
		int n = c.length;
		int m = c[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (c[i][j] == 'S') {
					sx = i;
					sy = j;
				}
			}
		}
		int[] qx = new int[n * m];
		int[] qy = new int[n * m];
		boolean[][] was = new boolean[n][m];
		int head = 0;
		int tail = 1;
		int maxSize = tail - head;
		qx[0] = sx;
		qy[0] = sy;
		was[sx][sy] = true;
		all: while (head < tail) {
			int px = qx[head];
			int py = qy[head];
			head++;
			for (int dir = 0; dir < 4; dir++) {
				int x = px + DX[dir];
				int y = py + DY[dir];
				if (x < 0 || y < 0 || x >= n || y >= m) {
					continue;
				}
				if (c[x][y] == '#' || was[x][y]) {
					continue;
				}
				qx[tail] = x;
				qy[tail] = y;
				tail++;
				was[x][y] = true;
				if (c[x][y] == 'T') {
					break all;
				}
				maxSize = Math.max(maxSize, tail - head);
			}
		}
		return maxSize;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Collection<Point> neighbours(int n, int m) {
			ArrayList<Point> p = new ArrayList<Point>();
			for (int i = 0; i < 4; i++) {
				int xx = x + DX[i];
				int yy = y + DY[i];
				if (xx < 0 || yy < 0 || xx >= n || yy >= m) {
					continue;
				}
				p.add(new Point(xx, yy));
			}
			return p;
		}
	}

	final static int[] DX = { 1, 0, -1, 0 };
	final static int[] DY = { 0, 1, 0, -1 };

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			sc = new FastScanner("b.in");
			out = new PrintWriter("b.out");
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