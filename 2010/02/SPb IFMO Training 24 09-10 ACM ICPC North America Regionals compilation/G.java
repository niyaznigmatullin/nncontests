import java.util.*;
import java.math.*;
import java.io.*;

public class G implements Runnable {
	public static void main(String[] args) {
		new Thread(new G()).start();
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
			// br = new BufferedReader(new InputStreamReader(System.in));
			// out = new PrintWriter(System.out);
			br = new BufferedReader(new FileReader("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(111);
		}
		// System.err.println(System.currentTimeMillis() - time);
	}

	void solve() {
		int n = nextInt();
		for (int i = 0; i < n; i++) {
			solve2();
		}
	}

	void solve2() {
		int n = nextInt();
		char[] s = nextToken().toCharArray();
		int[][] e = new int[26][31];
		int[] d = new int[26];
		int[] dp = new int[s.length + 1];
		Arrays.fill(dp, 1);
		int[] dp1 = new int[50];
		char[][] strings = new char[n][];
		HashSet<Integer> all = new HashSet<Integer>();
		all.add(0);
		for (int i = 0; i < n; i++) {
			char[] t = nextLine().trim().toUpperCase().toCharArray();
			Arrays.fill(d, 0);
			for (int j = t.length - 1; j >= 0; j--) {
				int r = t[j] - 'A';
				e[r][d[r]++] = j;
			}
			HashSet<Integer>[] r = new HashSet[t.length];
			for (int j = 0; j < t.length; j++) {
				r[j] = new HashSet<Integer>();
			}
			for (int j = 0; j < s.length; j++) {
				int w = s[j] - 'A';
				for (int k = 0; k < d[w]; k++) {
					if (e[w][k] == 0) {
						r[0].add(1 << j);
					} else {
						for (int q : r[e[w][k] - 1]) {
							r[e[w][k]].add((1 << j) | q);
						}
					}
				}
			}
			ArrayList<Integer> toAdd = new ArrayList<Integer>();
			System.err.println(r[t.length - 1]);
			for (int z : all) {
				for (int v : r[t.length - 1]) {
					if ((z & v) == 0) {
						toAdd.add(z | v);
					}
				}
			}
			for (int z : toAdd) {
				all.add(z);
			}
		}

		if (all.contains((1 << s.length) - 1))
			all.remove((1 << s.length) - 1);
		out.println(all.size() - 1);
		// strings[i] = t;
		// 

		// Arrays.fill(dp1, 0);
		// for (int j = 0; j < s.length; j++) {
		// int r = s[j] - 'A';
		// for (int k = 0; k < d[r]; k++) {
		// if (e[r][k] == 0) {
		// dp1[e[r][k]] += dp[j];
		// } else {
		// dp1[e[r][k]] += dp1[e[r][k] - 1];
		// }
		// }
		// dp[j + 1] += dp1[t.length - 1];
		// }
		// }
		// int ans = dp[s.length] - 1;
		// int[] p1 = new int[50];
		// Arrays.fill(dp, 0);
		// dp[0] = 1;
		// ArrayList<End> ee = new ArrayList<End>(100000);
		// for (int i = 0; i < n; i++) {
		// int k = -1;
		// p1[0] = -1;
		// char[] t = strings[i];
		// for (int j = 1; j < t.length; j++) {
		// while (k > -1 && t[k + 1] != t[j]) {
		// k = p1[k];
		// }
		// if (t[k + 1] == t[j]) {
		// k++;
		// }
		// p1[j] = k;
		// }
		// k = -1;
		// for (int j = 0; j < s.length; j++) {
		// while (k > -1 && k + 1 < t.length && t[k + 1] != s[j]) {
		// k = p1[k];
		// }
		// if (k + 1 < t.length && t[k + 1] == s[j]) {
		// k++;
		// }
		// if (k == t.length - 1) {
		// ee.add(new End(j, t.length));
		// }
		// }
		// }
		// Collections.sort(ee);
		// for (End r : ee) {
		// dp[r.end + 1] += dp[r.end]
		// }
		// ans -= dp[s.length];
		// out.println();
	}

	class End implements Comparable<End> {
		int end, len;

		public End(int end, int len) {
			this.end = end;
			this.len = len;
		}

		@Override
		public int compareTo(End o) {
			if (end < o.end)
				return -1;
			if (end > o.end)
				return 1;
			return 0;
		}

	}
}