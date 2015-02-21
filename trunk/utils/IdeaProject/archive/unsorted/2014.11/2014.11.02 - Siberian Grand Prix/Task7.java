import java.io.*;
import java.util.*;

public class Task7 {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		char[][] c = new char[n][];
		for (int i = 0; i < n; i++) {
			c[i] = next().toCharArray();
		}
		int[] letter = new int[26];
		Arrays.fill(letter, -1);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (Character.isLetter(c[i][j])) {
					letter[c[i][j] - 'A'] = (i ^ j) & 1;
				}
			}
		}
		DinicGraph g = new DinicGraph(27 + 27 + n * m + 2 + 2);
		int src1 = n * m + 54;
		int tar1 = src1 + 1;
		int src2 = tar1 + 1;
		int tar2 = src2 + 1;
		int all = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (c[i][j] == '*') {
					all++;
					if (((i ^ j) & 1) == 1) {
						g.addEdge(src2, n * m + 27 + 26, 1);
						g.addEdge(i * m + j, tar2, 1);
					} else {
						g.addEdge(src2, i * m + j, 1);
						g.addEdge(n * m + 26, tar2, 1);
					}
				} else if (Character.isLetter(c[i][j])) {
					if (letter[c[i][j] - 'A'] == 1) {
						g.addEdge(i * m + j, n * m + 27 + c[i][j] - 'A', 1);
					} else {
						g.addEdge(n * m + c[i][j] - 'A', i * m + j, 1);
					}
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			if (letter[i] < 0)
				continue;
			all++;
			if (letter[i] == 1) {
				g.addEdge(n * m + 27 + i, tar2, 1);
				g.addEdge(src2, tar1, 1);
			} else {
				g.addEdge(src2, n * m + i, 1);
				g.addEdge(src1, tar2, 1);
			}
		}
		if ((all & 1) == 1) {
			out.println("No");
			return;
		}
		final int INF = 123456;
		g.addEdge(src1, n * m + 26, INF);
		g.addEdge(n * m + 27 + 26, tar1, INF);
		g.addEdge(tar1, src1, INF);
		DinicGraph.Edge[][][] edges = new DinicGraph.Edge[n][m][4];
		final int[] DX = { 1, 0, -1, 0 };
		final int[] DY = { 0, 1, 0, -1 };
		final char[] C = { '^', '<', 'v', '>' };
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (((i ^ j) & 1) == 1)
					continue;
				if (c[i][j] == '.')
					continue;
				for (int dir = 0; dir < 4; dir++) {
					int x = i + DX[dir];
					int y = j + DY[dir];
					if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] == '.')
						continue;
					edges[i][j][dir] = g.addEdge(i * m + j, x * m + y, 1);
				}
			}
		}
		long flow = g.getMaxFlow(src2, tar2);
		if (flow != all) {
			out.println("No");
		} else {
			out.println("Yes");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					for (int dir = 0; dir < 4; dir++) {
						if (edges[i][j][dir] == null)
							continue;
						if (edges[i][j][dir].flow > 0) {
							c[i][j] = C[dir];
							c[i + DX[dir]][j + DY[dir]] = C[dir + 2 & 3];
						}
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (Character.isUpperCase(c[i][j])) {
						c[i][j] = '@';
					}
				}
			}
			for (char[] e : c)
				out.println(e);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("7.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("7.out");
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

class DinicGraph {

	public static class Edge {
		public int from;
		public int to;
		public int flow;
		public int cap;
		public Edge rev;

		public Edge(int from, int to, int flow, int cap) {
			super();
			this.from = from;
			this.to = to;
			this.flow = flow;
			this.cap = cap;
		}

	}

	public ArrayList<Edge>[] edges;
	int[] cur;
	int[] q;
	public int[] d;
	int n;

	public DinicGraph(int n) {
		edges = new ArrayList[n];
		this.n = n;
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new ArrayList<Edge>();
		}
	}

	public Edge addEdge(int from, int to, int cap) {
		Edge e1 = new Edge(from, to, 0, cap);
		Edge e2 = new Edge(to, from, 0, 0);
		e1.rev = e2;
		e2.rev = e1;
		edges[from].add(e1);
		edges[to].add(e2);
		return e1;
	}

	public Edge addUndirectedEdge(int from, int to, int cap) {
		Edge e1 = new Edge(from, to, 0, cap);
		Edge e2 = new Edge(to, from, 0, cap);
		e1.rev = e2;
		e2.rev = e1;
		edges[from].add(e1);
		edges[to].add(e2);
		return e1;
	}

	boolean bfs(int source, int target) {
		int head = 0;
		int tail = 1;
		Arrays.fill(d, Integer.MAX_VALUE);
		d[source] = 0;
		q[0] = source;
		while (head < tail) {
			int x = q[head++];
			for (int i = 0; i < edges[x].size(); i++) {
				Edge e = edges[x].get(i);
				if (e.cap - e.flow > 0 && d[e.to] == Integer.MAX_VALUE) {
					d[e.to] = d[x] + 1;
					q[tail++] = e.to;
					if (e.to == target) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int dfs(int x, int target, int cMin) {
		if (x == target) {
			return cMin;
		}
		for (int i = cur[x]; i < edges[x].size(); cur[x] = ++i) {
			Edge e = edges[x].get(i);
			if (d[e.to] != d[x] + 1 || e.cap - e.flow == 0) {
				continue;
			}
			int add = dfs(e.to, target, Math.min(cMin, e.cap - e.flow));
			if (add == 0) {
				continue;
			}
			e.flow += add;
			e.rev.flow -= add;
			return add;
		}
		return 0;
	}

	public long getMaxFlow(int source, int target) {
		cur = new int[n];
		q = new int[n];
		d = new int[n];
		long flow = 0;
		while (bfs(source, target)) {
			Arrays.fill(cur, 0);
			while (true) {
				int add = dfs(source, target, Integer.MAX_VALUE);
				if (add == 0) {
					break;
				}
				flow += add;
			}
		}
		return flow;
	}

	public List<List<Edge>> decompose(int source, int target) {
		List<List<Edge>> ret = new ArrayList<List<Edge>>();
		boolean[] was = new boolean[n];
		while (true) {
			List<Edge> list = new ArrayList<Edge>();
			Arrays.fill(was, false);
			if (getPath(source, target, list, Integer.MAX_VALUE, was) == 0) {
				break;
			}
			Collections.reverse(list);
			ret.add(list);
		}
		return ret;
	}

	int getPath(int v, int t, List<Edge> list, int cMin, boolean[] was) {
		if (v == t) {
			return cMin;
		}
		was[v] = true;
		for (Edge e : edges[v]) {
			if (was[e.to])
				continue;
			if (e.flow > 0) {
				int got = getPath(e.to, t, list, Math.min(cMin, e.flow), was);
				if (got == 0) {
					continue;
				}
				list.add(e);
				e.flow -= got;
				return got;
			}
		}
		return 0;
	}

	public boolean[] getCut(int source, int target) {
		if (bfs(source, target)) {
			return null;
		}
		boolean[] ret = new boolean[n];
		for (int i = 0; i < n; i++) {
			ret[i] = d[i] != Integer.MAX_VALUE;
		}
		return ret;
	}

	public void clear() {
		for (List<Edge> f : edges) {
			for (Edge e : f) {
				e.flow = 0;
			}
		}
	}

}
