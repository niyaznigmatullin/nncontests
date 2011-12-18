import java.io.*;
import java.util.*;

public class I {

	static void solve() throws IOException {
		int c = nextInt();
		int n = nextInt();
		int a = nextInt();
		int access[] = new int[a];
		for (int i = 0; i < a; i++) {
			access[i] = nextInt();
		}
		int nextAccess[] = new int[n];
		for (int i = 0; i < n; i++) {
			nextAccess[i] = Integer.MAX_VALUE - i;
		}
		int nextAccess2[] = new int[a];
		for (int i = a - 1; i >= 0; i--) {
			nextAccess2[i] = nextAccess[access[i]];
			nextAccess[access[i]] = i;
		}
		NavigableSet<Integer> set = new TreeSet<Integer>();
		int ans = 0;
		for (int i = 0; i < a; i++) {
			if (set.contains(i)) {
				set.remove(i);
			} else {
				if (set.size() >= c) {
					set.remove(set.last());
				}
				++ans;
			}
			set.add(nextAccess2[i]);
		}
		out.println(ans);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}