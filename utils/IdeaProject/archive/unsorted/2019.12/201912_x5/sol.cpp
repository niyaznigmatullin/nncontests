/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct product {
	int type;
	int brand;
	int profit;
	int id;
};

struct instance {
	int typesCount;
	int brandsCount;
	int height;
	int width;
	int D0;
	vector<product> products;
};

struct answer {
	vector<vector<int>> a;

	bool check(instance const &z) {
		if ((int) a.size() != z.height) {
			return false;
		}
		for (auto &e : a) {
			if ((int) e.size() != z.width) {
				return false;
			}
		}
		auto &p = z.products;
		int k = z.typesCount;
		int minRow = z.height;
		int maxRow = -1;
		int minCol = z.width;
		int maxCol = -1;
		for (int i = 0; i < k; i++) {
			for (int row = 0; row < z.height; row++) {
				for (int col = 0; col < z.width; col++) {
					if (a[row][col] >= (int) p.size() || a[row][col] < -1) return false;
					if (a[row][col] == -1) continue;
					if (p[a[row][col]].type == i) {
						minRow = min(minRow, row);
						maxRow = max(maxRow, row);
						minCol = min(minCol, col);
						maxCol = max(maxCol, col);
					}
				}
			}
			if (maxRow >= 0) {
				for (int row = minRow; row <= maxRow; row++) {
					for (int col = minCol; col <= maxCol; col++) {
						if (a[row][col] < 0) return false;
						if (p[a[row][col]].type != i) return false;
					}
				}
			}
		}
		return true;
	}
};

answer solve(instance const &z) {
	int n = (int) z.products.size();
	return { { { n } } };

}

instance readInstance() {
	int n, k, m, h, w, D0;
	cin >> n >> k >> m >> h >> w >> D0;
	vector<product> products(n);
	for (int i = 0; i < n; i++) {
		int x, y, z;
		cin >> x >> y >> z;
		--x;
		--y;
		assert(0 <= x && x < k);
		assert(0 <= y && y < m);
		products[i] = {x, y, z, i};
	}
	return {k, m, h, w, D0, products};
}

void writeAnswer(answer const &ans) {
	for (auto &e : ans.a) {
		for (int i = 0; i < (int) e.size(); i++) {
			if (i > 0) cout << ' ';
			cout << e[i] + 1;
		}
		cout << '\n';
	}
}

int main() {
	instance z = readInstance();
	answer ans = solve(z);
	assert(ans.check(z));
	writeAnswer(ans);
}
