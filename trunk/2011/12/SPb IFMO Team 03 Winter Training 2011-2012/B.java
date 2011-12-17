import static java.math.BigInteger.valueOf;

import java.io.*;
import java.util.*;

public class B {

	static void solve() throws IOException {
		int tc = nextInt();
		while (tc-- > 0) {
			String s = nextToken();
			int length = s.length();
			long number = Long.parseLong(s);
			long result = get(number, length);
			out.println(result);
		}
	}

	static long get(long a, int length) {
		long answer = 0;
		long tenPow = 1;
		for (int i = 0; i < length; i++) {
			long nextTenPow = tenPow * 10;
			for (int digit = 0;; digit++) {
				if (digit >= 10) {
					throw new AssertionError();
				}
				long ok = square(digit * tenPow + answer, nextTenPow);
				if (ok % nextTenPow == a % nextTenPow) {
					answer = answer + digit * tenPow;
					break;
				}
			}
			tenPow = nextTenPow;
		}
		return answer;
	}

	static long square(long number, long tenPow) {
		return valueOf(number).multiply(valueOf(number))
				.multiply(valueOf(number)).mod(valueOf(tenPow)).longValue();
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("cubic.in"));
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