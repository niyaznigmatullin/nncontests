import java.io.*;
import java.util.*;

public class A {

	static void solve() throws IOException {
		char[] c = next().toCharArray();
		int balance = 0;
		int n = c.length;
		int[] f = new int[n];
		for (int i = 0; i < n; i++) {
			if (c[i] == '(') {
				++balance;
			} else if (c[i] == ')') {
				--balance;
			}
			f[i] = balance;
			if (balance < 0) {
				out.println(-1);
				return;
			}
		}
		for (int i = n - 2; i >= 0; i--) {
			f[i] = Math.min(f[i], f[i + 1]);
		}
		int last = -1;
		for (int i = 0; i < n; i++) if (c[i] == '#') last = i;
		int cur = 0;
		int cn = 0;
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			if (c[i] == '#') {
				int add = i == last ? balance - cur : 1;
				if (add <= 0) {
					out.println(-1);
					return;
				}
				ans[cn++] = add;
				cur += add;
			}
			if (f[i] < cur) {
				out.println(-1);
				return;
			}
		}
		if (balance != cur) {
			out.println(-1);
			return;
		}
		for (int i = 0; i < cn; i++) {
			out.println(ans[i]);			
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("a.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("a.out");
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
