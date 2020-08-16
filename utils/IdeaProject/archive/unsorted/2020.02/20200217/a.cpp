/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<int> a(n + 1);
	vector<int> b(n);
	for (int i = 0; i <= n; i++) {
		cin >> a[i];
	}	
	for (int i = 0; i < n; i++) {
		cin >> b[i];
	}
	sort(a.begin(), a.end());
	sort(b.begin(), b.end());
	vector<int> best(n);
	for (int i = 0; i < n; i++) {
		best[i] = max(0, a[i] - b[i]);
		if (i > 0) best[i] = max(best[i], best[i - 1]);
	}
	int curBest = 0;
	vector<int> ans(n + 1);
	for (int i = n; i >= 0; i--) {
		ans[i] = max(i == 0 ? 0 : best[i - 1], curBest);
		curBest = max(curBest, max(0, a[i] - b[i - 1]));
	}
	for (int i = 0; i <= n; i++) {
		if (i > 0) cout << ' ';
		cout << ans[i];
	}
	cout << endl;
}
