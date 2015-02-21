import java.io.*;
import java.util.*;

public class J {

	static class Pair {
		int a;
		int b;

		public Pair(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

	}

	static class ConvexHull {
		long[] a;
		long[] b;
		int n;

		ConvexHull(int n) {
			a = new long[n];
			b = new long[n];
		}

		void add(long na, long nb) {
			// if (n > 0 && na <= a[n - 1])
			// throw new AssertionError();
			while (n > 1) {
				long num = b[n - 1] - nb;
				long den = na - a[n - 1];
				if (den < 0) {
					den = -den;
				}
				if (a[n - 2] * num + den * b[n - 2] >= a[n - 1] * num + den
						* b[n - 1]) {
					--n;
				} else {
					break;
				}
			}
			a[n] = na;
			b[n] = nb;
			++n;
		}

		long get(long x) {
			if (n == 0)
				throw new AssertionError();
			int l = -1;
			int r = n - 1;
			while (l < r - 1) {
				int mid = l + r >> 1;
				if (a[mid] * x + b[mid] < a[mid + 1] * x + b[mid + 1]) {
					l = mid;
				} else {
					r = mid;
				}
			}
			return a[r] * x + b[r];
		}

		void remove(long oa) {
			if (a[n - 1] != oa)
				return;
			--n;
		}
	}

	static void solve() throws IOException {
		int n = nextInt();
		Pair[] a = new Pair[n];
		for (int i = 0; i < n; i++) {
			a[i] = new Pair(nextInt(), nextInt());
		}
		Arrays.sort(a, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.a < o2.a ? -1 : o1.a > o2.a ? 1 : 0;
			}
		});
		Pair[] stack = new Pair[n + 1];
		long[] dp = new long[n + 1];
		int cur = 1;
		stack[0] = new Pair(0, Integer.MAX_VALUE);
		ConvexHull hull = new ConvexHull(n + 1);
		for (Pair e : a) {
			while (cur > 0 && stack[cur - 1].b <= e.b) {
				--cur;
				hull.remove(-stack[cur].b);
			}
			stack[cur++] = e;
			hull.add(-stack[cur - 1].b, dp[cur - 2]);
			dp[cur - 1] = hull.get(stack[cur - 1].a);
		}
		out.println(-dp[cur - 1]);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("j.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("j.out");
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
