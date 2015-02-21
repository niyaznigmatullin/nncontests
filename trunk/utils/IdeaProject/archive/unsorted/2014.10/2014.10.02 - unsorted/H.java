import java.io.*;
import java.util.*;

public class H {

	static void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int l = 1;
		int r = 1000000001;
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = nextInt();
		}
		int[] b = new int[n];
		for (int i = 0; i < b.length; i++) {
			b[i] = nextInt();
		}
		while (l < r - 1) {
			int mid = (l + r) >> 1;
			long count = 0;
			for (int i = 0; i < n; i++) {
				if (a[i] >= mid)
					count += (a[i] - mid) / b[i] + 1;
			}
			if (count < m)
				r = mid;
			else
				l = mid;
		}
		long ans = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] >= r) {
				int z = (a[i] - r) / b[i] + 1;
				m -= z;
				ans += (long) a[i] * z;
				ans -= (long) z * (z - 1) / 2 * b[i];
			}
		}
		ans += (long) (r - 1) * m;
		out.println(ans);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("h.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("h.out");
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
