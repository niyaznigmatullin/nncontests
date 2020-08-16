/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int const N = 223456;
vector<int> edges[N];
int blocked[N], sz[N];

void dfs(int v, int pv) {
	sz[v] = 1;
	for (int to : edges[v]) {
		if (to == pv || blocked[to]) continue;
		dfs(to, v);
		sz[v] += sz[to];
	}
}

bool ask(int v, vector<int> const &query) {
	cout << "? " << query.size() << " " << (v + 1);
	for (int x : query) {
		cout << " " << (x + 1);
	}
	cout << endl;
	int ans;
	cin >> ans;
	return ans;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	for (int i = 0; i + 1 < n; i++) {
		int v, u;
		cin >> v >> u;
		v--;
		u--;
		edges[v].push_back(u);
		edges[u].push_back(v);
	}
	int v = 0;
	while (true) {
		dfs(v, -1);
		int sr = sz[v];
		if (sr == 1) {
			cout << "! " << (v + 1) << endl;
			return 0;
		}
		int pv = -1;
		while (true) {
			bool found = false;
			for (int to : edges[v]) {
				if (to == pv || blocked[to]) continue;
				if (sz[to] * 2 > sr) {
					pv = v;
					v = to;
					found = true;
					break;
				}
			}
			if (!found) break;
		}
		int cursize = 0;
		vector<int> query;
		vector<int> other;
		for (int to : edges[v]) {
			if (blocked[to]) continue;
			if (cursize * 4 >= sr) {
				other.push_back(to);
				continue;
			}
			dfs(to, v);
			cursize += sz[to];
			query.push_back(to);
		}
		vector<int> toBlock;
		if (ask(v, query)) {
			for (int i : query) {
				blocked[i] = true;
			}
			if (sr == 2) {
				cout << "! " << (v + 1) << endl;
				return 0;
			}
		} else {
			for (int i : other) {
				blocked[i] = true;
			}
			if (sr == 2) {
				assert(query.size() == 1);
				cout << "! " << (query[0] + 1) << endl;
				return 0;
			}
		}
	}
}