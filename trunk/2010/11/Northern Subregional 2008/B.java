import java.io.*;
import java.util.*;
import java.math.*;

public class B implements Runnable {
	public static void main(String[] args) {
		new B().run();
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
			sc = new FastScanner("billboard.in");
			out = new PrintWriter("billboard.out");
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

	class Node {
		Node l;
		Node r;
		int pr;
		int x;
		int minValue;
		int num;

		public Node(int x, int num) {
			this.x = x;
			this.num = num;
			minValue = num;
			pr = rand.nextInt();
		}

		@Override
		public String toString() {
			return "Node [l=" + l + ", r=" + r + ", x=" + x + ", minValue="
					+ minValue + ", num=" + num + "]";
		}

	}

	Node merge(Node l, Node r) {
		if (l == null) {
			return r;
		}
		if (r == null) {
			return l;
		}
		Node ret;
		if (l.pr > r.pr) {
			ret = l;
			l.r = merge(l.r, r);
		} else {
			ret = r;
			r.l = merge(l, r.l);
		}
		return update(ret);
	}

	Node[] split(Node v, int x, int num) {
		if (v == null) {
			return new Node[2];
		}
		Node[] ret;
		if (v.x < x || (v.x == x && v.num < num)) {
			ret = split(v.r, x, num);
			v.r = ret[0];
			ret[0] = v;
		} else {
			ret = split(v.l, x, num);
			v.l = ret[1];
			ret[1] = v;
		}
		update(ret[0]);
		update(ret[1]);
		return ret;
	}

	void add(Node v) {
		Node[] t = split(root, v.x, v.num);
		root = merge(t[0], v);
		root = merge(root, t[1]);
	}

	Node root;

	Node update(Node v) {
		if (v == null) {
			return v;
		}
		int first = getMinValue(v.l);
		int second = getMinValue(v.r);
		v.minValue = Math.min(v.num, Math.min(first, second));
		return v;
	}

	int getMinValue(Node v) {
		return v == null ? Integer.MAX_VALUE : v.minValue;
	}

	final static Random rand = new Random(1);

	void solve() {
		int h = nextInt();
		int w = nextInt();
		int n = nextInt();
		h = Math.min(h, n);
		Node[] ar = new Node[h];
		for (int i = 0; i < h; i++) {
			ar[i] = new Node(w, i);
			add(ar[i]);
		}
		for (int i = 0; i < n; i++) {
			int x = nextInt();
			Node[] t = split(root, x, -1);
			int min = getMinValue(t[1]);
			root = merge(t[0], t[1]);
			if (min == Integer.MAX_VALUE) {
				out.println(-1);
				continue;
			}
			t = split(root, ar[min].x, ar[min].num);
			Node[] t2 = split(t[1], ar[min].x, ar[min].num + 1);
			t2[0].x -= x;
			root = merge(t[0], t2[1]);
			add(ar[min]);
			out.println(min + 1);
		}
	}
}