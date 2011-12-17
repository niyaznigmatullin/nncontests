import java.io.*;
import java.util.*;

public class G {

	static char[][] c;
	static boolean[][] was;
	static int n;
	static int m;

	static int[] DX = { 1, 0, -1, 0 };
	static int[] DY = { 0, 1, 0, -1 };

	static int dfs(int x, int y) {
		int ret = 0;
		if (c[x][y] == 'G') {
			ret++;
		}
		was[x][y] = true;
		for (int dir = 0; dir < 4; dir++) {
			int xx = x + DX[dir];
			int yy = y + DY[dir];
			if (xx < 0 || yy < 0 || xx >= n || yy >= m) {
				continue;
			}
			if (c[xx][yy] == 'T') {
				return ret;
			}
		}
		for (int dir = 0; dir < 4; dir++) {
			int xx = x + DX[dir];
			int yy = y + DY[dir];
			if (xx < 0 || yy < 0 || xx >= n || yy >= m || c[xx][yy] == '#'
					|| was[xx][yy]) {
				continue;
			}
			ret += dfs(xx, yy);
		}
		return ret;
	}

	static void solve() throws IOException {
		m = nextInt();
		n = nextInt();
		c = new char[n][];
		for (int i = 0; i < n; i++) {
			c[i] = nextToken().toCharArray();
		}
		was = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (c[i][j] == 'P') {
					out.println(dfs(i, j));
					return;
				}
			}
		}
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