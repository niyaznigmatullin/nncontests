import java.util.*;
import java.math.*;
import java.io.*;

public class A implements Runnable {
	public static void main(String[] args) {
		new Thread(new A()).start();
	}

	StringTokenizer st;
	PrintWriter out;
	BufferedReader br;
	boolean eof = false, in_out = false, std = false;

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return "0";
			}
		}
		return st.nextToken();
	}

	String nextLine() {
		String ret = "";
		try {
			ret = br.readLine();
		} catch (Exception e) {
			ret = "";
		}
		if (ret == null) {
			eof = true;
			return "$";
		}
		return ret;
	}

	String nextString() {
		return nextToken();
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

	public void run() {
		// long time = System.currentTimeMillis();
		try {
			br = new BufferedReader(new FileReader(new File("up.in")));
			out = new PrintWriter("up.out");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
		out.close();
	}

	void solve() {
		while (solve2())
			;
	}

	boolean solve2() {
		int n = nextInt();
		if (n == 0)
			return false;
		int s = nextInt();
		int p = nextInt();
		HashSet<Integer> has = new HashSet<Integer>();
		has.add(0);
		has.add(n);
		int[] b = new int[p];
		int[] e = new int[p];
		for (int i = 0; i < p; i++) {
			b[i] = nextInt();
			e[i] = nextInt();
			for (int j = -s; j <= s; j++) {
				if (b[i] + j >= 0 && b[i] + j <= n)
					has.add(b[i] + j);
				if (e[i] + j >= 0 && e[i] + j <= n)
					has.add(e[i] + j);
			}
		}
		int[] t = new int[has.size()];
		int m = 0;
		HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
		for (int w : has) {
			t[m++] = w;
		}
		Arrays.sort(t);
		for (int i = 0; i < t.length; i++) {
			dict.put(t[i], i);
		}
		int[][] edges = new int[m][m];
		for (int[] d : edges) {
			Arrays.fill(d, Integer.MAX_VALUE / 2);
		}
		for (int i = 0; i < p; i++) {
			edges[dict.get(b[i])][dict.get(e[i])] = 0;
		}
		int[] u = new int[m];
		Arrays.fill(u, -1);
		for (int i = 0; i < p; i++) {
			u[dict.get(b[i])] = dict.get(e[i]);
		}
		// System.err.println(Arrays.toString(t));
		// System.err.println(Arrays.toString(u));
		for (int i = 0; i < m; i++) {
			if (u[i] != -1) {
				continue;
			}
			// boolean ww = false;
			// int kol = 0;
			for (int j = i + 1; j < m; j++) {
				if (u[j] != -1)
					break;
				edges[i][j] = Math.min(edges[i][j], (t[j] - t[i] + s - 1) / s);
				// if (u[j] == -1 && ww) {
				// break;
				// }
				// if (u[j] != -1) {
				// kol++;
				// ww = true;
				// } else {
				// kol = 0;
				// }
			}
			for (int j = i + 1; j < m && t[j] - t[i] <= s; j++) {
				edges[i][j] = Math.min(edges[i][j], 1);
			}
		}
		// System.err.println(Arrays.deepToString(edges));
		boolean[] was = new boolean[m];
		int[] d = new int[m];
		Arrays.fill(d, Integer.MAX_VALUE / 2);
		d[0] = 0;
		for (int it = 0; it < m; it++) {
			int max = -1;
			for (int i = 0; i < m; i++) {
				if (was[i])
					continue;
				if (max == -1 || d[max] > d[i]) {
					max = i;
				}
			}
			was[max] = true;
			if (u[max] != -1) {
				d[u[max]] = Math.min(d[u[max]], d[max]);
				// System.err.println(max + " " + t[max] + " "
				// + Arrays.toString(d));
				continue;
			}
			for (int i = 0; i < m; i++) {
				if (i == max)
					continue;
				d[i] = Math.min(d[max] + edges[max][i], d[i]);
			}
			// System.err.println(max + " " + t[max] + " " +
			// Arrays.toString(d));
		}
		out.println(d[m - 1]);
		return true;
	}
}