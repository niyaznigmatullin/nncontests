import java.io.*;
import java.util.*;

public class F {

	static void solve() throws IOException {
		int t = nextInt();
		for (int ct = 0; ct < t; ct++) {
			int n = nextInt();
			int k = nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			long l = -1;
			long r = Long.MAX_VALUE / 3;
			while (l < r - 1) {
				long mid = l + r >> 1;
				long make = 0;
				for (int i = 0; i < n; i++) {
					make += mid / a[i];
					if (make >= k)
						break;
				}
				if (make >= k)
					r = mid;
				else
					l = mid;
			}
			out.println(r);
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
