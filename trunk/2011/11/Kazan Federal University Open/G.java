import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class G {

	static String[] bits = { "1011111", "0000101", "1110110", "1110101",
			"0101101", "1111001", "1111011", "1000101", "1111111", "1111101" };

	static void solve() throws Exception {
		String[] s = new String[3];
		for (int i = 0; i < 3; i++) {
			s[i] = br.readLine();
			while (s[i].length() < 27) {
				s[i] += " ";
			}
		}
		int[] mask = new int[10];
		for (int i = 0; i < 10; i++) {
			mask[i] = Integer.parseInt(bits[i], 2);
		}
		int[] got = new int[9];
		for (int i = 0; i < 9; i++) {
			int pos = i * 3;
			int curMask = 0;
			for (int j = 0; j < 3; j++) {
				curMask <<= 1;
				if (s[j].charAt(pos + 1) == '_') {
					curMask |= 1;
				}
			}
			for (int j = 1; j < 3; j++) {
				for (int k = 0; k < 3; k += 2) {
					curMask <<= 1;
					if (s[j].charAt(pos + k) == '|') {
						curMask |= 1;
					}
				}
			}
			got[i] = curMask;
		}
		Set<Answer> set = new HashSet<Answer>();
		loop: for (int i = 0; i < 9; i++) {
			int[] cur = new int[9];
			all: for (int j = 0; j < 9; j++) {
				if (j != i) {
					for (int t = 0; t < 10; t++) {
						if (mask[t] == got[j]) {
							cur[j] = t;
							continue all;
						}
					}
					continue loop;
				}
			}
			int mod = 0;
			int coef = 9 - i;
			int div = 1;
			for (int j = 0; j < 9; j++) {
				mod = (mod + (9 - j) * cur[j]) % 11;
				div = (div * coef) % 11;
			}
			mod = 11 - mod;
			mod = mod * div % 11;
			if (mod < 10 && (mask[mod] & got[i]) == got[i]) {
				cur[i] = mod;
				set.add(new Answer(cur));
			}
		}
		System.err.println(set);
		int found = set.size();
		if (found == 0) {
			out.println("failure");
		} else if (found > 1) {
			out.println("ambiguous");
		} else {
			int[] ans = set.iterator().next().ans;
			for (int i = 0; i < 9; i++) {
				out.print(ans[i]);
			}
			out.println();
		}
	}

	static class Answer {
		int[] ans;

		public Answer(int[] ans) {
			this.ans = ans;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(ans);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Answer other = (Answer) obj;
			if (!Arrays.equals(ans, other.ans))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return Arrays.toString(ans);
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) {
		try {
			long time = System.currentTimeMillis();
			File file = new File("g.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("g.out"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
			br.close();
			System.err.println("Runtime = "
					+ (System.currentTimeMillis() - time) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}