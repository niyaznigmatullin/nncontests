import static java.lang.Math.min;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class I {

	private static void solve() throws IOException {
		int n = nextInt();
		int[][] g = new int[n][n];
		int edges = nextInt();
		int k = nextInt();
		for (int i = 0; i < edges; i++) {
			int u = nextInt() - 1;
			int v = nextInt() - 1;
			int l = nextInt();
			g[u][v] = g[v][u] = l;
		}
		int[][] to = new int[k + 1][n];
		int[] path = new int[k];
		for (int i = 0; i < k; i++) {
			path[i] = nextInt();
		}
		int INF = Integer.MAX_VALUE;
		for (int[] to1 : to) {
			fill(to1, INF);
		}
		fill(to[k], -1);
		for (int it = k - 1; it >= 0; --it) {
			int w = path[it];
			for (int u = 0; u < n; u++) {
				if (to[it + 1][u] == INF) {
					continue;
				}
				for (int v = 0; v < n; v++) {
					if (g[u][v] != w) {
						continue;
					}
					to[it][v] = min(to[it][v], u);
				}
			}
		}
		int start = -1;
		for (int i = 0; i < n; i++) {
			if (to[0][i] != INF) {
				start = i;
				break;
			}
		}
		if (start < 0) {
			out.println("No solution");
			return;
		}
		out.print(start + 1);
		for (int i = 0; i < k; i++) {
			start = to[i][start];
			out.print(' ');
			out.print(start + 1);
		}
		out.println();
	}

	public static void main(String[] args) {
		try {
			br = new BufferedReader(new FileReader("pathfind.in"));
			out = new PrintWriter("pathfind.out");
			solve();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(239);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null)
				return null;
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