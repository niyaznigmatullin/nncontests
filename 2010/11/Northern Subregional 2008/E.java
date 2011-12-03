import java.io.*;
import java.util.*;
import java.math.*;

public class E implements Runnable {
	public static void main(String[] args) {
		new E().run();
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
			sc = new FastScanner("enchanted.in");
			out = new PrintWriter("enchanted.out");
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

	class Graph {
		int n;
		int m;
		ArrayList<Integer>[] edges;
		int[] p;
		boolean[] was;

		public Graph(int n, int m) {
			this.n = n;
			this.m = m;
			edges = new ArrayList[n];
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new ArrayList<Integer>();
			}
		}

		boolean dfs(int v) {
			if (was[v]) {
				return false;
			}
			was[v] = true;
			for (int i : edges[v]) {
				if (p[i] == -1 || dfs(p[i])) {
					p[i] = v;
					return true;
				}
			}
			return false;
		}

		int kuhn() {
			p = new int[m];
			Arrays.fill(p, -1);
			int ans = 0;
			was = new boolean[n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(was, false);
				if (dfs(i)) {
					++ans;
				}
			}
			return ans;
		}
		
		void addEdge(int x, int y) {
			edges[x].add(y);
		}
	}

	void solve() {
		String s1 = nextToken();
		String s2 = new StringBuilder(nextToken()).reverse().toString();
		String t1 = nextToken();
		String t2 = new StringBuilder(nextToken()).reverse().toString();
		Graph g = new Graph(s1.length(), s1.length());
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == t1.charAt(j)
						&& s2.charAt(i) == t2.charAt(j)) {
					g.addEdge(i, j);
				}
			}
		}
		int got = g.kuhn();
		if (got == s1.length()) {
			out.println("Yes");
		} else {
			out.println("No");
		}
	}
}