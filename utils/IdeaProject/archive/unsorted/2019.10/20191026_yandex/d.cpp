/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct edge {
	int from;
	int to;
	int w;
};

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m, k;
	cin >> n >> m >> k;
	vector<int> prof(n);
	for (int i = 0; i < n; i++) {
		cin >> prof[i];
	}	
	vector<int> drivers(k);
	for (int i = 0; i < k; i++) {
		cin >> drivers[i];
		--drivers[i];
	}
	vector<vector<edge>> edges(n);
	for (int i = 0; i < m; i++) {
		int from, to, w;
		cin >> w >> from >> to;
		--from;
		--to;
		// edges[from].push_back({from, to, w});
		edges[to].push_back({to, from, w});
	}
	vector<long long> best(n);
	for (int i = 0; i < n; i++) {
		best[i] = prof[i];
	}
	set<pair<long long, int>> q;
	for (int i = 0; i < n; i++) {
		q.insert({-best[i], i});
	}
	while (!q.empty()) {
		int v = q.begin()->second;
		q.erase(q.begin());
		for (edge &e : edges[v]) {
			if (best[e.to] < best[e.from] - e.w) {
				q.erase({-best[e.to], e.to});
				best[e.to] = best[e.from] - e.w;
				q.insert({-best[e.to], e.to});
			}
		}
	}
	long long answer = 0;
	for (int i : drivers) {
		answer += best[i];
	}
	cout << answer << endl;
}
