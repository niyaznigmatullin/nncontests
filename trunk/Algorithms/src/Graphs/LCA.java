package Graphs;


public class LCA {

	static int[][] edges;
	static int[] size;
	static int[] parent;
	static int[][] dp;
	static int[] depth;

	static int dfs(int v, int p, int d) {
		size[v] = 1;
		parent[v] = p;
		depth[v] = d;
		for (int i = 0; i < edges[v].length; i++) {
			int e = edges[v][i];
			if (e == p) {
				continue;
			}
			size[v] += dfs(e, v, d + 1);
		}
		return size[v];
	}

	static void initLCA(int n) {
		int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n)) + 2;
		dp = new int[m][n];
		for (int i = 0; i < n; i++) {
			dp[0][i] = parent[i] < 0 ? i : parent[i];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
			}
		}
	}

	static int lca(int v, int u) {
		if (depth[u] > depth[v]) {
			int t = u;
			u = v;
			v = t;
		}
		for (int i = dp.length - 1; i >= 0; i--) {
			if (depth[v] - depth[u] >= 1 << i) {
				v = dp[i][v];
			}
		}
		if (depth[v] != depth[u]) {
			throw new AssertionError();
		}
		if (u == v) {
			return u;
		}
		for (int i = dp.length - 1; i >= 0; i--) {
			if (dp[i][u] != dp[i][v]) {
				u = dp[i][u];
				v = dp[i][v];
			}
		}
		if (parent[u] != parent[v]) {
			throw new AssertionError();
		}
		return parent[u];
	}
}
