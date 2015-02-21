import java.io.*;
import java.util.*;

public class H {

	static void solve() throws IOException {
		int n = nextInt();
		int k = nextInt();
		int cost0 = nextInt();
		int[] cost = new int[k + 1];
		for (int i = 0; i <= k; i++) {
			cost[i] = nextInt();
		}
		int[][] dp = new int[1 << k][1 << k];
		for (int[] d : dp) {
			Arrays.fill(d, Integer.MAX_VALUE);
		}
		dp[0][0] = 0;
		for (int i = 0; i < n; i++) {
			int[][] next = new int[1 << k][1 << k];
			for (int[] e : next) {
				Arrays.fill(e, Integer.MAX_VALUE);
			}
			for (int mask1 = 0; mask1 < 1 << k; mask1++) {
				for (int mask2 = 0; mask2 < 1 << k; mask2++) {
					int val = dp[mask1][mask2];
					if (val == Integer.MAX_VALUE) {
						continue;
					}
					if ((mask1 & 1) == 0 && (mask2 & 1) == 0) {
						next[mask1 >> 1][mask2 >> 1] = Math.min(
								next[mask1 >> 1][mask2 >> 1], val + cost[0]);
					}
					for (int move1 = -2; move1 < k; move1++) {
						if (k == 0 && move1 == -2) {
							continue;
						}
						if (move1 < k - 1 && (mask1 & 1) == 1) {
							continue;
						}
						if (move1 >= 0 && ((mask1 >> k - move1 - 1) & 1) == 0) {
							continue;
						}
						int cost1 = getCost(cost0, cost, move1);
						int cnMask1 = makeMove(k, mask1, move1);
						for (int move2 = -2; move2 < k; move2++) {
							if (k == 0 && move2 == -2) {
								continue;
							}
							if (move2 < k - 1 && (mask2 & 1) == 1) {
								continue;
							}
							if (move2 >= 0
									&& ((mask2 >> k - move2 - 1) & 1) == 0) {
								continue;
							}
							int cost2 = getCost(cost0, cost, move2);
							int cnMask2 = makeMove(k, mask2, move2);
							int nMask1 = (cnMask1 & (~(1 << k - 1)))
									| (cnMask2 & (1 << k - 1));
							int nMask2 = (cnMask2 & (~(1 << k - 1)))
									| (cnMask1 & (1 << k - 1));
							next[nMask1][nMask2] = Math.min(
									next[nMask1][nMask2], val + cost1 + cost2);
						}
					}
				}
			}
			dp = next;
		}
		out.println(dp[0][0]);
	}

	private static int getCost(int cost0, int[] cost, int move1) {
		return move1 == -2 ? 0 : move1 == -1 ? cost0 : cost[move1 + 1];
	}

	private static int makeMove(int k, int mask1, int move1) {
		return move1 == -2 ? ((mask1 >> 1) | (1 << k - 1))
				: move1 == -1 ? (mask1 >> 1)
						: ((mask1 & ~(1 << k - move1 - 1)) >> 1);
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	public static void main(String[] args) throws IOException {
		InputStream input = System.in;
		PrintStream output = System.out;
		File file = new File("wedding.in");
		if (file.exists() && file.canRead()) {
			input = new FileInputStream(file);
			output = new PrintStream("wedding.out");
		}
		br = new BufferedReader(new InputStreamReader(input));
		out = new PrintWriter(output);
		solve();
		out.close();
		br.close();
	}

	static boolean hasNext() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String line = br.readLine();
			if (line == null) {
				return false;
			}
			st = new StringTokenizer(line);
		}
		return true;
	}

	static String next() throws IOException {
		return hasNext() ? st.nextToken() : null;
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
