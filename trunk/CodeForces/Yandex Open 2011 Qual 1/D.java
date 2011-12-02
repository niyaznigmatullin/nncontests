import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	static class Album implements Comparable<Album> {
		int type;
		int count;

		public Album(int type, int count) {
			super();
			this.type = type;
			this.count = count;
		}

		@Override
		public int compareTo(Album o) {
			if (count != o.count) {
				return o.count - count;
			}
			return type - o.type;
		}

	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int[] a = new int[m];
		TreeSet<Album> ts = new TreeSet<Album>();
		for (int i = 0; i < m; i++) {
			a[i] = nextInt();
			ts.add(new Album(i, a[i]));
		}
		int[] ans = new int[n];
		{
			Album got = ts.pollFirst();
			ans[0] = got.type;
			got.count--;
			if (got.count > 0) {
				ts.add(got);
			}
		}		
		for (int i = 1; i + 1 < n; i++) {
			if (ts.isEmpty()) {
				out.println(-1);
				return;
			}
			Album got = ts.pollFirst();
			if (ans[i - 1] == got.type) {
				if (ts.isEmpty()) {
					out.println(-1);
					return;
				}
				Album tmp = got;
				got = ts.pollFirst();
				ts.add(tmp);
			}
			ans[i] = got.type;
			got.count--;
			if (got.count > 0) {
				ts.add(got);
			}
		}		
		if (ts.isEmpty()) {
			out.println(-1);
			return;
		}		
		boolean haveAns = false;
		loop: while (!ts.isEmpty()) {
			Album last = ts.pollLast();
			if (last.type != ans[0] && last.type != ans[n - 2]) {
				ans[n - 1] = last.type;
				haveAns = true;
				break;
			}
			for (int i = 1; i + 1 < n; i++) {
				if (ans[i] != last.type && ans[i - 1] != last.type) {
					for (int j = n - 1; j > i; j--) {
						ans[j] = ans[j - 1];
					}
					ans[i] = last.type;
					haveAns = true;
					break loop;
				}
			}
		}
		if (!haveAns) {
			out.println(-1);
			return;
		}
		for (int i = 0; i < n; i++) {
			out.print(ans[i] + 1 + " ");
		}
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
		new D().run();
	}
}