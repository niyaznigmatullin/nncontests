package Graphs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Dijkstra {
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

	static class Element implements Comparable<Element> {
		int v;
		long d;

		public Element(int v, long d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Element o) {
			if (d != o.d) {
				return d < o.d ? -1 : 1;
			}
			return v - o.v;
		}
	}

	static class Graph {
		ArrayList<Edge>[] edges;
		int n;

		@SuppressWarnings("unchecked")
		public Graph(int n) {
			this.n = n;
			edges = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				edges[i] = new ArrayList<Edge>();
			}
		}

		void addEdge(int from, int to, int w) {
			edges[from].add(new Edge(from, to, w));
		}

		long[] dijkstraSlow(int source) {
			long[] d = new long[n];
			Arrays.fill(d, Long.MAX_VALUE);
			d[source] = 0;
			boolean[] was = new boolean[n];
			while (true) {
				int min = -1;
				for (int i = 0; i < n; i++) {
					if (was[i] || d[i] == Long.MAX_VALUE) {
						continue;
					}
					if (min == -1 || d[min] > d[i]) {
						min = i;
					}
				}
				if (min == -1) {
					break;
				}
				was[min] = true;
				for (int i = 0; i < edges[min].size(); i++) {
					Edge e = edges[min].get(i);
					if (d[e.to] > d[e.from] + e.w) {
						d[e.to] = d[e.from] + e.w;
					}
				}
			}
			return d;
		}

		long[] dijkstra(int source) {
			long[] d = new long[n];
			Arrays.fill(d, Long.MAX_VALUE);
			Element[] q = new Element[n];
			for (int i = 0; i < q.length; i++) {
				q[i] = new Element(i, Long.MAX_VALUE);
			}
			q[source].d = d[source] = 0;
			TreeSet<Element> queue = new TreeSet<Element>();
			queue.add(q[source]);
			while (!queue.isEmpty()) {
				Element el = queue.pollFirst();
				for (int i = 0; i < edges[el.v].size(); i++) {
					Edge e = edges[el.v].get(i);
					if (d[e.to] > d[e.from] + e.w) {
						queue.remove(q[e.to]);
						d[e.to] = q[e.to].d = d[e.from] + e.w;
						queue.add(q[e.to]);
					}
				}
			}
			return d;
		}
	}
}
