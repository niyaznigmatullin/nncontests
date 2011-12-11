import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;

public class B {

	static int[] parent;
	static int[] depth;
	static int[][] dp;
	static int[] size;
	static SegmentTree[] tree;
	static int[] count;
	static int[] treeParent;
	static int[] countEdges;

	void solve() {
		int n = nextInt();
		int m = nextInt();
		parent = new int[n];
		depth = new int[n];
		depth[0] = 0;
		dp = new int[Integer.numberOfTrailingZeros(Integer.highestOneBit(n)) + 2][n];
		for (int i = 1; i < n; i++) {
			parent[i] = nextInt() - 1;
			depth[i] = depth[parent[i]] + 1;
			dp[0][i] = parent[i];
			for (int j = 1; j < dp.length; j++) {
				dp[j][i] = dp[j - 1][dp[j - 1][i]];
			}
		}
		size = new int[n];
		fill(size, 1);
		for (int i = n - 1; i > 0; i--) {
			size[parent[i]] += size[i];
		}
		count = new int[n];
		tree = new SegmentTree[n];
		treeParent = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			if (i > 0 && size[parent[i]] <= 2 * size[i]) {
				count[parent[i]] = count[i] + 1;
			} else {
				tree[i] = new SegmentTree(count[i], depth[i]);
				treeParent[i] = i;
				tree[i].set(0, count[i], 1);
			}
		}
		countEdges = new int[n];
		fill(countEdges, 1);
		for (int i = 1; i < n; i++) {
			if (size[parent[i]] <= 2 * size[i]) {
				tree[i] = tree[parent[i]];
				treeParent[i] = treeParent[parent[i]];
			}
		}
		for (int i = 0; i < m; i++) {
			int k = nextInt();
			int[] from = new int[k];
			int[] to = new int[k];
			int removed = 0;
			for (int j = 0; j < k; j++) {
				from[j] = nextInt() - 1;
				to[j] = nextInt() - 1;
				int a = from[j];
				int b = to[j];
				if (a == b) {
					continue;
				}
				int lca = lca(a, b);
				if (lca == a || lca == b) {
					if (lca == a) {
						int t = a;
						a = b;
						b = t;
					}
					removed += setOnPath(a, b, 0);
				} else {
					removed += setOnPath(a, lca, 0);
					removed += setOnPath(b, lca, 0);
				}
			}
			out.println(n - 1 - removed);
			for (int j = 0; j < k; j++) {
				int a = from[j];
				int b = to[j];
				int lca = lca(a, b);
				if (lca == a || lca == b) {
					if (lca == a) {
						int t = a;
						a = b;
						b = t;
					}
					removed += setOnPath(a, b, 1);
				} else {
					removed += setOnPath(a, lca, 1);
					removed += setOnPath(b, lca, 1);
				}
			}
		}

	}

	static int setOnPath(int a, int b, int what) {
		int ret = 0;
		while (tree[a] != tree[b]) {
			ret += tree[a].set(getFor(a), getFor(treeParent[a]), what);
			a = treeParent[a];
			ret += countEdges[a];
			countEdges[a] = what;
			a = parent[a];
		}
		return tree[a].set(getFor(a), getFor(b), what) + ret;
	}

	static int getFor(int a) {
		return depth[treeParent[a]] + count[treeParent[a]] - depth[a];
	}

	static int lca(int a, int b) {
		if (depth[a] > depth[b]) {
			int t = a;
			a = b;
			b = t;
		}
		for (int i = dp.length - 1; i >= 0; i--) {
			if (depth[dp[i][b]] >= depth[a]) {
				b = dp[i][b];
			}
		}
		if (a == b) {
			return a;
		}
		for (int i = dp.length - 1; i >= 0; i--) {
			if (dp[i][a] != dp[i][b]) {
				a = dp[i][a];
				b = dp[i][b];
			}
		}
		return parent[a];
	}

	static class SegmentTree {
		private int[] sum;
		private int[] set;
		private boolean[] toSet;
		private int[] count;
		final int n;
		int depth;

		public SegmentTree(int n, int depth) {
			this.n = Integer.highestOneBit(n) << 1;
			sum = new int[this.n << 1];
			set = new int[this.n << 1];
			count = new int[this.n << 1];
			toSet = new boolean[this.n << 1];
			count(0, 0, this.n);
			this.depth = depth;
		}

		private int count(int node, int l, int r) {
			if (l >= r) {
				return 0;
			}
			if (l == r - 1) {
				count[node] = 1;
				return 1;
			}
			int m = (l + r) >> 1;
			return count[node] = count((node << 1) | 1, l, m)
					+ count((node << 1) + 2, m, r);
		}

		private void set(int node, int x) {
			set[node] = x;
			toSet[node] = true;
		}

		private void relax(int node) {
			if (!toSet[node]) {
				return;
			}
			sum[node] = getSum(node);
			if (toSet[node]) {
				set((node << 1) | 1, set[node]);
				set((node << 1) + 2, set[node]);
				toSet[node] = false;
			}
		}

		private int getSum(int node) {
			return toSet[node] ? set[node] * count(node) : sum[node];
		}

		private int count(int node) {
			return count[node];
		}

		private void set(int node, int l, int r, int left, int right, int x) {
			if (right <= l || r <= left) {
				return;
			}
			if (left <= l && r <= right) {
				set(node, x);
				return;
			}
			relax(node);
			int m = (l + r) >> 1;
			set((node << 1) | 1, l, m, left, right, x);
			set((node << 1) + 2, m, r, left, right, x);
			sum[node] = getSum((node << 1) | 1) + getSum((node << 1) + 2);
		}

		private int getSum(int node, int l, int r, int left, int right) {
			if (right <= l || r <= left) {
				return 0;
			}
			if (left <= l && r <= right) {
				return getSum(node);
			}
			relax(node);
			int m = (l + r) >> 1;
			return getSum((node << 1) | 1, l, m, left, right)
					+ getSum((node << 1) + 2, m, r, left, right);
		}

		public int set(int l, int r, int x) {
			if (l >= r) {
				return 0;
			}
			int ret = getSum(l, r);
			set(0, 0, n, l, r, x);
			return ret;
		}

		public int getSum(int l, int r) {
			if (l >= r) {
				return 0;
			}
			return getSum(0, 0, n, l, r);
		}

	}

	static InputReader sc;
	static PrintWriter out;

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	public void run() {
		try {
			InputStream input = System.in;
			OutputStream output = System.out;
			File inputFile = new File("bridges2.in");
			if (inputFile.canRead()) {
				input = (new FileInputStream(inputFile));
				output = (new FileOutputStream("bridges2.out"));
			}
			sc = new StreamInputReader(input);
			out = new PrintWriter(output);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	static double nextDouble() {
		String s = sc.next();
		if (s == null) {
			return Double.NaN;
		}
		return Double.parseDouble(s);
	}

	static long nextLong() {
		return sc.nextLong();
	}

	static int nextInt() {
		return sc.nextInt();
	}

	static abstract class InputReader {
		private boolean finished = false;

		public abstract int read();

		public long nextLong() {
			int c = read();
			while (c <= 32 && c >= 0) {
				c = read();
			}
			if (c == -1) {
				finished = true;
				return Long.MIN_VALUE;
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			if (c < '0' || c > '9') {
				throw new NumberFormatException("digit expected " + (char) c
						+ " found");
			}
			long ret = 0;
			while (c >= '0' && c <= '9') {
				ret = ret * 10 + c - '0';
				c = read();
			}
			if (c > 32) {
				throw new NumberFormatException("space character expected "
						+ (char) c + " found");
			}
			return ret * sgn;
		}

		public int nextInt() {
			int c = read();
			while (c <= 32 && c >= 0) {
				c = read();
			}
			if (c == -1) {
				finished = true;
				return Integer.MIN_VALUE;
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			if (c < '0' || c > '9') {
				throw new NumberFormatException("digit expected " + (char) c
						+ " found");
			}
			int ret = 0;
			while (c >= '0' && c <= '9') {
				ret = ret * 10 + c - '0';
				c = read();
			}
			if (c > 32) {
				throw new NumberFormatException("space character expected "
						+ (char) c + " found");
			}
			return ret * sgn;
		}

		public String next() {
			int c = read();
			while (c <= 32 && c >= 0) {
				c = read();
			}
			if (c == -1) {
				finished = true;
				return null;
			}
			StringBuilder res = new StringBuilder();
			while (c > 32) {
				res.appendCodePoint(c);
				c = read();
			}
			return res.toString();
		}

		public String nextLine() {
			int c = read();
			while (c == 13) {
				c = read();
			}
			if (c == 10) {
				return "";
			}
			if (c < 0) {
				finished = true;
				return null;
			}
			StringBuilder sb = new StringBuilder();
			while (c != 10 && c >= 0) {
				if (c != 13) {
					sb.appendCodePoint(c);
				}
				c = read();
			}
			return sb.toString();
		}

		public boolean isFinished() {
			return finished;
		}

		public void setFinished(boolean finished) {
			this.finished = finished;
		}

		public abstract void close();
	}

	static class StreamInputReader extends InputReader {
		private InputStream stream;
		private byte[] buf;
		private int current, numOfChars;

		public StreamInputReader(InputStream stream) {
			this(stream, 1024);
		}

		public StreamInputReader(InputStream stream, int bufSize) {
			this.stream = stream;
			buf = new byte[bufSize];
		}

		public int read() {
			if (numOfChars < 0) {
				return -1;
			}
			if (current >= numOfChars) {
				current = 0;
				try {
					numOfChars = stream.read(buf);
				} catch (IOException e) {
					return -1;
				}
				if (numOfChars <= 0) {
					return -1;
				}
			}
			return buf[current++];
		}

		@Override
		public void close() {
			try {
				stream.close();
			} catch (IOException ignored) {

			}
		}
	}

	public static void main(String[] args) {
		new B().run();
	}
}
