/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

struct edge {
	int from;
	int to;
	int t;
};


int get(vector<int>  &a, int x) {
	return x == a[x] ? x : (a[x] = get(a, a[x]));
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m, D;
	cin >> n >> m >> D;
	vector<edge> allEdges;
	vector<vector<edge> > edges(n);
	for (int i = 0; i < m; i++) {
		int from, to, t;
		cin >> from >> to >> t;
		--from;
		--to;
		edges[from].push_back({from, to, t});
		edges[to].push_back({to, from, t});
		allEdges.push_back({from, to, t});
	}
	sort(all(allEdges), [](edge const &a, edge const &b) {
		return a.t > b.t;
	});
	vector<int> q(n);
	int head = 0;
	int tail = 0;
	q[tail++] = 0;
	int const INF = 1 << 30;
	vector<int> d(n, INF);
	d[0] = 0;
	while (head < tail) {
		int v = q[head++];
		for (auto &e : edges[v]) {
			if (d[v] < e.t) {
				if (d[e.to] == INF) {
					d[e.to] = d[v] + 1;
					q[tail++] = e.to;
				}
			}
		}
	}
	vector<long long> cur(n);
	vector<int> sz(n, 1);
	vector<int> tt(n, D);
	for (int i = 0; i < n; i++) {
		cur[i] = d[i] == INF ? -INF : 0;
	}
	vector<int> dsu(n);
	for (int i = 0; i < n; i++) dsu[i] = i;
	for (auto &e : allEdges) {
		int x = get(dsu, e.from);
		int y = get(dsu, e.to);
		if (x == y) continue;
		dsu[x] = y;
		if (cur[x] != -INF) {
			cur[x] += (tt[x] - e.t) * (long long) sz[x];
		}
		if (cur[y] != -INF) {
			cur[y] += (tt[y] - e.t) * (long long) sz[y];
		}
		cur[y] = std::max(cur[y], cur[x]);
		sz[y] += sz[x];
		tt[y] = e.t;
	}
	int x = get(dsu, 0);
	assert(sz[x] == n);
	assert(cur[x] != -INF);
	cur[x] += (tt[x] - 0) * (long long) sz[x];
	cout << cur[x] << endl;
}