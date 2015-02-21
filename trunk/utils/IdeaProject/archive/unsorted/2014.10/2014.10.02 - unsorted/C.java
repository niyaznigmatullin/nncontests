import java.io.*;
import java.util.*;

public class C {

	static void solve() throws IOException {
		int n = nextInt();
		int[] d = new int[123456];
		int cn = 0;
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				d[cn++] = i;
				if (i * i != n)
					d[cn++] = n / i;
			}
		}
		long[] dd = new long[1234567];
		int cc = 0;
		for (int i = 0; i < cn; i++) {
			for (int j = i; j < cn; j++) {
				long z = (long) d[i] * d[j];
				dd[cc++] = z;
			}
		}
		Arrays.sort(dd, 0, cc);
		long[] ans = new long[cc];
		int ac = 0;
		for (int i = 0; i < cc; i++) {
			if (i == 0 || dd[i] != dd[i - 1]) {
				ans[ac++] = dd[i];
			}
		}
		out.println(ac);
		for (int i = 0; i < ac; i++) {
			out.println(n + ans[i] + " " + (n + (long) n * n / ans[i]));
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("c.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("c.out");
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
