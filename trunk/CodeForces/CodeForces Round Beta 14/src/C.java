import java.io.*;
import java.math.*;
import java.util.*;

public class C {
	public static void main(String[] args) {
		new C().run();
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

	long nextLong() {
		return sc.nextLong();
	}

	double nextDouble() {
		return sc.nextDouble();
	}

	int nextInt() {
		return sc.nextInt();
	}

	String nextToken() {
		return sc.nextToken();
	}

	void solve() {
		int[] x1 = new int[4];
		int[] y1 = new int[4];
		int[] x2 = new int[4];
		int[] y2 = new int[4];
		for (int i = 0; i < 4; i++) {
			x1[i] = nextInt();
			y1[i] = nextInt();
			x2[i] = nextInt();
			y2[i] = nextInt();
			if (x1[i] > x2[i]) {
				int temp = x1[i];
				x1[i] = x2[i];
				x2[i] = temp;
			}
			if (y1[i] > y2[i]) {
				int temp = y1[i];
				y1[i] = y2[i];
				y2[i] = temp;
			}
			if (x1[i] == x2[i] && y1[i] == y2[i]) {
				out.println("NO");
				return;
			}
			if (x1[i] != x2[i] && y1[i] != y2[i]) {
				out.println("NO");
				return;
			}
		}
		int Y1 = Integer.MIN_VALUE, Y2 = Integer.MIN_VALUE;
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++) {
				if (x1[i] == x2[i] && x1[j] == x2[j]) {
					if (y1[i] != y1[j] || y2[i] != y2[j]) {
						out.println("NO");
						return;
					}
					if (Y1 != Integer.MIN_VALUE) {
						out.println("NO");
						return;
					}
					Y1 = y1[i];
					Y2 = y2[i];
				}
			}
		}
		if (Y1 == Integer.MIN_VALUE) {
			out.println("NO");
			return;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = i + 1; j < 4; j++) {
				if (y1[i] == y2[i] && y1[j] == y2[j]) {
					if (x1[i] != x1[j]
							|| x2[i] != x2[j]
							|| ((y1[i] != Y1 || y1[j] != Y2) && (y1[i] != Y2 || y1[j] != Y1))) {
						out.println("NO");
						return;
					}
				}
			}
		}
		out.println("YES");
	}
}