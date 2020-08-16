/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct edge {
	int from, to, w;
};

void solve(int test) {
	int n, m;
	cin >> n >> m;
	int const INF = 1 << 29;
	vector<vector<int>> a(n, vector<int>(n, INF));
	for (int i = 0; i < n; i++) a[i][i] = 0;
	vector<edge> allEdges(m);
	for (int i = 0; i < m; i++) {
		int from, to, w;
		cin >> from >> to >> w;
		--from;
		--to;
		a[from][to] = a[to][from] = w;
		allEdges[i] = {from, to, w};
	}
	for (int k = 0; k < n; k++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] > a[i][k] + a[k][j]) {
					a[i][j] = a[i][k] + a[k][j];
				}
			}
		}
	}
	bool ok = true;
	for (int i = 0; i < m; i++) {
		edge const &e = allEdges[i];
		if (a[e.from][e.to] != e.w) {
			ok = false;
			break;
		}
 	}
 	cout << "Case #" << test << ": ";
 	if (ok) {
 		cout << m << '\n';
 		for (int i = 0; i < m; i++) {
 			edge const &e = allEdges[i];
 			cout << e.from + 1 << ' ' << e.to + 1 << ' ' << e.w << '\n';
 		}
 	} else {
 		cout << "Impossible\n";
 	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}