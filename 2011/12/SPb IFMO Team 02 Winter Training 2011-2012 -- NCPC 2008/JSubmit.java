import static java.lang.Math.min;

import java.io.*;
import java.util.*;

public class JSubmit {

	static void solve() throws IOException {
		// stress();
		int n = nextInt();
		out.println(getSmart(n));
	}

//	static void stress() {
//		for (int n = 2; n < 1000; n++) {
//			long a = getSmart(n);
//			long b = getStupid(n);
//			if (a != b) {
//				System.err.println(n + " " + a + " " + b);
//				throw new AssertionError();
//			}
//			if (n % 10 == 0) {
//				System.err.println(" ok " + n);
//			}
//		}
//	}

	static long getSmart(int n) {
		Factor[] factors = factor(n);
		long ok = getAllTriples(n, factors);
		ok -= getTriplesWhereAequalsZeroOrBEqualsZero(n);
		long ok1 = getTriplesWhereAequalsB(n, factors);
		long res = (ok + ok1) / 2;
		return res;
	}

	// static long getStupid(int n) {
	// long res = 0;
	// for (int a = 1; a < n; a++) {
	// for (int b = a; b < n; b++) {
	// for (int c = 1; c < n; c++) {
	// if ((a * a + b * b) % n == c * c % n) {
	// res++;
	// }
	// }
	// }
	// }
	// return res;
	// }

	// static long getAllTriplesStupid(int n) {
	// long res = 0;
	// for (int a = 0; a < n; a++) {
	// for (int b = 0; b < n; b++) {
	// for (int c = 1; c < n; c++) {
	//
	// if ((a * a + b * b) % n == c * c % n) {
	// res++;
	// }
	// }
	// }
	// }
	// return res;
	// }

	static long getAllTriples(int n, Factor[] factors) {
		int[][] ways = new int[factors.length][];
		for (int i = 0; i < factors.length; i++) {
			ways[i] = factors[i].p == 2 ? getTwoSquaresFor2(factors[i].pow)
					: getTwoSquares(factors[i].p, factors[i].pow);
		}
		long result = 0;
		for (int c = 1; c < n; c++) {
			int ok = (int) ((long) c * c % n);
			if (ok == 0) {
				ok = n;
			}
			long waysGetOk = 1;
			for (int i = 0; i < factors.length; i++) {
				if (factors[i].p > 2) {
					int curPow = 0;
					while (ok % factors[i].p == 0) {
						curPow++;
						ok /= factors[i].p;
					}
					curPow = min(curPow, factors[i].pow);
					waysGetOk *= ways[i][curPow];
				} else {
					int curPow = Integer.numberOfTrailingZeros(ok);
					ok >>= curPow;
					int bit = (ok & 3) >>> 1;
					int index = curPow * 2 + bit;
					if (curPow >= factors[i].pow) {
						index = 2 * factors[i].pow;
					}
					if (curPow == factors[i].pow - 1) {
						index = 2 * curPow;
					}
					waysGetOk *= ways[i][index];
				}
			}
			result += waysGetOk;
		}
		return result;

		// for (Factor factor : factors) {
		// int[] waysAB = getTwoSquares(factor.p, factor.pow);
		// int[] waysC = getOneSquare(factor.p, factor.pow);
		// long waysHere = 0;
		// for (int i = 0; i < waysAB.length; i++) {
		// for (int j = 0; j < waysC.length; j++) {
		// waysHere += waysAB[i]
		// }
		// }
		// }
	}

	static long getTriplesWhereAequalsZeroOrBEqualsZero(int n) {
		int[] oneSquare = new int[n];
		for (int c = 1; c < n; c++) {
			int ok = (int) ((long) c * c % n);
			oneSquare[ok]++;
		}
		long result = 0;
		for (int a = 1; a < n; a++) {
			int ok = (int) (1L * a * a % n);
			result += oneSquare[ok];
		}
		return result * 2 + oneSquare[0];
	}

	// static long getTriplesStupidWhereAequalsZeroOrBEqualsZero(int n) {
	// long res = 0;
	// for (int a = 0; a < n; a++) {
	// for (int b = 0; b < n; b++) {
	// for (int c = 1; c < n; c++) {
	// if ((a == 0 || b == 0) && (a * a + b * b) % n == c * c % n) {
	// res++;
	// }
	// }
	//
	// }
	// }
	// return res;
	// }

	static long getTriplesWhereAequalsB(int n, Factor[] factors) {
		int[] oneSquare = new int[n];
		for (int c = 1; c < n; c++) {
			int ok = (int) ((long) c * c % n);
			oneSquare[ok]++;
		}
		long result = 0;
		for (int a = 1; a < n; a++) {
			int ok = (int) (2L * a * a % n);
			result += oneSquare[ok];
		}
		return result;
	}

	static class Factor {
		final int p, pow;

		private Factor(int p, int pow) {
			this.p = p;
			this.pow = pow;
		}
	}

	static Factor[] factor(int n) {
		List<Factor> res = new ArrayList<Factor>();
		for (int p = 2; p * p <= n; p++) {
			if (n % p == 0) {
				int pow = 1;
				n /= p;
				while (n % p == 0) {
					pow++;
					n /= p;
				}
				res.add(new Factor(p, pow));
			}
		}
		if (n > 1) {
			res.add(new Factor(n, 1));
		}
		return (Factor[]) res.toArray(new Factor[res.size()]);
	}

	static int[] getTwoSquares(int p, int pow) {
		int[] ok = new int[pow + 1];
		int number = 1;
		for (int i = 0; i < pow; i++) {
			number *= p;
		}
		if (p % 4 == 3) {
			for (int i = 0; i < pow; i++) {
				ok[i] = i % 2 == 0 ? number + number / p : 0;
			}
			ok[pow] = pow % 2 == 0 ? number : number / p;
		} else {
			ok[pow] = number / p * (p * (pow + 1) - pow);
			for (int i = 0; i < pow; i++) {
				ok[i] = (i + 1) * (number - number / p);
			}
		}
		return ok;
	}

	static int[] getTwoSquaresFor2(int pow) {
		int[] ok = new int[2 * pow + 1];
		ok[2 * pow] = ok[2 * pow - 2] = 1 << pow;
		for (int i = 0; i < pow - 1; i++) {
			ok[2 * i] = 1 << pow + 1;
		}
		return ok;
	}

	// static int[] getOneSquare(int p, int pow) {
	// int[] ok = new int[pow + 1];
	// return null;
	// }

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