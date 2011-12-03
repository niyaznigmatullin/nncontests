import java.io.*;
import java.util.*;
import java.math.*;

public class G {

	static class Pair {
		char c;
		char d;

		public Pair(char c, char d) {
			this.c = c;
			this.d = d;
		}

	}

	static List<Integer>[] edges;

	void solve() {
		String s = nextToken();
		if (s.charAt(0) != '-') {
			s = "+" + s;
		}
		int n = 26;
		int[][] d = new int[n][n];
		while (s.length() > 0) {
			String t = s.substring(0, 3);
			s = s.substring(3);
			int a, b;
			if (t.charAt(0) == '-') {
				a = t.charAt(2) - 'A';
				b = t.charAt(1) - 'A';
			} else {
				a = t.charAt(1) - 'A';
				b = t.charAt(2) - 'A';
			}
			d[a][b]++;
		}
		all: while (true) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (d[j][i] > 0) {
						for (int k = 0; k < n; k++) {
							if (d[i][k] > 0) {
								d[j][i]--;
								d[i][k]--;
								if (j != k) {
									d[j][k]++;
								}
								continue all;
							}
						}
					}
				}
			}
			break;
		}
		List<Pair> ans = new ArrayList<Pair>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < d[i][j]; k++) {
					ans.add(new Pair((char) (i + 'A'), (char) (j + 'A')));
				}
			}
		}
		// all: while (true) {
		// for (int i = 0; i < 26; i++) {
		// if (enter[i] == 0 && !edges[i].isEmpty()) {
		// int p = i;
		// while (!edges[p].isEmpty()) {
		// p = edges[p].remove(edges[p].size() - 1);
		// enter[p]--;
		// }
		// if (i != p) {
		// ans.add(new Pair((char) (i + 'A'), (char) (p + 'A')));
		// }
		// continue all;
		// }
		// }
		// for (int i = 0; i < 26; i++) {
		// if (!edges[i].isEmpty()) {
		// int p = i;
		// while (!edges[p].isEmpty()) {
		// p = edges[p].remove(edges[p].size() - 1);
		// enter[p]--;
		// }
		// if (i != p) {
		// ans.add(new Pair((char) (i + 'A'), (char) (p + 'A')));
		// }
		// continue all;
		// }
		// }
		// break;
		// }
		if (ans.isEmpty()) {
			out.print(0);
		}
		for (int i = 0; i < ans.size(); i++) {
			if (i > 0) {
				out.print("+");
			}
			out.print(ans.get(i).c + "" + ans.get(i).d);
		}
		out.println();
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("g.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream("g.out"));
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
		new G().run();
	}
}