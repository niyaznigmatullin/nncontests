import java.io.*;
import java.util.*;

public class C {

	static class Edge {
		int from;
		int to;

		public Edge(int from, int to) {
			if (from > to) {
				int t = from;
				from = to;
				to = t;
			}
			this.from = from;
			this.to = to;
		}

		@Override
		public int hashCode() {
			final int prime = 33533;
			int result = 1;
			result = prime * result + from;
			result = prime * result + to;
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
			Edge other = (Edge) obj;
			if (from != other.from)
				return false;
			if (to != other.to)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + "]";
		}

	}

	static class Graph {
		int start;
		int finish;
		List<Edge> edges;
		int n;

		public Graph(int n, int start, int finish) {
			this.n = n;
			this.start = start;
			this.finish = finish;
			edges = new ArrayList<>(2);
		}

		void add(int v, int u) {
			edges.add(new Edge(v, u));
		}
	}

	static Graph two() {
		Graph ret = new Graph(2, 0, 1);
		ret.add(0, 1);
		return ret;
	}

	static final GraphExpression TWO = new SimpleGraphExpression(two());

	static abstract class Expression {

	}

	static abstract class ListExpression extends Expression {
		abstract List<Graph> genGraphList(int[] varsValues);
	}

	static abstract class GraphExpression extends Expression {
		abstract Graph genGraph(int[] varsValues);
	}

	static class SimpleGraphExpression extends GraphExpression {
		Graph g;

		public SimpleGraphExpression(Graph g) {
			super();
			this.g = g;
		}

		@Override
		Graph genGraph(int[] varsValues) {
			return g;
		}

	}

	static class ConnectExpression extends GraphExpression {
		ListExpression graphList;

		public ConnectExpression(ListExpression graphList) {
			this.graphList = graphList;
		}

		@Override
		Graph genGraph(int[] varsValues) {
			List<Graph> list = graphList.genGraphList(varsValues);
			if (list.size() == 1) {
				return list.get(0);
			}
			int size = 1;
			for (Graph e : list) {
				size += e.n - 1;
			}
			Graph g = new Graph(size, 0, -1);
			int curSize = 1;
			int curFirst = 0;
			for (Graph e : list) {
				for (Edge f : e.edges) {
					int v = f.from;
					int u = f.to;
					if (v == 0) {
						v = curFirst;
					} else {
						v += curSize - 1;
					}
					if (u == 0) {
						u = curFirst;
					} else {
						u += curSize - 1;
					}
					g.add(v, u);
				}
				curFirst = curSize + e.finish - 1;
				curSize += e.n - 1;
			}
			g.finish = curFirst;
			return g;
		}
	}

	static class LoopExpression extends GraphExpression {
		ListExpression graphList;

		public LoopExpression(ListExpression graphList) {
			this.graphList = graphList;
		}

		@Override
		Graph genGraph(int[] varsValues) {
			List<Graph> list = graphList.genGraphList(varsValues);
			if (list.size() == 1) {
				return list.get(0);
			}
			int size = 0;
			for (Graph e : list) {
				size += e.n - 1;
			}
			Graph g = new Graph(size, 0, list.get(0).finish);
			int curSize = 1;
			int curFirst = 0;
			int veryFirst = 0;
			for (int i = 0; i < list.size(); i++) {
				Graph e = list.get(i);
				for (Edge f : e.edges) {
					int v = f.from;
					int u = f.to;
					if (i + 1 == list.size() && v == e.finish) {
						v = veryFirst;
					} else if (v == 0) {
						v = curFirst;
					} else {
						if (i + 1 == list.size() && v > e.finish)
							--v;
						v += curSize - 1;
					}
					if (i + 1 == list.size() && u == e.finish) {
						u = veryFirst;
					} else if (u == 0) {
						u = curFirst;
					} else {
						if (i + 1 == list.size() && u > e.finish)
							--u;
						u += curSize - 1;
					}
					g.add(v, u);
				}
				curFirst = curSize + e.finish - 1;
				curSize += e.n - 1;
			}
			return g;
		}
	}

	static class MergeExpression extends GraphExpression {
		ListExpression graphList;

		public MergeExpression(ListExpression graphList) {
			this.graphList = graphList;
		}

		@Override
		Graph genGraph(int[] varsValues) {
			List<Graph> list = graphList.genGraphList(varsValues);
			if (list.size() == 1) {
				return list.get(0);
			}
			int size = 1;
			for (Graph e : list) {
				size += e.n - 1;
			}
			Graph firstG = list.get(0);
			Graph g = new Graph(size, firstG.start, firstG.finish);
			int curSize = 1;
			for (Graph e : list) {
				for (Edge f : e.edges) {
					int v = f.from;
					int u = f.to;
					if (v == e.start) {
						v = 0;
					} else {
						if (v > e.start)
							--v;
						v += curSize;
					}
					if (u == e.start) {
						u = 0;
					} else {
						if (u > e.start)
							--u;
						u += curSize;
					}
					g.add(v, u);
				}
				curSize += e.n - 1;
			}
			return g;
		}
	}

	static class SimpleListExpression extends ListExpression {
		List<GraphExpression> list;

		public SimpleListExpression(List<GraphExpression> list) {
			this.list = list;
		}

		@Override
		List<Graph> genGraphList(int[] varsValues) {
			List<Graph> ret = new ArrayList<>();
			for (GraphExpression e : list) {
				ret.add(e.genGraph(varsValues));
			}
			return ret;
		}

	}

	static class RepeatExpression extends ListExpression {
		NumberExpression number;
		GraphExpression graph;

		public RepeatExpression(NumberExpression number, GraphExpression graph) {
			this.number = number;
			this.graph = graph;
		}

		@Override
		List<Graph> genGraphList(int[] varsValues) {
			List<Graph> ret = new ArrayList<>();
			int val = number.getValue(varsValues);
			Graph g = graph.genGraph(varsValues);
			for (int i = 0; i < val; i++) {
				ret.add(g);
			}
			return ret;
		}
	}

	static class RangeExpression extends ListExpression {
		int varID;
		NumberExpression from;
		NumberExpression to;
		GraphExpression graph;

		public RangeExpression(int varID, NumberExpression from,
				NumberExpression to, GraphExpression graph) {
			this.varID = varID;
			this.from = from;
			this.to = to;
			this.graph = graph;
		}

		@Override
		List<Graph> genGraphList(int[] varsValues) {
			int a = from.getValue(varsValues);
			int b = to.getValue(varsValues);
			if (a > b) {
				int t = a;
				a = b;
				b = t;
			}
			List<Graph> ret = new ArrayList<>();
			for (int i = a; i <= b; i++) {
				int old = varsValues[varID];
				varsValues[varID] = i;
				ret.add(graph.genGraph(varsValues));
				varsValues[varID] = old;
			}
			return ret;
		}

	}

	static interface NumberExpression {
		int getValue(int[] varsValues);
	}

	static class IntNumberExpression implements NumberExpression {
		int value;

		public IntNumberExpression(int value) {
			this.value = value;
		}

		@Override
		public int getValue(int[] varsValues) {
			return value;
		}

	}

	static class VarNumberExpression implements NumberExpression {
		int varID;

		public VarNumberExpression(int varID) {
			this.varID = varID;
		}

		@Override
		public int getValue(int[] varsValues) {
			if (varsValues[varID] == Integer.MIN_VALUE)
				throw new AssertionError();
			return varsValues[varID];
		}

	}

	static char[] c;
	static int cur;

	static NumberExpression parseNumberOrVar() {
		if (c[cur] >= 'A' && c[cur] <= 'Z') {
			return new VarNumberExpression(c[cur++] - 'A');
		} else {
			int number = 0;
			while (cur < c.length && c[cur] >= '0' && c[cur] <= '9') {
				number = number * 10 + c[cur] - '0';
				++cur;
			}
			return new IntNumberExpression(number);
		}
	}

	static GraphExpression parseGraph() {
		if (cur + 1 < c.length && c[cur] == 'c' && c[cur + 1] == '(') {
			cur += 2;
			GraphExpression ret = new ConnectExpression(parseList());
			cur++;
			return ret;
		}
		if (c[cur] == 'c') {
			++cur;
			return TWO;
		}
		if (cur + 4 < c.length && c[cur] == 'l' && c[cur + 1] == 'o'
				&& c[cur + 2] == 'o' && c[cur + 3] == 'p' && c[cur + 4] == '(') {
			cur += 5;
			GraphExpression ret = new LoopExpression(parseList());
			cur++;
			return ret;
		}
		if (cur + 1 < c.length && c[cur] == 't' && c[cur + 1] == '(') {
			cur += 2;
			GraphExpression ret = new MergeExpression(parseList());
			cur++;
			return ret;
		}
		throw new AssertionError();
	}

	static ListExpression parseList() {
		if (c[cur] == 'c' || c[cur] == 'l' || c[cur] == 't') {
			List<GraphExpression> list = new ArrayList<>();
			list.add(parseGraph());
			while (cur < c.length && c[cur] == ',') {
				++cur;
				list.add(parseGraph());
			}
			return new SimpleListExpression(list);
		}
		if (c[cur] >= '0' && c[cur] <= '9' || c[cur] >= 'A' && c[cur] <= 'Z') {
			return new RepeatExpression(parseNumberOrVar(), parseIfGraph());
		}
		if (cur + 5 < c.length && c[cur] == 'r' && c[cur + 1] == 'a'
				&& c[cur + 2] == 'n' && c[cur + 3] == 'g' && c[cur + 4] == 'e'
				&& c[cur + 5] == '(') {
			cur += 6;
			int varID = c[cur] - 'A';
			cur += 2;
			NumberExpression from = parseNumberOrVar();
			cur++;
			NumberExpression to = parseNumberOrVar();
			cur++;
			return new RangeExpression(varID, from, to, parseIfGraph());
		}
		throw new AssertionError();
	}

	private static GraphExpression parseIfGraph() {
		GraphExpression need;
		if (cur < c.length && c[cur] == ',') {
			++cur;
			need = parseGraph();
		} else {
			need = TWO;
		}
		return need;
	}

	static void solve() throws IOException {
		c = br.readLine().replaceAll(" ", "").toCharArray();
		cur = 0;
		int[] varsValues = new int[26];
		for (int i = 0; i < 26; i++)
			varsValues[i] = Integer.MIN_VALUE;
		Graph graph = parseGraph().genGraph(varsValues);
		int[] deg = new int[graph.n];
		for (Edge e : graph.edges) {
			deg[e.from]++;
			deg[e.to]++;
		}
		int odd = -1;
		Set<Edge> badEdges = new HashSet<>();
		for (int i = 0; i < graph.n; i++) {
			if (deg[i] % 2 == 0)
				continue;
			if (odd < 0) {
				odd = i;
			} else {
				badEdges.add(new Edge(i, odd));
				graph.add(i, odd);
				odd = -1;
			}
		}
		edges = new List[graph.n];
		for (int i = 0; i < graph.n; i++) {
			edges[i] = new ArrayList<>();
		}
		for (Edge e : graph.edges) {
			DirectedEdge e1 = new DirectedEdge(e.from, e.to);
			DirectedEdge e2 = new DirectedEdge(e.to, e.from);
			e1.rev = e2;
			e2.rev = e1;
			edges[e.from].add(e1);
			edges[e.to].add(e2);
		}
		path = new ArrayList<>();
		curEdge = new int[graph.n];
		dfs(0);
		List<List<Integer>> answer = new ArrayList<>();
		List<Integer> currentPath = new ArrayList<>();
		for (int i : path) {
			if (!currentPath.isEmpty()
					&& badEdges.contains(new Edge(i, currentPath
							.get(currentPath.size() - 1)))) {
				badEdges.remove(new Edge(i,
						currentPath.get(currentPath.size() - 1)));
				answer.add(new ArrayList<>(currentPath));
				currentPath.clear();
			}
			currentPath.add(i);
		}
		if (!answer.isEmpty()) {
			currentPath.remove(currentPath.size() - 1);
			currentPath.addAll(answer.get(0));
			answer.set(0, currentPath);
		} else {
			answer.add(currentPath);
		}
		int[] id = new int[graph.n];
		for (int i = 0; i < graph.n; i++) {
			id[i] = i;
		}
		{
			int t = id[graph.finish];
			id[graph.finish] = id[graph.n - 1];
			id[graph.n - 1] = t;
		}
		out.println(graph.n + " " + answer.size());
		for (List<Integer> e : answer) {
			out.print(e.size());
			for (int i : e) {
				out.print(" " + (id[i] + 1));
			}
			out.println();
		}
	}

	static void dfs(int v) {
		while (curEdge[v] < edges[v].size()) {
			DirectedEdge e = edges[v].get(curEdge[v]);
			if (e.removed) {
				++curEdge[v];
				continue;
			}
			e.removed = true;
			e.rev.removed = true;
			dfs(e.to);
		}
		path.add(v);
	}

	static int[] curEdge;
	static List<Integer> path;
	static List<DirectedEdge>[] edges;

	static class DirectedEdge {
		int from;
		int to;
		DirectedEdge rev;
		boolean removed;

		public DirectedEdge(int from, int to) {
			this.from = from;
			this.to = to;
		}

	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("cactus.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("cactus.out");
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
