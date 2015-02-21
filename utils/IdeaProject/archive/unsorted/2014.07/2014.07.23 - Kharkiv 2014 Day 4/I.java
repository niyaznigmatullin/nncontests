import java.io.*;
import java.util.*;

public class I {

	static void solve() throws IOException {
		int n = nextInt();
		int a = nextInt();
		int b = nextInt();
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = nextInt();
			if (a < 0) {
				c[i] = -c[i];
			}
		}
		if (a < 0) {
			a = -a;
		}
		long max = c[0];
		long ans = max;
		for (int i = 1; i < n; i++) {
			if (max < 0) {
				max = c[i];
			} else {
				max += c[i];
			}
			ans = Math.max(ans, max);
		}
		out.println(a * ans + b);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("i.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("i.out");
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
