/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<int> b(n);
	for (int i = 0; i < n; i++) cin >> b[i];
	int mx = 0;
	for (int i : b) mx = max(mx, i);
	vector<int> p(mx + 1);
	for (int i = 2; i <= mx; i++) {
		if (p[i] != 0) continue;
		for (int j = i; j <= mx; j += i) {
			p[j] = i;
		}
	}
	vector<int> primes;
	int small = 0;
	while ((small + 1) * (small + 1) <= mx) small++;
	primes.push_back(1);
	for (int i = 2; i * i <= mx; i++) {
		if (i == p[i])
			primes.push_back(i);
	}
	vector<int> one;
	vector<pair<int, int>> two;
	for (int i = 0; i < n; i++) {
		int p1 = -1;
		int p2 = -1;
		int c1 = 0;
		int c2 = 0;
		for (int x = b[i]; x > 1; x /= p[x]) {
			if (p1 < 0 || p1 == p[x]) {
				p1 = p[x];
				c1++;
			} else if (p1 != p[x]) {
				assert(p2 < 0 || p2 == p[x]);
				p2 = p[x];
				c2++;
			}
		}
		if (p1 > p2) {
			swap(p2, p1);
			swap(c1, c2);
		}
		if ((c1 & 1) == 1 && (c2 & 1) == 1) {
			two.push_back({p1, p2});
		} else if ((c1 & 1) == 1) {
			one.push_back(p1);
		} else if ((c2 & 1) == 1) {
			one.push_back(p2);
		} else {
			cout << 1 << endl;
			return 0;
		}
	}
	sort(one.begin(), one.end());
	for (int i = 0; i + 1 < (int) one.size(); i++) {
		if (one[i] == one[i + 1]) {
			cout << 2 << endl;
			return 0;
		}
	}
	sort(two.begin(), two.end());
	for (int i = 0; i + 1 < (int) two.size(); i++) {
		if (two[i] == two[i + 1]) {
			cout << 2 << endl;
			return 0;
		}
	}
	vector<vector<int>> edges(mx + 1);
	for (int i : one) {
		edges[1].push_back(i);
		edges[i].push_back(1);
		cerr << i << endl;
	}
	for (auto &e : two) {
		edges[e.first].push_back(e.second);
		edges[e.second].push_back(e.first);
		cerr << " " << e.first << ' ' <<  e.second << endl;
	}
	int const INF = 1 << 30;
	vector<vector<int>> a(primes.size(), vector<int>(primes.size(), INF));
	auto ffind = [&primes](int x) {
		return (int) (lower_bound(primes.begin(), primes.end(), x) - primes.begin());
	};
	int ans = INF;
	for (int i = small + 1; i <= mx; i++) {
		for (int e1 : edges[i]) {
			for (int e2 : edges[i]) {
				if (e1 == e2) break;
				int f1 = ffind(e1);
				int f2 = ffind(e2);
				if (a[f1][f2] == 2) {
					ans = min(ans, 4);
				}
				a[f1][f2] = a[f2][f1] = min(a[f1][f2], 2);
			}
		}
	}
	for (int i = 1; i <= small; i++) {
		for (int j : edges[i]) {
			if (i < j && j <= small) {
				int f1 = ffind(i);
				int f2 = ffind(j);
				if (a[f1][f2] != INF) {
					ans = min(ans, a[f1][f2] + 1);
				}
				a[f1][f2] = a[f2][f1] = min(a[f1][f2], 1);
			}
		}
	}
	int pr = (int) primes.size();
	// for (int i = 0; i < pr; i++) {
	// 	for (int j = 0; j < pr; j++) {
	// 		cerr << a[i][j] << "\t\t\t";
	// 	}
	// 	cerr << '\n';
	// }
	auto aa = a;
	for (int i = 0; i < pr; i++) a[i][i] = 0;
	for (int k = 0; k < pr; k++) {
		for (int i = 0; i < pr; i++) {
			for (int j = 0; j < pr; j++) {
				if (i != j && i != k && j != k && a[i][j] != INF && aa[i][k] != INF && aa[k][j] != INF) {
					ans = min(ans, a[i][j] + aa[i][k] + aa[k][j]);
					if (a[i][j] + aa[i][k] + aa[k][j] == 6) {
						cerr << a[i][j] << ' ' <<  aa[i][k] << ' ' << aa[k][j] << ' ' << primes[i] << ' ' << primes[k] << ' ' << primes[j] << endl;
					}
				}
			}
		}
		for (int i = 0; i < pr; i++) {
			for (int j = 0; j < pr; j++) {
				if (a[i][k] != INF && a[k][j] != INF) {
					a[i][j] = min(a[i][j], a[i][k] + a[k][j]);
				}
			}
		}
	}
	if (ans == INF) ans = -1;
	cout << ans << endl;
}
