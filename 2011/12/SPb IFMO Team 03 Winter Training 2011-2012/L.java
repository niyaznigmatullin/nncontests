import java.io.*;
import java.util.*;

public class L {

	static void solve() throws IOException {
		int tc = nextInt();
		for (int i = 1; i <= tc; i++) {
			int vendors = nextInt(), money = nextInt();
			int[] c = new int[vendors];
			for (int j = 0; j < vendors; j++) {
				c[j] = nextInt();
			}
			long ans = solve(c, money);
			out.println(i + " " + ans);
		}
	}

	static long solve(int[] c, int money) {
		Arrays.sort(c);
		if (c[0] > money) {
			return 0;
		}
		int all = 0;
		for (int i : c) {
			all += i;
		}
		if (all <= money) {
			return 1;
		}
		long[] ways = new long[money + 1];
		ways[0] = 1;
		long answer = 0;
		for (int dontbuy = c.length - 1; dontbuy >= 0; --dontbuy) {
			all -= c[dontbuy];
			for (int j = money - all; j > money - all - c[dontbuy] && j >= 0; --j) {
				answer += ways[j];
			}
			for (int j = money - c[dontbuy]; j >= 0; --j) {
				ways[j + c[dontbuy]] += ways[j];
			}

		}
		return answer;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("margaritas.in"));
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