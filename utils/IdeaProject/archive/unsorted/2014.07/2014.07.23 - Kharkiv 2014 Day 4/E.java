import java.io.*;
import java.util.*;

public class E {

	static final int MOD = 1000000007;

	static int mul(int a, int b) {
		return (int) ((long) a * b % MOD);
	}

	static int add(int a, int b) {
		a += b;
		if (a >= MOD) {
			a -= MOD;
		}
		return a;
	}

	static void mul(int[] ans, int[] a, int[] b) {
		for (int i = 0; i < 4; i++) {
			ans[i] = add(mul(a[i & 2], b[i & 1]),
					mul(a[(i & 2) + 1], b[(i & 1) + 2]));
		}
	}

	static int[] tmp = new int[4];

	static void mulRight(int[] ans, int[] b) {
		mul(tmp, ans, b);
		for (int i = 0; i < 4; i++) {
			ans[i] = tmp[i];
		}
	}

	static void mulLeft(int[] ans, int[] b) {
		mul(tmp, b, ans);
		for (int i = 0; i < 4; i++) {
			ans[i] = tmp[i];
		}
	}

	static void fill1(int[] a) {
		a[0] = a[3] = 1;
		a[2] = a[1] = 0;
	}

	static class SegmentTree {
		int[][] a, b;
		int n;

		public SegmentTree(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			a = new int[this.n * 2][4];
			b = new int[this.n * 2][4];
		}

		void set(int x, int[] y) {
			x += n;
			for (int i = 0; i < 4; i++) {
				a[x][i] = b[x][i] = y[i];
			}
			while (x > 1) {
				x >>= 1;
				mul(a[x], a[2 * x], a[2 * x + 1]);
				mul(b[x], b[2 * x + 1], b[2 * x]);
			}
		}

		static int[] tLeft = new int[4];
		static int[] tRight = new int[4];

		void getA(int l, int r, int[] ans) {
			--r;
			l += n;
			r += n;
			fill1(tLeft);
			fill1(tRight);
			while (l <= r) {
				if ((l & 1) == 1) {
					mulRight(tLeft, a[l++]);
				}
				if ((r & 1) == 0) {
					mulLeft(tRight, a[r--]);
				}
				l >>= 1;
				r >>= 1;
			}
			mul(ans, tLeft, tRight);
		}

		void getB(int l, int r, int[] ans) {
			--r;
			l += n;
			r += n;
			fill1(tLeft);
			fill1(tRight);
			while (l <= r) {
				if ((l & 1) == 1) {
					mulLeft(tLeft, b[l++]);
				}
				if ((r & 1) == 0) {
					mulRight(tRight, b[r--]);
				}
				l >>= 1;
				r >>= 1;
			}
			mul(ans, tRight, tLeft);
		}

	}

	static void solve() throws IOException {
		int n = nextInt();
		int q = nextInt();
		SegmentTree tree = new SegmentTree(n);
		int[] cur = new int[4];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 4; j++) {
				cur[j] = nextInt();
			}
			tree.set(i, cur);
		}
		int[] ans = new int[4];
		for (int i = 0; i < q; i++) {
			int k = nextInt();
			int v = nextInt();
			int l = nextInt() - 1;
			int r = nextInt() - 1;
			if (l <= r) {
				tree.getB(l, r + 1, ans);
			} else {
				tree.getA(r, l + 1, ans);
			}
			cur[0] = k;
			cur[2] = v;
			cur[1] = cur[3] = 0;
			mulLeft(cur, ans);
			out.println(cur[0] + " " + cur[2]);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("e.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("e.out");
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
