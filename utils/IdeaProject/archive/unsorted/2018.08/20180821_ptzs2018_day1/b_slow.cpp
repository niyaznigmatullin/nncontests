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

const int N = 3e5;

vector<Edge> g[N];
int parent[N], u[N];
ll parent_w[N];

void dfs( int v, int p, ll wp ) {
	parent[v] = p, parent_w[v] = wp;
	for (auto e : g[v])
		if (e.b != p)
			dfs(e.b, v, e.w);
}

int main() {
	int n, k;
	scanf("%d", &n);
	forn(i, n - 1) {
		int a, b, w;
		scanf("%d%d%d", &a, &b, &w), a--, b--;
		g[a].push_back({a, b, w});
		g[b].push_back({b, a, w});
	}
	scanf("%d", &k);
	unordered_set<int> s;
	while (k--) {
		char c;
		int v;
		scanf(" %c%d", &c, &v), v--;
		if (c == '+')
			u[v] = 1, s.insert(v);
		else 
			u[v] = 0, s.erase(v);
		if (!s.size()) {
			puts("0");
			continue;
		}
		int root = *s.begin();
		dfs(root, -1, 0);
		vector<int> mark(n, 0);
		mark[root] = 1;
		ll w = 0;
		forn(i, n)
			if (u[i])
				for (int v = i; v != -1 && !mark[v]; v = parent[v])
					mark[v] = 1, w += parent_w[v];
		printf("%lld\n", w);
	}
}
