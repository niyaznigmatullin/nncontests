import java.io.*;
import java.util.*;
import java.math.*;

public class Task3 implements Runnable {
	public static void main(String[] args) {
		new Task3().run();
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
		try {
			sc = new FastScanner("input.txt");
			out = new PrintWriter("output.txt");
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

	final double STEP = Math.pow(2, 1.0 / 12);
	final double LN2 = Math.log(2.0);

	int MAXK = 1000;
	int n, m;
	double[] sum;

	int f(String s) {
		int octave = s.charAt(s.length() - 1) - '1';
		int note = 42;
		s = s.substring(0, s.length() - 1);
		if (s.equals("C"))
			note = -9;
		else if (s.equals("C#") || s.equals("Db"))
			note = -8;
		else if (s.equals("D"))
			note = -7;
		else if (s.equals("D#") || s.equals("Eb"))
			note = -6;
		else if (s.equals("E"))
			note = -5;
		else if (s.equals("F"))
			note = -4;
		else if (s.equals("F#") || s.equals("Gb"))
			note = -3;
		else if (s.equals("G"))
			note = -2;
		else if (s.equals("G#") || s.equals("Ab"))
			note = -1;
		else if (s.equals("A"))
			note = 0;
		else if (s.equals("A#") || s.equals("Bb"))
			note = 1;
		else if (s.equals("B"))
			note = 2;

		return note + octave * 12;
	}

	double log2(double x) {
		return Math.log(x) / LN2;
	}

	final int ITER = 20;

	void go(int x) {
		// System.err.println(x);

		double freq = 440 * Math.pow(STEP, x);

		// System.err.println(freq);

		double bestDiff = 1000000;
		int bestMask = 42, bestHarm = 42;

		// for (int k = 2; k <= MAXK; k++) {
		for (int i = 0; i < m; i++) {
			int j = (int) (freq / sum[i]);
			for (int k = -ITER + j; k <= ITER + j; k++) {
				if (k >= 2 && 12 * Math.abs(log2(sum[i] * k / freq)) < bestDiff) {
					bestDiff = 12 * Math.abs(log2(sum[i] * k / freq));
					bestMask = i;
					bestHarm = k;
				}
			}
			// k--;
			// if (k >= 2 && 12 * Math.abs(log2(sum[i] * k / freq)) <
			// bestDiff) {
			// bestDiff = 12 * Math.abs(log2(sum[i] * k / freq));
			// bestMask = i;
			// bestHarm = k;
			// }
			// k += 2;
			// if (k >= 2 && 12 * Math.abs(log2(sum[i] * k / freq)) <
			// bestDiff) {
			// bestDiff = 12 * Math.abs(log2(sum[i] * k / freq));
			// bestMask = i;
			// bestHarm = k;
			// }
		}
		// }

		// System.err.println(bestDiff);

		for (int i = 0; i < n; i++)
			out.print((bestMask & (1 << i)) != 0 ? 'o' : '.');

		out.print(" " + bestHarm + " ");
		out.printf("%.9f\n", bestDiff);

	}

	void solve() {
		n = nextInt();
		int L0 = nextInt();
		int cr[] = new int[n];
		for (int i = 0; i < n; i++)
			cr[i] = nextInt();
		m = 1 << n;
		sum = new double[m];

		for (int i = 0; i < n; i++)
			sum[1 << i] = cr[i];

		for (int mask = 1; mask < m; mask++) {
			int tmp = Integer.lowestOneBit(mask);
			sum[mask] = sum[tmp] + sum[mask - tmp];
		}

		for (int i = 0; i < m; i++) {
			sum[i] += L0;
			// System.err.println(sum[i]);
			sum[i] = 340.0 / (2 * sum[i] * 0.001);
			// System.err.println(sum[i]);
		}

		int q = nextInt();
		for (int i = 0; i < q; i++) {
			int freq = f(nextToken());
			go(freq);
		}
	}
}