import java.io.*;
import java.util.*;
import java.math.*;

public class G implements Runnable {

	static class PersistentArray<E> {
		class Node {
			Node l;
			Node r;
			E value;

			public Node() {

			}

			public Node(E e) {
				value = e;
			}

			public Node(Node l, Node r) {
				this.l = l;
				this.r = r;
			}

			@Override
			public String toString() {
				return "Node [l=" + l + ", r=" + r + ", value=" + value + "]";
			}
		}

		Node root;
		int n;

		public PersistentArray(int n) {
			root = new Node();
			this.n = n;
			build(root, 0, n);
		}

		public PersistentArray(int n, E[] e) {
			root = new Node();
			this.n = n;
			buildKnown(root, 0, n, e);
		}

		public PersistentArray(Node root, int n) {
			this.root = root;
			this.n = n;
		}

		void buildKnown(Node v, int left, int right, E[] e) {
			if (left >= right - 1) {
				if (left == right - 1) {
					v.value = e[left];
				}
				return;
			}
			int mid = (left + right) >> 1;
			if (left < mid) {
				v.l = new Node();
				buildKnown(v.l, left, mid, e);
			}
			if (mid < right) {
				v.r = new Node();
				buildKnown(v.r, mid, right, e);
			}
		}

		void build(Node v, int left, int right) {
			if (left >= right - 1) {
				return;
			}
			int mid = (left + right) >> 1;
			if (left < mid) {
				v.l = new Node();
				build(v.l, left, mid);
			}
			if (mid < right) {
				v.r = new Node();
				build(v.r, mid, right);
			}
		}

		PersistentArray<E> set(int x, E e) {
			if (x < 0 || x >= n) {
				throw new ArrayIndexOutOfBoundsException(x);
			}
			return new PersistentArray<E>(set(root, 0, n, x, e), n);
		}

		private Node set(Node v, int left, int right, int x, E e) {
			if (left == right - 1) {
				return new Node(e);
			}
			int mid = (left + right) >> 1;
			if (x < mid) {
				return new Node(set(v.l, left, mid, x, e), v.r);
			} else {
				return new Node(v.l, set(v.r, mid, right, x, e));
			}
		}

		public E get(int x) {
			if (x < 0 || x >= n) {
				throw new ArrayIndexOutOfBoundsException(x);
			}
			Node v = root;
			int left = 0;
			int right = n;
			while (left < right - 1) {
				int mid = (left + right) >> 1;
				if (x < mid) {
					v = v.l;
					right = mid;
				} else {
					v = v.r;
					left = mid;
				}
			}
			return v.value;
		}
	}

	static class PersistentFenwick {
		PersistentArray<PersistentArray<Integer>> a;
		int n;
		int m;

		public PersistentFenwick(PersistentArray<PersistentArray<Integer>> a,
				int n, int m) {
			this.a = a;
			this.n = n;
			this.m = m;
		}

		public PersistentFenwick(int n, int m) {
			this.n = n;
			this.m = m;
			final Integer ZERO = new Integer(0);
			Integer[] c = new Integer[m];
			Arrays.fill(c, ZERO);
			PersistentArray<Integer>[] aa = new PersistentArray[n];
			PersistentArray<Integer> b = new PersistentArray<Integer>(m, c);
			Arrays.fill(aa, b);
			a = new PersistentArray<PersistentArray<Integer>>(n, aa);
		}

		public PersistentFenwick add(int x, int y, int z) {
			PersistentArray<PersistentArray<Integer>> ret = a;
			for (int i = x; i < n; i |= i + 1) {
				PersistentArray<Integer> b = ret.get(i);
				for (int j = y; j < m; j |= j + 1) {
					Integer got = b.get(j);
					b = b.set(j, z + got);
				}
				ret = ret.set(i, b);
			}
			return new PersistentFenwick(ret, n, m);
		}

		public int get(int x, int y) {
			int ret = 0;
			for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
				PersistentArray<Integer> b = a.get(i);
				for (int j = y; j >= 0; j = (j & (j + 1)) - 1) {
					ret += b.get(j);
				}
			}
			return ret;
		}

		public int get(int x1, int y1, int x2, int y2) {
			int ret = get(x2, y2);
			if (x1 > 0) {
				ret -= get(x1 - 1, y2);
			}
			if (y1 > 0) {
				ret -= get(x2, y1 - 1);
			}
			if (x1 > 0 && y1 > 0) {
				ret += get(x1 - 1, y1 - 1);
			}
			return ret;
		}
	}

	static interface Request {
		int answer(ArrayList<PersistentFenwick> f, int c);
	}

	static class Add implements Request {
		int x;
		int y;
		int a;

		public Add(int x, int y, int a) {
			this.x = x;
			this.y = y;
			this.a = a;
		}

		@Override
		public int answer(ArrayList<PersistentFenwick> f, int c) {
			PersistentFenwick curF = f.get(f.size() - 1);
			curF = curF.add(x ^ c, y ^ c, a);
			// Fenwick2 ff = FF.get(FF.size() - 1);
			// ff = ff.add(x ^ c, y ^ c, a);
			// FF.add(ff);
			f.add(curF);
			// if (ff.get(x ^ c, y ^ c, x ^ c, y ^ c) != curF.get(x ^ c, y ^ c,
			// x
			// ^ c, y ^ c)) {
			// throw new AssertionError();
			// }
			return curF.get(x ^ c, y ^ c, x ^ c, y ^ c);
		}
	}

	static class Query implements Request {
		int x1;
		int y1;
		int x2;
		int y2;
		int t;

		public Query(int x1, int x2, int y1, int y2, int t) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.t = t;
		}

		@Override
		public int answer(ArrayList<PersistentFenwick> f, int c) {
			PersistentFenwick curF;
			if (t > 0) {
				if (f.size() <= t) {
					curF = f.get(f.size() - 1);
				} else {
					curF = f.get(t);
				}
			} else {
				if (-t >= f.size()) {
					curF = f.get(0);
				} else {
					curF = f.get(f.size() + t - 1);
				}
			}
			int x1 = this.x1 ^ c;
			int x2 = this.x2 ^ c;
			int y1 = this.y1 ^ c;
			int y2 = this.y2 ^ c;
			if (x1 > x2) {
				int tmp = x1;
				x1 = x2;
				x2 = tmp;
			}
			if (y1 > y2) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}
			// if (FF.get(id).get(x1, y1, x2, y2) != curF.get(x1, y1, x2, y2)) {
			// throw new AssertionError();
			// }
			return (curF.get(x1, y1, x2, y2));
		}
	}

	static class Fenwick2 {
		long[][] a;
		int n;
		int m;

		public Fenwick2(Fenwick2 f) {
			this.n = f.n;
			this.m = f.m;
			a = new long[f.n][];
			for (int i = 0; i < n; i++) {
				a[i] = f.a[i].clone();
			}
		}

		public Fenwick2(int n, int m) {
			a = new long[n][m];
			this.n = n;
			this.m = m;
		}

		private void addPrivate(int x, int y, int z) {
			a[x][y] += z;
		}

		Fenwick2 add(int x, int y, int z) {
			Fenwick2 f2 = new Fenwick2(this);
			f2.addPrivate(x, y, z);
			return f2;
		}

		long get(int x1, int y1, int x2, int y2) {
			long ret = 0;
			for (int i = x1; i <= x2; i++) {
				for (int j = y1; j <= y2; j++) {
					ret += a[i][j];
				}
			}
			return ret;
		}
	}

	void solve() {
		int r = nextInt();
		int q = nextInt();
		Request[] req = new Request[q];
		for (int i = 0; i < q; i++) {
			int type = nextInt();
			if (type == 1) {
				req[i] = new Add(nextInt(), nextInt(), nextInt());
			} else {
				req[i] = new Query(nextInt(), nextInt(), nextInt(), nextInt(),
						nextInt());
			}
		}
		int c = 0;
		ArrayList<PersistentFenwick> f = new ArrayList<PersistentFenwick>();
		// FF = new ArrayList<G.Fenwick2>();
		// FF.add(new Fenwick2(4096, 4096));
		f.add(new PersistentFenwick(4096, 4096));
		System.err.println("Initialized");
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int j = 0; j < r; j++) {
			System.err.println("j = " + j);
			for (int i = 0; i < q; i++) {
				int got = req[i].answer(f, c);
				c = (int) (got & 4095);
				if (req[i] instanceof Query) {
					ans.add(got);
				}
			}
			System.gc();
		}
		Collections.reverse(ans);
		while (ans.size() > 20000) {
			ans.remove(ans.size() - 1);
		}
		Collections.reverse(ans);
		for (long i : ans) {
			out.println(i);
		}
	}

	// static ArrayList<Fenwick2> FF = new ArrayList<Fenwick2>();

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			sc = new FastScanner("g.in");
			out = new PrintWriter("g.out");
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
		new G().run();
	}
}