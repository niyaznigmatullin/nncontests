import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class H {

	static boolean[] was;
	static List<Integer>[] edges;
	static int[] p;

	static boolean dfs(int v) {
		if (was[v]) {
			return false;
		}
		was[v] = true;
		for (int i : edges[v]) {
			if (p[i] < 0 || dfs(p[i])) {
				p[i] = v;
				return true;
			}
		}
		return false;
	}

	static boolean canDo(int h, int w, int h0, int w0) {
		if (h0 <= h && w0 <= w) {
			return true;
		}
		if (h0 >= h) {
			return false;
		}
		double d = sqrt(h0 * h0 + w0 * w0);
		double l = sqrt(d * d - h * h);
		double alpha = atan(h / l);
		double beta = atan(1. * h0 / w0);
		double gamma = alpha - beta;
		double width = h0 * sin(gamma) + w0 * cos(gamma);
		if (width <= w + 1e-9) {
			return true;
		}
		return false;
	}

	static void solve() throws Exception {
		int n = nextInt();
		int m = nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			b[i] = nextInt();
			if (a[i] > b[i]) {
				int t = a[i];
				a[i] = b[i];
				b[i] = t;
			}
		}
		edges = new List[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int c = nextInt();
			int d = nextInt();
			if (c > d) {
				int t = c;
				c = d;
				d = t;
			}
			for (int j = 0; j < n; j++) {
				if (canDo(a[j], b[j], c, d)) {
					edges[j].add(i);
				}
			}
		}
		was = new boolean[n];
		p = new int[m];
		fill(p, -1);
		int ans = 0;
		for (int i = 0; i < n; i++) {
			fill(was, false);
			if (dfs(i)) {
				ans++;
			}
		}
		out.println(ans);
		for (int i = 0; i < m; i++) {
			if (p[i] >= 0) {
				out.println(i + 1 + " " + (p[i] + 1));
			}
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
			File file = new File("h.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("h.out"));
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