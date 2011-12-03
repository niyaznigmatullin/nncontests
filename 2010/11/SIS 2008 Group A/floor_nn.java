import java.io.*;
import java.util.*;
import java.math.*;

public class floor_nn implements Runnable {
	public static void main(String[] args) {
		new floor_nn().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}

		BigInteger nextBigInteger() {
			return new BigInteger(nextToken());
		}

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner("floor.in");
			out = new PrintWriter("floor.out");
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	class Graph {
		int n;
		int m;
		ArrayList<Integer>[] edges;
		boolean[] matched;
		int[] cur;
		int[] p;
		int[] d;
		int[] q;

		public Graph(int n, int m) {
			this.n = n;
			this.m = m;
			edges = new ArrayList[n];
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new ArrayList<Integer>();
			}
			matched = new boolean[n];
			d = new int[n + 1];
			p = new int[m];
			Arrays.fill(p, n);
		}

		void addEdge(int from, int to) {
			edges[from].add(to);
		}

		boolean bfs() {
			int head = 0;
			int tail = 0;
			for (int i = 0; i < n; i++) {
				if (!matched[i]) {
					q[tail++] = i;
					d[i] = 0;
				} else {
					d[i] = Integer.MAX_VALUE;
				}
			}
			d[n] = Integer.MAX_VALUE;
			while (head < tail) {
				int x = q[head++];
				for (int i = 0; i < edges[x].size(); i++) {
					int e = edges[x].get(i);
					int v = p[e];
					if (d[v] == Integer.MAX_VALUE) {
						d[v] = d[x] + 1;
						if (v == n) {
							return true;
						}
						q[tail++] = v;
					}
				}
			}
			return false;
		}

		int getMatching() {
			cur = new int[n];
			q = new int[n];
			int ans = 0;
			while (bfs()) {
				Arrays.fill(cur, 0);
				for (int i = 0; i < n; i++) {
					if (!matched[i]) {
						dfs(i);
					}
				}
			}
			for (int i = 0; i < n; i++) {
				if (matched[i]) {
					++ans;
				}
			}
			return ans;
		}

		boolean dfs(int v) {
			if (v == n) {
				return true;
			}
			for (int i = cur[v]; i < edges[v].size(); cur[v] = ++i) {
				int e = edges[v].get(i);
				int u = p[e];
				if (d[u] != d[v] + 1) {
					continue;
				}
				if (dfs(u)) {
					matched[v] = true;
					p[e] = v;
					return true;
				}
			}
			return false;
		}

	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int a = nextInt();
		int b = nextInt();
		long time = System.currentTimeMillis();
		char[][] c = new char[n][];
		for (int i = 0; i < c.length; i++) {
			c[i] = nextToken().toCharArray();
		}
		int count = 0;
		int cur1 = 0;
		int cur2 = 0;
		int[][] num = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (c[i][j] == '*') {
					++count;
					if ((i + j) % 2 == 0) {
						num[i][j] = cur1++;
					} else {
						num[i][j] = cur2++;
					}
				}
			}
		}
		Graph g = new Graph(cur1, cur2);
		if (2 * b <= a) {
			out.println(count * b);
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = (i & 1); j < m; j += 2) {
				if (c[i][j] != '*') {
					continue;
				}
				for (int dir = 0; dir < 4; dir++) {
					int x = i + DX[dir];
					int y = j + DY[dir];
					if (x < 0 || y < 0 || x >= n || y >= m || c[x][y] != '*') {
						continue;
					}
					g.addEdge(num[i][j], num[x][y]);
				}
			}
		}
		int ans = g.getMatching();
		out.println(a * ans + b * (count - 2 * ans));
		System.err.println(System.currentTimeMillis() - time);
	}

	final int[] DX = { 1, 0, -1, 0 };
	final int[] DY = { 0, 1, 0, -1 };

}