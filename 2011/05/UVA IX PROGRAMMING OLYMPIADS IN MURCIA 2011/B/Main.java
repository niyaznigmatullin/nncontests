package B;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

	static class Party {
		String name;
		int votes;

		public Party(String name, int votes) {
			this.name = name;
			this.votes = votes;
		}
	}

	void solve() {
		int t = nextInt();
		for (int test = 0; test < t; test++) {
			// try {
			int n = nextInt();
			nextToken();
			nextToken();
			nextToken();
			nextToken();
			Party[] parties = new Party[n];
			for (int i = 0; i < n; i++) {
				parties[i] = new Party(nextToken(), nextInt());
			}
			out.print(solve(parties));
			// } catch (NumberFormatException e) {
			//
			// }
		}
	}

	static String solve(Party[] p) {
		int n = p.length;
		int all = 0;
		for (int i = 0; i < n; i++) {
			all += p[i].votes;
		}
		int[] answer = new int[n];
		int left = 100;
		for (int i = 0; i < n; i++) {
			int here = all == 0 ? 100 / n : p[i].votes * 100 / all;
			answer[i] = here;
			left -= here;
		}
		boolean[] had = new boolean[n];
		while (left > 0) {
			int min = -1;
			for (int i = 0; i < n; i++) {
				if (had[i]) {
					continue;
				}
				if (min == -1
						|| (p[i].votes > p[min].votes || p[i].votes == p[min].votes
								&& p[i].name.compareTo(p[min].name) < 0)) {
					min = i;
				}
			}
			if (min == -1) {
				break;
			}
			had[min] = true;
			answer[min]++;
			left--;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(p[i].name).append(' ').append(answer[i]).append('\n');
		}
		return sb.toString();
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
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
		new Main().run();
	}
}
