import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import static java.lang.System.exit;
import static java.util.Arrays.fill;

import java.io.*;
import java.util.*;

public class G {

	static class Plan {
		int from;
		int to;
		int d;

		public Plan(int d, int x, int y) {
			super();
			this.d = d;
			this.from = x;
			this.to = y;
		}

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		double dist(Point p) {
			double dx = x - p.x;
			double dy = y - p.y;
			return sqrt(dx * dx + dy * dy);
		}

	}

	static void solve() throws Exception {
		int n = nextInt();
		int m = nextInt();
		Map<Point, Integer> map = new HashMap<Point, Integer>();
		int[] start = new int[n];
		for (int i = 0; i < n; i++) {
			Point cur = readPoint();
			if (!map.containsKey(cur)) {
				map.put(cur, map.size());
			}
			start[i] = map.get(cur);
		}
		Plan[] plans = new Plan[m];
		for (int i = 0; i < m; i++) {
			Point p1 = readPoint();
			Point p2 = readPoint();
			int d = nextInt();
			if (!map.containsKey(p1)) {
				map.put(p1, map.size());
			}
			if (!map.containsKey(p2)) {
				map.put(p2, map.size());
			}
			plans[i] = new Plan(d, map.get(p1), map.get(p2));
		}
		Point[] pnts = new Point[map.size()];
		for (Map.Entry<Point, Integer> e : map.entrySet()) {
			pnts[e.getValue()] = e.getKey();
		}
		double[][] dist = new double[map.size()][map.size()];
		for (int i = 0; i < pnts.length; i++) {
			for (int j = 0; j < pnts.length; j++) {
				dist[i][j] = pnts[i].dist(pnts[j]);
			}
		}
		// double[][][] dp = new double[n][map.size()][1 << m];
		// int[][][] prevMan = new int[n][map.size()][1 << m];
		// int[][][] prevPoint = new int[n][map.size()][1 << m];
		// int[][][] prevMask = new int[n][map.size()][1 << m];
		// int[][][] eat = new int[n][map.size()][1 << m];
		// for (double[][] d1 : dp) {
		// for (double[] d2 : d1) {
		// fill(d2, Double.POSITIVE_INFINITY);
		// }
		// }
		// dp[0][start[0]][0] = 0;
		// for (int i = 0; i < n; i++) {
		// for (int mask = 0; mask < 1 << m; mask++) {
		// for (int j = 0; j < pnts.length; j++) {
		// if (dp[i][j][mask] == Double.POSITIVE_INFINITY) {
		// continue;
		// }
		// if (i + 1 < n
		// && dp[i][j][mask] < dp[i + 1][start[i + 1]][mask]) {
		// dp[i + 1][start[i + 1]][mask] = dp[i][j][mask];
		// prevMan[i + 1][start[i + 1]][mask] = i;
		// prevPoint[i + 1][start[i + 1]][mask] = j;
		// prevMask[i + 1][start[i + 1]][mask] = mask;
		// }
		// for (int k = 0; k < m; k++) {
		// if (((mask >> k) & 1) == 1) {
		// continue;
		// }
		// {
		// double add = dist[j][plans[k].from] + plans[k].d;
		// if (dp[i][plans[k].to][mask | (1 << k)] > dp[i][j][mask]
		// + add) {
		// dp[i][plans[k].to][mask | (1 << k)] = dp[i][j][mask]
		// + add;
		// prevMan[i][plans[k].to][mask | (1 << k)] = i;
		// prevPoint[i][plans[k].to][mask | (1 << k)] = j;
		// prevMask[i][plans[k].to][mask | (1 << k)] = mask;
		// eat[i][plans[k].to][mask | (1 << k)] = k;
		// }
		// }
		// {
		// double add = dist[j][plans[k].to] + plans[k].d;
		// if (dp[i][plans[k].from][mask | (1 << k)] > dp[i][j][mask]
		// + add) {
		// dp[i][plans[k].from][mask | (1 << k)] = dp[i][j][mask]
		// + add;
		// prevMan[i][plans[k].from][mask | (1 << k)] = i;
		// prevPoint[i][plans[k].from][mask | (1 << k)] = j;
		// prevMask[i][plans[k].from][mask | (1 << k)] = mask;
		// eat[i][plans[k].from][mask | (1 << k)] = k;
		// }
		// }
		// }
		// }
		// }
		// }
		DP[] dps = new DP[n];
		for (int i = 0; i < n; i++) {
			dps[i] = new DP(start[i], dist, plans);
		}
		double[][] dp = new double[n + 1][1 << m];
		int[][] lastMask = new int[n + 1][1 << m];
		for (double[] d : dp) {
			fill(d, Double.POSITIVE_INFINITY);
		}
		dp[0][0] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 1 << m; j++) {
				if (dp[i][j] == Double.POSITIVE_INFINITY) {
					continue;
				}
				int mask = ((1 << m) - 1) ^ j;
				for (int k = mask; ; k = mask & (k - 1)) {
					double newOne = max(dp[i][j], dps[i].dpAll[k]);
					if (dp[i + 1][j | k] > newOne) {
						dp[i + 1][j | k] = newOne;
						lastMask[i + 1][j | k] = k;
					}
					if (k == 0) {
						break;
					}
				}
			}
		}
		List<Integer>[] did = new List[n];
		for (int i = n, j = (1 << m) - 1; i > 0; i--) {
			did[i - 1] = dps[i - 1].getAnswer(lastMask[i][j]);
			j ^= lastMask[i][j];
		}
		for (int i = 0; i < n; i++) {
			out.print(did[i].size());
			for (int j : did[i]) {
				int k = abs(j);
				out.print(" " + k + " " + (j < 0 ? 1 : 0));
			}
			out.println();
		}
	}

	static Point readPoint() throws IOException {
		return new Point(nextInt(), nextInt());
	}

	static class DP {
		double[][] dp;
		int[][] eat;
		int[][] prevPoint;
		int[] bestPoint;
		double[] dpAll;

		public DP(int start, double[][] dist, Plan[] plans) {
			int m = plans.length;
			dp = new double[dist.length][1 << m];
			prevPoint = new int[dist.length][1 << m];
			eat = new int[dist.length][1 << m];
			for (double[] d : dp) {
				fill(d, Double.POSITIVE_INFINITY);
			}
			dp[start][0] = 0;
			for (int mask = 0; mask < 1 << m; mask++) {
				for (int i = 0; i < dist.length; i++) {
					if (dp[i][mask] == Double.POSITIVE_INFINITY) {
						continue;
					}
					for (int j = 0; j < m; j++) {
						if (((mask >> j) & 1) == 1) {
							continue;
						}
						{
							int from = plans[j].from;
							int to = plans[j].to;
							double add = plans[j].d + dist[i][from];
							if (dp[to][mask | (1 << j)] > dp[i][mask] + add) {
								dp[to][mask | (1 << j)] = dp[i][mask] + add;
								prevPoint[to][mask | (1 << j)] = i;
								eat[to][mask | (1 << j)] = j + 1;
							}
						}
						{
							int from = plans[j].to;
							int to = plans[j].from;
							double add = plans[j].d + dist[i][from];
							if (dp[to][mask | (1 << j)] > dp[i][mask] + add) {
								dp[to][mask | (1 << j)] = dp[i][mask] + add;
								prevPoint[to][mask | (1 << j)] = i;
								eat[to][mask | (1 << j)] = -j - 1;
							}
						}
					}
				}
			}
			dpAll = new double[1 << m];
			bestPoint = new int[1 << m];
			fill(bestPoint, -1);
			for (int i = 0; i < 1 << m; i++) {
				dpAll[i] = Double.POSITIVE_INFINITY;
				for (int j = 0; j < dist.length; j++) {
					if (dp[j][i] < dpAll[i]) {
						dpAll[i] = dp[j][i];
						bestPoint[i] = j;
					}
				}
			}
		}

		List<Integer> getAnswer(int mask) {
			int bestPoint = this.bestPoint[mask];
			List<Integer> ret = new ArrayList<Integer>();
			for (int i = bestPoint; mask > 0;) {
				ret.add(eat[i][mask]);
				int k = abs(eat[i][mask]) - 1;
				i = prevPoint[i][mask];
				mask ^= 1 << k;
			}
			Collections.reverse(ret);
			return ret;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter out;

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			String s = br.readLine();
			if (s == null) {
				return null;
			}
			st = new StringTokenizer(s);
		}
		return st.nextToken();
	}

	public static void main(String[] args) {
		try {
			File file = new File("input.txt");
			if (file.canRead()) {
				System.setIn(new FileInputStream(file));
				System.setOut(new PrintStream(new File("output.txt")));
			}
			br = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			br.close();
			out.close();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			List<Void> list = new ArrayList<Void>();
			while (true) {
				list.add(null);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}
