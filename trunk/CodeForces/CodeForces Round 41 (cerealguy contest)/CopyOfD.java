import java.io.*;
import java.util.*;
import java.math.*;

public class CopyOfD implements Runnable {
	public static void main(String[] args) {
		new CopyOfD().run();
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

	BigInteger nextBigInteger() {
		return sc.nextBigInteger();
	}

	static Random rand = new Random();

	int[] a18 = { 186, 131, 71, 496, 489, 450, 82, 130, 189, 318, 413, 42, 387,
			285, 454, 204, 69, 458 };
	int[] a19 = { 18, 267, 12, 154, 485, 404, 352, 363, 6, 315, 419, 477, 376,
			187, 332, 438, 11, 152, 459 };
	int[] a17 = { 56, 309, 195, 406, 9, 477, 55, 334, 269, 281, 428, 275, 482,
			92, 225, 404, 419 };
	int[] a20 = { 1, 2, 3, 5, 8, 13, 21, 30, 39, 53, 74, 95, 128, 152, 182,
			212, 258, 316, 374, 413 };

	void solve() {
		int n = nextInt();
		int[] was = new int[1001];
		int T = 0;
		loop: while (true) {
			++T;
			int[] h = new int[n];
			if (n == 17) {
				h = a17;
			} else if (n == 18) {
				h = a18;
			} else if (n == 19) {
				h = a19;
			} else if (n == 20) {
				h = a20;
			} else
				for (int i = 0; i < h.length; i++) {
					int e;
					do {
						e = rand.nextInt(501);
					} while (was[e] == T);
					was[e] = T;
					h[i] = e;
				}
			++T;
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					if (was[h[i] + h[j]] == T) {
						continue loop;
					}
					was[h[i] + h[j]] = T;
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) {
						out.print("0 ");
						continue;
					}
					out.print(h[i] + h[j] + " ");
				}
				out.println();
			}
			return;
		}
	}
}