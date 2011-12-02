import java.io.*;
import java.util.*;
import java.math.*;

public class D implements Runnable {
	static class PersistentTree {
		static class Node {
			Node left;
			Node right;
			long value;

			public Node(Node left, Node right) {
				this.left = left;
				this.right = right;
				value = (left == null ? 0 : this.left.value)
						+ (right == null ? 0 : this.right.value);
			}

			public Node() {

			}

			@Override
			public String toString() {
				return "Node [value=" + value + ", left=" + left + ", right="
						+ right + "]";
			}

			public Node(long value) {
				this.value = value;
			}

		}

		Node root;
		int n;

		public PersistentTree(int n) {
			this.n = n;
			root = new Node();
			makeArray(root, 0, n);
		}

		public PersistentTree(Node root, int n) {
			this.root = root;
			this.n = n;
		}

		static void makeArray(Node v, int left, int right) {
			if (left + 1 >= right) {
				return;
			}
			int mid = (left + right) >> 1;
			if (left < mid) {
				v.left = new Node();
				makeArray(v.left, left, mid);
			}
			if (mid < right) {
				v.right = new Node();
				makeArray(v.right, mid, right);
			}
		}

		static Node set(Node v, Node v2, int left, int right, int index,
				long newValue, long s) {
			if (left + 1 == right) {
				long e = v.value + newValue;
				v2.value += (e * e - v.value * v.value) * s;
				return new Node(e);
			}
			int mid = (left + right) >> 1;
			if (index < mid) {
				Node ret = new Node(set(v.left, v2.left, left, mid, index,
						newValue, s), v.right);
				long e = ret.value;
				v2.value += (e * e - v.value * v.value) * s;
				return ret;
			} else {
				Node ret = new Node(v.left, set(v.right, v2.right, mid, right,
						index, newValue, s));
				long e = ret.value;
				v2.value += (e * e - v.value * v.value) * s;
				return ret;
			}
		}

		long getSum(int l, int r) {
			return getSum(root, 0, n, l, r);
		}

		private long getSum(Node v, int l, int r, int left, int right) {
			if (r <= left || right <= l) {
				return 0;
			}
			if (left <= l && r <= right) {
				return v.value;
			}
			int m = (l + r) >> 1;
			return getSum(v.left, l, m, left, right)
					+ getSum(v.right, m, r, left, right);
		}

		public PersistentTree update(PersistentTree allTree, int e, long i,
				long s) {
			return new PersistentTree(set(root, allTree.root, 0, n, e, i, s), n);
		}

		@Override
		public String toString() {
			return "PersistentTree [root=" + root + ", n=" + n + "]";
		}
	}

	static final int MAX_NUMBER = 11;

	void solve() {
		int n = nextInt();
		int m = nextInt();
		PersistentTree init = new PersistentTree(n);
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		int[] next = new int[n];
		int[] head = new int[MAX_NUMBER];
		Arrays.fill(head, -1);
		int[] value = new int[n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			value[count] = i;
			next[count] = head[a[i]];
			head[a[i]] = count;
			count++;
		}
		PersistentTree allTree = new PersistentTree(n);
		for (int i = 0; i < MAX_NUMBER; i++) {
			PersistentTree tree = init;
			for (int e = head[i]; e != -1; e = next[e]) {
				tree = tree.update(allTree, e, 1, i);
			}
			System.err.println(tree);
		}
		System.err.println(allTree);
		for (int i = 0; i < m; i++) {
			int l = nextInt() - 1;
			int r = nextInt();
			out.println(allTree.getSum(l, r));
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
		new D().run();
	}
}