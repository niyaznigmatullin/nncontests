import java.io.*;
import java.util.*;

public class G {

	static void solve() throws IOException {
		int n = nextInt();
		int s = nextInt();
		long[] a = new long[n + 1];
		Arrays.fill(a, Integer.MAX_VALUE);
		a[0] = 1;
		for (int i = 1; i <= n; i++) {
			int x = nextInt();
			a[i] = Math.min(a[i], a[i - 1] * x);
		}
		long ans = 0;
		for (int i = n; i >= 0; i--) {
			ans += s / a[i];
			s %= a[i];
		}
		out.println(ans);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("g.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("g.out");
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
