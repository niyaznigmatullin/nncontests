import java.io.*;
import java.util.*;

public class D implements Runnable {
	public static void main(String[] args) {
		new D().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	class E {
		long min, sum;

	}

	class S2 {
		E[] a;
		final int maxN = 1023;

		public S2() {
			a = new E[maxN * 2];
		}

		void update(int l, int r, int val) {
			l += maxN;
			a[l].min = val;
			while (l > 0) {
				a[l].sum += val;
				l >>= 1;
				a[l].min = Math.min(a[l * 2].min, a[l * 2 + 1].min);
			}
		}

		long getMin(int l, int r) {
			l += maxN;
			r += maxN;
			long ret = Integer.MAX_VALUE;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret = Math.min(ret, a[l++].min);
				}
				if ((r & 1) == 0) {
					ret = Math.min(ret, a[r--].min);
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}

		long getSum(int l, int r) {
			l += maxN;
			r += maxN;
			long ret = 0;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret += a[l++].sum;
				}
				if ((r & 1) == 0) {
					ret += a[r--].sum;
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	class S1 {
		S2[] a;
		final int maxN = 1023;

		public S1() {
			a = new S2[maxN * 2];
		}

		void update(int node, int l1, int r1, int l2, int r2, int x1, int y1,
				int x2, int y2, int val) {
			if (x1 <= l1 && r1 <= x2) {
				a[node].update(y1, y2, val);
				return;
			}
			if (x1 > r1 || l1 > x2) {
				return;
			}
			update(node * 2, l1, (l1 + r1) / 2, l2, r2, x1, y1, x2, y2, val);
			update(node * 2 + 1, (l1 + r1) / 2 + 1, r1, l2, r2, x1, y1, x2, y2,
					val);
		}

		void update(int x1, int y1, int x2, int y2, int val) {
			update(1, 1, maxN, 1, maxN, x1, y1, x2, y2, val);
		}

		long getMin(int l, int r, int l2, int r2) {
			l += maxN;
			r += maxN;
			long ret = Integer.MAX_VALUE;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret = Math.min(ret, a[l++].getMin(l2, r2));
				}
				if ((r & 1) == 0) {
					ret = Math.min(ret, a[r--].getMin(l2, r2));
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}

		long getSum(int l, int r, int l2, int r2) {
			l += maxN;
			r += maxN;
			long ret = 0;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret += a[l++].getSum(l2, r2);
				}
				if ((r & 1) == 0) {
					ret += a[r--].getSum(l2, r2);
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int a = nextInt();
		int b = nextInt();
		S1 t = new S1();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				t.update(i, j, i, j, nextInt());
			}
		}
		long ans = Long.MAX_VALUE;
		for (int i = a - 1; i < n; i++) {
			for (int j = b - 1; j < m; j++) {
				ans = Math.min(ans, t.getSum(i - a + 1, i, j - b + 1, j)
						- t.getMin(i - a + 1, i, j - b + 1, j) * a * b);
			}
		}
		out.println(ans);
	}
}