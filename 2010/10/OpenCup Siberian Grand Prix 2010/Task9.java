import java.io.*;
import java.util.*;
import java.math.*;

public class Task9 implements Runnable {
	public static void main(String[] args) {
		new Task9().run();
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

	void solve() {
		double X = nextDouble();
		double Y = nextDouble();
		double k = nextDouble();
		double t0 = nextDouble();
		
		double l = 0.0, r = 100.0;
		
		for (int i = 0; i < 50; i++) {
			double a = (l + r) * 0.5;
			//System.err.println(a);
			double tme = Math.sqrt(2 * X / a);
			//System.err.println(tme);
			double v0 = Math.sqrt(2 * X * a);
			tme += Math.log(1 - k * Y / v0)/(-k);
			//System.err.println(tme);
			if (tme < t0)
				r = a;
			else
				l = a;
		}
		
		out.printf("%.10f", l);
	}
}