import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class G {

	static void solve() throws IOException {
		int tc = nextInt();
		for (int i = 1; i <= tc; i++) {
			nextToken();
			String from = nextToken() + nextToken();
			String to = nextToken();
			BigInteger res = moves(from, to);
			out.print(i + " ");
			if (res == null) {
				out.println(-1);
			} else {
				out.println(res);
			}
		}
	}

	static BigInteger moves(String from, String to) {
		int n = from.length();
		int[] p = new int[n];
		for (int i = 0; i < n / 2; i++) {
			p[i] = 2 * i + 1;
		}
		for (int i = n / 2; i < n; i++) {
			p[i] = 2 * i - n;
		}
		boolean[] visited = new boolean[n];
		BigInteger mod = ONE;
		BigInteger r = ZERO;
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				continue;
			}
			StringBuilder u = new StringBuilder();
			StringBuilder v = new StringBuilder();
			for (int j = i; !visited[j]; j = p[j]) {
				visited[j] = true;
				u.append(from.charAt(j));
				v.append(to.charAt(j));
			}
			int curMod = getPeriod(u.toString());
			int curR = getShift(u.toString(), v.toString());
			if (curR < 0) {
				return null;
			}
			if (curR >= curMod) {
				throw new AssertionError();
			}
			BigInteger newR = get(mod, r, valueOf(curMod), valueOf(curR));
			if (newR == null) {
				return null;
			}
			BigInteger newMod = mod.divide(mod.gcd(valueOf(curMod))).multiply(
					valueOf(curMod));
			mod = newMod;
			r = newR;
		}
		return r;
	}

	static BigInteger get(BigInteger m1, BigInteger r1, BigInteger m2,
			BigInteger r2) {
		BigInteger d = m1.gcd(m2);
		BigInteger r2r1 = r2.subtract(r1);
		if (r2r1.mod(d).signum() != 0) {
			return null;
		}
		BigInteger k = m1.divide(d).modInverse(m2.divide(d));
		k = k.multiply(r2r1.divide(d)).mod(m2.divide(d));
		return m1.multiply(k).add(r1);
	}

	static int getShift(String from, String to) {
		shift: for (int shift = 0; shift < from.length(); shift++) {
			for (int i = 0; i < from.length(); i++) {
				if (from.charAt(i) != to.charAt((i + shift) % from.length())) {
					continue shift;
				}
			}
			return shift;
		}
		return -1;
	}

	static int getPeriod(String s) {
		fori: for (int i = 1; i < s.length(); i++) {
			if (s.length() % i != 0) {
				continue;
			}
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) != s.charAt((i + j) % s.length())) {
					continue fori;
				}
			}
			return i;
		}
		return s.length();

	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("shuffle.in"));
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