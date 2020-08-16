/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct dsu {
	vector<int> p;
	vector<int> incoming; // one of the admirers of a vertex in the set, -1 if there is none, and keep good admirer if there is one
	// Invariant: if two vertices are in the same set, then their admirers are to be connected, and already in the same set or added to the queue
	vector<int> good; // good[v] = true, if v has at least one admirer

	dsu(int n, vector<int> incoming, vector<int> good): p(n), incoming(incoming), good(good) {
		for (int i = 0; i < n; i++) p[i] = i;
	}

	int get(int v) {
		return v == p[v] ? v : (p[v] = get(p[v]));
	}

	void join(int v, int u) {
		v = get(v);
		u = get(u);
		p[v] = u;
		if (incoming[u] < 0 || (!good[incoming[u]] && incoming[v] >= 0)) {
			incoming[u] = incoming[v];
		}
	}
};

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	freopen("fcolor.in", "r", stdin);
	freopen("fcolor.out", "w", stdout);
	int n, m;
	cin >> n >> m;
	vector<vector<int>> incoming(n);
	queue<pair<int, int>> connect;
	vector<int> single(n, -1);
	for (int i = 0; i < m; i++) {
		int v, u;
		cin >> v >> u;
		--v;
		--u;
		incoming[v].push_back(u);
		single[v] = u;
	}
	vector<int> good(n, 0);
	for (int i = 0; i < n; i++) {
		good[i] = !incoming[i].empty();
	}
	dsu d(n, single, good);
	for (int v = 0; v < n; v++) {
		auto &e = incoming[v];
		for (int i = 0; i + 1 < (int) e.size(); i++) {
			connect.emplace(e[i], e[i + 1]);
		}
	}
	while (!connect.empty()) {
		auto e = connect.front();
		connect.pop();
		int v = d.get(e.first);
		int u = d.get(e.second);
		if (v != u) {
			if (d.incoming[v] >= 0 && d.incoming[u] >= 0) {
				connect.emplace(d.incoming[v], d.incoming[u]);
			}
			d.join(v, u);
		}
	}
	vector<int> minVertex(n, n + 1); // making lex min
	for (int i = 0; i < n; i++) {
		int &e = minVertex[d.get(i)];
		e = min(e, i);
	}
	vector<pair<int, int>> components;
	for (int i = 0; i < n; i++) {
		if (minVertex[i] < n) {
			components.emplace_back(minVertex[i], i);
		}
	}
	sort(components.begin(), components.end());
	vector<int> componentId(n);
	for (int i = 0; i < (int) components.size(); i++) {
		componentId[components[i].second] = i;
	}
	vector<int> answer(n);
	for (int i = 0; i < n; i++) {
		answer[i] = componentId[d.get(i)];
	}
	for (int i : answer) cout << i + 1 << '\n';
}
 