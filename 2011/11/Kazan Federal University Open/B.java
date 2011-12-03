import static java.math.BigInteger.ZERO;
import static java.util.Arrays.deepToString;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class B {

	static void solve() throws Exception {
		BigInteger a1 = new BigInteger(next());
		BigInteger b1 = new BigInteger(next());
		BigInteger c1 = new BigInteger(next());
		BigInteger a2 = new BigInteger(next());
		BigInteger b2 = new BigInteger(next());
		BigInteger c2 = new BigInteger(next());
		BigInteger x = a1.multiply(c1).negate();
		BigInteger y = b1.multiply(c1).negate();
		BigInteger d = a1.pow(2).add(b1.pow(2));
		if (a1.multiply(b2).subtract(a2.multiply(b1)).equals(ZERO)
				&& !a2.multiply(x).add(b2.multiply(y)).add(c2.multiply(d))
						.equals(ZERO)) {
			out.println("NO");
		} else {
			out.println("YES");
		}
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
		try {
			long time = System.currentTimeMillis();
			File file = new File("b.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("b.out"));
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