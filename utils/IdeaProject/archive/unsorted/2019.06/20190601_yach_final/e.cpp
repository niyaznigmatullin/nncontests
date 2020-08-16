/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct cluster {
	int first;
	int second;
	int id;
};

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<cluster> a(n);
	vector<int> xs(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i].first >> a[i].second;
		a[i].second += a[i].first;
		xs[i] = a[i].second;
		a[i].id = i;
	}
	sort(a.begin(), a.end(), [](cluster const &a, cluster const &b) {
		return a.second < b.second;
	});
	sort(xs.begin(), xs.end());
	xs.resize(unique(xs.begin(), xs.end()) - xs.begin());
	vector<int> dp(xs.size());
	vector<int> last(xs.size());
	for (int i = 0; i < n; i++) {
		auto &e = a[i];
		int from = (int) (upper_bound(xs.begin(), xs.end(), e.first) - xs.begin()) - 1;
		int to = (int) (lower_bound(xs.begin(), xs.end(), e.second) - xs.begin());
		if (to > 0 && dp[to - 1] > dp[to]) {
			dp[to] = dp[to - 1];
			last[to] = last[to - 1];
		}
		int val = e.second - e.first + (from < 0 ? 0 : dp[from]);
		if (dp[to] < val) {
			dp[to] = val;
			last[to] = i;
		}
	}
	vector<int> ans;
	int i = (int) xs.size() - 1;
	while (dp[i] > 0) {
		ans.push_back(a[last[i]].id);
		i = (int) (upper_bound(xs.begin(), xs.end(), a[last[i]].first) - xs.begin()) - 1;
	}
	cout << dp.back() << endl;
	for (int i = 0; i < (int) ans.size(); i++) {
		if (i > 0) cout << ' ';
		cout << ans[i];
	}
	cout << endl;
}
