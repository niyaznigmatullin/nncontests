import java.io.*;
import java.util.*;

public class E {

	static final int MOD = 1000000007;

	static void solve() throws IOException {
		long n = nextLong();
		int k = nextInt();
		int S = 70;
		int[] sum = new int[S];
		long[] len = new long[S];
		sum[0] = k - 1;
		len[0] = k - 1;
		for (int i = 1; i < S; i++) {
			sum[i] = ((k - 1) * (i + 1)) % k;
			len[i] = (len[i - 1] * k + k - 1);
			double z = 1. * len[i - 1] * k + k - 1;
			if (z > 2 * n) {
				S = i;
				break;
			}
		}
		int[][][] ones = new int[k][k + 1][k + 1];
		for (int i = 0; i < k; i++) {
			for (int j = 0; j <= k; j++) {
				if (j < k)
					ones[i][(j + i) % k][j] = 1;
				else
					ones[i][j][j] = 1;
				ones[i][i][j] = 1;
			}
		}
		int[][][] ms = new int[S][k + 1][k + 1];
		ms[0] = ones[1];
		for (int j = 1; j < k - 1; j++) {
			ms[0] = mul(ones[1], ms[0]);
		}
		for (int f = 1; f < S; f++) {
			int[][] a = mul(ms[f - 1], ones[(f + 1) % k]);
			ms[f] = ms[f - 1];
			for (int i = 0; i < k - 1; i++) {
				ms[f] = mul(a, ms[f]);
			}
		}
		int[][] m = new int[k + 1][k + 1];
		for (int i = 0; i <= k; i++) {
			m[i][i] = 1;
		}
		for (int i = S - 1; i >= 0; i--) {
			while (n >= len[i] + 1) {
				m = mul(ms[i], m);
				m = mul(ones[(i + 2) % k], m);
				n -= len[i] + 1;
			}
		}
		while (n > 0) {
			m = mul(ones[1], m);
			--n;
		}
		int ans = 0;
		for (int i = 0; i <= k; i++) {
			ans = add(ans, m[i][k]);
		}
		out.println(ans);
	}

	static int[][] mul(int[][] a, int[][] b) {
		int[][] ret = new int[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				int d = 0;
				for (int k = 0; k < b.length; k++) {
					d = add(d, mul(a[i][k], b[k][j]));
				}
				ret[i][j] = d;
			}
		}
		return ret;
	}

	static int add(int a, int b) {
		a += b;
		if (a >= MOD)
			a -= MOD;
		return a;
	}

	static int mul(int a, int b) {
		return (int) ((long) a * b % MOD);
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
