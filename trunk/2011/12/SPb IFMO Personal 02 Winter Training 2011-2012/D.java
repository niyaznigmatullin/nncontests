import static java.lang.System.arraycopy;
import static java.lang.System.exit;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class D {

	static void solve() throws Exception {
		int n = nextInt();
		int m = nextInt();
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int c = System.in.read();
				while (c == 13 || c == 10) {
					c = System.in.read();
				}
				a[i][j] = c;
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[i][j] == INTER) {
					count++;
				}
			}
		}
		int[] x = new int[count];
		int[] y = new int[count];
		for (int i = 0, cur = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[i][j] == INTER) {
					x[cur] = i;
					y[cur++] = j;
				}
			}
		}
		int fx = -1;
		int fy = -1;
		all: for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[i][j] != ' ') {
					fx = i;
					fy = j;
					break all;
				}
			}
		}
		int[][] mask = new int[n][m];
		for (int i = fx, j = fy, dir = 0;;) {
			if (((mask[i][j] >> dir) & 1) == 1) {
				break;
			}
			mask[i][j] |= 1 << dir;
			i += DX[dir];
			j += DY[dir];
			if (a[i][j] == LU || a[i][j] == RU || a[i][j] == LD
					|| a[i][j] == RD) {
				dir = (dir + 1) & 3;
			}
		}
		int[][] has = new int[n][m];
		for (int[] d : has) {
			fill(d, -1);
		}
		for (int i = 0; i < count; i++) {
			has[x[i]][y[i]] = i;
		}
		int ans = 0;
		boolean[] allcando = new boolean[count];
		Random rand = new Random();
		all: for (int it = 0; ans < count && it < 150000; it++) {
			boolean[] cando = new boolean[count];
			int got = 0;
			for (int i = 0; i < count; i++) {
				cando[i] = rand.nextBoolean();
				if (cando[i]) {
					got++;
				}
			}
			while (got <= ans) {
				for (int i = 0; i < count; i++) {
					if (!cando[i]) {
						cando[i] = rand.nextBoolean();
						got += cando[i] ? 1 : 0;
					}
				}				
			}
			int[][] was = new int[n][m];
			for (int i = fx, j = fy, dir = 0;;) {
				if (((was[i][j] >> dir) & 1) == 1) {
					break;
				}
				was[i][j] |= 1 << dir;
				i += DX[dir];
				j += DY[dir];
				if (has[i][j] >= 0 && cando[has[i][j]]) {
					int z = (1 << dir) | (1 << ((dir + 1) & 3));
					if (mask[i][j] == z) {
						dir = (dir + 1) & 3;
					} else {
						dir = (dir + 3) & 3;
					}
				} else if (a[i][j] == LU || a[i][j] == RU || a[i][j] == LD
						|| a[i][j] == RD) {
					dir = (dir + 1) & 3;
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (a[i][j] != ' ' && was[i][j] == 0) {
						continue all;
					}
				}
			}
			ans = got;
			arraycopy(cando, 0, allcando, 0, count);
		}
		out.println(ans);
		for (int i = 0; i < count; i++) {
			if (allcando[i]) {
				out.println(x[i] + " " + y[i]);
			}
		}
	}

	static int[] DX = { 0, 1, 0, -1 };
	static int[] DY = { 1, 0, -1, 0 };

	final static int INTER = 197;
	final static int LU = 218;
	final static int RU = 191;
	final static int LD = 192;
	final static int RD = 217;

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static int nextInt() throws IOException {
		int c = System.in.read();
		while (c < '0' || c > '9') {
			c = System.in.read();
		}
		int ret = 0;
		while (c >= '0' && c <= '9') {
			ret = ret * 10 + c - '0';
			c = System.in.read();
		}
		return ret;
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
			}
			st = new StringTokenizer(s);
		}
		return st.nextToken();
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
			}
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			br.close();
			out.close();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			List<Void> list = new ArrayList<Void>();
			while (true) {
				list.add(null);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}
