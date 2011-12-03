import static java.lang.Math.max;
import static java.util.Arrays.sort;

import java.io.*;
import java.util.*;
import java.math.*;

public class I {

	static class Segment implements Comparable<Segment> {
		int l;
		int r;

		public Segment(int l, int r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo(Segment o) {
			return l - o.l;
		}

	}

	void solve() {
		int n = nextInt();
		Segment[] s = new Segment[n];
		for (int i = 0; i < n; i++) {
			s[i] = new Segment(nextInt(), nextInt());
		}
		sort(s);
		int m = nextInt();
		Segment[] t = new Segment[m];
		for (int i = 0; i < m; i++) {
			t[i] = new Segment(nextInt(), nextInt());
		}
		sort(t);
		int ans = 0;
		for (int i = 0, j = 0; i < n; i++) {
			boolean ok = true;
			int last = s[i].l;
			while (j < m && t[j].l <= last) {
				last = max(last, t[j].r);
				j++;
			}
			if (last != s[i].r) {
				ok = false;
			}
			while (j < m && t[j].r <= s[i].r) {
				j++;
			}
			if (!ok) {
				ans++;
			}
		}
		out.println(ans);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("i.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream("i.out"));
			}
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
		new I().run();
	}
}