import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;
import java.math.*;

public class E {

	static class SegmentTree {
		Set<Integer>[] a;
		long[] add;
		int n;

		public SegmentTree(int m) {
			n = Integer.highestOneBit(m) << 1;
			m = 2 * n - 1;
			a = new Set[m];
			add = new long[m];
		}

		void put(int node, int val) {
			if (a[node] == null) {
				a[node] = new HashSet<Integer>(5);
			}
			a[node].add(val);
		}

		boolean contains(int node, int val) {
			return a[node] == null ? false : a[node].contains(val);
		}

		void add(int x, int val) {
			int node = 0;
			int left = 0;
			int right = n;
			put(node, val);
			while (left < right - 1) {
				int mid = (left + right) >> 1;
				if (x < mid) {
					node = node * 2 + 1;
					right = mid;
				} else {
					node = node * 2 + 2;
					left = mid;
				}
				put(node, val);
			}
		}

		void add(int l, int r, int val) {
			add(0, 0, n, l, r, val);
		}

		private void add(int node, int l, int r, int left, int right, int val) {
			if (right <= l || r <= left) {
				return;
			}
			if (left <= l && r <= right) {
				add[node] += val;
				return;
			}
			int m = (l + r) >> 1;
			add(node * 2 + 1, l, m, left, right, val);
			add(node * 2 + 2, m, r, left, right, val);
		}

		boolean has(int l, int r, int val) {
			return has(0, 0, n, l, r, val, 0);
		}

		private boolean has(int node, int l, int r, int left, int right,
				int val, long curAdd) {
			if (right <= l || r <= left) {
				return false;
			}
			if (left <= l && r <= right) {
				long cur = val - curAdd;
				if (abs(cur) > Integer.MAX_VALUE) {
					return false;
				}
				return contains(node, (int) cur);
			}
			int m = (l + r) >> 1;
			return has(node * 2 + 1, l, m, left, right, val, curAdd + add[node])
					|| has(node * 2 + 2, m, r, left, right, val, curAdd
							+ add[node]);
		}
	}

	static class LongHashMap {
		final static int A = BigInteger.probablePrime(16, rand).intValue();
		int[] head;
		int[] next;
		int[] map;
		int[] value;
		int[] free;
		int size;

		public LongHashMap(int n) {
			n = Integer.highestOneBit(n) << 1;
			map = new int[n];
			value = new int[n];
			head = new int[n];
			next = new int[n];
			free = new int[n];
			clear();
		}

		void clear() {
			fill(head, -1);
			for (int i = 0; i < free.length; i++) {
				free[i] = i;
			}
		}

		// int put(int v, int val) {
		void put(int v) {
			// if (size * 4 / 3 > map.length) {
			// long[] oldMap = map;
			// int[] oldValue = value;
			// int[] oldHead = head;
			// int[] oldNext = next;
			// map = new long[map.length << 1];
			// value = new int[map.length];
			// head = new int[map.length];
			// next = new int[map.length];
			// free = new int[map.length];
			// clear();
			// size = 0;
			// for (int i = 0; i < oldHead.length; i++) {
			// int e = oldHead[i];
			// while (e >= 0) {
			// put(oldMap[e], oldValue[e]);
			// e = oldNext[e];
			// }
			// }
			// }
			int key = hash(v);
			int e = head[key];
			int last = -1;
			while (e >= 0) {
				if (map[e] == v) {
					value[e]++;
					return;
				}
				last = e;
				e = next[e];
			}
			e = free[size++];
			if (last >= 0) {
				next[last] = e;
			} else {
				head[key] = e;
			}
			next[e] = -1;
			map[e] = v;
			value[e] = 1;
		}

		boolean containsKey(int v) {
			int key = hash(v);
			int e = head[key];
			while (e >= 0) {
				if (map[e] == v) {
					return true;
				}
				e = next[e];
			}
			return false;
		}

		int get(int v) {
			int key = hash(v);
			int e = head[key];
			while (e >= 0) {
				if (map[e] == v) {
					return value[e];
				}
				e = next[e];
			}
			return Integer.MIN_VALUE;
		}

		void remove(int v) {
			int key = hash(v);
			int e = head[key];
			int last = -1;
			while (e >= 0) {
				if (map[e] == v) {
					if (value[e] > 1) {
						value[e]--;
					} else {
						if (last >= 0) {
							next[last] = next[e];
						} else {
							head[key] = next[e];
						}
						free[--size] = e;
					}
					return;
				}
				last = e;
				e = next[e];
			}
		}

		int hash(int v) {
			return (v * A & map.length - 1);
		}
	}

	static class MultiSet {
		LongHashMap map;

		// Map<Long, Integer> map;

		public MultiSet(int n) {
			map = new LongHashMap(2 * n);
			// map = new TreeMap<Long, Integer>();
		}

		void add(int x) {
			// int got = map.get(x);
			// if (got < 0) {
			// map.put(x, 1);
			// } else {
			// map.put(x, got + 1);
			// }
			map.put(x);
		}

		boolean contains(int x) {
			return map.containsKey(x);
		}

		void remove(int x) {
			// int got = map.get(x);
			// if (got == 1) {
			map.remove(x);
			// } else {
			// map.put(x, got - 1);
			// }
			// return true;
		}
	}

	static class Block {
		int[] a;
		int add;
		MultiSet set;

		public Block(int[] a) {
			this.a = a;
			set = new MultiSet(a.length);
			for (int i : a) {
				set.add(i);
			}
		}

		void add(int x) {
			add += x;
		}

		void add(int l, int r, int x) {
			for (int i = 0; i < a.length; i++) {
				if (i >= l && i < r && add + x != 0) {
					set.remove(a[i]);
					a[i] += add + x;
					set.add(a[i]);
				}
				if (i < l || i >= r && add != 0) {
					set.remove(a[i]);
					a[i] += add;
					set.add(a[i]);
				}
			}
			add = 0;
		}

		boolean contains(int x) {
			return set.contains(x - add);
		}

		boolean contains(int l, int r, int x) {
			x -= add;
			for (int i = l; i < r; i++) {
				if (a[i] == x) {
					return true;
				}
			}
			return false;
		}
	}

	static final Random rand = new Random(123123L);
	static final int BLOCKSIZE = 100;

	void solve() {
		long time = currentTimeMillis();
		int n = nextInt();
		int m = nextInt();
		// int n = 100000;
		// int m = 100000;
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
			// a[i] = rand.nextInt(10000);
		}
		// SegmentTree tree = new SegmentTree(n);
		// for (int i = 0; i < n; i++) {
		// tree.add(i, a[i]);
		// }
		Block[] blocks = new Block[(n + BLOCKSIZE - 1) / BLOCKSIZE];
		for (int i = 0, j = 0; i < n; i += BLOCKSIZE, j++) {
			int[] b = copyOfRange(a, i, Math.min(i + BLOCKSIZE, n));
			blocks[j] = new Block(b);
		}
		for (int query = 0; query < m; query++) {
			String s = nextToken();
			if (s.equals("?")) {
				// if (rand.nextBoolean()) {
				int l = nextInt() - 1;
				int r = nextInt() - 1;
				int x = nextInt();
				// int l = rand.nextInt(n);
				// int r = rand.nextInt(n - l) + l;
				// int x = rand.nextInt(1000);
				int block1 = l / BLOCKSIZE;
				int block2 = r / BLOCKSIZE;
				boolean has = false;
				if (block1 == block2) {
					has = blocks[block1].contains(l % BLOCKSIZE, r % BLOCKSIZE
							+ 1, x);
				} else {
					for (int i = block1 + 1; i < block2; i++) {
						if (blocks[i].contains(x)) {
							has = true;
							break;
						}
					}
					if (!has
							&& blocks[block1].contains(l % BLOCKSIZE,
									BLOCKSIZE, x)) {
						has = true;
					}
					if (!has
							&& blocks[block2].contains(0, r % BLOCKSIZE + 1, x)) {
						has = true;
					}
				}
				out.println(has ? "YES" : "NO");
				// out.println(tree.has(nextInt() - 1, nextInt(), nextInt()) ?
				// "YES"
				// : "NO");
			} else {
				int l = nextInt() - 1;
				int r = nextInt() - 1;
				int x = nextInt();
				if (x == 0) {
					continue;
				}
				// int l = rand.nextInt(n);
				// int r = rand.nextInt(n - l) + l;
				// int x = rand.nextInt(1000);
				int block1 = l / BLOCKSIZE;
				int block2 = r / BLOCKSIZE;
				if (block1 == block2) {
					blocks[block1].add(l % BLOCKSIZE, r % BLOCKSIZE + 1, x);
				} else {
					for (int i = block1 + 1; i < block2; i++) {
						blocks[i].add(x);
					}
					blocks[block1].add(l % BLOCKSIZE, BLOCKSIZE, x);
					blocks[block2].add(0, r % BLOCKSIZE + 1, x);
				}
				// tree.add(nextInt() - 1, nextInt(), nextInt());
			}
		}
		System.err.println(currentTimeMillis() - time);
	}

	void solve2() {
		long time = currentTimeMillis();
		// int n = nextInt();
		// int m = nextInt();
		int n = 100000;
		int m = 100000;
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			// a[i] = nextInt();
			a[i] = rand.nextInt(10000);
		}
		// SegmentTree tree = new SegmentTree(n);
		// for (int i = 0; i < n; i++) {
		// tree.add(i, a[i]);
		// }
		Block[] blocks = new Block[(n + BLOCKSIZE - 1) / BLOCKSIZE];
		for (int i = 0, j = 0; i < n; i += BLOCKSIZE, j++) {
			int[] b = copyOfRange(a, i, Math.min(i + BLOCKSIZE, n));
			blocks[j] = new Block(b);
		}
		for (int query = 0; query < m; query++) {
			// String s = nextToken();
			// if (s.equals("?")) {
			if (rand.nextBoolean()) {
				// int l = nextInt() - 1;
				// int r = nextInt() - 1;
				// int x = nextInt();
				int l = rand.nextInt(n);
				int r = rand.nextInt(n - l) + l;
				int x = rand.nextInt(1000);
				int block1 = l / BLOCKSIZE;
				int block2 = r / BLOCKSIZE;
				boolean has = false;
				if (block1 == block2) {
					has = blocks[block1].contains(l % BLOCKSIZE, r % BLOCKSIZE
							+ 1, x);
				} else {
					for (int i = block1 + 1; i < block2; i++) {
						if (blocks[i].contains(x)) {
							has = true;
							break;
						}
					}
					if (!has
							&& blocks[block1].contains(l % BLOCKSIZE,
									BLOCKSIZE, x)) {
						has = true;
					}
					if (!has
							&& blocks[block2].contains(0, r % BLOCKSIZE + 1, x)) {
						has = true;
					}
				}
				out.println(has ? "YES" : "NO");
				// out.println(tree.has(nextInt() - 1, nextInt(), nextInt()) ?
				// "YES"
				// : "NO");
			} else {
				// int l = nextInt() - 1;
				// int r = nextInt() - 1;
				// int x = nextInt();
				int l = rand.nextInt(n);
				int r = rand.nextInt(n - l) + l;
				int x = rand.nextInt(1000);
				int block1 = l / BLOCKSIZE;
				int block2 = r / BLOCKSIZE;
				if (block1 == block2) {
					blocks[block1].add(l % BLOCKSIZE, r % BLOCKSIZE + 1, x);
				} else {
					for (int i = block1 + 1; i < block2; i++) {
						blocks[i].add(x);
					}
					blocks[block1].add(l % BLOCKSIZE, BLOCKSIZE, x);
					blocks[block2].add(0, r % BLOCKSIZE + 1, x);
				}
				// tree.add(nextInt() - 1, nextInt(), nextInt());
			}
		}
		System.err.println(currentTimeMillis() - time);
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		try {
			File file = new File("e.in");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream("e.out"));
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
		new E().run();
	}
}