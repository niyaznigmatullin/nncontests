/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

string go(int n, int m, int to, vector<vector<int>> const &f, string const &s, string const &t) {
	if (f[n][m] == to) {
		return s.substr(0, n);
	}
	if (n > 0 && f[n - 1][m] + 1 == f[n][m]) {
		return go(n - 1, m, to, f, s, t);
	}
	if (m > 0 && f[n][m - 1] + 1 == f[n][m]) {
		string ret = go(n, m - 1, to, f, s, t);
		ret += t[m - 1];
		return ret;
	}
	if (n > 0 && m > 0 && f[n - 1][m - 1] + (s[n - 1] != t[m - 1]) == f[n][m]) {
		string ret = go(n - 1, m - 1, to, f, s, t);
		ret += t[m - 1];
		return ret;
	}
	assert(false);
}

vector<vector<int>> getEditDistance(string const &s, string const &t) {
	int n = (int) s.size();
	int m = (int) t.size();
	vector<vector<int>> f(n + 1, vector<int>(m + 1));
	for (int i = 0; i <= n; i++) {
		for (int j = 0; j <= m; j++) {
			f[i][j] = i + j;
			if (i > 0) {
				f[i][j] = min(f[i - 1][j] + 1, f[i][j]);
			}
			if (j > 0) {
				f[i][j] = min(f[i][j], f[i][j - 1] + 1);
			}
			if (i > 0 && j > 0) {
				f[i][j] = min(f[i][j], f[i - 1][j - 1] + (s[i - 1] != t[j - 1]));
			}
		}
	}
	return f;
}

void solve(int test) {
	string s, t;
	cin >> s >> t;
	int n = (int) s.size();
	int m = (int) t.size();
	vector<vector<int>> f = getEditDistance(s, t);
	int to = f[n][m] / 2;
	string ans = go(n, m, to, f, s, t);
	cout << "Case #" << test << ": " << ans << "\n";
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}
