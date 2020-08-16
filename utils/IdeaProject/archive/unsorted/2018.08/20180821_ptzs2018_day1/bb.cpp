/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

#define err(...) //fprintf(stderr, "%.2f : ", 1. * clock() / CLOCKS_PER_SEC), fprintf(stderr, __VA_ARGS__), fflush(stderr)

struct edge {
	int from;
	int to;
	int w;
};

int const N = 1234567;
int const K = 20;

vector<edge> edges[N];
long long depth[N];
int en[N], ex[N], parent[N];
int pp[K][N];
int T = 0;

void dfs(int v, int pv, long long d) {
	depth[v] = d;
	parent[v] = pv;
	en[v] = T++;
	for (edge &e : edges[v]) {
		if (e.to == pv) continue;
		dfs(e.to, v, d + e.w);
	}
	ex[v] = T;
}

bool anc(int v, int u) {
	return en[v] <= en[u] && ex[u] <= ex[v];
}

int lca(int v, int u) {
	if (anc(v, u)) return v;
	for (int i = K - 1; i >= 0; i--) {
		if (!anc(pp[i][v], u)) v = pp[i][v];
	}
	return parent[v];
}

set<pair<int, int> > q;

long long dist(int v, int u) {
	err("dist(%d, %d)\n", v, u);
	return depth[v] + depth[u] - 2 * depth[lca(v, u)];
}

long long get(int v) {
	if (q.empty()) return 0;
	auto it = q.lower_bound({en[v], v});
	int next = it == q.end() ? q.begin()->second : it->second;
	if (it == q.begin()) {
		it = q.end();
	}
	--it;
	int prev = it->second;
	return dist(next, v) + dist(prev, v) - dist(next, prev);
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	for (int i = 0; i + 1 < n; i++) {
		int from, to, w;
		cin >> from >> to >> w;
		--from;
		--to;
		edges[from].push_back({from, to, w});
		edges[to].push_back({to, from, w});
	}
	dfs(0, -1, 0);
	for (int i = 0; i < n; i++) pp[0][i] = parent[i] < 0 ? i : parent[i];
	for (int i = 1; i < K; i++) {
		for (int v = 0; v < n; v++) {
			pp[i][v] = pp[i - 1][pp[i - 1][v]];
		}
	}
	int qC;
	cin >> qC;
	long long ans = 0;
	for (int i = 0; i < qC; i++) {
		string type;
		int v;
		cin >> type >> v;
		--v;
		if (type == "+") {
			ans += get(v);
			q.insert({en[v], v});
		} else {
			q.erase({en[v], v});
			ans -= get(v);
		}
		cout << ans / 2 << '\n';
	}
}
