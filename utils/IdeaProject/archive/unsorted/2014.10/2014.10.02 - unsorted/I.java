import java.io.*;
import java.util.*;

public class I {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int k = nextInt();
		boolean[][] hasEdge = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(hasEdge[i], true);
		}
		for (int i = 0; i < m; i++) {
			int x = nextInt() - 1;
			int y = nextInt() - 1;
			hasEdge[x][y] = false;
			hasEdge[y][x] = false;
		}
		DSU dsu = new DSU(n);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (hasEdge[i][j]) {
					dsu.union(i, j);
				}
			}
		}
		int comps = 0;
		int[] ans = new int[n];
		Arrays.fill(ans, -1);
		for (int i = 0; i < n; i++) {
			if (i == dsu.get(i)) {
				ans[i] = comps++;
			}
			for (int j = i + 1; j < n; j++) {
				if (!hasEdge[i][j] && dsu.get(i) == dsu.get(j)) {
					out.println("-1");
					return;
				}
			}
		}
		if (comps > k) {
			out.println("-1");
			return;
		}
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				out.print(' ');
			}
			out.print(1 + ans[dsu.get(i)]);
		}
		out.println();
	}

	static class DSU {
		int[] p;

		DSU(int n) {
			p = new int[n];
			for (int i = 0; i < n; i++)
				p[i] = i;
		}

		int get(int x) {
			return x == p[x] ? x : (p[x] = get(p[x]));
		}

		void union(int x, int y) {
			p[get(x)] = get(y);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("i.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("i.out");
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
