import java.io.*;
import java.util.*;

public class B implements Runnable {
	public static void main(String[] args) {
		new B().run();
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

	ArrayList<Integer>[] edges;
	boolean[] was;

	void dfs(int x, int c, int[] color) {
		color[x] = c;
		was[x] = true;
		for (int i : edges[x]) {
			if (was[i]) {
				continue;
			}
			dfs(i, c, color);
		}
	}

	void solve() {
		int n = nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt() - 1;
		}
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			b[i] = nextInt();
		}
		edges = new ArrayList[n];
		for (int i = 0; i < edges.length; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n; i++) {
			int x = i - b[i];
			if (x > 0) {
				edges[x].add(i);
				edges[i].add(x);
			}
			x = i + b[i];
			if (x < n) {
				edges[i].add(x);
				edges[x].add(i);
			}
		}
		int[] color = new int[n];
		int curColor = 0;
		was = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (was[i]) {
				continue;
			}
			dfs(i, curColor++, color);
		}
		Arrays.fill(was, false);
		curColor = 0;
		ArrayList<ArrayList<Integer>> ar = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			if (was[i]) {
				continue;
			}
			ArrayList<Integer> d = new ArrayList<Integer>();
			int x = i;
			while (!was[x]) {
				was[x] = true;
				d.add(x);
				x = a[x];
			}
			ar.add(d);
			curColor++;
		}
		for (ArrayList<Integer> d : ar) {
			int y = color[d.get(0)];
			for (int i : d) {
				if (color[i] != y) {
					out.println("NO");
					return;
				}
			}
		}
		out.println("YES");
	}
}