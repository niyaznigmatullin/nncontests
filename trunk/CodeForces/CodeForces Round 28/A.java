import java.io.*;
import java.util.*;

public class A implements Runnable {
	public static void main(String[] args) {
		new A().run();
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

	class E implements Comparable<E> {
		int x, num;

		public E(int x, int num) {
			this.x = x;
			this.num = num;
		}

		@Override
		public int compareTo(E o) {
			if (x != o.x) {
				return x < o.x ? -1 : 1;
			}
			return num - o.num;
		}
	}

	void solve() {
		int n = nextInt();
		int m = nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = nextInt();
			y[i] = nextInt();
		}
		TreeSet<E> tm = new TreeSet<E>();
		for (int i = 0; i < m; i++) {
			int e = nextInt();
			tm.add(new E(e, i));
		}
		TreeSet<E> tm2 = new TreeSet<E>();
		tm2.addAll(tm);
		int[] a = new int[n];
		Arrays.fill(a, -2);
		boolean ok = true;
		E last = new E(0, -1);
		for (int i = 0; i < n; i += 2) {
			int d = Math.abs(x[(i + 1) % n] - x[i])
					+ Math.abs(y[(i + 1) % n] - y[i]);
			if (i > 0) {
				d += Math.abs(x[i] - x[i - 1]) + Math.abs(y[i] - y[i - 1]);
			} else {
				d += Math.abs(x[i] - x[n - 1]) + Math.abs(y[i] - y[n - 1]);
			}
			last.x = d;
			E e = tm.ceiling(last);
			if (e == null) {
				ok = false;
				break;
			}
			a[i] = e.num;
			tm.remove(e);
		}
		if (ok) {
			out.println("YES");
			for (int i = 0; i < n; i++) {
				out.print(1 + a[i] + " ");
			}
			return;
		}
		tm = tm2;
		Arrays.fill(a, -2);
		ok = true;
		for (int i = 1; i < n; i += 2) {
			int d = Math.abs(x[(i + 1) % n] - x[i])
					+ Math.abs(y[(i + 1) % n] - y[i]);
			if (i > 0) {
				d += Math.abs(x[i] - x[i - 1]) + Math.abs(y[i] - y[i - 1]);
			} else {
				d += Math.abs(x[i] - x[n - 1]) + Math.abs(y[i] - y[n - 1]);
			}
			last.x = d;
			E e = tm.ceiling(last);
			if (e == null) {
				ok = false;
				break;
			}
			a[i] = e.num;
			tm.remove(e);
		}
		if (ok) {
			out.println("YES");
			for (int i = 0; i < n; i++) {
				out.print(1 + a[i] + " ");
			}
			return;
		}
		out.println("NO");
	}
}