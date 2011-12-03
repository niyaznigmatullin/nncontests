import java.io.*;
import java.util.*;
import java.math.*;

public class F1 implements Runnable {

	void solve() {
		int all = BigInteger.valueOf(3).pow(12).intValue();
		for (int i = 0; i < all; i++) {
			int prob = get(i);
			// System.out.println(prob);
			if (prob > 4) {
				out.println(printAns(i));
			}
		}
	}

	static final int THREE = (3 * 3 * 3 * 3);

	static int get(int all) {
		int first = all % THREE;
		all /= THREE;
		int second = all % THREE;
		all /= THREE;
		int third = all % THREE;
		int ans = 0;
		for (int i = 0; i < 8; i++) {
			int a = i >> 1;
			int b = ((i >> 2) << 1) | (i & 1);
			int c = i & 3;
			int win = 0;
			int lose = 0;
			int get1 = getIt(a, first);
			int get2 = getIt(b, second);
			int get3 = getIt(c, third);
			if (get1 != 2) {
				if (get1 == (i & 1)) {
					win++;
				} else {
					lose++;
				}
			}
			if (get2 != 2) {
				if (get2 == ((i >> 1) & 1)) {
					win++;
				} else {
					lose++;
				}
			}
			if (get3 != 2) {
				if (get3 == ((i >> 2) & 1)) {
					win++;
				} else {
					lose++;
				}
			}
			if (win > 0 && lose == 0) {
				ans++;
			}
		}
		return ans;
	}

	static int getIt(int x, int first) {
		for (int i = 0; i < x; i++) {
			first /= 3;
		}
		return first % 3;
	}

	static String printAns(int all) {
		StringBuilder ans = new StringBuilder();
		System.err.println(all % THREE + " " + (all / THREE % THREE) + " " + all / THREE / THREE);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				int e = all % 3;
				ans.append(e == 2 ? 'P' : e == 1 ? 'T' : 'H');
				all /= 3;
			}
			ans.append('\n');
		}
		return ans.toString();
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			sc = new FastScanner("f1.in");
			out = new PrintWriter("f1.out");
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
		new F1().run();
	}
}