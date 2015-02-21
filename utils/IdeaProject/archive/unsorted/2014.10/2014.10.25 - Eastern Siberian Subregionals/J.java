import java.io.*;
import java.util.*;

public class J {

	static boolean[][] has;
	static int n, m;

	static int ans;

	static void go(int x, int y, int have) {
		if (ans < have)
			ans = have;
		if (y == m) {
			go(x + 1, 0, have);
			return;
		}
		// if (((n - x - 1) * m + (m - y)) / 20 + have <= ans)
		// return;
		// if (x + 4 > n)
		// return;
		if (x == n)
			return;
		for (int dx = 4; dx <= 5; dx++) {
			int dy = 9 - dx;
			if (x + dx > n || y + dy > m)
				continue;
			boolean found = false;
			all: for (int i = 0; i < dx; i++) {
				for (int j = 0; j < dy; j++) {
					if (has[x + i][y + j]) {
						found = true;
						break all;
					}
				}
			}
			if (found)
				continue;
			for (int i = 1; i + 1 < dx; i++) {
				for (int j = 1; j + 1 < dy; j++) {
					has[x + i][y + j] = true;
				}
			}
			go(x, y + 1, have + 1);
			for (int i = 1; i + 1 < dx; i++) {
				for (int j = 1; j + 1 < dy; j++) {
					has[x + i][y + j] = false;
				}
			}
		}
		go(x, y + 1, have);
	}

	static void solve() throws IOException {
		int t = nextInt();
		for (int ct = 0; ct < t; ct++) {
			n = nextInt();
			m = nextInt();
			int k = nextInt();
			has = new boolean[n][m];
			for (int i = 0; i < k; i++) {
				int x = nextInt();
				int y = nextInt();
				int w = nextInt();
				int h = nextInt();
				for (int dx = 0; dx < w; dx++) {
					for (int dy = 0; dy < h; dy++) {
						has[x + dx][y + dy] = true;
					}
				}
			}
			ans = 0;
			go(0, 0, 0);
			out.println(ans);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("j.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("j.out");
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
