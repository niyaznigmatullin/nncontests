package H;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

	void solve() {
		int t = nextInt();
		for (int test = 0; test < t; test++) {
			int n = nextInt();
			int[][] a = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					a[i][j] = nextInt();
				}
			}
			out.println(solve(a));
		}
	}

	final static int[] DX = { 1, 0, -1, 0 };
	final static int[] DY = { 0, 1, 0, -1 };

	static int solve(int[][] a) {
		int n = a.length;
		boolean[][] controlled = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] != 0) {
					continue;
				}
				int sum = 0;
				int all = 0;
				for (int dir = 0; dir < 4; dir++) {
					int x = i + DX[dir];
					int y = j + DY[dir];
					if (x < 0 || y < 0 || x >= n || y >= n) {
						continue;
					}
					all++;
					sum += a[x][y];
				}
				double r = 1. * sum / all;
				int left = Math.max(i - (int) r, 0);
				int right = Math.min(i + (int) r, n - 1);
				for (int k = left; k <= right; k++) {
					int dif = Math.abs(k - i);
					if (dif * all > sum) {
						continue;
					}
					int e = (int) Math.round(Math.sqrt(Math.max(0, r * r - dif
							* dif)));
					int l2 = Math.max(j - e, 0);
					int r2 = Math.min(j + e, n - 1);
					for (int l = l2; l <= r2; l++) {
						int dif2 = Math.abs(j - l);
						if ((dif * dif + dif2 * dif2) * all * all > sum * sum) {
							continue;
						}
						controlled[k][l] = true;
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!controlled[i][j]) {
					ans++;
				}
			}
		}
		return ans;
	}

	InputReader sc;
	PrintWriter out;

	public void run() {
		try {
			File inputFile = new File("input.txt");
			if (inputFile.canRead()) {
				System.setIn(new FileInputStream(inputFile));
				System.setOut(new PrintStream("output.txt"));
			}
			sc = new StreamInputReader(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	double nextDouble() {
		return Double.parseDouble(sc.next());
	}

	int nextInt() {
		return sc.nextInt();
	}

	static abstract class InputReader {
		private boolean finished = false;

		public abstract int read();

		public int nextInt() {
			int c = read();
			while (c <= 32 && c >= 0) {
				c = read();
			}
			if (c == -1) {
				finished = true;
				return Integer.MIN_VALUE;
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			if (c < '0' || c > '9') {
				throw new NumberFormatException("digit expected " + (char) c
						+ " found");
			}
			int ret = 0;
			while (c >= '0' && c <= '9') {
				ret = ret * 10 + c - '0';
				c = read();
			}
			if (c > 32) {
				throw new NumberFormatException("space character expected "
						+ (char) c + " found");
			}
			return ret * sgn;
		}

		public String next() {
			int c = read();
			while (c <= 32 && c >= 0) {
				c = read();
			}
			if (c == -1) {
				finished = true;
				return null;
			}
			StringBuilder res = new StringBuilder();
			while (c > 32) {
				res.appendCodePoint(c);
				c = read();
			}
			return res.toString();
		}

		public boolean isFinished() {
			return finished;
		}

		public void setFinished(boolean finished) {
			this.finished = finished;
		}

		public abstract void close();
	}

	static class StreamInputReader extends InputReader {
		private InputStream stream;
		private byte[] buf;
		private int current, numOfChars;

		public StreamInputReader(InputStream stream) {
			this(stream, 1024);
		}

		public StreamInputReader(InputStream stream, int bufSize) {
			this.stream = stream;
			buf = new byte[bufSize];
		}

		public int read() {
			if (numOfChars == -1) {
				return -1;
			}
			if (current >= numOfChars) {
				current = 0;
				try {
					numOfChars = stream.read(buf);
				} catch (IOException e) {
					return -1;
				}
				if (numOfChars <= 0) {
					return -1;
				}
			}
			return buf[current++];
		}

		@Override
		public void close() {
			try {
				stream.close();
			} catch (IOException ignored) {

			}
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
