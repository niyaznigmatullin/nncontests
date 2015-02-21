import java.io.*;
import java.util.*;

public class B {

	static void solveCase() throws IOException {
		String s = br.readLine();
		int n = 1;
		while (n * (n - 1) / 2 != s.length())
			++n;
		if (n < 5) {
			out.println("YES");
			return;
		}
		if (n == 5) {
			if (s.contains("0")) {
				out.println("YES");
			} else {
				out.println("NO");
			}
			return;
		}
		boolean[][] a = new boolean[6][6];
		for (int i = 0, k = 0; i < 6; i++) {
			for (int j = 0; j < i; j++, k++) {
				a[j][i] = a[i][j] = s.charAt(k) == '1';
			}
		}
		// int[] deg = new int[n];
		// int edgesCount = 0;
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < n; j++) {
		// if (a[i][j])
		// deg[i]++;
		// }
		// }
		// int count3 = 0;
		// int count2 = 0;
		// for (int i = 0; i < n; i++) {
		// if (deg[i] > 3)
		// ++count3;
		// if (deg[i] > 2)
		// ++count2;
		// edgesCount += deg[i];
		// }
		// edgesCount /= 2;
		// if (count3 <= 4 && count2 <= 5) {
		// out.println("YES");
		// return;
		// }
		// if (edgesCount > 3 * n - 6) {
		// out.println("NO");
		// return;
		// }
		for (int mask = 0; mask < 1 << 6; mask++) {
			if (Integer.bitCount(mask) != 3)
				continue;
			boolean isk33 = true;
			all: for (int i = 0; i < 6; i++) {
				if (((mask >> i) & 1) == 1)
					continue;
				for (int j = 0; j < 6; j++) {
					if (((mask >> j) & 1) == 0)
						continue;
					if (!a[i][j]) {
						isk33 = false;
						break all;
					}
				}
			}
			if (isk33) {
				out.println("NO");
				return;
			}
		}
		// for (int sixth = 0; sixth < 6; sixth++) {
		// for (int v1 = 0; v1 < 6; v1++) {
		// for (int v2 = 0; v2 < 6; v2++) {
		// if (v1 == v2 || v1 == sixth || v2 == sixth)
		// continue;
		// boolean[][] b = new boolean[6][6];
		// for (int i = 0; i < 6; i++) {
		// if (i == sixth)
		// continue;
		// for (int j = 0; j < 6; j++) {
		// if (j == sixth || i == j)
		// continue;
		// if (a[i][j]) {
		// b[i][j] = true;
		// }
		// }
		// }
		// if (a[sixth][v1] && a[sixth][v2]) {
		// b[v1][v2] = b[v2][v1] = true;
		// }
		// boolean isk5 = true;
		// all: for (int i = 0; i < 6; i++) {
		// for (int j = 0; j < 6; j++) {
		// if (i != j && i != sixth && j != sixth && !b[i][j]) {
		// isk5 = false;
		// break all;
		// }
		// }
		// }
		// if (isk5) {
		// out.println("NO");
		// return;
		// }
		// }
		// }
		// }
		for (int sixth = 0; sixth < 6; sixth++) {
			for (int to = 0; to < 6; to++) {
				if (!a[sixth][to])
					continue;
				if (sixth == to)
					continue;
				boolean[][] b = new boolean[6][6];
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if (a[i][j]) {
							b[i][j] = true;
						}
					}
				}
				for (int i = 0; i < 6; i++) {
					if (a[i][sixth]) {
						b[to][i] = b[i][to] = true;
					}
				}
				boolean isk5 = true;
				all: for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if (i != j && i != sixth && j != sixth && !b[i][j]) {
							isk5 = false;
							break all;
						}
					}
				}
				if (isk5) {
					out.println("NO");
					return;
				}
			}
		}
		out.println("YES");
	}

	static void solve() throws IOException {
		int x = Integer.parseInt(br.readLine());
		for (int i = 0; i < x; i++) {
			solveCase();
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("b.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("b.out");
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
