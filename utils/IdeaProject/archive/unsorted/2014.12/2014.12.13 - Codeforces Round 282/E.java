import java.io.*;
import java.util.*;

public class E {

	static class Rectangle {
		int x1;
		int y1;
		int x2;
		int y2;

		@Override
		public String toString() {
			return "Rectangle [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2
					+ ", y2=" + y2 + "]";
		}

		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	}

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int k = nextInt();
		Rectangle[] as = new Rectangle[m];
		for (int i = 0; i < m; i++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;
			int c = nextInt();
			int d = nextInt();
			as[i] = new Rectangle(a, b, c, d);
		}
		k = Integer.numberOfTrailingZeros(Integer.highestOneBit(k));
		int[] ans = new int[30];
		for (int bit = 0; bit < 30; bit++) {
			ans[bit] = solve(1 << bit, n, as);
		}
		for (int i = k + 1; i < 30; i++) {
			ans[k] ^= ans[i];
			ans[i] = 0;
		}
		System.err.println(Arrays.toString(ans));
		for (int i = 0; i < 30; i++) {
			if (ans[i] != 0) {
				out.println("Hamed");
				return;
			}
		}
		out.println("Malek");
	}

	static int solve(int bit, int n, Rectangle[] as) {
		Rectangle[] f = new Rectangle[as.length];
		for (int i = 0; i < as.length; i++) {
			f[i] = new Rectangle(as[i].x1 / bit, as[i].y1 / bit,
					as[i].x2 / bit, as[i].y2 / bit);
		}
		Event[] events = new Event[f.length * 2];
		for (int i = 0; i < f.length; i++) {
			events[2 * i] = new Event(f[i], f[i].x1, true);
			events[2 * i + 1] = new Event(f[i], f[i].x2, false);
		}
		Arrays.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				if (o1.x != o2.x)
					return Integer.compare(o1.x, o2.x);
				if (o1.start == o2.start)
					return 0;
				return o1.start ? 1 : -1;
			}
		});
		int last = -1;
		int[] ys = new int[f.length * 2 + 2];
		for (int i = 0; i < f.length; i++) {
			ys[2 * i] = f[i].y1;
			ys[2 * i + 1] = f[i].y2;
		}
		ys[f.length * 2] = 0;
		ys[f.length * 2 + 1] = n / bit;
		Arrays.sort(ys);
		{
			int j = 1;
			for (int i = 1; i < ys.length; i++) {
				if (ys[i] != ys[j - 1]) {
					ys[j++] = ys[i];
				}
			}
			ys = Arrays.copyOf(ys, j);
		}
		Tree tree = new Tree(ys.length);
		for (int i = 0; i + 1 < ys.length; i++) {
			tree.addCount(i, ys[i + 1] - ys[i]);
		}
		int ans = 0;
		for (Event e : events) {
			ans ^= ((ys[ys.length - 1] & 1) ^ tree.countMinPrefix[1])
					& (e.x - last & 1);
			System.err.println(e.x + " " + last + " " + ys[ys.length - 1] + " "
					+ tree.countMinPrefix[1]);
			if (e.start) {
				tree.addValue(Arrays.binarySearch(ys, e.which.y1), 1);
				tree.addValue(Arrays.binarySearch(ys, e.which.y2), -1);
			} else {
				tree.addValue(Arrays.binarySearch(ys, e.which.y1), -1);
				tree.addValue(Arrays.binarySearch(ys, e.which.y2), 1);
			}
			last = e.x;
		}
		int ans2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((~i & ~j) % bit == 0) {
					for (Rectangle e : as) {
						if (i >= e.x1 && i < e.x2 && j >= e.y1 && j < e.y2) {
							ans2 ^= 1;
						}
					}
				}
			}
		}
		System.err.println(Arrays.toString(f));
		System.err.println(ans + " " + ans2);
		if (ans != ans2)
			throw new AssertionError();
		return ans;
	}

	static class Event {
		Rectangle which;
		int x;
		boolean start;

		public Event(Rectangle which, int x, boolean start) {
			this.which = which;
			this.x = x;
			this.start = start;
		}

	}

	static class Tree {
		int[] minPrefix;
		int[] countMinPrefix;
		int[] sum;
		int n;

		public Tree(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			minPrefix = new int[this.n << 1];
			countMinPrefix = new int[this.n << 1];
			sum = new int[this.n << 1];
		}

		void addCount(int x, int y) {
			x += n;
			countMinPrefix[x] ^= y & 1;
			make(x);
		}

		void make(int x) {
			while (x > 1) {
				x >>= 1;
				minPrefix[x] = Math.min(minPrefix[x * 2], sum[x * 2]
						+ minPrefix[x * 2 + 1]);
				sum[x] = sum[x * 2] + sum[x * 2 + 1];
				countMinPrefix[x] = 0;
				if (minPrefix[x] == minPrefix[x * 2])
					countMinPrefix[x] ^= countMinPrefix[x * 2];
				if (sum[x * 2] + minPrefix[x * 2 + 1] == minPrefix[x])
					countMinPrefix[x] ^= countMinPrefix[x * 2 + 1];
			}
		}

		void addValue(int x, int y) {
			x += n;
			minPrefix[x] += y;
			sum[x] += y;
			make(x);
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
