/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct edge {
	int v, u;
	long long a;
	int w;
};

int const N = 70;
int const BITS = 60;
int was[N];
long long up[N];

int pv[N];
int sz[N];

int get(int v) {
	// int y = v;
	// while (pv[y] != y) {
	// 	y = pv[y];
	// }
	// while (v != pv[v]) {
	// 	int z = pv[v];
	// 	pv[v] = y;
	// 	v = z;
	// }
	// return v;
	return pv[v] == v ? v : (pv[v] = get(pv[v]));
}

vector<edge> edges[N];

void dfs(int v, int pv, long long x) {
	was[v] = true;
	up[v] = x;
	for (edge &e : edges[v]) {
		if (e.u == pv) continue;
		dfs(e.u, v, x ^ e.a);
	}
}

double T() {
	return (double) clock() / CLOCKS_PER_SEC;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);	
	int n, q;
	cin >> n >> q;
	vector<edge> all, nall, other;
	all.reserve(1000);
	nall.reserve(1000);
	other.reserve(1000);
	vector<long long> g(BITS);
	double first = 0;
	double second = 0;
	for (int i = 0; i < n; i++) {
		edges[i].reserve(2 * n);
	}
	for (int i = 0; i < q; i++) {
		int u, v, w;
		long long a;
		// first -= T();
		cin >> u >> v >> a >> w;
		--u;
		--v;
		all.push_back({u, v, a, w});
		sort(all.begin(), all.end(), [](edge const &a, edge const &b) {
			return a.w > b.w;
		});
		for (int i = 0; i < n; i++) {
			pv[i] = i;
			edges[i].clear();
			was[i] = false;
		}
		for (int i = 0; i < BITS; i++) {
			g[i] = 0;
		}
		nall.clear();
		other.clear();
		long long ans = 0;
		for (edge &e : all) {			
			if (get(e.v) != get(e.u)) {
				pv[get(e.v)] = get(e.u);
				edges[e.v].push_back({e.v, e.u, e.a, e.w});
				edges[e.u].push_back({e.u, e.v, e.a, e.w});
				nall.push_back(e);
				ans += e.w;
			} else {
				other.push_back(e);
			}
		}
		for (int i = 0; i < n; i++) {
			if (was[i]) continue;
			dfs(i, -1, 0);
		}
		// first += T();
		// second -= T();
		for (edge &e : other) {
			long long x = up[e.v] ^ up[e.u] ^ e.a;
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
			if (x != 0) {
				nall.push_back(e);
				ans += e.w;
			}
		}
		// second += T();
		all.swap(nall);
		cout << ans << '\n';
	}
	cerr << first << endl;
	cerr << second << endl;
}
