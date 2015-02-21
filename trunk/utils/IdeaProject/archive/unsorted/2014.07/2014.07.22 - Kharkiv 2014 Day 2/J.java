import java.io.*;
import java.util.*;

public class J {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		Query[] qs = new Query[m];
		List<Query>[] addLeft = new List[n];
		List<Query>[] addRight = new List[n];
		for (int i = 0; i < n; i++) {
			addLeft[i] = new ArrayList<>();
			addRight[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			int type = nextInt();
			if (type == 0) {
				qs[i] = new Query(type, nextInt() - 1, nextInt(), nextInt(), i);
				addLeft[qs[i].l].add(qs[i]);
				addRight[qs[i].r - 1].add(qs[i]);
			} else {
				qs[i] = new Query(type, nextInt() - 1, nextInt(), -1, i);
			}
		}
		Fenwick fSum = new Fenwick(m);
		List<Integer>[] becomeZero = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			becomeZero[i] = new ArrayList<>();
		}
		for (int i = 0; i < n; i++) {
			for (Query e : addLeft[i]) {
				fSum.add(e.id, e.v);
			}
			if (a[i] > 0) {
				int l = -1;
				int r = m;
				while (l < r - 1) {
					int mid = l + r >> 1;
					if (fSum.get(mid) >= a[i]) {
						r = mid;
					} else {
						l = mid;
					}
				}
				if (r < m) {
					becomeZero[r].add(i);
				}
			}
			for (Query e : addRight[i]) {
				fSum.add(e.id, -e.v);
			}
		}
		Fenwick f = new Fenwick(n);
		for (int i = 0; i < n; i++) {
			if (a[i] == 0) {
				f.add(i, 1);
			}
		}
		for (int i = 0; i < m; i++) {
			for (int v : becomeZero[i]) {
				f.add(v, 1);
			}
			if (qs[i].type == 1) {
				out.println(f.getSum(qs[i].l, qs[i].r));
			}
		}
	}

	static class Query {
		int type;
		int l;
		int r;
		int v;
		int id;

		public Query(int type, int l, int r, int v, int id) {
			super();
			this.type = type;
			this.l = l;
			this.r = r;
			this.v = v;
			this.id = id;
		}

	}

	static class Fenwick {
		long[] a;

		public Fenwick(int n) {
			a = new long[n];
		}

		long getSum(int l, int r) {
			return get(r - 1) - get(l - 1);
		}

		void add(int x, int y) {
			for (int i = x; i < a.length; i |= i + 1) {
				a[i] += y;
			}
		}

		long get(int x) {
			long ret = 0;
			for (int i = x; i >= 0; i = (i & i + 1) - 1) {
				ret += a[i];
			}
			return ret;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("j.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("j.out");
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
