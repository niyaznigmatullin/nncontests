import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class O extends Thread {
	static final Random rand = new Random(12839123L);
	static boolean[] marked = new boolean[20000];
	static int[] got = new int[100];
	static int[] a = new int[100];

	static String go(int x, int k) {
		if (x == k) {
			StringBuilder sb = new StringBuilder();
			sb.append('1');
			for (int i = 0; i + 1 < k; i++) {
				for (int j = 0; j < got[i]; j++) {
					sb.append('0');
				}
				sb.append('1');
			}
			return sb.toString();
		}
		all: for (int i = 1; i <= 4 * k; i++) {
			a[x] = a[x - 1] + i + 1;
			got[x - 1] = i;
			for (int j = 0; j < x; j++) {
				if (marked[a[x] - a[j]]) {
					continue all;
				}
			}
			for (int j = 0; j < x; j++) {
				marked[a[x] - a[j]] = true;
			}
			String got = go(x + 1, k);
			if (got != null) {
				return got;
			}
			for (int j = 0; j < x; j++) {
				marked[a[x] - a[j]] = false;
			}
		}
		return null;
	}

	static void solve() throws Exception {
		int k = nextInt();
		String got = go(1, k);
		BigInteger a = new BigInteger(got, k);
		BigInteger b = new BigInteger(rev(got), k);
		String output = got + "*" + rev(got) + "=" + a.multiply(b).toString(k);
		out.println(output);
	}

	static void solve2() throws Exception {
		go(1, 36);
		// List<String> list = new ArrayList<String>();
		// int k = 7;
		// int[] ans = new int[100];
		// all: for (int it = 10000001; it < 100000000; it += 2) {
		// if (Integer.bitCount(it) == k) {
		// String e = Integer.toBinaryString(it);
		// String f = rev(e);
		// {
		// int count = e.length() + f.length() + 1;
		// fill(ans, 0, count, 0);
		// for (int i = 0; i < e.length(); i++) {
		// if (e.charAt(i) == '0') {
		// continue;
		// }
		// for (int j = 0; j < f.length(); j++) {
		// if (f.charAt(j) == '0') {
		// continue;
		// }
		// ans[i + j]++;
		// }
		// }
		// boolean found = false;
		// for (int i = 0; i < count; i++) {
		// if (ans[i] > 1) {
		// found = true;
		// break;
		// }
		// }
		// if (!found) {
		// continue;
		// }
		// for (int i = 0; i < count - 1; i++) {
		// ans[i + 1] += ans[i] / k;
		// ans[i] %= k;
		// }
		// for (int i = 0; i < count; i++) {
		// if (ans[i] > 1) {
		// continue all;
		// }
		// }
		// System.err.println(e + "*" + f + "=");
		// }
		// }
		// }
		// int k = nextInt();
		// int[] a = new int[k];
		// int[] b = new int[k];
		// int[] p = new int[200000];
		// int[] c = new int[k - 1];
		// int[] d = new int[k - 1];
		// int q = 0;
		// all: while (true) {
		// if ((++q & 1023) == 0) {
		// System.err.println(q);
		// }
		// // all: for (int it = 0; it < 10000; it++) {
		// for (int i = 0; i < c.length; i++) {
		// c[i] = rand.nextInt(k * k / 10 * 7) + 1;
		// }
		// // c[rand.nextInt(k - 1)] = 1;
		// // c[1] = 5;
		// // c[2] = 3;
		// // c[3] = 4;
		// // c[4] = 2;
		// for (int i = 0; i < d.length; i++) {
		// // d[i] = rand.nextInt(k * k * 4 / 3) + 1;
		// d[i] = c[c.length - i - 1];
		// }
		// b[0] = a[0] = 0;
		// for (int i = 0; i < c.length; i++) {
		// a[i + 1] = a[i] + c[i] + 1;
		// b[i + 1] = b[i] + d[i] + 1;
		// }
		// int len = a[a.length - 1] + b[b.length - 1] + 2;
		// boolean good = false;
		// for (int i = 0; i < k; i++) {
		// for (int j = 0; j < k; j++) {
		// int s = a[i] + b[j];
		// p[s]++;
		// if (p[s] == k) {
		// good = true;
		// p[s + 1]++;
		// p[s] = 0;
		// }
		// }
		// }
		// if (!good) {
		// for (int i = 0; i < k; i++) {
		// for (int j = 0; j < k; j++) {
		// int s = a[i] + b[j];
		// p[s] = 0;
		// p[s + 1] = 0;
		// }
		// }
		// continue;
		// }
		// boolean bad = false;
		// for (int i = 0; i < k; i++) {
		// for (int j = 0; j < k; j++) {
		// int s = a[i] + b[j];
		// if (p[s] > 1 || p[s + 1] > 1) {
		// bad = true;
		// }
		// }
		// }
		// if (bad) {
		// for (int i = 0; i < k; i++) {
		// for (int j = 0; j < k; j++) {
		// int s = a[i] + b[j];
		// if (p[s] > 1 || p[s + 1] > 1) {
		// bad = true;
		// }
		// p[s] = 0;
		// p[s + 1] = 0;
		// }
		// }
		//
		// continue all;
		// }
		// StringBuilder sb = new StringBuilder();
		// sb.append('1');
		// for (int i = 0; i < c.length; i++) {
		// for (int j = 0; j < c[i]; j++) {
		// sb.append('0');
		// }
		// sb.append('1');
		// }
		// sb.append('*');
		// sb.append('1');
		// for (int i = 0; i < d.length; i++) {
		// for (int j = 0; j < d[i]; j++) {
		// sb.append('0');
		// }
		// sb.append('1');
		// }
		// sb.append('=');
		// boolean first = true;
		// for (int i = len - 1; i >= 0; i--) {
		// if (first) {
		// if (p[i] == 0) {
		// continue;
		// }
		// sb.append(p[i]);
		// first = false;
		// } else {
		// sb.append(p[i]);
		// }
		// }
		// // System.err.println(sb);
		// // check(sb.toString(), k);
		// out.println(sb);
		// return;
		// }
		// String s = "1";
		// for (int i = 1; i < k; i++) {
		// for (int j = 0; j < i; j++) {
		// s += "0";
		// }
		// s += "1";
		// }
		// String s = "1010010001000001000000001";
		// String t = rev(s);
		// BigInteger a = new BigInteger(s, k);
		// BigInteger b = new BigInteger(t, k);
		// String ans = a.multiply(b).toString(k);
		// out.println(s + "*" + t + "=" + ans);
	}

	static boolean check(String s, int k) {
		StringTokenizer st = new StringTokenizer(s, "*=");
		for (int i = 2; i < 10; i++) {
			if (s.indexOf(i + '0') >= 0) {
				throw new AssertionError();
			}
		}
		BigInteger a = new BigInteger(st.nextToken(), k);
		BigInteger b = new BigInteger(st.nextToken(), k);
		BigInteger c = new BigInteger(st.nextToken(), k);
		return a.multiply(b).equals(c);
	}

	static String rev(String s) {
		return new StringBuilder(s).reverse().toString();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) {
		new Thread(null, new O(), "", 1 << 24).start();
	}

	public void run() {
		try {
			long time = System.currentTimeMillis();
			File file = new File("n.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("n.out"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
			br.close();
			System.err.println("Runtime = "
					+ (System.currentTimeMillis() - time) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}