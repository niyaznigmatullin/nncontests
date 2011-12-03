import java.io.*;
import java.util.*;
import java.math.*;

public class F implements Runnable {

	static final int ALPHABET = 26;
	static final int MAXSUMLEN = 401;

	static class AhoCorasick {
		int[][] g;
		int[] sufLink;
		int free;
		boolean[] isTerminal;
		int[] depth;
		int[] termDepth;
		int[][] shortGLetter;
		int[][] shortGWhere;

		public AhoCorasick(int len) {
			g = new int[ALPHABET][len];
			for (int[] d : g) {
				Arrays.fill(d, -1);
			}
			sufLink = new int[len];
			isTerminal = new boolean[len];
			depth = new int[len];
			termDepth = new int[len];
			Arrays.fill(termDepth, -1);
			Arrays.fill(sufLink, -1);
			free = 1;
		}

		void doShortG() {
			shortGLetter = new int[MAXSUMLEN][];
			shortGWhere = new int[MAXSUMLEN][];
			for (int i = 0; i < MAXSUMLEN; i++) {
				int count = 0;
				for (int j = 0; j < ALPHABET; j++) {
					if (g[j][i] >= 0) {
						count++;
					}
				}
				shortGLetter[i] = new int[count];
				shortGWhere[i] = new int[count];
				int k = 0;
				for (int j = 0; j < ALPHABET; j++) {
					if (g[j][i] >= 0) {
						shortGLetter[i][k] = j;
						shortGWhere[i][k] = g[j][i];
						k++;
					}
				}
			}
		}

		int add(String s, int id) {
			int p = 0;
			for (int i = 0; i < s.length(); i++) {
				int c = s.charAt(i)
						- (Character.isUpperCase(s.charAt(i)) ? 'A' : 'a');
				if (g[c][p] == -1) {
					depth[free] = i + 1;
					g[c][p] = free++;
				}
				p = g[c][p];
			}
			isTerminal[p] = true;
			return p;
		}

		void buildSuf() {
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.add(0);
			while (!q.isEmpty()) {
				int v = q.poll();
				for (int i = 0; i < ALPHABET; i++) {
					int u = g[i][v];
					int w = sufLink[v] == -1 ? 0 : g[i][sufLink[v]];
					if (u >= 0) {
						sufLink[u] = w;
						q.add(u);
					} else {
						g[i][v] = w;
					}
				}
			}
			for (int i = 0; i < MAXSUMLEN; i++) {
				doDepth(i);
			}
		}

		void doDepth(int v) {
			if (termDepth[v] >= 0) {
				return;
			}
			if (isTerminal[v]) {
				termDepth[v] = depth[v];
				return;
			}
			int w = sufLink[v];
			if (w == -1) {
				termDepth[v] = 0;
				return;
			}
			doDepth(w);
			termDepth[v] = termDepth[w];
		}
	}

	final static int MOD = 1000000007;

	static String genRandomTest(int n) {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append((char) (rand.nextInt(26) + 'a'));
		}
		return sb.toString();
	}

	void solve() {
		// System.err.println(400 + " " + 20);
		// for (int i = 0; i < 20; i++) {
		// System.err.println(genRandomTest(20));
		// }
		int n = nextInt();
		int m = nextInt();
		AhoCorasick ac = new AhoCorasick(MAXSUMLEN);
		for (int i = 0; i < m; i++) {
			String s = nextToken();
			ac.add(s, i);
		}
		// long time = System.currentTimeMillis();
		ac.buildSuf();
		ac.doShortG();
		int[][][] dp = new int[n + 1][][];
		for (int i = 0; i <= n; i++) {
			dp[i] = new int[i + 1][MAXSUMLEN];
		}
		dp[0][0][0] = 1;
		for (int i = 0; i < n; i++) {
			int[][] d1 = dp[i];
			int[][] d2 = dp[i + 1];
			for (int j = 0; j <= i; j++) {
				if (i - j > 30) {
					continue;
				}
				for (int k = 0; k < MAXSUMLEN; k++) {
					if (d1[j][k] == 0) {
						continue;
					}
					for (int c = 0; c < 26; c++) {
						int newK = ac.g[c][k];
						if (newK == -1) {
							continue;
						}
						int newJ = j;
						int y = i + 1 - ac.termDepth[newK];
						if (j >= y) {
							newJ = i + 1;
						}
						d2[newJ][newK] += d1[j][k];
						if (d2[newJ][newK] >= MOD) {
							d2[newJ][newK] -= MOD;
						}
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < MAXSUMLEN; i++) {
			ans += dp[n][n][i];
			if (ans >= MOD) {
				ans -= MOD;
			}
		}
		out.println(ans);
		// System.err.println(System.currentTimeMillis() - time);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
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
		new F().run();
	}
}