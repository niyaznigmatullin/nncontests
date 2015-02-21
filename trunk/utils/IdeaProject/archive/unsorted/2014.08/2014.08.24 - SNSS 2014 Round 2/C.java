import java.io.*;
import java.util.*;

public class C {

	static void solve() throws IOException {
		char[] s = new StringBuilder(next()).reverse().toString().toCharArray();
		s = Arrays.copyOf(s, s.length + 1);
		s[s.length - 1] = '0';
		{
			int i = 0;
			while (s[i] == '9') {
				s[i] = '0';
				++i;
			}
			++s[i];
			if (s[s.length - 1] == '0') {
				s = Arrays.copyOf(s, s.length - 1);
			}
		}
		char[] t = new StringBuilder(next()).reverse().toString().toCharArray();
		for (int i = 0; i < t.length; i++) {
			if (t[i] == '6')
				t[i] = '9';
		}
		int ans = 0;
		int cur = 0;
		for (int i = 0; i < t.length; i++) {
			if (t[i] == '9')
				++cur;
		}
		final int MOD = 10000000 - 3;
		int[] pow = new int[t.length + 1];
		pow[0] = 1;
		for (int i = 1; i < pow.length; i++) {
			pow[i] = (pow[i - 1] + pow[i - 1]) % MOD;
		}
		if (s.length != t.length) {
			out.println(pow[cur]);
			return;
		}
		for (int i = t.length - 1; i >= 0; i--) {
			if (t[i] == '9')
				--cur;
			if (t[i] == '9') {
				if (s[i] > '6') {
					ans = (ans + pow[cur]) % MOD;
				}
			} else {
				if (s[i] > t[i]) {
					ans = (ans + pow[cur]) % MOD;
				}
			}
			if (t[i] == '9') {
				if (s[i] != '9' && s[i] != '6')
					break;
			} else {
				if (s[i] != t[i])
					break;
			}
		}
		out.println(ans);
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
