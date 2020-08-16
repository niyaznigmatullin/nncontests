/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

bool has_repeat(vector<int> a) {
	sort(a.begin(), a.end());
	for (int i = 0; i + 1 < (int) a.size(); i++) 
		if (a[i] == a[i + 1]) return true;
	return false;
}

void solve(int test) {
	int n;
	cin >> n;
	vector<vector<int>> a(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> a[i][j];
		}
	}
	int trace = 0;
	for (int i = 0; i < n; i++) trace += a[i][i];
	int rows = 0;
	for (auto &e : a) {
		rows += has_repeat(e);
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			swap(a[i][j], a[j][i]);
		}
	}
	int cols = 0;
	for (auto &e : a) {
		cols += has_repeat(e);
	}
	cout << "Case #" << test << ": " << trace << ' ' << rows << ' ' << cols << endl;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}