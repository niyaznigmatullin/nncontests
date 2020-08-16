/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<vector<int>> a(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		int k;
		cin >> k;
		for (int j = 0; j < k; j++) {
			int x;
			cin >> x;
			--x;
			a[i][x] = 1;
		}
	}
	vector<pair<int, int>> ans;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i != j && a[i][j] == 1 && a[j][i] == 0) {
				ans.push_back({j + 1, i + 1});
			}
		}
	}
	cout << ans.size() << endl;
	for (auto &e : ans) {
		cout << e.first << ' ' << e.second << '\n';
	}
}