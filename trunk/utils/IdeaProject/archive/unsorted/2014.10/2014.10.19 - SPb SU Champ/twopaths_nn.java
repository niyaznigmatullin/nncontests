import java.io.*;
import java.util.*;

public class twopaths_nn {

	static List<Integer>[] edges;

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		edges = new List[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<>();
		}
		int[] deg = new int[n];
		for (int i = 0; i < m; i++) {
			int v = nextInt() - 1;
			int u = nextInt() - 1;
			edges[v].add(u);
			deg[u]++;
		}
		int[] q = new int[n];
		int head = 0;
		int tail = 0;
		for (int i = 0; i < n; i++) {
			if (deg[i] == 0) {
				q[tail++] = i;
			}
		}
		while (head < tail) {
			int v = q[head++];
			for (int i : edges[v]) {
				deg[i]--;
				if (deg[i] == 0) {
					q[tail++] = i;
				}
			}
		}
		int[] where = new int[n];
		for (int i = 0; i < n; i++) {
			where[q[i]] = i;
		}
		int[][] dp = new int[n][n];
		for (int[] e : dp) {
			Arrays.fill(e, Integer.MAX_VALUE);
		}
		int start1 = nextInt() - 1;
		int finish1 = nextInt() - 1;
		int start2 = nextInt() - 1;
		int finish2 = nextInt() - 1;
		for (int it1 = n - 1; it1 >= 0; it1--) {
			int v = q[it1];
			for (int it2 = n - 1; it2 >= 0; it2--) {
				int u = q[it2];
				if (v == finish1 && u == finish2) {
					dp[v][u] = -1;
				} else {
					if (where[v] < where[u]) {
						for (int e = 0; e < edges[v].size(); e++) {
							int to = edges[v].get(e);
							if (dp[to][u] != Integer.MAX_VALUE) {
								dp[v][u] = to;
							}
						}
					} else if (where[v] > where[u]) {
						for (int e = 0; e < edges[u].size(); e++) {
							int to = edges[u].get(e);
							if (dp[v][to] != Integer.MAX_VALUE) {
								dp[v][u] = to;
							}
						}
					} else {
						for (int e1 = 0; e1 < edges[v].size(); e1++) {
							int to1 = edges[v].get(e1);
							for (int e2 = 0; e2 < edges[v].size(); e2++) {
								if (e1 == e2)
									continue;
								int to2 = edges[v].get(e2);
								if (dp[to1][to2] != Integer.MAX_VALUE) {
									dp[v][v] = to1 * n + to2;
								}
							}
						}
					}
				}
			}
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("two-paths.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("two-paths.out");
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
