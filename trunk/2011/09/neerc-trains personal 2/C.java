import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.*;
import java.util.*;
import java.math.*;

public class C {
	static class SegmentTree {
		private long[] min;
		private long[] max;
		private long[] sum;
		private long[] add;
		private long[] set;
		private boolean[] toSet;
		private int[] count;
		int[] where;
		final int n;

		public SegmentTree(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			min = new long[this.n << 1];
			max = new long[this.n << 1];
			sum = new long[this.n << 1];
			add = new long[this.n << 1];
			set = new long[this.n << 1];
			where = new int[this.n << 1];
			count = new int[this.n << 1];
			toSet = new boolean[this.n << 1];
			count(0, 0, this.n);
		}

		private int count(int node, int l, int r) {
			if (l == r - 1) {
				count[node] = 1;
				return 1;
			}
			int m = (l + r) >> 1;
			return count[node] = count((node << 1) | 1, l, m)
					+ count((node << 1) + 2, m, r);
		}

		private void add(int node, long x) {
			if (toSet[node]) {
				set[node] += x;
			} else {
				add[node] += x;
			}
		}

		private void set(int node, long x) {
			add[node] = 0;
			set[node] = x;
			toSet[node] = true;
		}

		private void relax(int node) {
			if (!toSet[node] && add[node] == 0) {
				return;
			}
			min[node] = getMin(node);
			where[node] = getWhere(node);
			max[node] = getMax(node);
			sum[node] = getSum(node);
			if (toSet[node]) {
				set((node << 1) | 1, set[node]);
				set((node << 1) + 2, set[node]);
				toSet[node] = false;
			} else {
				add((node << 1) | 1, add[node]);
				add((node << 1) + 2, add[node]);
				add[node] = 0;
			}
		}

		private long getSum(int node) {
			return toSet[node] ? set[node] * count(node) : sum[node]
					+ add[node] * count(node);
		}

		private long count(int node) {
			return count[node];
		}

		private long getMax(int node) {
			return toSet[node] ? set[node] : max[node] + add[node];
		}

		private long getMin(int node) {
			return toSet[node] ? set[node] : min[node] + add[node];
		}

		private int getWhere(int node) {
			return where[node];
		}

		private void add(int node, int l, int r, int left, int right, long x) {
			if (right <= l || r <= left) {
				return;
			}
			if (left <= l && r <= right) {
				if (l == r - 1) {
					where[node] = l;
				}
				add(node, x);
				return;
			}
			relax(node);
			int m = (l + r) >> 1;
			add((node << 1) | 1, l, m, left, right, x);
			add((node << 1) + 2, m, r, left, right, x);
			min[node] = Math.min(getMin((node << 1) | 1),
					getMin((node << 1) + 2));
			where[node] = (getMin((node << 1) | 1) == min[node] ? where[node * 2 + 1]
					: where[node * 2 + 2]);
			max[node] = Math.max(getMax((node << 1) | 1),
					getMax((node << 1) + 2));
			sum[node] = getSum((node << 1) | 1) + getSum((node << 1) + 2);
		}

		private void set(int node, int l, int r, int left, int right, long x) {
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
			min[node] = Math.min(getMin((node << 1) | 1),
					getMin((node << 1) + 2));
			max[node] = Math.max(getMax((node << 1) | 1),
					getMax((node << 1) + 2));
			sum[node] = getSum((node << 1) | 1) + getSum((node << 1) + 2);
		}

		private long getMin(int node, int l, int r, int left, int right) {
			if (right <= l || r <= left) {
				return Long.MAX_VALUE;
			}
			if (left <= l && r <= right) {
				return getMin(node);
			}
			relax(node);
			int m = (l + r) >> 1;
			return min(getMin((node << 1) | 1, l, m, left, right),
					getMin((node << 1) + 2, m, r, left, right));
		}

		private int getWhere(int node, int l, int r, int left, int right) {
			if (right <= l || r <= left) {
				return 0;
			}
			if (left <= l && r <= right) {
				return getWhere(node);
			}
			relax(node);
			int m = (l + r) >> 1;
			if (getMin((node << 1) | 1, l, m, left, right) > getMin(
					(node << 1) + 2, m, r, left, right)) {
				return getWhere(node * 2 + 2, m, r, left, right);
			} else {
				return getWhere(node * 2 + 1, l, m, left, right);
			}
		}

		private long getMax(int node, int l, int r, int left, int right) {
			if (right <= l || r <= left) {
				return Long.MIN_VALUE;
			}
			if (left <= l && r <= right) {
				return getMax(node);
			}
			relax(node);
			int m = (l + r) >> 1;
			return Math.max(getMax((node << 1) | 1, l, m, left, right),
					getMax((node << 1) + 2, m, r, left, right));
		}

		private long getSum(int node, int l, int r, int left, int right) {
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

		public void set(int l, int r, long x) {
			if (l >= r) {
				return;
			}
			set(0, 0, n, l, r, x);
		}

		public void add(int l, int r, long x) {
			if (l >= r) {
				return;
			}
			add(0, 0, n, l, r, x);
		}

		public long getSum(int l, int r) {
			if (l >= r) {
				return 0;
			}
			return getSum(0, 0, n, l, r);
		}

		public long getMax(int l, int r) {
			if (l >= r) {
				return Long.MAX_VALUE;
			}
			return getMax(0, 0, n, l, r);
		}

		public long getMin(int l, int r) {
			if (l >= r) {
				return Long.MAX_VALUE;
			}
			return getMin(0, 0, n, l, r);
		}

		public int getWhere(int l, int r) {
			if (l >= r) {
				return Integer.MIN_VALUE;
			}
			return getWhere(0, 0, n, l, r);
		}
	}

	static class Query {
		int type;
		int v;
		int u;
		int answer;

		public Query(int type, int v, int u) {
			this.type = type;
			this.v = v;
			this.u = u;
		}

	}

	static List<Integer> parent;
	static int[] p;
	static List<Query> queries;
	static List<Integer>[] edges;

	void solve() {
		int m = nextInt();
		parent = new ArrayList<Integer>();
		parent.add(-1);
		queries = new ArrayList<Query>();
		for (int i = 0; i < m; i++) {
			String s = nextToken();
			if (s.equals("+")) {
				int v = nextInt() - 1;
				parent.add(v);
			} else if (s.equals("-")) {
				int v = nextInt() - 1;
				queries.add(new Query(1, v, -1));
			} else {
				int u = nextInt() - 1;
				int v = nextInt() - 1;
				queries.add(new Query(2, v, u));
			}
		}
		p = new int[parent.size()];
		for (int i = 0; i < p.length; i++) {
			p[i] = parent.get(i);
		}
		int n = p.length;
		edges = new List[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; i++) {
			if (p[i] >= 0) {
				edges[p[i]].add(i);
			}
		}
		List<Integer> all = new ArrayList<Integer>();
		first = new int[n];
		last = new int[n];
		depth = new int[n];
		has = new List[n];
		for (int i = 0; i < n; i++) {
			has[i] = new ArrayList<Integer>();
		}
		dfs(0, all, 0);
		SegmentTree tree = new SegmentTree(all.size());
		for (int i = 0; i < all.size(); i++) {
			tree.add(i, i + 1, depth[all.get(i)]);
		}
		for (Query e : queries) {
			if (e.type == 1) {
				tree.add(first[e.v], last[e.v], -1);
				for (int i : has[e.v]) {
					tree.add(i, i + 1, 1000000);
				}
			} else {
				int l = min(first[e.v], first[e.u]);
				int r = max(last[e.v], last[e.u]);
				out.println(all.get((int) tree.getWhere(l, r + 1)) + 1);
			}
		}
	}

	static List<Integer>[] has;

	static void dfs(int v, List<Integer> all, int d) {
		first[v] = all.size();
		last[v] = all.size();
		depth[v] = d;
		has[v].add(all.size());
		all.add(v);
		for (int i : edges[v]) {
			dfs(i, all, d + 1);
			last[v] = all.size();
			has[v].add(all.size());
			all.add(v);
		}
	}

	static int[] depth;
	static int[] first;
	static int[] last;
	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("c.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream("c.out"));
			}
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

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	class FastScanner extends BufferedReader {
		StringTokenizer st;
		boolean eof;
		String buf;
		String curLine;
		boolean createST;

		public FastScanner(String fileName) throws FileNotFoundException {
			this(fileName, true);
		}

		public FastScanner(String fileName, boolean createST)
				throws FileNotFoundException {
			super(new FileReader(fileName));
			this.createST = createST;
			nextToken();
		}

		public FastScanner(InputStream stream) {
			this(stream, true);
		}

		public FastScanner(InputStream stream, boolean createST) {
			super(new InputStreamReader(stream));
			this.createST = createST;
			nextToken();
		}

		String nextLine() {
			String ret = curLine;
			if (createST) {
				st = null;
			}
			nextToken();
			return ret;
		}

		String nextToken() {
			if (!createST) {
				try {
					curLine = readLine();
				} catch (Exception e) {
					eof = true;
				}
				return null;
			}
			while (st == null || !st.hasMoreTokens()) {
				try {
					curLine = readLine();
					st = new StringTokenizer(curLine);
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

		public void close() {
			try {
				buf = null;
				st = null;
				curLine = null;
				super.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	public static void main(String[] args) {
		new C().run();
	}
}
