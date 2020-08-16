/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void solve(int) {
	cout << 210 << endl;
	long long ans210;
	cin >> ans210;
	vector<int> ans(7);
	for (int i = 4; i <= 6; i++) {
		ans[i] = (int) (ans210 >> (210 / i));
		ans210 -= (long long) ans[i] << (210 / i);
	}
	cout << 54 << endl;
	long long ans54;
	cin >> ans54;
	for (int i = 6; i >= 4; i--) {
		ans54 -= (long long) ans[i] << (54 / i);
	}
	for (int i = 1; i <= 3; i++) {
		ans[i] = (int) (ans54 >> (54 / i));
		ans54 -= (long long) ans[i] << (54 / i);
	}
	for (int i = 1; i <= 6; i++) {
		if (i > 1) cout << ' ';
		cout << ans[i];
	}
	cout << endl;
	int verdict;
	cin >> verdict;
	if (verdict < 0) {
		exit(0);
	}
}

int main() {
	int t, TWO;
	cin >> t >> TWO;
	for (int i = 1; i <= t; i++) {
		solve(i);
	}	
}