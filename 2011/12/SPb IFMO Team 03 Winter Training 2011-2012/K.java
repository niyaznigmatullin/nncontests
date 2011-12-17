import java.io.*;
import java.util.*;

public class K {

	static void solve() throws IOException {
		int tc = nextInt();
		while (tc-- > 0) {
			int n = nextInt();
			List<Pair> res = get(n);
			out.println(res.size());
			boolean first = true;
			for (Pair p : res) {
				if (!first) {
					out.print("  ");
				}
				first = false;
				out.print(p.a + " " + p.b);
			}
			out.println();
		}
	}

	static class Pair {
		int a, b;

		private Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	static List<Pair> get(int n) {
		if (n == 0) {
			return new ArrayList<Pair>();
		}
		if (n == 1) {
			List<Pair> ok = new ArrayList<Pair>();
			ok.add(new Pair(0, 0));
			return ok;
		}
		if ((n & 1) == 0) {
			int ok = Integer.numberOfTrailingZeros(n);
			List<Pair> res = get(n >> ok);
			for (Pair p : res) {
				p.a += ok;
			}
			return res;
		}
		int threePow = 0;
		int n1 = n;
		int val = 1;
		while (n1 >= 3) {
			threePow++;
			n1 /= 3;
			val *= 3;
		}
		List<Pair> res = get(n - val);
		res.add(new Pair(0, threePow));
		return res;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("23sum.in"));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}