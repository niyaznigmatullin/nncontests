import java.io.*;
import java.util.*;

public class N {

	static class Edge {
		int from;
		int to;
		int w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

	}

	static class Element {
		long d;
		int v;
		int type;

		public Element(long d, int v, int type) {
			this.d = d;
			this.v = v;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Element [d=" + d + ", v=" + v + ", type=" + type + "]";
		}

	}

	static int[] d;
	static List<Edge>[] edges;
	static long[] sd;
	static long ans;
	static int[] edgeUp;
	static int k;
	static int[] id1, id2;
	static Fenwick ones, sums;

	static void dfs(int v, int p) {
		sd[v] = d[v];
		for (int i = 0; i < edges[v].size(); i++) {
			Edge e = edges[v].get(i);
			if (e.to == p)
				continue;
			edgeUp[e.to] = e.w;
			dfs(e.to, v);
			sd[v] += sd[e.to];
		}
	}

	static void dfs2(int v, int p) {
		if (v > 0) {
			ones.add(id1[v], -1);
			sums.add(id1[v], -sd[v] * edgeUp[v]);
		}
		int l = -1;
		int r = ones.a.length - 1;
		while (l < r - 1) {
			int mid = l + r >> 1;
			if (ones.get(mid) >= k) {
				r = mid;
			} else {
				l = mid;
			}
		}
		ans = Math.min(ans, sums.get(r));
		if (v > 0) {
			ones.add(id2[v], 1);
			sums.add(id2[v], (sd[0] - sd[v]) * edgeUp[v]);
		}
		for (int i = 0; i < edges[v].size(); i++) {
			Edge e = edges[v].get(i);
			if (e.to == p)
				continue;
			dfs2(e.to, v);
		}
		if (v > 0) {
			ones.add(id1[v], 1);
			sums.add(id1[v], sd[v] * edgeUp[v]);
			ones.add(id2[v], -1);
			sums.add(id2[v], -(sd[0] - sd[v]) * edgeUp[v]);
		}
	}

	static void solve() throws IOException {
		int n = nextInt();
		k = n - 1 - nextInt();
		d = new int[n];
		for (int i = 0; i < n; i++) {
			d[i] = nextInt();
		}
		edges = new List[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i + 1 < n; i++) {
			int v = nextInt() - 1;
			int u = nextInt() - 1;
			int w = nextInt();
			edges[v].add(new Edge(v, u, w));
			edges[u].add(new Edge(u, v, w));
		}
		edgeUp = new int[n];
		sd = new long[n];
		dfs(0, -1);
		Element[] z = new Element[2 * (n - 1)];
		for (int i = 1; i < n; i++) {
			z[2 * (i - 1)] = new Element(edgeUp[i] * sd[i], i, 0);
			z[2 * (i - 1) + 1] = new Element(edgeUp[i] * (sd[0] - sd[i]), i, 1);
		}
		Arrays.sort(z, new Comparator<Element>() {
			@Override
			public int compare(Element o1, Element o2) {
				return Long.compare(o1.d, o2.d);
			}
		});
		id1 = new int[n];
		id2 = new int[n];
		for (int i = 0; i < z.length; i++) {
			(z[i].type == 0 ? id1 : id2)[z[i].v] = i;
		}
		ones = new Fenwick(z.length);
		sums = new Fenwick(z.length);
		for (int i = 1; i < n; i++) {
			ones.add(id1[i], 1);
			sums.add(id1[i], edgeUp[i] * sd[i]);
		}
		ans = Long.MAX_VALUE;
		dfs2(0, -1);
		out.println(ans);
	}

	static class Fenwick {
		long[] a;

		public Fenwick(int n) {
			a = new long[n];
		}

		void add(int x, long y) {
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
		File file = new File("n.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("n.out");
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
		int c = br.read();
		while (c <= 32)
			c = br.read();
		int x = 0;
		while (c > 32) {
			x = x * 10 + c - '0';
			c = br.read();
		}
		return x;
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
