import java.io.*;
import java.util.*;

public class A {

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			String s = next();
			boolean[] has = new boolean[10];
			for (char c : s.toCharArray()) {
				has[c - '0'] = true;
			}
			StringBuilder ans = new StringBuilder();
			for (int j = 0; j < 10; j++)
				if (!has[j])
					ans.append(j);
			if (ans.length() == 0)
				ans.append("allo");
			out.println(ans);
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
