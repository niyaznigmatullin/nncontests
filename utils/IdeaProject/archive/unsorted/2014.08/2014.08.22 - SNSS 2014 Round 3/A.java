import java.io.*;
import java.util.*;

public class A {

	static final int MOD = 1000000007;

	static int mul(int a, int b) {
		return (int) ((long) a * b % MOD);
	}

	static int add(int a, int b) {
		a += b;
		if (a >= MOD)
			a -= MOD;
		return a;
	}

	static void solve() throws IOException {
		int testCount = nextInt();
		for (int curTest = 0; curTest < testCount; curTest++) {
			int b = nextInt();
			int nb = nextInt();
			int nc = nextInt();
			int nt = nextInt();
			int ns = nextInt();
			int[] d = new int[b + 1];
			Arrays.fill(d, nb);
			d[0] = 0;
			int[] dc = getIt(b + 1, nc);
			int[] dt = getIt(b + 3, nt);
			int[] ds = getIt(b + 1, ns);
			// System.err.println(Arrays.toString(d));
			// System.err.println(Arrays.toString(dc));
			// System.err.println(Arrays.toString(dt));
			// System.err.println(Arrays.toString(ds));
			for (int[] f : new int[][] { dc, dt, ds }) {
				int shift = f.length - b - 1;
				int[] next = new int[b + 1];
				for (int i = 0; i <= b; i++) {
					for (int j = 0; j < f.length; j++) {
						int k = (i + Math.max(0, j - shift));
						if (k > b)
							break;
						next[k] = add(next[k], mul(f[j], d[i]));
					}
				}
				d = next;
				// System.err.println(Arrays.toString(d));
			}
			int ans = 0;
			for (int i : d)
				ans = add(ans, i);
			out.println(ans);
		}
	}

	static final int N = 1001;
	static int[][] c = new int[N][N];
	static {
		for (int i = 0; i < N; i++) {
			c[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				c[i][j] = add(c[i - 1][j], c[i - 1][j - 1]);
			}
		}
	}

	static int[] getIt(int b, int count) {
		int[] ret = new int[b + 1];
		for (int i = 0; i <= b; i++) {
			for (int j = i; j <= i && j <= count; j++) {
				ret[i] = add(ret[i], c[count][j]);
			}
		}
		return ret;
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
