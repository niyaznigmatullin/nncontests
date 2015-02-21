import java.io.*;
import java.util.*;

public class F {

	static void solve() throws IOException {
		int n = nextInt();
		int k = nextInt();
		int a1 = nextInt();
		if (n == 1) {
			out.println(0);
			return;
		}
		for (int i = 0; i < n - 2; i++) {
			next();
		}
		int an = nextInt();
		int dif = Math.abs(an - a1);
		if (k <= dif) {
			dif -= k;
			out.println(dif);
		} else {
			k -= dif;
			if (n == 2 && (k & 1) == 1) {
				out.println(1);
			} else {
				out.println(0);
			}
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("f.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("f.out");
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
