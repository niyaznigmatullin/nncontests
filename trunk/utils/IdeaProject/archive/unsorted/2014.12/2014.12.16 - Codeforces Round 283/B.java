import java.io.*;
import java.util.*;

public class B {

	static void solve() throws IOException {
		int n = nextInt();
		int[] a = new int[n];
		int[] cnt = new int[2];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt() - 1;
			cnt[a[i]]++;
		}
		int[][] b = new int[2][];
		b[0] = new int[cnt[0]];
		b[1] = new int[cnt[1]];
		cnt[0] = cnt[1] = 0;
		for (int i = 0; i < n; i++) {
			b[a[i]][cnt[a[i]]++] = i;
		}
		int ac = 0;
		int[] as = new int[n];
		int[] at = new int[n];
		all: for (int s = 1; s <= n; s++) {
			int cur0 = 0;
			int cur1 = 0;
			int scoreA = 0;
			int scoreB = 0;
			int last = -1;
			while (cur0 < b[0].length || cur1 < b[1].length) {
				int next0 = cur0 + s - 1;
				int next1 = cur1 + s - 1;
				int when = Integer.MAX_VALUE;
				int first = -1;
				int second = -1;
				if (next0 < b[0].length) {
					when = Math.min(when, b[0][next0]);
					first = b[0][next0];
				}
				if (next1 < b[1].length) {
					when = Math.min(when, b[1][next1]);
					second = b[1][next1];
				}
				if (when == Integer.MAX_VALUE) {
					continue all;
				}
				cur0 = find(b[0], when);
				cur1 = find(b[1], when);
				if (when == first) {
					++scoreA;
					last = 0;
				} else {
					++scoreB;
					last = 1;
				}
			}
			if (scoreA > scoreB && last == 0) {
				as[ac] = s;
				at[ac] = scoreA;
				++ac;
			}
			if (scoreA < scoreB && last == 1) {
				as[ac] = s;
				at[ac] = scoreB;
				++ac;
			}
		}
		for (int i = 0; i < ac; i++) {
			for (int j = i + 1; j < ac; j++) {
				if (at[i] > at[j] || at[i] == at[j] && as[i] > as[j]) {
					int t = at[i];
					at[i] = at[j];
					at[j] = t;
					t = as[i];
					as[i] = as[j];
					as[j] = t;
				}
			}
		}
		out.println(ac);
		for (int i = 0; i < ac; i++) {
			out.println(at[i] + " " + as[i]);
		}
	}

	static int find(int[] a, int x) {
		int l = -1;
		int r = a.length;
		while (l < r - 1) {
			int mid = (l + r) >> 1;
			if (a[mid] > x)
				r = mid;
			else
				l = mid;
		}
		return r;
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
