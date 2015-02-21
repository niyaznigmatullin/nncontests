import java.io.*;
import java.util.*;

public class K {

	static int[] left;
	static int[] right;
	static int[] bonus;
	static boolean[] was;
	static boolean[][] can;

	static void dfs(int v) {
		if (was[v])
			return;
		was[v] = true;
		if (left[v] < 0) {
			can[bonus[v]][v] = true;
			can[1 ^ bonus[v]][v] = false;
		} else {
			dfs(left[v]);
			dfs(right[v]);
			if (!can[0][left[v]] || !can[0][right[v]]) {
				can[1 ^ bonus[v]][v] = true;
			}
			if (!can[1][left[v]] || !can[1][right[v]]) {
				can[bonus[v]][v] = true;
			}
			if ((!can[0][left[v]] || !can[0][right[v]])
					&& (!can[1][left[v]] || !can[1][right[v]])) {
				can[1 ^ bonus[v]][v] = true;
			}
			if ((!can[1][left[v]] || !can[0][right[v]])
					&& (!can[0][left[v]] || !can[1][right[v]])) {
				can[bonus[v]][v] = true;
			}
		}
	}

	static void solve() throws IOException {
		int n = nextInt();
		left = new int[n];
		right = new int[n];
		bonus = new int[n];
		for (int i = 0; i < n; i++) {
			left[i] = nextInt() - 1;
			right[i] = nextInt() - 1;
			bonus[i] = nextInt();
		}
		was = new boolean[n];
		can = new boolean[2][n];
		for (int i = 0; i < n; i++) {
			dfs(i);
			out.println(can[0][i] ? "Ksusha" : "Sasha");
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("k.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("k.out");
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
