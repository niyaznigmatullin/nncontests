package DataStructures;

public class FenwickTree {
	static class Fenwick {
		int[] a;

		public Fenwick(int n) {
			a = new int[n];
		}

		void add(int x, int y) {
			for (int i = x; i < a.length; i |= i + 1) {
				a[i] += y;
			}
		}

		int sum(int x) {
			int ret = 0;
			for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
				ret += a[i];
			}
			return ret;
		}

		int sum(int l, int r) {
			int ret = sum(r);
			if (l > 0) {
				ret -= sum(l - 1);
			}
			return ret;
		}
	}

	static class Fenwick2D {
		int[][] a;

		public Fenwick2D(int n, int m) {
			a = new int[n][m];
		}

		void add(int x, int y, int val) {
			for (int i = x; i < a.length; i |= i + 1) {
				for (int j = y; j < a[i].length; j |= j + 1) {
					a[i][j] += val;
				}
			}
		}

		int sum(int x, int y) {
			int ret = 0;
			for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
				for (int j = y; j >= 0; j = (j & (j + 1)) - 1) {
					ret += a[i][j];
				}
			}
			return ret;
		}
	}
}
