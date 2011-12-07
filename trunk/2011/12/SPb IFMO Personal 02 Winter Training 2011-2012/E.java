import static java.lang.System.exit;

import java.io.*;
import java.util.*;

public class E {

	static void solve() throws Exception {
		int n = nextInt();
		int[] a = new int[n + 1];
		int[] sign = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			sign[i] = next().equals("WITH") ? 1 : -1;
		}
		a[n] = nextInt();
		for (int i = n - 1; i >= 0; i--) {
			a[i] = a[i] + sign[i] * a[i + 1];
		}
		out.println(a[0]);
	}

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
