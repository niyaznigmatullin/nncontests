package Graphs;
import java.util.Arrays;

public class HungarianAlgorithm {
	static class Hungarian {
		int[][] a;
		int n;

		public Hungarian(int n, int[][] a) {
			this.n = n;
			this.a = a;
		}

		public int minCost() {
			int[] row = new int[n];
			int[] col = new int[n];
			int[] pair = new int[n];
			int[] min = new int[n];
			int[] from = new int[n];
			boolean[] was = new boolean[n];
			int allMin = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					allMin = Math.min(allMin, a[i][j]);
				}
			}
			for (int i = 0; i < n; i++) {
				row[i] = allMin;
			}
			Arrays.fill(pair, -1);
			for (int i = 0; i < n; i++) {
				Arrays.fill(min, Integer.MAX_VALUE);
				Arrays.fill(was, false);
				Arrays.fill(from, -1);
				int cur = i;
				int curPair = -1;
				do {
					if (curPair != -1) {
						was[curPair] = true;
						cur = pair[curPair];
					}
					int d = Integer.MAX_VALUE;
					int minPair = -1;
					for (int j = 0; j < n; j++) {
						if (was[j]) {
							continue;
						}
						int val = a[cur][j] - row[cur] - col[j];
						if (val < min[j]) {
							min[j] = val;
							from[j] = curPair;
						}
						if (d > min[j]) {
							d = min[j];
							minPair = j;
						}
					}
					row[i] += d;
					for (int j = 0; j < n; j++) {
						if (was[j]) {
							col[j] -= d;
							row[pair[j]] += d;
						} else {
							min[j] -= d;
						}
					}
					curPair = minPair;
				} while (pair[curPair] >= 0);
				while (from[curPair] >= 0) {
					int prev = from[curPair];
					pair[curPair] = pair[prev];
					curPair = prev;
				}
				pair[curPair] = i;
			}
			int ret = 0;
			for (int i = 0; i < n; i++) {
				ret += a[pair[i]][i];
			}
			return ret;
		}
	}

}
