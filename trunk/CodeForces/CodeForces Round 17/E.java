import java.io.*;
import java.util.*;

public class E implements Runnable {
	public static void main(String[] args) {
		new E().run();
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
	BufferedReader br;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			// sc = new FastScanner(System.in);
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			// br.readLine();
			// long ans = solve(br.readLine());
			// out.println(ans);
			// out.close();
			int q = 0;
			while (true) {
				System.err.println(++q);
				int n = rand.nextInt(5);
				char[] c = new char[n];
				for (int i = 0; i < n; i++) {
					c[i] = (char) (rand.nextInt(1) + 'a');
				}
				if (solve1(new String(c)) != solve(new String(c))) {
					throw new AssertionError(new String(c) + " "
							+ solve1(new String(c)) + " "
							+ solve(new String(c)));
				}
			}
			// sc.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	boolean isPalindrom(String s) {
		return new StringBuilder(s).reverse().toString().equals(s);
	}

	long solve1(String s) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				if (isPalindrom(s.substring(i, j))) {
					a.add(i);
					b.add(j - 1);
				}
			}
		}
		long ans = 0;
		for (int i = 0; i < a.size(); i++) {
			for (int j = i + 1; j < a.size(); j++) {
				if (a.get(j) <= b.get(i) && a.get(i) <= b.get(j)) {
					++ans;
				}
			}
		}
		return ans;
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

	long[] get1(char[] c) {
		int n = c.length;
		long[] ret = new long[n];
		int l = 0;
		int r = -1;
		for (int i = 0; i < n; i++) {
			int k = (int) (i > r ? 0 : Math.min(ret[l + r - i], r - i)) + 1;
			while (i + k < n && i - k >= 0 && c[i + k] == c[i - k]) {
				++k;
			}
			ret[i] = --k;
			if (i + k > r) {
				l = i - k;
				r = i + k;
			}
		}
		return ret;
	}

	long[] get2(char[] c) {
		int n = c.length;
		long[] ret = new long[n];
		int l = 0;
		int r = -1;
		for (int i = 0; i < n; ++i) {
			int k = (int) (i > r ? 0 : Math.min(ret[l + r - i + 1], r - i + 1)) + 1;
			while (i + k - 1 < n && i - k >= 0 && c[i + k - 1] == c[i - k]) {
				++k;
			}
			ret[i] = --k;
			if (i + k - 1 > r) {
				l = i - k;
				r = i + k - 1;
			}
		}
		return ret;
	}

	class Node {
		Node l, r;
		int x, y, c;
		boolean toUp;
		long sum, toAdd, has;

		public Node(int x) {
			this.x = x;
			sum = 0;
			toAdd = 0;
			has = 0;
			toUp = false;
			y = rand.nextInt();
			c = 1;
			l = r = null;
		}

		@Override
		public String toString() {
			return "Node [c=" + c + ", x=" + x + ", has=" + has + ", l=" + l
					+ ", r=" + r + ", sum=" + sum + ", toAdd=" + toAdd
					+ ", toUp=" + toUp + ", y=" + y + "]";
		}

	}

	Random rand = new Random();

	void doUp(Node v, long x) {
		if (v == null) {
			return;
		}
		if (v.toUp) {
			v.toAdd += x;
		} else {
			v.toUp = true;
			v.toAdd = x;
		}
	}

	void update(Node v) {
		if (v == null) {
			return;
		}
		if (!v.toUp) {
			return;
		}
		doUp(v.l, v.toAdd);
		doUp(v.r, v.toAdd);
		v.has += v.toAdd;
		v.toUp = false;
		v.sum += getSum(v.l) + getSum(v.r) + v.has;
		v.toAdd = 0;
	}

	long getSum(Node v) {
		if (v == null) {
			return 0;
		}
		if (v.toUp) {
			return v.sum + v.c * v.toAdd;
		}
		return v.sum;
	}

	void update2(Node v) {
		if (v == null) {
			return;
		}
		v.c = getC(v.l) + getC(v.r) + 1;
		v.sum = getSum(v.l) + getSum(v.r) + v.has;
	}

	int getC(Node v) {
		if (v == null) {
			return 0;
		}
		return v.c;
	}

	Node merge(Node l, Node r) {
		update(l);
		update(r);
		if (l == null) {
			return r;
		}
		if (r == null) {
			return l;
		}
		Node ret;
		if (l.y < r.y) {
			ret = l;
			ret.r = merge(ret.r, r);
		} else {
			ret = r;
			ret.l = merge(l, ret.l);
		}
		update2(ret);
		return ret;
	}

	Node[] split(Node v, int x) {
		update(v);
		if (v == null) {
			return new Node[2];
		}
		Node[] t;
		if (v.x <= x) {
			t = split(v.r, x);
			v.r = t[0];
			t[0] = v;
		} else {
			t = split(v.l, x);
			v.l = t[1];
			t[1] = v;
		}
		update2(t[0]);
		update2(t[1]);
		return t;
	}

	long getSum(int l, int r) {
		Node[] v1 = split(root, r);
		Node[] v2 = split(v1[0], l - 1);
		long ret = getSum(v2[1]);
		root = merge(v2[0], v2[1]);
		root = merge(root, v1[1]);
		return ret;
	}

	Node root;

	void print(Node v) {
		if (v == null) {
			return;
		}
		print(v.l);
		System.err.print("[" + v.x + " -> " + v.has + "], ");
		print(v.r);
	}

	void upSum(int l, int r) {
		// System.err.println("upsum = [" + l + ", " + r + "]");
		Node[] v1 = split(root, r);
		Node[] v2 = split(v1[0], l - 1);
		doUp(v2[1], 1);
		root = merge(v2[0], v2[1]);
		root = merge(root, v1[1]);
	}

	long solve(String s) throws IOException {
		root = null;
		// br.readLine();
		char[] c = s.toCharArray();// br.readLine().toCharArray();
		for (int i = 0; i < c.length; i++) {
			root = merge(root, new Node(i));
		}
		long[] d1 = get1(c);
		long[] d2 = get2(c);
		// out.println(Arrays.toString(d1));
		// out.println(Arrays.toString(d2));
		long ans = 0;
		for (int i = 0; i < c.length; i++) {
			ans += (long) d2[i] * (d1[i] + 1);
			ans += (long) d1[i] * (d1[i] + 1) / 2;
			ans += (long) d2[i] * (d2[i] - 1) / 2;
		}
		// System.err.println(ans);
		for (int i = 0; i < c.length; i++) {
			ans += getSum((int) (i - d1[i]), c.length - 1);
			if (d2[i] != 0) {
				ans += getSum((int) (i - d2[i]), c.length - 1);
			}
			upSum(i, i + (int) (d1[i]));
			if (d2[i] != 0) {
				upSum(i, i + (int) d2[i] - 1);
			}
		}
		// System.err.println(ans);
		for (int i = 0; i < c.length - i - 1; i++) {
			char temp = c[i];
			c[i] = c[c.length - i - 1];
			c[c.length - i - 1] = temp;
		}
		root = null;
		for (int i = 0; i < c.length; i++) {
			root = merge(root, new Node(i));
		}
		d1 = get1(c);
		d2 = get2(c);
		for (int i = 0; i < c.length; i++) {
			ans += getSum((int) (i - d1[i]), c.length - 1);
			if (d2[i] != 0) {
				ans += getSum((int) (i - d2[i]), c.length - 1);
			}
			upSum(i, i + (int) (d1[i]));
			if (d2[i] != 0) {
				upSum(i, i + (int) d2[i] - 1);
			}
		}
		// System.err.println(ans);
		root = null;
		for (int i = 0; i < c.length; i++) {
			root = merge(root, new Node(i));
		}
		for (int i = 0; i < c.length; i++) {
			ans -= getSum((int) (i - d1[i]), c.length - 1);
			if (d2[i] != 0) {
				ans -= getSum((int) (i - d2[i]), c.length - 1);
			}
			upSum(i + (int) (d1[i]), i + (int) (d1[i]));
			if (d2[i] != 0) {
				upSum(i + (int) d2[i] - 1, i + (int) d2[i] - 1);
			}
		}
		// System.err.println(ans);
		return ans;
	}
}