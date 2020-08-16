/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, k;
	cin >> n >> k;
	vector<int> from(n), to(n);
	vector<int> all(2 * n);
	int maxLen = 0;
	for (int i = 0; i < n; i++) {
		cin >> from[i];
		cin >> to[i];
		maxLen = max(maxLen, to[i]);
		to[i] += from[i];
		all[i * 2] = from[i];
		all[i * 2 + 1] = to[i];
	}	
	sort(all.begin(), all.end());
	all.resize(unique(all.begin(), all.end()) - all.begin());
	for (int i = 0; i < n; i++) {
		from[i] = (int) (lower_bound(all.begin(), all.end(), from[i]) - all.begin());
		to[i] = (int) (lower_bound(all.begin(), all.end(), to[i]) - all.begin());
	}
	vector<vector<int>> z(all.size());
	for (int i = 0; i < n; i++) {
		z[from[i]].push_back(i);
	}
	vector<vector<int>> dp(all.size(), vector<int>(k + 2 * maxLen + 1, 0));
	for (int i = 0; i < (int) all.size(); i++) {
		if (i + 1 < (int) all.size()) {
			int diff = all[i + 1] - all[i];
			for (int e = 0; e <= k + 2 * maxLen; e++) {
				int ne = max(0, e - diff);
				dp[i + 1][ne] = max(dp[i + 1][ne], dp[i][e]);
			}
		}
		for (int j : z[i]) {
			int up = (all[to[j]] - all[from[j]]) * 2;
			int t = all[to[j]] - all[from[j]];
			for (int e = 0; e <= k; e++) {
				dp[to[j]][e + up] = max(dp[to[j]][e + up], dp[i][e] + t);
			}
		}
	}
	int ans = 0;
	for (int i = 0; i <= k + 2 * maxLen; i++) {
		ans = max(ans, dp.back()[i]);
	}
	cout << ans << endl;
}