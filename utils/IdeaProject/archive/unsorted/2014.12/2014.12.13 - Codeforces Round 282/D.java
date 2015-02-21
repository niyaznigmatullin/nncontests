import java.io.*;
import java.util.*;

public class D {

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

	static List<Edge>[] edges;
	static int[] sumSquares;
	static int[] sum;
	static int[] size;
	static int[] upSumSquares;
	static int[] upSum;
	static int[] upSize;

	static int addToSquare(int old, int sum, int size, int weight) {
		return add(old,
				add(mul(mul(weight, weight), size), mul(weight, add(sum, sum))));
	}

	static int addToLinear(int old, int size, int weight) {
		return add(old, mul(size, weight));
	}

	static final int K = 20;
	static int[] en;
	static int[] ex;
	static int T;
	static int[] pv;
	static int[][] pp;
	static int[] depth;

	static boolean anc(int v, int u) {
		return en[v] <= en[u] && ex[u] <= ex[v];
	}

	static void dfs(int v, int p, int d) {
		pv[v] = p;
		depth[v] = d;
		en[v] = T++;
		size[v] = 1;
		for (Edge e : edges[v]) {
			if (e.to == p)
				continue;
			dfs(e.to, v, add(d, e.w));
			size[v] += size[e.to];
			sum[v] = add(sum[v], addToLinear(sum[e.to], size[e.to], e.w));
			sumSquares[v] = add(
					addToSquare(sumSquares[e.to], sum[e.to], size[e.to], e.w),
					sumSquares[v]);
		}
		ex[v] = T;
	}

	static void dfs2(int v, int p, int sizeUp, int sumUp, int sqUp) {
		upSumSquares[v] = sqUp;
		upSum[v] = sumUp;
		upSize[v] = sizeUp;
		for (Edge e : edges[v]) {
			if (e.to == p)
				continue;
			int cur = addToSquare(sumSquares[e.to], sum[e.to], size[e.to], e.w);
			int nSqUp = add(sqUp, add(sumSquares[v], MOD - cur));
			int nSizeUp = sizeUp + size[v] - size[e.to];
			int nSumUp = add(sumUp,
					add(sum[v], MOD - addToLinear(sum[e.to], size[e.to], e.w)));
			dfs2(e.to, v, nSizeUp, add(nSumUp, mul(nSizeUp, e.w)),
					addToSquare(nSqUp, nSumUp, nSizeUp, e.w));
		}
	}

	static final int MOD = 1000000007;

	static int add(int a, int b) {
		a += b;
		if (a >= MOD) {
			a -= MOD;
		}
		return a;
	}

	static int mul(int a, int b) {
		return (int) ((long) a * b % MOD);
	}

	static int lca(int v, int u) {
		if (anc(v, u))
			return v;
		for (int i = K - 1; i >= 0; i--) {
			if (!anc(pp[i][v], u))
				v = pp[i][v];
		}
		return pv[v];
	}

	static void solve() throws IOException {
		int n = nextInt();
		edges = new List[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i + 1 < n; i++) {
			int v = nextInt() - 1;
			int u = nextInt() - 1;
			int w = nextInt();
			edges[v].add(new Edge(v, u, w));
			edges[u].add(new Edge(u, v, w));
		}
		T = 0;
		en = new int[n];
		ex = new int[n];
		depth = new int[n];
		sumSquares = new int[n];
		upSumSquares = new int[n];
		sum = new int[n];
		size = new int[n];
		upSum = new int[n];
		upSize = new int[n];
		pv = new int[n];
		dfs(0, -1, 0);
		dfs2(0, -1, 0, 0, 0);
		int m = nextInt();
		pp = new int[K][n];
		for (int i = 0; i < n; i++) {
			pp[0][i] = pv[i] < 0 ? i : pv[i];
		}
		for (int i = 1; i < K; i++) {
			for (int v = 0; v < n; v++) {
				pp[i][v] = pp[i - 1][pp[i - 1][v]];
			}
		}
		for (int i = 0; i < m; i++) {
			int u = nextInt() - 1;
			int v = nextInt() - 1;
			int ans = add(sumSquares[u], upSumSquares[u]);
			if (anc(v, u)) {
				int stree = addToSquare(
						upSumSquares[v],
						upSum[v],
						upSize[v],
						add(add(depth[v], depth[u]),
								MOD - mul(2, depth[lca(v, u)])));
				ans = add(MOD - add(stree, stree), ans);
			} else {
				int stree = addToSquare(
						sumSquares[v],
						sum[v],
						size[v],
						add(add(depth[v], depth[u]),
								MOD - mul(2, depth[lca(v, u)])));
				ans = add(add(stree, stree), MOD - ans);
			}
			out.println(ans);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("d.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("d.out");
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
