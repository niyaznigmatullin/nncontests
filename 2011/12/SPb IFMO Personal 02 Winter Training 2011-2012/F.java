import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.exit;

import java.io.*;
import java.util.*;

public class F {

	static void solve() throws Exception {
		int t = nextInt();
		int h = nextInt();
		int v = nextInt();
		int a = t + h + v;
		if ((a & 1) != 0) {
			while (true)
				;
		}
		a /= 2;
		int minR = max(h - MAX, -MAX);
		int maxR = min(h + MAX, MAX);
		int minU = max(v - MAX, -MAX);
		int maxU = min(v + MAX, MAX);
		maxR = min(maxR, a - minU);
		maxU = min(maxU, a - minR);
		minR = max(minR, a - maxU);
		minU = max(minU, a - maxR);		
		int fr = maxR;
		int fu = a - maxR;
		int fl = fr - h;
		int fd = fu - v;
		if (fr + fu + fl + fd != t) {
			throw new AssertionError();
		}
		if (fr - fl != h) {
			throw new AssertionError();
		}
		if (fu - fd != v) {
			throw new AssertionError();
		}
		out.println(fl + " " + fr + " " + fu + " " + fd);
	}

	static final int MAX = 100000000;

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
			}
			st = new StringTokenizer(s);
		}
		return st.nextToken();
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
			}
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			br.close();
			out.close();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			List<Void> list = new ArrayList<Void>();
			while (true) {
				list.add(null);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}
