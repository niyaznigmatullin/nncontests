package Graphs;

import static java.lang.Math.max;

public class HeavyLightDecomposition {
	static class SegmentTreeMax {
		long[] max;
		int n;

		public SegmentTreeMax(int n) {
			this.n = Integer.highestOneBit(n) << 1;
			max = new long[this.n << 1];
		}

		void add(int x, long y) {
			x += n;
			max[x] += y;
			while (x > 1) {
				x >>= 1;
				max[x] = max(max[x << 1], max[2 * x + 1]);
			}
		}

		long getSum(int l, int r) {
			long ret = Long.MIN_VALUE;
			l += n;
			r += n;
			while (l <= r) {
				if ((l & 1) == 1) {
					ret = max(ret, max[l++]);
				}
				if ((r & 1) == 0) {
					ret = max(ret, max[r--]);
				}
				l >>= 1;
				r >>= 1;
			}
			return ret;
		}
	}

	static int[][] edges;
	static int[] size;
	static int[] parent;
	static int[] gran;
	static int[] where;
	static int[][] dp;
	static int[] depth;
	static SegmentTreeMax[] trees;

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

	static int dfs2(int v, int p, int g) {
		int max = -1;
		for (int i = 0; i < edges[v].length; i++) {
			int e = edges[v][i];
			if (e == p) {
				continue;
			}
			if (max == -1 || size[e] > size[max]) {
				max = e;
			}
		}
		if (g == -1) {
			g = v;
		}
		gran[v] = g;
		int got = 0;
		for (int i = 0; i < edges[v].length; i++) {
			int e = edges[v][i];
			if (e == p) {
				continue;
			}
			if (max == e) {
				got = dfs2(e, v, g);
			} else {
				dfs2(e, v, -1);
			}
		}
		where[v] = got;
		if (g == v) {
			trees[v] = new SegmentTreeMax(got + 1);
			return 0;
		} else {
			return got + 1;
		}
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

	static long getMax(int v, int u) {
		long ret = Long.MIN_VALUE;
		while (true) {
			if (gran[v] == gran[u]) {
				ret = max(ret, trees[gran[v]].getSum(where[v], where[u]));
				break;
			}
			int next = gran[v];
			ret = max(ret, trees[next].getSum(where[v], where[next]));
			v = parent[next];
		}
		return ret;
	}

}
