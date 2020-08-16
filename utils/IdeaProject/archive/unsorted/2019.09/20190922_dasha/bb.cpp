/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<long long> a(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	vector<int> b(n);
	for (int i = 0; i < n; i++) {
		cin >> b[i];
	}
	// vector<vector<int>> bits(60);
	vector<int> count(60);
	for (int i = 0; i < n; i++) {
		for (int bit = 0; bit < 60; bit++) {
			if (((a[i] >> bit) & 1) == 1) {
				// bits[bit].push_back(i);
				count[bit]++;
			}
		}
	}
	map<long long, vector<int>> m;
	for (int i = 0; i < n; i++) m[a[i]].push_back(i);
	long long empty = 0;
	for (int i = 0; i < 60; i++) {
		if (count[i] == 0) {
			empty |= 1LL << i;
		}
	}
	long long ans = 0;
	for (int i = 0; i < n; i++) ans += b[i];
	while (true) {
		if (m.find(empty) == m.end()) break;
		long long addEmpty = 0;
		for (int i : m[empty]) {
			ans -= b[i];
		}
		int cn = (int) m[empty].size();
		for (int i = 0; i < 60; i++) {
			if (((empty >> i) & 1) == 1) {
				count[i] -= cn;
				if (count[i] == 0) {
					addEmpty |= 1LL << i;
				}
			}
		}
		empty |= addEmpty;
	}
	cout << ans << endl;
}
