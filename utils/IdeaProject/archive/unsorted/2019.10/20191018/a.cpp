/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m;
	cin >> n >> m;
	int x1, y1, x2, y2;
	cin >> x1 >> y1 >> x2 >> y2;
	--x2;
	--y2;
	int k, d;
	cin >> k >> d;
	vector<int> all(d);
	for (int i = 0; i < d; i++) {
		int x, y;
		cin >> x >> y;
		int count = 0;
		if (x < x1) count += x1 - x;
		if (x > x2) count += x - x2;
		if (y < y1) count += y1 - y;
		if (y > y2) count += y - y2;
		all[i] = count;
	}
	int ans;
	if (d < k) {
		ans = -1;
	} else {
		sort(all.begin(), all.end());
		ans = all[k - 1];
	}
	cout << ans << endl;
}
