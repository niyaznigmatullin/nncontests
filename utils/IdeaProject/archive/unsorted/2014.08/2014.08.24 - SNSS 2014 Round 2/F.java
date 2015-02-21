import java.io.*;
import java.util.*;

public class F {

	static class Edge {
		int fromLine;
		int fromVertex;
		int toLine;
		int toVertex;
		int cost;

		public Edge(int fromLine, int fromVertex, int toLine, int toVertex,
				int cost) {
			this.fromLine = fromLine;
			this.fromVertex = fromVertex;
			this.toLine = toLine;
			this.toVertex = toVertex;
			this.cost = cost;
		}

	}

	static void solveTest() throws IOException {
		int cost0 = nextInt();
		int n = nextInt();
		int m = nextInt();
		int a = nextInt() - 1;
		int b = nextInt() - 1;
		List<Edge>[][] edges = new List[m][n];
		boolean[][] hasOnLine = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				edges[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < m; i++) {
			int count = nextInt();
			int[] x = new int[count];
			int[] t = new int[count];
			for (int j = 0; j < count; j++) {
				x[j] = nextInt() - 1;
				hasOnLine[i][x[j]] = true;
				t[j] = nextInt();
			}
			for (int j = 0; j + 1 < count; j++) {
				edges[i][x[j]].add(new Edge(i, x[j], i, x[j + 1], t[j + 1]
						- t[j]));
				edges[i][x[j + 1]].add(new Edge(i, x[j + 1], i, x[j], t[j + 1]
						- t[j]));
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < m; k++) {
					if (j == k)
						continue;
					if (hasOnLine[j][i] && hasOnLine[k][i]) {
						edges[j][i].add(new Edge(j, i, k, i, cost0));
					}
				}
			}
		}
		int[][] d = new int[m][n];
		for (int[] e : d) {
			Arrays.fill(e, Integer.MAX_VALUE);
		}
		for (int i = 0; i < m; i++) {
			if (hasOnLine[i][a]) {
				d[i][a] = 0;
			}
		}
		boolean[][] was = new boolean[m][n];
		while (true) {
			int minLine = -1;
			int minVertex = -1;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (was[i][j] || d[i][j] == Integer.MAX_VALUE)
						continue;
					if (minLine < 0 || d[minLine][minVertex] > d[i][j]) {
						minLine = i;
						minVertex = j;
					}
				}
			}
			if (minLine < 0)
				break;
			if (minVertex == b) {
				break;
			}
			was[minLine][minVertex] = true;
			for (Edge e : edges[minLine][minVertex]) {
				if (d[e.toLine][e.toVertex] > d[minLine][minVertex] + e.cost) {
					d[e.toLine][e.toVertex] = d[minLine][minVertex] + e.cost;
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++)
			ans = Math.min(ans, d[i][b]);
		out.println(ans);
	}

	static void solve() throws IOException {
		int testCount = nextInt();
		for (int i = 0; i < testCount; i++) {
			solveTest();
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("f.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("f.out");
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
