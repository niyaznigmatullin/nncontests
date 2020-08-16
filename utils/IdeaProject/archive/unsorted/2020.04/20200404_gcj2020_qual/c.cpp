/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct interval {
	int from;
	int to;
	int id;
};

void solve(int test) {
	int n;
	cin >> n;
	vector<interval> a(n);
	for (int i = 0; i < n; i++) {
		int from, to;
		cin >> from >> to;
		a.push_back({from, to, i});
	}
	sort(a.begin(), a.end(), [](interval const &a, interval const &b) {
		return a.to < b.to;
	});
	int last1 = 0;
	int last2 = 0;
	string ans = "";
	for (int i = 0; i < n; i++) {
		ans += ".";
	}
	for (auto &e : a) {
		if (last1 <= e.from && last2 <= e.from) {
			if (last1 > last2) {
				ans[e.id] = 'C';
				last1 = e.to;
			} else {
				ans[e.id] = 'J';
				last2 = e.to;
			}
		} else if (last1 <= e.from) {
			ans[e.id] = 'C';
			last1 = e.to;
		} else if (last2 <= e.from) {
			ans[e.id] = 'J';
			last2 = e.to;
		} else {
			ans = "IMPOSSIBLE";
			break;
		}
	}
	cout << "Case #" << test << ": " << ans << '\n';
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}