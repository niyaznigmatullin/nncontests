import java.io.*;
import java.util.*;
import java.math.*;

public class flow_nn implements Runnable {
	public static void main(String[] args) {
		new flow_nn().run();
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
			sc = new FastScanner("flow.in");
			out = new PrintWriter("flow.out");
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

	class Edge {
		int from, to, flow, cap;
		Edge rev;

		public Edge(int from, int to, int flow, int cap) {
			this.from = from;
			this.to = to;
			this.flow = flow;
			this.cap = cap;
		}

	}

	class Graph {
		ArrayList<Edge>[] edges;
		int n;
		boolean[] was;

		public Graph(int n) {
			this.n = n;
			edges = new ArrayList[n];
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new ArrayList<flow_nn.Edge>();
			}
		}

		void addEdge(int from, int to, int flow, int cap) {
			Edge e1 = new Edge(from, to, flow, cap);
			Edge e2 = new Edge(to, from, -flow, 0);
			e1.rev = e2;
			e2.rev = e1;
			edges[from].add(e1);
			edges[to].add(e2);
		}

		long maxFlow(int source, int target) {
			int a = 1 << 30;
			long flow = 0;
			was = new boolean[n];
			while (true) {
				while (true) {
					Arrays.fill(was, false);
					int d = dfs(source, target, a, Integer.MAX_VALUE);
					if (d == 0) {
						break;
					}
					flow += d;
				}
				if ((a & 1) == 1) {
					break;
				}
				a |= a >> 1;
			}
			return flow;
		}

		int dfs(int v, int target, int a, int minFlow) {
			if (v == target) {
				return minFlow;
			}
			was[v] = true;
			for (Edge e : edges[v]) {
				if (was[e.to]) {
					continue;
				}
				if (((e.cap - e.flow) & a) == 0) {
					continue;
				}
				int got = dfs(e.to, target, a,
						Math.min(minFlow, ((e.cap - e.flow) & a)));
				if (got == 0) {
					continue;
				}
				e.flow += got;
				e.rev.flow -= got;
				return got;
			}
			return 0;
		}
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		Graph g = new Graph(n);
		for (int i = 0; i < m; i++) {
			g.addEdge(nextInt() - 1, nextInt() - 1, 0, nextInt());
		}
		out.println(g.maxFlow(0, n - 1));
	}
}