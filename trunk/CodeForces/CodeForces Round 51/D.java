import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {

	void solve() {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			long l = nextLong();
			long r = nextLong();
			out.println(go(r) - go(l - 1));
		}
	}

	long go(long n) {
		if (n < 10) {
			return n;
		}
		int m = (n + "").length();
		ArrayList<Integer> d = new ArrayList<Integer>();
		for (int j = 0; j < 9; j++) {
			if (((479 >> j) & 1) == 1) {
				d.add(j + 1);
			}
		}
		go2(d, n, m);
		long ret = 0;
		for (int i = 1; i < 1 << 9; i++) {
			d.clear();
			for (int j = 0; j < 9; j++) {
				if (((i >> j) & 1) == 1) {
					d.add(j + 1);
				}
			}
			ret += go2(d, n, m);
		}
		return ret;
	}

	int qq = 0;

	long go2(ArrayList<Integer> d, long n, int m) {
		System.err.println(++qq);
		int lcm = 1;
		for (int i : d) {
			lcm = lcm(lcm, i);
		}
		d.add(0);
		int[] digits = new int[d.size()];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = d.get(i);
		}
		System.err.println("lengths = " + lcm + " " + m + " " + digits.length);
		long[][][][] dp = new long[10][m + 1][lcm][1 << digits.length];		
		dp[0][0][0][0] = 1;
		int ten = 1 % lcm;
		for (int i = 0; i < m; i++) {
			ten = (ten * 10) % lcm;
			for (int j = 0; j < lcm; j++) {
				for (int k = 0; k < digits.length; k++) {
					int d1 = digits[k];
					for (int mask = 0; mask < 1 << digits.length; mask++) {
						if (dp[d1][i][j][mask] == 0) {
							continue;
						}
						for (int e = 0; e < d.size(); e++) {
							int d2 = digits[e];
							int newJ = (j + ten);
							if (newJ >= lcm) {
								newJ -= lcm;
							}
							int newMask = mask | (1 << e);
							// System.err.println(d2 + " " + i + " " + newJ +
							// " "
							// + newMask);
							dp[d2][i + 1][newJ][newMask] += dp[d1][i][j][mask];
						}
					}
				}
			}
		}
		long ret = 0;
		int[] num = new int[m];
		{
			long p = n;
			for (int i = 0; i < m; i++) {
				num[m - i - 1] = (int) (p % 10);
				p /= 10;
			}
		}
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				for (int k = 0; k < dp[i][j].length; k++) {
					for (int mask = 0; mask < dp[i][j][k].length; mask++) {
						for (int e = 0; e < digits.length; e++) {
							if (((mask >> e) & 1) == 0) {
								dp[i][j][k][mask] += dp[i][j][k][mask
										^ (1 << e)];
							}
						}
					}
				}
			}
		}
		int left = 0;
		int mask = (1 << digits.length) - 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < num[i]; j++) {
				ret += dp[j][i][left][mask];
			}
			ten = 1;
			for (int j = 0; j < m - i; j++) {
				ten = (ten * 10) % lcm;
			}
			left = (left + lcm - (ten * num[i]) % lcm) % lcm;
			boolean ok = false;
			for (int j = 0; j < digits.length; j++) {
				if (digits[j] == num[i]) {
					mask &= ~(1 << j);
					ok = true;
					break;
				}
			}
			if (!ok) {
				break;
			}
		}
		dp = null;
		return ret;
	}

	int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}

	int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
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