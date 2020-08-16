/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int const INF = 2000000000;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m, k;
	cin >> n >> m >> k;
	vector<vector<int>> a(n, vector<int>(k));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < k; j++) {
			cin >> a[i][j];
		}
	}	
	vector<int> ans(n);
	int z = 0;
	if (m >= k) {
		for (int i = 0; i < k; i++) {
			int best = -1;
			for (int j = 0; j < n; j++) {
				if (best < 0 || a[j][i] > a[best][i]) {
					best = j;
				}
			}
			z += a[best][i];
			ans[best] = 1;
		}
	} else {
		vector<pair<int, int>> d(1 << k);
		for (int i = 0; i < 1 << k; i++) {
			int best = -INF;
			int bestI = -1;
			for (int j = 0; j < n; j++) {
				int s = 0;
				for (int e = 0; e < k; e++) {
					if (((i >> e) & 1) == 1) {
						s += a[j][e];
					}
				}
				if (s > best) {
					best = s;
					bestI = j;
				}
			}
			d[i] = {best, bestI};
		}
		vector<vector<pair<int,int>>> dp(m + 1, vector<pair<int,int>>(1 << k, {-INF,-1}));
		dp[0][0] = {0,-1};
		for (int have = 0; have < m; have++) {
			for (int nmask = 1; nmask < 1 << k; nmask++) {
				for (int last = nmask; last > 0; last = (last - 1) & nmask) {
					auto &to = dp[have + 1][nmask];
					int val = dp[have][nmask & ~last].first;
					if (val == -INF) continue;
					val += d[last].first;
					if (to.first < val) {
						to.first = val;
						to.second = last;
					}
				}
			}
		}
		int bestHave = 0;
		for (int i = 1; i <= m; i++) {
			if (dp[i][(1 << k) - 1] > dp[bestHave][(1 << k) - 1]) {
				bestHave = i;
			}
		}
		z = dp[bestHave][(1 << k) - 1].first;
		int mask = (1 << k) - 1;
		while (bestHave > 0) {
			int last = dp[bestHave][mask].second;
			ans[d[last].second] = 1;
			mask &= ~last;
			bestHave--;
		}
	}
	int sum = 0;
	for (int x : ans) sum += x;
	for (int i = 0; sum < m && i < n; i++) {
		if (ans[i] == 1) continue;
		ans[i] = 1;
		sum++;
	}
	cout << z << '\n';
	bool first = true;
	for (int i = 0; i < n; i++) {
		if (ans[i] == 1) {
			if (first) {
				first = false;
			} else cout << ' ';
			cout << i + 1;
		}
	}
	cout << endl;
	return 0;
}