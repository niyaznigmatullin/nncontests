import static java.util.Arrays.fill;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class J {

	static void solve() throws IOException {
		// for (int pow = 1; pow < 12; pow++) {
		// System.err.println(Arrays.toString(getSmartFor2(pow)));
		// System.err.println(Arrays.toString(JSubmit.getTwoSquaresFor2(pow)));
		// // System.err.println(Arrays.toString(getStupid(1 << pow)));
		// }
		// if (true) {
		// return;
		// }
		final int CAP = 20000;
		for (int p = 3; p < 30; p++) {
			if (!BigInteger.valueOf(p).isProbablePrime(100)) {
				continue;
			}
			System.err.println("GETTING FOR P = " + p);
			for (int pow = 1, a = p; a <= CAP; pow++, a *= p) {
				int[] ok = getSmart(p, pow);
				System.err.println("pow = " + pow + ":");
				System.err.println(Arrays.toString(ok));
				System.err.println(Arrays.toString(JSubmit
						.getTwoSquares(p, pow)));
				// System.err.println("smart");
			}
			// int[] ok = getStupid(p * p * p);
			// System.err.println(p + " " + Arrays.toString(ok));
		}
	}

	// static int waysZero(int p, int pow) {
	//
	// }

	static int[] getStupid(int n) {
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int x = (i * i + j * j) % n;
				c[x]++;
			}
		}
		return c;
	}

	static int[] getSmartFor2(int pow) {
		int n = 1;
		for (int i = 0; i < pow; i++) {
			n *= 2;
		}
		int[] stup = getStupid(n);
		int[] ok = new int[pow * 2 + 1];
		fill(ok, -1);
		for (int i = 1; i <= n; i++) {
			int powHere = Integer.numberOfTrailingZeros(i);
			int mod4 = (i >>> powHere) % 4;
			int index = powHere * 2 + mod4 / 2;
			if (ok[index] < 0) {
				ok[index] = stup[i % n];
			} else {
				if (ok[index] != stup[i % n]) {
					System.err.println("pow here: " + powHere);
					System.err.println(ok[index] + " != " + stup[i % n]);
					System.err.println("i = " + i);
					throw new AssertionError();
				}
			}
		}
		return ok;
	}

	static int[] getSmart(int p, int pow) {
		int n = 1;
		for (int i = 0; i < pow; i++) {
			n *= p;
		}
		int[] stup = getStupid(n);
		int[] ok = new int[pow + 1];
		fill(ok, -1);
		for (int i = 1; i <= n; i++) {
			int t = i, powHere = 0;
			while (t % p == 0) {
				++powHere;
				t /= p;
			}
			if (ok[powHere] < 0) {
				ok[powHere] = stup[i % n];
			} else {
				if (ok[powHere] != stup[i % n]) {
					System.err.println("pow here: " + powHere);
					System.err.println(ok[powHere] + " != " + stup[i % n]);
					System.err.println("i = " + i);
					throw new AssertionError();
				}
			}
		}
		return ok;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}