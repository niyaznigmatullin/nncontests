import java.io.*;
import java.math.*;
import java.util.*;

public class J {
	public static void main(String[] args) {
		Main.main(args);
	}

	// import java.io.*; import java.math.*; import java.util.*;

	static class Main {

		static class Pair {
			int a;
			int b;

			public Pair(int a, int b) {
				this.a = a;
				this.b = b;
			}

		}

		void solve() {
			int t = nextInt();
			for (int test = 0; test++ < t;) {
				int n = nextInt();
				Pair[] p = new Pair[n];
				for (int i = 0; i < n; i++) {
					p[i] = new Pair(nextInt(), nextInt());
				}
				out.println("Case #" + (test) + ": " + solve(p));
			}
		}

		static int solve(Pair[] p) {
			Arrays.sort(p, new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					return o1.b - o2.b;
				}
			});
			int ans = 0;
			int n = p.length;
			boolean[] isDead = new boolean[n];
			for (int i = 0; i < n; i++) {
				if (isDead[i]) {
					continue;
				}
				int count = 1;
				for (int j = i + 1; j < n; j++) {
					if (!isDead[j] && p[j].a <= p[i].b) {
						count++;
					}
				}
				if (count > 2) {
					ans += count;
					for (int j = i + 1; j < n; j++) {
						if (p[j].a <= p[i].b) {
							isDead[j] = true;
						}
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
					throw new NumberFormatException("digit expected "
							+ (char) c + " found");
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

}