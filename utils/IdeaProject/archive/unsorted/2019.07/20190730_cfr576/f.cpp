/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int const N = 123456;
int pr[N];
int z[23];
bool was[23];

int first, second;

vector<vector<int>> edges;

unordered_map<int, vector<int>> ds;
int p[123456];

bool dfs(int v) {
	if (was[v]) return false;
	was[v] = true;
	for (int u : edges[v]) {
		if (u == first || u == second) continue;
		if (p[u] == -1 || dfs(p[u])) {
			p[u] = v;
			return true;
		}
	}
	return false;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	vector<int> primes;
	for (int i = 2; i < N; i++) {
		if (pr[i] == 0) {
			primes.push_back(i);
		}
		for (int j = i + i; j < N; j += i) {
			pr[j] = 1;
		}
	}
	int n;
	cin >> n;
	int oldn = n;
	vector<int> a;
	map<int, int> cn;
	vector<int> ids;
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		int z = cn[x]++;
		if (z <= 2) {
			a.push_back(x);
			ids.push_back(i);
		}
	}
	if (a.size() > 1000) {
		a.resize(1000);
	}
	n = (int) a.size();
	vector<set<int>> f(n);
	for (int i = 0; i < n; i++) {
		int m = a[i];
		for (int j : primes) {
			if (j * j > m) break;
			if (m % j == 0) {
				while (m % j == 0) m /= j;
				f[i].insert(j);
			}
		}
		if (m > 1) f[i].insert(m);
		for (int j : f[i]) {
			ds[j].push_back(i);
		}
	}
	for (int i = 1; i < n; i++) {
		first = 0;
		second = i;
		cout << "Start: " << a[first] << ' ' << a[second] << endl;
		int vs = 0;
		for (int i : f[first]) z[vs++] = i;
		for (int i : f[second]) z[vs++] = i;
		cout << "vs = " << vs << endl;
		edges.clear();
		edges.resize(vs);
		for (int i = 0; i < vs; i++) {
			for (int e = 0; e < n; e++) {
				if (f[e].find(z[i]) == f[e].end()) {
					edges[i].push_back(e);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			p[i] = -1;
		}
		bool ok = true;
		for (int j = 0; j < vs; j++) {
			for (int e = 0; e < vs; e++) was[e] = false;
			if (!dfs(j)) {
				cout << i << ' ' << j << ' ' << z[j] << endl;
				ok = false;
				break;
			}
		}
		for (int i = 0; i < n; i++) cout << p[i] << ' ';
			cout << endl;
		cout << vs << endl;
		if (ok) {
			vector<int> ans(oldn);
			for (int i = 0; i < n; i++) {
				if (p[i] != -1) {
					if (p[i] < (int) f[first].size()) {
						ans[ids[i]] = 1;
					} else {
						ans[ids[i]] = 2;
					}
				}
			}
			ans[ids[first]] = 1;
			ans[ids[second]] = 2;
			cout << "YES\n";
			for (int i = 0; i < oldn; i++) {
				if (i > 0) cout << ' ';
				if (ans[i] == 0) {
					ans[i] = 1;
				}
				cout << ans[i];
			}
			cout << '\n';
			return 0;
		}
	}
	cout << "NO\n";
}