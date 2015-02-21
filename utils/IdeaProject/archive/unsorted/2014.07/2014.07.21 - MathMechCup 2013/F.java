import java.io.*;
import java.util.*;

public class F {

	static String[][] NAMES = { { "Dmitry", "Egor", "Pavel" },
			{ "Nikolay", "RomanA", "Vladimir" },
			{ "Igor", "RomanB", "Vyacheslav" } };

	static boolean[][][] was;

	static void solve() throws IOException {
		int n = nextInt();
		was = new boolean[3][3][3];
		for (int i = 0; i < 3 * n; i++) {
			int[] a = new int[3];
			Arrays.fill(a, -1);
			for (int j = 0; j < 3; j++) {
				String s = next();
				for (int v = 0; v < 3; v++) {
					for (int u = 0; u < 3; u++) {
						if (NAMES[v][u].equals(s)) {
							a[v] = u;
						}
					}
				}
			}
			was[a[0]][a[1]][a[2]] = true;
		}
		ans = 0;
		cur = new ArrayList<>();
		best = new ArrayList<>();
		go(0);
		out.println(best.size() / 3);
		for (int i = 0; i < best.size(); i += 3) {
			for (int j = 0; j < 3; j++) {
				int id = best.get(i + j);
				int a = id / 9;
				int b = id / 3 % 3;
				int c = id % 3;
				out.println(NAMES[0][a] + " " + NAMES[1][b] + " " + NAMES[2][c]);
			}
		}
	}

	static int ans;
	static List<Integer> cur, best;

	static void go(int v) {
		if (ans == 9)
			return;
		if (v > ans) {
			best = new ArrayList<>(cur);
			ans = v;
		}
		for (int f1 = 0; f1 < 3; f1++) {
			for (int f2 = 0; f2 < 3; f2++) {
				if (was[0][f1][f2])
					continue;
				for (int s1 = 0; s1 < 3; s1++) {
					if (s1 == f1)
						continue;
					for (int s2 = 0; s2 < 3; s2++) {
						if (s2 == f2 || was[1][s1][s2])
							continue;
						int t1 = f1 ^ s1 ^ 3;
						int t2 = f2 ^ s2 ^ 3;
						if (was[2][t1][t2])
							continue;
						was[0][f1][f2] = true;
						was[1][s1][s2] = true;
						was[2][t1][t2] = true;
						cur.add(f1 * 3 + f2);
						cur.add(9 + s1 * 3 + s2);
						cur.add(18 + t1 * 3 + t2);
						go(v + 1);
						cur.remove(cur.size() - 1);
						cur.remove(cur.size() - 1);
						cur.remove(cur.size() - 1);
						was[0][f1][f2] = false;
						was[1][s1][s2] = false;
						was[2][t1][t2] = false;
					}
				}
			}
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("teams.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("teams.out");
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
