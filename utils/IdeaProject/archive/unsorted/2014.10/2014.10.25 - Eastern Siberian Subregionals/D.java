import java.io.*;
import java.util.*;

public class D {

	static List<Integer>[] edges;
	static int[] was;
	static int[] depth;

	static int dfs(int v, int d) {
		was[v] = 1;
		depth[v] = d;
		int ans = Integer.MAX_VALUE;
		for (int e = 0; e < edges[v].size(); e++) {
			int to = edges[v].get(e);
			if (was[to] == 1) {
				if (depth[to] < d - 1) {
					ans = Math.min(ans, d - depth[to] + 1);
				}
				continue;
			}
			if (was[to] == 0) {
				ans = Math.min(ans, dfs(to, d + 1));
			}
		}
		was[v] = 2;
		return ans;
	}

	static void solve() throws IOException {
		int t = nextInt();
		for (int ct = 0; ct < t; ct++) {
			int n = nextInt();
			int m = nextInt();
			edges = new List[n];
			for (int i = 0; i < n; i++)
				edges[i] = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				int a = nextInt() - 1;
				int b = nextInt() - 1;
				edges[a].add(b);
				edges[b].add(a);
			}
			was = new int[n];
			depth = new int[n];
			int ans = Integer.MAX_VALUE;
			for (int it = 0; it < 15; it++) {
				Arrays.fill(was, 0);
				for (int i = 0; i < n; i++) {
					Collections.shuffle(edges[i]);
				}
				for (int i = 0; i < n; i++) {
					if (was[i] == 0) {
						ans = Math.min(ans, dfs(i, 0));
					}
				}
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
