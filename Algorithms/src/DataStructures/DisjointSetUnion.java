package DataStructures;
public class DisjointSetUnion {
	static class DSU {
		int[] p;
		int[] r;

		public DSU(int n) {
			p = new int[n];
			r = new int[n];
			clear();
		}

		int get(int x) {
			return p[x] == x ? x : (p[x] = get(p[x]));
		}

		boolean union(int x, int y) {
			x = get(x);
			y = get(y);
			if (x == y) {
				return false;
			}
			if (r[x] >= r[y]) {
				if (r[x] == r[y]) {
					r[x]++;
				}
				p[y] = x;
			} else {
				p[x] = y;
			}
			return true;
		}

		void clear() {
			for (int i = 0; i < p.length; i++) {
				p[i] = i;
				r[i] = 0;
			}
		}
	}

}
