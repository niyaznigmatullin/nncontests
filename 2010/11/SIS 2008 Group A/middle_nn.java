import java.io.*;
import java.util.*;
import java.math.*;

public class middle_nn implements Runnable {
	public static void main(String[] args) {
		new middle_nn().run();
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

		BigInteger nextBigInteger() {
			return new BigInteger(nextToken());
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
			sc = new FastScanner("middle.in");
			out = new PrintWriter("middle.out");
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

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	class SegmentTree {
		long[] a;
		long[] b;
		int n;

		public SegmentTree(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			a = new long[this.n << 1];
			b = new long[this.n << 1];
			Arrays.fill(b, Long.MIN_VALUE);
		}

		int size(int node) {
			int d = Integer.numberOfTrailingZeros(Integer
					.highestOneBit(node + 1));
			return n >> d;
		}

		void set(int node, long x) {
			if (node >= a.length) {
				return;
			}
			b[node] = x;
		}

		void relax(int node) {
			if (b[node] == Long.MIN_VALUE) {
				return;
			}
			a[node] = b[node] * size(node);
			set(node * 2 + 1, b[node]);
			set(node * 2 + 2, b[node]);
			b[node] = Long.MIN_VALUE;
		}

		long getA(int node) {
			return node >= a.length ? 0 : b[node] == Long.MIN_VALUE ? a[node]
					: b[node] * size(node);
		}

		void update(int node, int l, int r, int left, int right, long x) {
			if (right <= l || r <= left) {
				return;
			}
			if (left <= l && r <= right) {
				set(node, x);
				return;
			}
			relax(node);
			int m = (l + r) >> 1;
			update(node * 2 + 1, l, m, left, right, x);
			update(node * 2 + 2, m, r, left, right, x);
			a[node] = getA(node * 2 + 1) + getA(node * 2 + 2);
		}

		long getSum(int node, int l, int r, int left, int right) {
			if (right <= l || r <= left) {
				return 0;
			}
			if (left <= l && r <= right) {
				return getA(node);
			}
			relax(node);
			int m = (l + r) >> 1;
			return getSum(node * 2 + 1, l, m, left, right)
					+ getSum(node * 2 + 2, m, r, left, right);
		}

		long getSum(int l, int r) {
			if (l > r) {
				return 0;
			}
			return getSum(0, 0, n, l, r);
		}

		void update(int l, int r, long x) {
			if (l > r) {
				return;
			}
			update(0, 0, n, l, r, x);
		}
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		SegmentTree tree1 = new SegmentTree(n);
		SegmentTree tree2 = new SegmentTree(n);
		for (int i = 0; i < n; i++) {
			int x = nextInt();
			tree1.update(i, i + 1, x);
			tree2.update(i, i + 1, x);
		}
		for (int i = 0; i < m; i++) {
			int l = nextInt() - 1;
			int r = nextInt();
			long sumInitial = tree1.getSum(0, n);
			long sumCurrent = tree2.getSum(0, n);
			long sum = tree2.getSum(l, r);
			if (sumInitial < sumCurrent) {
				tree2.update(l, r, Math.round(Math.floor(1. * sum / (r - l))));
			} else {
				tree2.update(l, r, Math.round(Math.ceil(1. * sum / (r - l))));
			}
		}
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				out.print(" ");
			}
			out.print(tree2.getSum(i, i + 1));
		}
		out.println();
	}
}