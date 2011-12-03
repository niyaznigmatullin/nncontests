import java.io.*;
import java.util.*;
import java.math.*;

public class I implements Runnable {
	public static void main(String[] args) {
		new I().run();
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
	BufferedReader br;
	PrintWriter out;

	public void run() {
		Locale.setDefault(Locale.US);
		try {
//			sc = new FastScanner("important.in");
			br = new BufferedReader(new FileReader("important.in"));
			out = new PrintWriter("important.out");
			solve();
//			sc.close();
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

	void solve() throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine().trim());
		out.println("Yes");
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				out.print(" | ");
			}
			String s = br.readLine().trim();
			char ch = s.charAt(0);
			out.print(ch + " | ~" + ch);
		}
		out.println();
	}
}