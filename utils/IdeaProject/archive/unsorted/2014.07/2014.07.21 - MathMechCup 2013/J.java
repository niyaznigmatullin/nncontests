import java.io.*;
import java.util.*;

public class J {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int[][] d = new int[n][];
		for (int i = 0; i < n; i++) {
			d[i] = new int[nextInt()];
			for (int j = 0; j < d[i].length; j++) {
				d[i][j] = nextInt() - 1;
			}
		}
		int[] x = new int[m];
		int[] y = new int[m];
		for (int i = 0; i < m; i++) {
			x[i] = nextInt() - 1;
			y[i] = nextInt() - 1;
		}
		int[] ans = new int[m];
		Arrays.fill(ans, Integer.MAX_VALUE);
		Random rand = new Random();
		long[] z = new long[1000000];
		long[] parties = new long[n + m];
		for (int it = 0; it < 1; it++) {
			for (int i = 0; i < z.length; i++) {
				z[i] = ((long) rand.nextInt() << 32) | rand.nextInt();
			}
			Map<Long, Integer> was = new HashMap<>();
			for (int i = 0; i < n; i++) {
				long cur = 0;
				int k = d[i].length;
				for (int j = 0; j < k; j++) {
					cur ^= z[d[i][j]];
				}
				parties[i] = cur;
				add(was, cur);
			}
			for (int i = n; i < n + m; i++) {
				parties[i] = parties[x[i - n]] ^ parties[y[i - n]];
				Integer f = was.get(parties[i]);
				if (f == null)
					f = 0;
				ans[i - n] = Math.min(ans[i - n], f);
				was.put(parties[i], f + 1);
			}
		}
		for (int i = 0; i < m; i++) {
			if (i > 0)
				out.print(' ');
			out.print(ans[i]);
		}
		out.println();
	}

	static void add(Map<Long, Integer> was, long cur) {
		Integer z = was.get(cur);
		if (z == null)
			z = 0;
		++z;
		was.put(cur, z);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("party.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("party.out");
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
