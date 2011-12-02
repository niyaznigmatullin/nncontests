import java.io.*;
import java.util.*;
import java.math.*;
import static java.math.BigInteger.*;

public class D implements Runnable {
	public static void main(String[] args) {
		new D().run();
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

	BigInteger t1 = valueOf(12);
	BigInteger t2 = valueOf(13);

	void solve() {
		HashSet<BigInteger>[] b = new HashSet[10];
		for (int i = 0; i < b.length; i++) {
			b[i] = new HashSet<BigInteger>();
		}
		b[0].add(valueOf(2));
		b[1].add(valueOf(13));
		for (int i = 2; i < b.length; i++) {
			for (BigInteger j : b[i - 2]) {
				BigInteger e = j.multiply(t1);
				b[i].add(e);
				for (BigInteger k : b[i - 1]) {
					BigInteger e1 = k.multiply(t2).subtract(e);
					if (e1.compareTo(ZERO) >= 0) {
						b[i].add(e1);
					}
				}
			}
		}
		System.err.println(Arrays.toString(b));
	}
}