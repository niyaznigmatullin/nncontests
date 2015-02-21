import java.io.*;
import java.util.*;

public class G {

	static void solve() throws IOException {
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			long a = nextLong();
			long b = nextLong();
			out.println(f(a, b));
		}
	}

	static long f(long a, long b) {
		long ans = 0;
		while (true) {
			int best = -1;
			for (int i = 0; i < 60; i++) {
				long two = (1L << i) - 1;
				if ((a & two) != 0 || (b & two) != 0) {
					continue;
				}
				if ((Long.bitCount(a >> i) & 1) == (Long.bitCount(b >> i) & 1)) {
					best = i;
				}
			}
			if (best < 0)
				break;
			long add = 1L << best;
			a += add;
			b += add;
			ans += add;
		}
		return ans;
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("treasure.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("treasure.out");
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
