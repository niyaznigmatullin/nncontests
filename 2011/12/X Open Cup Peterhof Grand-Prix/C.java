import static java.lang.Math.min;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.binarySearch;

import java.io.*;
import java.util.*;

public class C {

	static int[] p(int max) {
		max++;
		boolean[] notp = new boolean[max];
		for (int i = 2; i * i < max; i++) {
			if (notp[i]) {
				continue;
			}
			for (int j = i * i; j < max; j += i) {
				notp[j] = true;
			}
		}
		int cnt = 0;
		for (int i = 2; i < max; i++) {
			if (!notp[i]) {
				cnt++;
			}
		}
		int[] p = new int[cnt];
		cnt = 0;
		for (int i = 2; i < max; i++) {
			if (!notp[i]) {
				p[cnt++] = i;
			}
		}
		return p;
	}

	static int b;
	static int[] p;
	static final int CACHE = 1000000;
	static int[][] cached = new int[25][CACHE];
	static int maxSmoothness;
	static long[] smoothNumbers;

	static long get(int smooth, long n) {
		if (smooth < 0) {
			return 1;
		}
		// if (smooth == 0) {
		// return 64 - Long.numberOfLeadingZeros(n);
		// }
		if (smooth == maxSmoothness) {
			int ok = binarySearch(smoothNumbers, n);
			if (ok < 0) {
				ok = ~ok;
			} else {
				ok++;
			}
			return ok;
		}
		if (n < CACHE) {
			int ok = cached[smooth][(int) n];
			if (ok != 0) {
				return ok;
			}
		}
		long n0 = n;
		long res = 0;
		while (n > 0) {
			res += get(smooth - 1, n);
			n /= p[smooth];
		}
		if (n0 < CACHE) {
			cached[smooth][(int) n0] = (int) res;
		}
		return res;
	}

	static long[] generateAllSmooth(int smoothness, long limit) {
		if (smoothness < 0) {
			return new long[] { 1 };
		}
		List<Long> list = new ArrayList<Long>();
		long mul = 1;
		while (limit > 0) {
			for (long val : generateAllSmooth(smoothness - 1, limit)) {
				list.add(val * mul);
			}
			mul *= p[smoothness];
			limit /= p[smoothness];
		}
		long[] ret = new long[list.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}

	private static void solve() throws IOException {
		// long time = currentTimeMillis();
		long n = nextLong();
		b = nextInt();
		p = p(b);
		maxSmoothness = min(5, p.length - 1);
		smoothNumbers = generateAllSmooth(maxSmoothness, n);
		Arrays.sort(smoothNumbers);
		// System.err.println(smoothNumbers.length);
		long res = get(p.length - 1, n);
		out.println(res);
		// System.err.println(currentTimeMillis() - time);
	}

	public static void main(String[] args) {
		try {
			br = new BufferedReader(new FileReader("bsmooth.in"));
			out = new PrintWriter("bsmooth.out");
			solve();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(239);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null)
				return null;
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