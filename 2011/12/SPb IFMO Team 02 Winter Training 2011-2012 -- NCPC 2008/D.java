import static java.lang.Math.min;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class D {

	static void solve() throws IOException {
		int n = nextInt();
		int[][] year = new int[n][n];
		for (int i = 0; i < n; i++) {
			fill(year[i], 2008);
			year[i][i] = 0;
		}
		int c = nextInt();
		int minYear = 2008;
		for (int i = 0; i < c; i++) {
			int a = nextInt() - 1, b = nextInt() - 1;
			int y = nextInt();
			year[a][b] = year[b][a] = y;
			minYear = min(minYear, y);
		}
		boolean[][] graph = new boolean[n][n];
		for (int y = minYear - 1; y <= 2008; y++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (j != i && year[i][j] == y) {
						graph[i][j] = true;
					}
				}
			}
			int[] clique = getClique(graph);
			if (clique != null) {
				out.println(y + 1);
				return;
			}
		}
		out.println("Impossible");
	}

	static int[] getClique(boolean[][] graph) {
		int n = graph.length;
		int[] countForDeg = new int[n];
		for (int i = 0; i < n; i++) {
			int d = deg(graph[i]);
			countForDeg[d]++;
		}
		int[][] listForDeg = new int[n][];
		for (int i = 0; i < n; i++) {
			listForDeg[i] = new int[countForDeg[i]];
		}
		fill(countForDeg, 0);
		for (int i = 0; i < n; i++) {
			int d = deg(graph[i]);
			listForDeg[d][countForDeg[d]++] = i;
		}
		int[] clique = new int[n];
		boolean[] inClique = new boolean[n];
		int size = 0;
		for (int deg = n - 1; deg >= 0; --deg) {
			int curCount = listForDeg[deg].length;
			if (curCount == 0) {
				continue;
			}
			boolean canAddAll = true;
			ok: for (int u : listForDeg[deg]) {
				boolean[] g = graph[u];
				for (int i = 0; i < size; i++) {
					if (!g[clique[i]]) {
						canAddAll = false;
						break ok;
					}
				}
				for (int v : listForDeg[deg]) {
					if (v != u && !g[v]) {
						canAddAll = false;
						break;
					}
				}
			}
			if (canAddAll) {
				for (int u : listForDeg[deg]) {
					clique[size++] = u;
					inClique[u] = true;
				}
			} else {
				int canAddAny = -1;
				for (int u : listForDeg[deg]) {
					boolean[] g = graph[u];
					boolean canAddThis = true;
					for (int i = 0; i < size; i++) {
						if (!g[clique[i]]) {
							canAddThis = false;
							break;
						}
					}
					if (canAddThis) {
						canAddAny = u;
						break;
					}
				}
				if (canAddAny >= 0) {
					clique[size++] = canAddAny;
					inClique[canAddAny] = true;
				}
				break;
			}
		}
		int[] notClique = new int[n];
		int notCliqueSize = 0;
		for (int i = 0; i < n; i++) {
			if (!inClique[i]) {
				notClique[notCliqueSize++] = i;
			}
		}
		for (int i = 0; i < notCliqueSize; i++) {
			for (int j = 0; j < i; j++) {
				int u = notClique[i];
				int v = notClique[j];
				if (graph[u][v]) {
					return null;
				}
			}
		}

		if (size * 3 <= n * 2 && notCliqueSize * 3 <= n * 2) {
			return copyOf(clique, size);
		} else {
			int size1 = size - 1;
			int notCliqueSize1 = notCliqueSize + 1;
			if (size1 * 3 <= n * 2 && notCliqueSize1 * 3 <= n * 2) {
				int canRemove = -1;
				removeWhat: for (int i = 0; i < size; i++) {
					int u = clique[i];
					boolean[] g = graph[u];
					for (int j = 0; j < notCliqueSize; j++) {
						int v = notClique[j];
						if (g[v]) {
							continue removeWhat;
						}
					}
					canRemove = i;
					break;
				}
				notClique[notCliqueSize++] = clique[canRemove];
				clique[canRemove] = clique[--size];
				return copyOf(clique, size);
			} else {
				return null;
			}
		}

	}

	static int deg(boolean[] g) {
		int d = 0;
		for (int i = 0; i < g.length; i++) {
			if (g[i]) {
				d++;
			}
		}
		return d;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}