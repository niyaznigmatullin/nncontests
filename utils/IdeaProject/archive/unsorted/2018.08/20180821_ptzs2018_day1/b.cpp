/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

#define err(...) fprintf(stderr, __VA_ARGS__), fflush(stderr)

typedef long long ll;

struct Edge {
	int a, b;
	ll w;
};
struct Range {
	int l, r, v; // [l, r)
};

const int N = 3e5;

vector<Edge> g[N];
vector<int> order;
int parent[N], u[N], color[N];
ll parent_w[N];

struct DSU {
	int p[N];
	int get( int x ) { return x == p[x] ? x : (p[x] = get(p[x])); }
	void init( int n ) { forn(i, n) p[i] = i; }
} dsu;

void dfs( int v, int p, ll wp ) {
	parent[v] = p, parent_w[v] = wp;
	for (auto e : g[v])
		if (e.b != p)
			dfs(e.b, v, e.w);
}
void dfs_order( int v, int p ) {
	order.push_back(v);
	for (auto e : g[v])
		if (e.b != p)
			dfs_order(e.b, v);
}

vector<int> lca_qs[N];

void lca( int v, int p ) {
	color[v] = 1;
	for (auto e : g[v])
		if (e.b != p)
			lca(e.b, v);
	for (int i : lca_qs[v])
		if (color[i])
			u[dsu.get(i)] = 1; // just mark lca
	dsu.p[v] = p;
}

void build( int n, vector<Edge> &es ) {
	forn(i, n)
		g[i].clear();
	for (auto e : es) {
		if (e.a == e.b) continue;
		g[e.a].push_back({e.a, e.b, e.w});
		g[e.b].push_back({e.b, e.a, e.w});
	}
}

int newN = 0;
void build_newgraph( int v, int p, int from, ll w, vector<Edge> &edges ) {
	if (u[v] && v != from) { // new vertex
		color[v] = newN++;
		edges.push_back({color[from], color[v], w});
		from = v, w = 0;
	}
	for (auto e : g[v])
		if (e.b != p)
			build_newgraph(e.b, v, from, w + e.w, edges);
}

void solve( int l, int r, int n, vector<Edge> &es, vector<Range> &qs, ll w, int prevRoot ) {
	vector<Range> qs1, qs2;
	for (auto q : qs) { 
		if (r <= q.l || q.r <= l)
			continue;
		if (q.l <= l && r <= q.r)
		    qs1.push_back(q);
		else
		    qs2.push_back(q);
	}
	build(n, es);
	int root;
	if (prevRoot != -1)
		root = prevRoot;
	else
		root = !qs1.size() ? 0 : qs1[0].v;
	dfs(root, -1, 0);
	fill(u, u + n, -1), u[root] = 0;
	for (auto q : qs1) 
		for (int v = q.v; v != -1 && u[v] == -1; v = parent[v])
			u[v] = 0, w += parent_w[v];

	int cc = 1;
	forn(i, n)
		if (u[i] == -1)
			u[i] = cc++;
	for (auto &e : es) {
		e.a = u[e.a];
		e.b = u[e.b];
	}
	for (auto &q : qs2)
		q.v = u[q.v];
	build(n = cc, es);
	root = 0;
	order.clear();
	dfs_order(root, -1);
	fill(u, u + n, 0), u[root] = 1;
	for (auto q : qs2)
		u[q.v] = 1;

	int last = -1;
	forn(i, n)
		lca_qs[i].clear();
	for (int v : order)
		if (u[v]) {
			if (last != -1) {
				lca_qs[last].push_back(v);
				lca_qs[v].push_back(last);
			}
			last = v;
		}
	fill(color, color + n, 0);
	dsu.init(n);
	lca(root, -1);
	newN = 0;
	color[root] = newN++;
	vector<Edge> edges;
	build_newgraph(root, -1, root, 0, edges);
	int m = (l + r) / 2, n1 = newN;
	if (r - l == 1) {
		printf("%lld\n", w);
		return;
	}
	for (auto &q : qs2)
		q.v = color[q.v];
	if (prevRoot == -1 && !qs1.size())
		prevRoot = -1;
	else
		prevRoot = color[root];
	solve(l, m, n1, edges, qs2, w, prevRoot);
	solve(m, r, n1, edges, qs2, w, prevRoot);
}

int main() {
	int n, k;
	scanf("%d", &n);
	vector<Edge> e(n);
	forn(i, n - 1) {
		int a, b, w;
		scanf("%d%d%d", &a, &b, &w), a--, b--;
		e[i] = {a, b, w};
	}
	scanf("%d", &k);
	vector<int> add(n, -1);
	vector<Range> q;
	forn(i, k) {
		char c;
		int v;
		scanf(" %c%d", &c, &v), v--;
		if (c == '+')
			add[v] = i;
		else 
			q.push_back({add[v], i, v}), add[v] = -1;
	}
	forn(i, n)
		if (add[i] != -1)
			q.push_back({add[i], k, i});
	solve(0, k, n, e, q, 0, -1);
}
