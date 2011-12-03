import static java.util.Arrays.deepToString;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class M extends Thread {

	static class SuffixAutomaton {
		int[][] go;
		int[] sufLink;
		int[] length;
		int[] dp;
		boolean[] was;
		int last;
		int root;
		int free;

		public SuffixAutomaton(int n) {
			++n;
			go = new int[26][n];
			for (int[] d : go) {
				fill(d, -1);
			}
			was = new boolean[n];
			sufLink = new int[n];
			fill(sufLink, -1);
			length = new int[n];
			root = last = 0;
			free = 1;
			dp = new int[n];
			fill(dp, -1);
		}

		void append(int c) {
			int newNode = free++;
			length[newNode] = length[last] + 1;
			while (last >= 0 && go[c][last] < 0) {
				go[c][last] = newNode;
				last = sufLink[last];
			}
			// System.err.println(c + " " + last);
			if (last < 0) {
				sufLink[newNode] = root;
				last = newNode;
				return;
			}
			int q = go[c][last];
			if (length[last] + 1 == length[q]) {
				sufLink[newNode] = q;
				last = newNode;
				return;
			}
			int copy = free++;
			length[copy] = length[last] + 1;
			for (int i = 0; i < 26; i++) {
				go[i][copy] = go[i][q];
			}
			sufLink[copy] = sufLink[q];
			while (last >= 0 && go[c][last] == q) {
				go[c][last] = copy;
				last = sufLink[last];
			}
			sufLink[q] = copy;
			sufLink[newNode] = copy;
			last = newNode;
		}

		int dfs(int v) {
			if (dp[v] != -1) {
				return dp[v];
			}
			dp[v] = 1;
			was[v] = true;
			for (int i = 0; i < 26; i++) {
				int p = go[i][v];
				if (p < 0) {
					continue;
				}
				if (was[p]) {
					throw new AssertionError();
				}
				dp[v] += dfs(p);
			}
			was[v] = false;
			return dp[v];
		}
	}

	static int solve(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return -1;
		}
		SuffixAutomaton sa = new SuffixAutomaton(s1.length() * 3 + 1);
		for (char c : s1.toCharArray()) {
			sa.append(c - 'a');
		}
		int p = 0;
		for (char c : s2.toCharArray()) {
			p = sa.go[c - 'a'][p];
			if (p < 0) {
				return -1;
			}
		}
		sa.dfs(0);
		// System.err.println(Arrays.toString(sa.dp));
		// debug(sa.go);
		p = 0;
		int ans = 0;
		for (char ch : s2.toCharArray()) {
			// System.err.println(ch);
			ans++;
			int c = ch - 'a';
			for (int i = 0; i < c; i++) {
				int w = sa.go[i][p];
				if (w < 0) {
					continue;
				}
				// System.err.println((char) ('a' + i) + " " + sa.dp[w]);
				ans += sa.dp[w];
			}
			p = sa.go[c][p];
			if (p < 0) {
				break;
			}
		}
		return ans;
	}

	static final Random rand = new Random(213123L);

	static String getString(int len, int k) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.appendCodePoint('a' + rand.nextInt(k));
		}
		return sb.toString();
	}

	static void stress() {
		while (true) {
			int n = rand.nextInt(20000) + 2;
			String s = getString(n, 3);
			int l = rand.nextInt(s.length() - 1);
			int r = rand.nextInt(s.length() - l - 1) + l + 1;
			String t = rand.nextBoolean() ? s.substring(l, r) : getString(
					rand.nextInt(n) + 1, 3);
			int ans1 = solve(s, t);
			// int ans2 = solveStupid(s, t);
			int ans2 = ans1;
			if (ans1 != ans2) {
				System.err.println(s);
				System.err.println(s.substring(l, r));
				System.err.println(ans1 + " " + ans2);
				throw new AssertionError();
			}
			System.err.println("TEST");
		}
	}

	static int solveStupid(String s, String t) {
		NavigableSet<String> set = new TreeSet<String>();
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				set.add(s.substring(i, j));
			}
		}
		if (!set.contains(t)) {
			return -1;
		}
		int ans = 1;
		while (!set.first().equals(t)) {
			set.pollFirst();
			ans++;
		}
		return ans;
	}

	static void solve() throws Exception {
		// stress();
		String s1 = next();
		String s2 = next();
		out.println(solve(s1, s2));
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static void debug(Object... a) {
		System.err.println(deepToString(a));
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	public static void main(String[] args) {
		new Thread(null, new M(), "", 1 << 24).start();
	}

	public void run() {
		try {
			long time = System.currentTimeMillis();
			File file = new File("m.in");
			InputStream input = System.in;
			OutputStream output = System.out;
			if (file.canRead()) {
				input = (new FileInputStream(file));
				output = (new PrintStream("m.out"));
			}
			br = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			solve();
			out.close();
			br.close();
			System.err.println("Runtime = "
					+ (System.currentTimeMillis() - time) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}