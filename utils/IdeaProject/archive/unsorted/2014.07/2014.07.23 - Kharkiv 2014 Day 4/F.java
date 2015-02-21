import java.io.*;
import java.util.*;

public class F {

	static List<Integer>[] edges;

	static void solve() throws IOException {
		int n = nextInt();
		edges = new List[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<>();
		}
		int root = -1;
		for (int i = 0; i < n; i++) {
			int p = nextInt() - 1;
			if (p >= 0) {
				edges[p].add(i);
			} else {
				root = i;
			}
		}
		diam = new int[n];
		x = new int[n];
		dfs1(root);
		dfs2(root, 0);
		for (int i = 0; i < n; i++) {
			out.println(x[i] + " 0 " + (diam[i] >> 1));
		}
	}

	static int[] diam;
	static int[] x;

	static void dfs2(int v, int center) {
		int left = center - (diam[v] >> 1) + 1;
		x[v] = center;
		for (int i : edges[v]) {
			dfs2(i, left + (diam[i] >> 1));
			left += diam[i] + 1;
		}
	}

	static int dfs1(int v) {
		int ret = 1;
		for (int i : edges[v]) {
			ret += dfs1(i) + 1;
		}
		if ((ret & 1) == 1) {
			ret++;
		}
		return diam[v] = ret;
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
