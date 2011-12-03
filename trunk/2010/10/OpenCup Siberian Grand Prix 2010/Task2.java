import java.io.*;
import java.util.*;
import java.math.*;

public class Task2 implements Runnable {
	public static void main(String[] args) {
		new Task2().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
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

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			sc = new FastScanner("input.txt");
			out = new PrintWriter("output.txt");
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

	void solve() {
		while (solve2())
			;
	}

	int renum(int x, int s, int n) {
		return x == n ? s : x == s ? n : x;
	}

	boolean solve2() {
		int n = nextInt();
		int m = nextInt();
		int s = nextInt() - 1;
		int t = nextInt() - 1;
		if (n == 0 && m == 0 && s == -1 && t == -1) {
			return false;
		}
		boolean[][] has = new boolean[n][n];
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1;
			int y = nextInt() - 1;
			x = renum(x, s, n - 1);
			y = renum(y, s, n - 1);
			has[x][y] = true;
			has[y][x] = true;
		}
		int rT = renum(t, s, n - 1);
		int[][] dp = new int[n - 1][1 << (n - 1)];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < n; i++) {
			if (has[n - 1][i]) {
				dp[i][1 << i] = 1;
			}
		}
		for (int i = 1; i < 1 << (n - 1); i++) {
			if ((i & (i - 1)) == 0) {
				continue;
			}
			for (int j = 0; j < n - 1; j++) {
				if (((i >> j) & 1) == 0) {
					continue;
				}
				for (int k = 0; k < n - 1; k++) {
					if (k == j || ((i >> k) & 1) == 0 || !has[j][k]) {
						continue;
					}
					int e = dp[j][i ^ (1 << k)];
					if (e != Integer.MAX_VALUE) {
						dp[k][i] = Math.min(dp[k][i], e + 1);
					}
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		int mask = -1;
		for (int i = 0; i < 1 << (n - 1); i++) {
			if (((i >> rT) & 1) == 1
					&& (Integer.bitCount(i) & 1) == 0) {
				if (ans > dp[rT][i]) {
					ans = dp[rT][i];
					mask = i;
				}
			}
		}
		if (mask == -1) {
			out.println("No path");
			return true;
		}
		ArrayList<Integer> a = new ArrayList<Integer>();
		int last = rT;
		all: while ((mask & (mask - 1)) > 0) {
			a.add(last);
			for (int i = 0; i < n; i++) {
				if (i == last || ((mask >> i) & 1) == 0 || !has[last][i]) {
					continue;
				}
				if (dp[last][mask] == dp[i][mask ^ (1 << last)] + 1) {
					mask ^= (1 << last);
					last = i;
					continue all;
				}
			}
			throw new AssertionError();
		}
		a.add(last);
		a.add(n - 1);
		Collections.reverse(a);
		for (int i : a) {
			out.print(1 + renum(i, s, n - 1) + " ");
		}
		out.println();
		return true;
	}
}