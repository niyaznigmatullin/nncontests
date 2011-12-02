package Graphs;
import java.util.ArrayList;
import java.util.Arrays;

public class Flow {

	static class Edge {
		int from;
		int to;
		int flow;
		int cap;
		Edge rev;

		public Edge(int from, int to, int flow, int cap) {
			super();
			this.from = from;
			this.to = to;
			this.flow = flow;
			this.cap = cap;
		}

	}

	static class Graph {
		ArrayList<Edge>[] edges;
		int[] cur;
		int[] q;
		int[] d;
		int n;

		public Graph(int n) {
			edges = new ArrayList[n];
			this.n = n;
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new ArrayList<Edge>();
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

		boolean bfs(int source, int target) {
			int head = 0;
			int tail = 1;
			Arrays.fill(d, Integer.MAX_VALUE);
			d[source] = 0;
			q[0] = source;
			while (head < tail) {
				int x = q[head++];
				for (Edge e : edges[x]) {
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

		int dfs(int x, int target, int cMin) {
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

		long getMaxFlow(int source, int target) {
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
	}

}
