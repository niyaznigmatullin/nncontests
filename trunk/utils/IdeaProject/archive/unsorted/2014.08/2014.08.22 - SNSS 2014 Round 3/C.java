import java.io.*;
import java.util.*;

public class C {

	static void solve() throws IOException {
		int testCount = nextInt();
		for (int curTest = 0; curTest < testCount; curTest++) {
			int n = nextInt();
			int m = nextInt();
			int k = nextInt();
			int[] xCar = new int[n];
			int[] yCar = new int[n];
			for (int i = 0; i < n; i++) {
				xCar[i] = nextInt();
				yCar[i] = nextInt();
			}
			int[] xGarage = new int[m];
			int[] yGarage = new int[m];
			for (int i = 0; i < m; i++) {
				xGarage[i] = nextInt();
				yGarage[i] = nextInt();
			}
			DinicGraph g = new DinicGraph(n + m + 2);
			int source = n + m;
			int target = n + m + 1;
			int[][] d = new int[n][m];
			for (int i = 0; i < n; i++) {
				g.addEdge(source, i, 1);
			}
			for (int i = 0; i < m; i++) {
				g.addEdge(n + i, target, k);
			}
			DinicGraph.Edge[][] edges = new DinicGraph.Edge[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					edges[i][j] = g.addEdge(i, j + n, 1);
					int dx = xCar[i] - xGarage[j];
					int dy = yCar[i] - yGarage[j];
					d[i][j] = dx * dx + dy * dy;
				}
			}
			int l = -1;
			int r = 1000000000;
			while (l < r - 1) {
				int mid = l + r >> 1;
				g.clear();
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						if (d[i][j] <= mid) {
							edges[i][j].cap = 1;
						} else {
							edges[i][j].cap = 0;
						}
					}
				}
				if (g.getMaxFlow(source, target) == n) {
					r = mid;
				} else {
					l = mid;
				}
			}
			out.println(Math.sqrt(r));
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("c.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("c.out");
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
