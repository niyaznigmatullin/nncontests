/**
 * Niyaz Nigmatullin
 */

#pragma GCC optimize("O3")
#pragma GCC optimize("unroll-loops")
#include <bits/stdc++.h>

using namespace std;

struct edge {
	int v, u;
	unsigned long long a;
	int w;

	bool operator==(edge const &e) const {
		return v == e.v && u == e.u && a == e.a && w == e.w;
	}
};

int const N = 65;
int const BITS = 61;
int was[N];
int pv[N];
unsigned long long up[N];


int get(int v) {
	if (pv[v] != v) pv[v] = get(pv[v]);
	return pv[v];
}

vector<edge> edges[N];

edge parent[N];

void dfs(int v, int pv, unsigned long long x) {
	was[v] = true;
	up[v] = x;
	for (auto &e : edges[v]) {
		if (e.u == pv) continue;
		parent[e.u] = e;
		dfs(e.u, v, x ^ e.a);
	}
}

double T() {
	return (double) clock() / CLOCKS_PER_SEC;
}

void remove(vector<edge> &a, edge const &e) {
	for (int i = 0; i < (int) a.size(); i++) {
		if (a[i] == e) {
			a.erase(a.begin() + i);
			return;
		}
	}
	assert(0);
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);	
	int n, q;
	cin >> n >> q;
	vector<edge> all, other;
	all.reserve(200);
	other.reserve(200);
	vector<unsigned long long> g(BITS);
	for (int i = 0; i < n; i++) {
		edges[i].reserve(n);
	}
	long long ans = 0;
	double tt = 0;
	for (int ii = 0; ii < q; ii++) {
		tt -= T();
		int u, v, w;
		unsigned long long a;
		cin >> u >> v >> a >> w;
		--u;
		--v;
		ans += w;
		for (int i = 0; i < n; i++) {
			was[i] = false;
		}
		dfs(v, -1, 0);
		if (was[u]) {
			edge best = {-1, -1, 0, -1};
			for (int i = u; i != v; i = parent[i].v) {
				if (best.w == -1 || parent[i].w < best.w) {
					best = parent[i];
				}
			}
			if (best.w < w) {
				remove(edges[best.v], {best.v, best.u, best.a, best.w});
				remove(edges[best.u], {best.u, best.v, best.a, best.w});
				other.push_back(best);
				edges[v].push_back({v, u, a, w});
				edges[u].push_back({u, v, a, w});
			} else {
				other.push_back({v, u, a, w});
			}
		} else {
			edges[v].push_back({v, u, a, w});
			edges[u].push_back({u, v, a, w});
		}
		for (int i = 0; i < n; i++) {
			was[i] = false;
		}
		for (int i = 0; i < BITS; i++) {
			g[i] = 0;
		}
		tt += T();
		for (int i = 0; i < n; i++) {
			if (was[i]) continue;
			dfs(i, -1, 0);
		}
		for (edge e : other) {
			unsigned long long x = up[e.v] ^ up[e.u] ^ e.a;
			for (int bit = 0; bit < BITS && x != 0; bit++) {
				if (((x >> bit) & 1) == 0) {
					continue;
				}
				if (g[bit] != 0) {
					x ^= g[bit];
				} else {
					g[bit] = x;
					break;
				}
			}
			if (x == 0) {
				ans -= e.w;
				remove(other, e);
				break;
			}
		}
		cout << ans << '\n';
	}
}
