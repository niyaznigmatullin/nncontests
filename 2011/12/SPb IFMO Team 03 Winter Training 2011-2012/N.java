import java.io.*;
import java.util.*;

public class N {

	static final int data[][] = { { 0, 1, 2, 0, 1, 3 }, { 0, 2, 3, 0, 2, 3 },
			{ 0, 3, 4, 0, 3, 3 }, { 0, 0, 1, 0, 4, 3 }, { 1, 2, 2, 1, 5, 0 },
			{ 2, 2, 3, 1, 5, 3 }, { 3, 2, 4, 1, 5, 2 }, { 4, 2, 1, 1, 5, 1 }, };

	static void solve() throws IOException {
		int tests = nextInt();
		for (int test = 0; test < tests; test++) {
			String cube = nextToken();
			Set<String> angles = new HashSet<String>();
			for (int view[] : data) {
				for (int shift = 0; shift < 3; shift++) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < 3; i++) {
						sb.append(cube.charAt(view[(i + shift) % 3 * 2] * 2));
						sb.append((char) (((cube
								.charAt(view[(i + shift) % 3 * 2] * 2 + 1) - '1') + view[(i + shift) % 3 * 2 + 1]) % 4 + '1'));
					}
					angles.add(sb.toString());
				}
			}
			int cnt = 0;
			boolean ans[] = new boolean[5];
			for (int i = 0; i < 5; i++) {
				if (ans[i] = angles.contains(nextToken())) {
					++cnt;
				}
			}
			out.print(test + 1 + " " + cnt);
			for (int i = 0; i < 5; i++) {
				out.print(" " + (ans[i] ? "Y" : "N"));
			}
			out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("spatial.in"));
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