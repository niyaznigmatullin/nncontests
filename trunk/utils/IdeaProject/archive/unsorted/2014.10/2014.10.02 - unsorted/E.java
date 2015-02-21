import java.io.*;
import java.util.*;

public class E {

	static void solve() throws IOException {
		String s = next();
		if (s.length() % 2 == 1) {
			out.println("NO");
			return;
		}
		int[] count = new int[26];
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}
		int max = 0;
		for (int i : count) max = Math.max(max, i);
		if (max * 2 <= s.length()) {
			out.println("YES");
		} else {
			out.println("NO");
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("e.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("e.out");
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
