package D;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

	static int[] p;
	static boolean[] was;

	static long go(int x, int n) {
		if (n == x) {
			long got = 1;
			for (int i = 1; i < n; i++) {
				if (p[i] > p[i - 1]) {
					got <<= 1;
				}
			}
			return got;
		}
		long ans = 0;
		for (int i = 0; i < n; i++) {
			if (was[i]) {
				continue;
			}
			was[i] = true;
			p[x] = i;
			ans += go(x + 1, n);
			was[i] = false;
		}
		return ans;
	}

	static long solve(int n) {
		p = new int[n];
		was = new boolean[n];
		return go(0, n);
	}

	static long[] ANSWER = { 0, 1, 3, 13, 75, 541, 4683, 47293, 545835,
			7087261, 102247563, 1622632573, };

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			out.println(ANSWER[nextInt()]);
		}
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
