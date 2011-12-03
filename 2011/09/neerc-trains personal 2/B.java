import java.io.*;
import java.util.*;
import java.math.*;

public class B {
	static class Card implements Comparable<Card> {
		int val;
		int num;

		public Card(int val, int num) {
			this.val = val;
			this.num = num;
		}

		@Override
		public int compareTo(Card o) {
			if (val != o.val) {
				return val - o.val;
			}
			return num - o.num;
		}

	}

	void solve() {
		int n = nextInt();
		TreeSet<Card> set = new TreeSet<Card>();
		for (int i = 0; i < n; i++) {
			int x = nextInt();
			set.add(new Card(x, i));
		}
		int m = nextInt();
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			Card e = set.higher(new Card(nextInt(), n + 1));
			if (e == null) {
				out.println(-1);
				return;
			}
			set.remove(e);
			ans.add(e.num);
		}
		for (int i : ans) {
			out.print(i + 1 + " ");
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("b.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream("b.out"));
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
		new B().run();
	}
}