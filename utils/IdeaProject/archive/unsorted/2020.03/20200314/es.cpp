/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

vector<int> factorize(int n) {
	vector<int> ans;
	for (int i = 2; i * i <= n; i++) {
		int cc = 0;
		while (n % i == 0) {
			cc ^= 1;
			n /= i;
		}
		if (cc == 1) ans.push_back(i);
	}
	if (n > 1) ans.push_back(n);
	return ans;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<int> a(n);
	vector<vector<int>> f(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
		f[i] = factorize(a[i]);
	}
	int ans = n + 1;
	for (int i = 1; i < 1 << n; i++) {
		int cur = __builtin_popcount(i);
		if (cur >= ans) continue;
		vector<int> q;
		for (int j = 0; j < n; j++) {
			if (((i >> j) & 1) == 1) {
				q.insert(q.end(), f[j].begin(), f[j].end());
			}
		}
		sort(q.begin(), q.end());
		bool bad = false;
		for (int e = 0; e < (int) q.size(); ) {
			int g = e;
			while (g < (int) q.size() && q[g] == q[e]) ++g;
			if ((g - e) & 1) {
				bad = true;
				break;
			}
			e = g;
		}
		if (!bad) {
			ans = cur;
		}
	}
	if (ans == n + 1) ans = -1;
	cout << ans << "\n";
}
