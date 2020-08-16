/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void solve(int test) {
	int n;
	cin >> n;
	string s, t;
	cin >> s >> t;
	vector<vector<int>> a(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		a[i][i] = 1;
	}
	for (int i = 0; i < n; i++) {
		if (t[i] == 'N') continue;
		for (int d = -1; d <= 1; d += 2) {
			int to = i + d;
			if (to < 0 || to >= n) continue;
			if (s[to] == 'N') continue;
			a[i][to] = 1;
		}
	}
	for (int k = 0; k < n; k++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] |= a[i][k] && a[k][j];
			}
		}
	}
	cout << "Case #" << test << ":\n";
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << (a[i][j] ? 'Y' : 'N');
		}
		cout << '\n';
	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}
