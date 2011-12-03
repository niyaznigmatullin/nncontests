import java.util.*;
import java.math.*;
import java.io.*;

public class D implements Runnable {
	public static void main(String[] args) {
		new Thread(new D()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return "0";
			}
		}
		return st.nextToken();
	}

	String nextLine() {
		String ret = "";
		try {
			ret = br.readLine();
		} catch (Exception e) {
			ret = "";
		}
		if (ret == null) {
			eof = true;
			return "$";
		}
		return ret;
	}

	String nextString() {
		return nextToken();
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

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.flush();
	}

	void solve() {
		while (solve2())
			;
	}

	boolean solve2() {
		int n = nextInt();
		int m = nextInt();
		if (n == 0 && m == 0)
			return false;
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = nextInt();
			}
		}
		int ans = 0;
		was = new boolean[n + 1][m + 1];
		for (int i = 1; i * i <= n; i++) {
			if (n % i != 0)
				continue;
			for (int j = 1; j * j <= m; j++) {
				if (m % j != 0)
					continue;
				ans += (check(a, i, j));
				ans += (check(a, n / i, j));
				ans += (check(a, i, m / j));
				ans += (check(a, n / i, m / j));
			}
		}
		out.println(ans);
		return true;
	}

	boolean[][] was;

	int check(int[][] a, int p, int q) {
		int n = a.length;
		int m = a[0].length;
		if ((p == 1 && q == 1) || (p == n && q == m)) {
			return 0;
		}
		if (was[p][q]) {
			return 0;
		}
		was[p][q] = true;
		int g = 0;
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < q; j++) {
				g = gcd(g, a[i][j]);
			}
		}
		int[][] b = new int[p][q];
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < q; j++) {
				b[i][j] = a[i][j] / g;
			}
		}
		int y = 0;
		for (int i = 0; i < n / p; i++) {
			for (int j = 0; j < m / q; j++) {
				if (a[i * p][j * q] % b[0][0] != 0)
					return 0;
				int f = a[i * p][j * q] / b[0][0];
				for (int k1 = 0; k1 < p; k1++) {
					for (int k2 = 0; k2 < q; k2++) {
						if (a[i * p + k1][j * q + k2] % b[k1][k2] != 0
								|| a[i * p + k1][j * q + k2] / b[k1][k2] != f) {
							return 0;
						}
					}
				}
				y = gcd(y, f);
			}
		}
		int d = 0;
		for (int i = 1; i * i <= y; i++) {
			if (y % i != 0)
				continue;
			if (y != i * i) {
				d += 2;
			} else
				d++;
		}
		return d;
	}

	int gcd(int x, int y) {
		if (x == 0 || y == 0)
			return x + y;
		return gcd(y, x % y);
	}
}