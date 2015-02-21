import static java.math.BigInteger.valueOf;

import java.io.*;
import java.util.*;

public class M {

	static void solve() throws IOException {
		int n = nextInt();
		int k = nextInt();
		long[] x = new long[n + 1];
		for (int i = 0; i < n; i++) {
			x[i + 1] = nextInt();
		}
		long[] y = new long[n + 1];
		for (int i = 0; i < n; i++) {
			y[i + 1] = nextInt();
		}
		for (int i = 1; i <= n; i++) {
			x[i] *= y[i];
			x[i] += x[i - 1];
			y[i] += y[i - 1];
		}
		// System.err.println(Arrays.toString(x));
		// System.err.println(Arrays.toString(y));
		int[] hull = new int[n + 1];
		int cn = 0;
		long num = Integer.MIN_VALUE;
		long den = 1;
		int ansL = -1;
		int ansR = -1;
		for (int i = k; i <= n; i++) {
			int add = i - k;
			while (cn > 1
					&& vmulFromPoint(x[add], y[add], x[hull[cn - 1]],
							y[hull[cn - 1]], x[hull[cn - 2]], y[hull[cn - 2]]) <= 0) {
				--cn;
			}
			hull[cn++] = add;
			int l = -1;
			int r = cn - 1;
			while (l < r - 1) {
				int mid = l + r >> 1;
				if (vmulFromPoint(x[i], y[i], x[hull[mid + 1]],
						y[hull[mid + 1]], x[hull[mid]], y[hull[mid]]) <= 0) {
					r = mid;
				} else {
					l = mid;
				}
			}
			long curNum = x[i] - x[hull[r]];
			long curDen = y[i] - y[hull[r]];
			if (detSign(curNum, curDen, num, den) > 0) {
				num = curNum;
				den = curDen;
				ansL = hull[r] + 1;
				ansR = i;
			}
		}
		out.println(ansL + " " + ansR);
	}

	static long vmulFromPoint(long xFrom, long yFrom, long x1, long y1,
			long x2, long y2) {
		x1 -= xFrom;
		y1 -= yFrom;
		x2 -= xFrom;
		y2 -= yFrom;
		return detSign(x1, y1, x2, y2);
	}

	private static int detSign(long x1, long y1, long x2, long y2) {
		return valueOf(x1).multiply(valueOf(y2)).compareTo(
				valueOf(y1).multiply(valueOf(x2)));
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("m.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("m.out");
		}
		br = new BufferedReader(new InputStreamReader(input));
		out = new PrintWriter(output);
		solve();
		out.close();
		br.close();
	}

	static boolean hasNext() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return false;
			}
			st = new StringTokenizer(line);
		}
		return true;
	}

	static String next() throws IOException {
		return hasNext() ? st.nextToken() : null;
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
