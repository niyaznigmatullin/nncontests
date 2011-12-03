import java.io.*;
import java.util.*;
import java.math.*;

public class J implements Runnable {
	public static void main(String[] args) {
		new J().run();
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

		BigInteger nextBigInteger() {
			return new BigInteger(nextToken());
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
			sc = new FastScanner("just.in");
			out = new PrintWriter("just.out");
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

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	final int MAXSUM = 9 * 12;
	final int MAXSUM2 = 9 * 6;
	final int MAXN = 1000000;

	void solve() {
		long n = nextLong();
		long ans = 0;
		if (n == 1000000000000L) {
			++ans;
			--n;
		}
		int[] sum = new int[MAXN];
		int[] sum2 = new int[MAXN];
		for (int i = 1; i < MAXN; i++) {
			sum[i] = sum[i / 10] + i % 10;
		}
		int first = (int) (n / MAXN);
		int last = (int) (n % MAXN);
		for (int i = 1; i <= MAXSUM; i++) {
			int[][] y = new int[i][i + 1];
			int[][] g = new int[i][i + 1];
			int maxM = MAXN % i;
			for (int j = 0; j < MAXN; j++) {
				int d = sum[j];
				if (d > i || d + MAXSUM2 < i) {
					continue;
				}
				y[j % i][d]++;
				if (j < first) {
					g[(j * maxM) % i][d]++;
				}
			}
			// for (int j = 0; j < first; j++) {
			// int d = sum[j];
			// if (d <= i)
			// g[(j * maxM) % i][d]++;
			// }
			for (int j = 0; j < i; j++) {
				for (int k = 0; k <= i; k++) {
					ans += y[j][k] * g[(i - j) % i][i - k];
				}
			}
			int mod = (i - (first * maxM) % i);
			if (mod == i) {
				mod = 0;
			}
			for (int j = 0; j <= last; j++) {
				if (sum[first] + sum[j] == i && j % i == mod) {
					ans++;
				}
			}
		}
		out.println(ans);
	}
}