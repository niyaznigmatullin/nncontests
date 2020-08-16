/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void solve(int test) {
	int r, c;
	cin >> r >> c;
	// cerr << "r = " << r << ", c = " << c << endl;
	int best = ((r * c - 1) - (c - 1) + 1) / 2;
	if (c == 1) best = 0;
	vector<int> a;
	for (int i = 0; i < c; i++) {
		for (int j = 0; j < r; j++) {
			a.push_back(j);
		}
	}
	vector<pair<int, int>> ans;
	while (true) {
		int pos = 1;
		while (pos < (int) a.size() && (a[0] != a[pos] || a[pos - 1] == a[0])) ++pos;
		if (pos == (int) a.size()) break;
		int next = pos + 1 < (int) a.size() ? a[pos + 1] : -1;
		int pos2 = 1;
		if (next == -1) {
			while (a[pos2] != 0) pos2++;
			--pos2;
		} else {
			while (a[pos2] != next || a[pos2 + 1] == next) ++pos2;
		}
		ans.push_back({pos2 + 1, pos - pos2});
		// cerr << "pos " << pos << ' ' << pos2 << endl;
		rotate(a.begin(), a.begin() + pos2 + 1, a.begin() + pos + 1);
		// for (int i = 0; i < (int) a.size(); i++) cerr << a[i] << ' '; cerr << endl;
	}
	// cerr << ans.size() << ' ' << best << endl;
	// assert(r < 4 || c < 4 || ans.size() == best);
	// if (ans.size() != best) cerr << r << ' ' << c << ' ' << ans.size() << ' ' << best << endl;
	cout << "Case #" << test << ": " << ans.size() << '\n';
	for (auto e : ans) {
		cout << e.first << ' ' << e.second << '\n';
	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int i = 1; i <= t; i++) {
		solve(i);
	}	
}