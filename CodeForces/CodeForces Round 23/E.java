import java.io.*;
import java.math.*;
import java.util.*;

public class E implements Runnable {
	public static void main(String[] args) {
		new E().run();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;
		boolean eof;
		String buf;

		public FastScanner(String fileName) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(fileName));
			nextToken();
		}

		public FastScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
			nextToken();
		}

		String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					eof = true;
					break;
				}
			}
			String ret = buf;
			buf = eof ? "-1" : st.nextToken();
			return ret;
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}

		void close() {
			try {
				br.close();
			} catch (Exception e) {

			}
		}

		boolean isEOF() {
			return eof;
		}
	}

	FastScanner sc;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
			sc = new FastScanner(System.in);
			out = new PrintWriter(System.out);
			solve();
			sc.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	ArrayList<Integer>[] edges;

	BigInteger dfs(int x, int parent) {
		BigInteger ret = BigInteger.ONE;
		int one = 0;
		boolean two = false;
		for (int i : edges[x]) {
			if (i == parent) {
				continue;
			}
			BigInteger got = dfs(i, x);
			if (got.equals(BigInteger.ONE)) {
				++one;
			} else {
				if (got.equals(BigInteger.valueOf(2))) {
					two = true;
				}
				ret = ret.multiply(got);
			}
		}
		if (one > 0) {
			ret = ret.multiply(BigInteger.valueOf(one + 1));
		} else {
			// ret = ret.sh
		}
		return BigInteger.ZERO;
	}

	void solve() {
		int n = nextInt();
		edges = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n - 1; i++) {
			int x = nextInt() - 1;
			int y = nextInt() - 1;
			edges[x].add(y);
			edges[y].add(x);
		}
		BigInteger ans = dfs(0, -1);
		out.println(ans);
	}
}