import static java.lang.Math.exp;
import static java.lang.System.arraycopy;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

import java.io.*;
import java.util.*;

public class F {

	static void solve() throws IOException {
		int n = nextInt();
		int t = nextInt();
		double f = nextDouble();
		double[] w = new double[n];
		double[] p = new double[n];
		for (int i = 0; i < n; i++) {
			p[i] = nextDouble();
			w[i] = nextDouble();
		}
		double[][][] dp = new double[t + 1][t + 1][1 << n];
		for (double[][] d1 : dp) {
			for (double[] d2 : d1) {
				fill(d2, Double.NEGATIVE_INFINITY);
			}
		}
		for (int i = 0; i <= t; i++) {
			for (int j = 0; j <= t; j++) {
				for (int k = 0; k < 1 << n; k++) {
					if (j == t || k == 0) {
						dp[i][j][k] = 0;
					}
				}
			}
		}
		Element[] curElement = new Element[t];
		int[] curIndex = new int[n];
		double[] curProbability = new double[n];
		for (int mask = 1; mask < 1 << n; mask++) {
			arraycopy(p, 0, curProbability, 0, n);
			for (int i = 0; i < t; i++) {
				int best = -1;
				double got = Double.NEGATIVE_INFINITY;
				for (int j = 0; j < n; j++) {
					if (((mask >> j) & 1) == 0) {
						continue;
					}
					if (curProbability[j] * w[j] > got) {
						best = j;
						got = curProbability[j] * w[j];
					}
				}
				curElement[i] = new Element(got / w[best], best);
				curProbability[best] *= f;
			}
			fill(curIndex, 0);
			for (int pos = 0; pos < t; pos++) {
				for (int j = 0; j < n; j++) {
					if (((mask >> j) & 1) == 0 || curElement[pos].id == j) {
						continue;
					}
					curIndex[j]++;
				}
			}
			for (int pos = t - 1; pos >= 0; pos--) {
				for (int j = 0; j < n; j++) {
					if (((mask >> j) & 1) == 0 || curElement[pos].id == j) {
						continue;
					}
					curIndex[j]--;
				}
				for (int time = pos; time < t; time++) {
					double curP = curElement[pos].probability;
					int id = curElement[pos].id;
					dp[pos][time][mask] = (1 - curP)
							* dp[pos + 1][time + 1][mask]
							+ curP
							* (w[id] + dp[curIndex[id]][time + 1][mask
									^ (1 << id)]);
				}
			}
		}
		out.println(dp[0][0][(1 << n) - 1]);
	}

	static class Element implements Comparable<Element> {
		double probability;
		int id;

		private Element(double expected, int id) {
			this.probability = expected;
			this.id = id;
		}

		@Override
		public int compareTo(Element o) {
			return Double.compare(o.probability, probability);

		}
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