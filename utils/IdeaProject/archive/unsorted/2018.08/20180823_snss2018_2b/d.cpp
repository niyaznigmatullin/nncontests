/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

void rotate(vector<vector<int> > &a) {
	int n = (int) a.size();
	vector<vector<int> > b(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			b[n - j - 1][i] = a[i][j];
		}
	}
	a.swap(b);
}

bool check(vector<vector<int> > const &a) {
	int n = (int) a.size();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if ((i > 0 && a[i][j] <= a[i - 1][j]) || (j > 0 && a[i][j] <= a[i][j - 1])) {
				return false;
			}
		}
	}
	return true;
}

void print(vector<vector<int> > const &a) {
	int n = (int) a.size();
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (j > 0) cout << ' ';
			cout << a[i][j];
		}
		cout << '\n';
	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<vector<int> > a(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> a[i][j];
		}
	}
	if (check(a)) {
		print(a);
		return 0;
	}
	rotate(a);
	if (check(a)) {
		print(a);
		return 0;
	}
	rotate(a);
	if (check(a)) {
		print(a);
		return 0;
	}
	rotate(a);
	if (check(a)) {
		print(a);
		return 0;
	}
	assert(false);
}