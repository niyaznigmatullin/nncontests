import java.io.*;
import java.util.*;
import java.math.*;

public class Task7 implements Runnable {
	public static void main(String[] args) {
		new Task7().run();
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
		try {
			sc = new FastScanner("input.txt");
			out = new PrintWriter("output.txt");
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

	ArrayList<Integer>[] g;
	ArrayList<Integer> d;
	int n, m, s;
	int[] color, ans;
	boolean[] use;

	void dfs(int v) {
		use[v] = true;
		for (int to : g[v]) {
			if (!use[to])
				dfs(to);
		}
		d.add(v);
	}

	int compare(int a, int b) {
		return a < b ? -1 : a == b ? 0 : 1;
	}

	boolean check(int from, int finish, int need) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] use = new boolean[n];
		int[] p = new int[n];
		q.add(from);
		Arrays.fill(p, -1);
		use[from] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int to : g[now]) {
				if (!use[to]) {
					q.add(to);
					p[to] = now;
					use[to] = true;
				}
			}
		}
		if (!use[finish])
			return true;
		ArrayList<Integer> path = new ArrayList<Integer>();
		int now = finish;
		while (now != -1) {
			path.add(now);
			now = p[now];
		}
		System.err.println(path);
		for (int i = 0; i < path.size() - 1; i++) {
			if (compare(ans[path.get(i)], ans[path.get(i + 1)]) == need)
				return false;
		}
		return true;
	}

	class Graph {
		ArrayList<Integer>[] edges;
		ArrayList<Integer>[] revEdges;
		int n;
		boolean[] was;
		int[] color;
		int[] w;
		int s;

		void addEdge(int from, int to) {
			edges[from].add(to);
			revEdges[to].add(from);
		}

		public Graph(int n) {
			this.n = n;
			edges = new ArrayList[n];
			revEdges = new ArrayList[n];
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new ArrayList<Integer>();
				revEdges[i] = new ArrayList<Integer>();
			}
		}

		Graph condense(int s) {
			ArrayList<Integer> t = topSort();
			color = new int[n];
			Arrays.fill(color, -1);
			int curColor = 0;
			for (int i : t) {
				if (color[i] == -1) {
					dfsR(i, curColor++);
				}
			}
			Graph g = new Graph(curColor);
			g.w = new int[g.n];
			for (int i = 0; i < n; i++) {
				g.w[color[i]]++;
				for (int j : edges[i]) {
					if (color[i] != color[j]) {
						g.addEdge(color[i], color[j]);
					}
				}
			}
			if (g.w[color[s]] > 1) {
				return null;
			}
			g.s = color[s];
			return g;
		}

		void dfsR(int x, int col) {
			color[x] = col;
			for (int i : revEdges[x]) {
				if (color[i] == -1) {
					dfsR(i, col);
				}
			}
		}

		ArrayList<Integer> topSort() {
			was = new boolean[n];
			ArrayList<Integer> ans = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				if (!was[i]) {
					dfs(i, ans);
				}
			}
			Collections.reverse(ans);
			return ans;
		}

		void dfs(int x, ArrayList<Integer> t) {
			was[x] = true;
			for (int i : edges[x]) {
				if (was[i]) {
					continue;
				}
				dfs(i, t);
			}
			t.add(x);
		}

		int[] getThat(int s) {
			int[] d = new int[n];
			was = new boolean[n];

			for (int i = 0; i < n; i++) {
				if (was[i]) {
					continue;
				}
				dfs2(i, d, s);
			}
			return d;
		}

		void dfs2(int x, int[] d, int t) {
			was[x] = true;
			for (int i : revEdges[x]) {
				if (!was[i]) {
					dfs2(i, d, t);
				}
				d[x] += d[i];
			}
			if (x == t) {
				d[x]++;
			}
			if (d[x] >= 2) {
				d[x] = 2;
			}
		}

		Graph go(int s) {
			Graph g = new Graph(n);
			was = new boolean[n];
			for (int i = 0; i < n; i++) {
				for (int j : edges[i]) {
					Arrays.fill(was, false);
					if (dfs3(s, i, j)) {
						g.addEdge(i, j);
					}
				}
			}
			return g;
		}

		boolean dfs3(int x, int y, int z) {
			if (x == z) {
				return false;
			}
			was[x] = true;
			if (x == y) {
				return true;
			}			
			for (int i : edges[x]) {
				if (was[i]) {
					continue;
				}
				if (dfs3(i, y, z)) {
					return true;
				}
			}
			return false;
		}
	}

	boolean solve2() {
		int n = nextInt();
		int m = nextInt();
		int s = nextInt() - 1;
		if (n == 0 && m == 0 && s == -1) {
			return false;
		}
		HashSet<Pair> hs = new HashSet<Task7.Pair>();
		Graph g = new Graph(n);
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1;
			int y = nextInt() - 1;
			Pair e = new Pair(x, y);
			if (hs.contains(e) || x == y) {
				continue;
			}
			g.addEdge(x, y);
			hs.add(e);
		}
		Graph g1 = g.go(s);
		ArrayList<Integer> t = g1.topSort();
		int[] num = new int[t.size()];
		for (int i = 0; i < t.size(); i++) {
			num[t.get(i)] = i;
		}
		for (int i = 0; i < g1.n; i++) {
			for (int j : g1.edges[i]) {
				if (num[i] > num[j]) {
					out.println("No solution");
					return true;
				}
			}
		}
		for (int i : num) {
			out.print(1 + i + " ");
		}
		out.println();
		return true;
	}

	class Pair {
		int x, y;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private Task7 getOuterType() {
			return Task7.this;
		}

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	// boolean solve2() {
	// n = nextInt();
	// m = nextInt();
	// s = nextInt() - 1;
	// if (n == 0 && m == 0 && s == -1) {
	// return false;
	// }
	// g = new ArrayList[n];
	// d = new ArrayList<Integer>();
	// color = new int[n];
	// use = new boolean[n];
	// for (int i = 0; i < n; i++) {
	// g[i] = new ArrayList<Integer>();
	// }
	// for (int i = 0; i < m; i++) {
	// g[nextInt() - 1].add(nextInt() - 1);
	// }
	// dfs(s);
	// Collections.reverse(d);
	// ans = new int[n];
	// for (int i = 0; i < n; i++) {
	// ans[d.get(i)] = i;
	// }
	// for (int i = 0; i < n; i++) {
	// if (i == s) {
	// continue;
	// }
	// if (!check(s, i, -1)) {
	// out.println("No solution");
	// return true;
	// }
	// if (!check(i, s, 1)) {
	// out.println("No solution");
	// return true;
	// }
	//
	// }
	// for (int i : ans) {
	// out.print(i + 1 + " ");
	// }
	// out.println();
	// return true;
	// }

	void solve() {
		while (solve2())
			;
	}
}