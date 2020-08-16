/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m, a, b;
	cin >> n >> m >> a >> b;
	vector<vector<int>> h(n, vector<int>(m));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> h[i][j];
		}
	}
	long long const INF = 1LL << 62;
	vector<long long> d(n * m, INF);
	d[0] = 0;
	set<pair<long long, int>> q;
	q.insert({d[0], 0});
	// vector<int> inq(n * m);
	// inq[0] = true;
	// vector<int> q(1 + n * m);
	// int head = 0;
	// int tail = 0;
	// q[tail++] = 0;
	// while (head != tail) {
	// 	int v = q[head++];
	// 	if (head == 1 + n * m) head = 0;
	// 	inq[v] = false;
	// 	int cx = v / m;
	// 	int cy = v % m;
	// 	for (int dx = -1; dx <= 1; dx++) {
	// 		for (int dy = -1; dy <= 1; dy++) {
	// 			if (dx * dx + dy * dy != 1) continue;
	// 			int nx = cx + dx;
	// 			int ny = cy + dy;
	// 			if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
	// 			int from = h[cx][cy];
	// 			int to = h[nx][ny];
	// 			long long cost = from < to ? (long long) a * (to - from) : (long long) b * (to - from);
	// 			int nv = nx * m + ny;
	// 			if (d[nv] > d[v] + cost) {
	// 				if (!inq[nv]) {
	// 					inq[nv] = true;
	// 					q[tail++] = nv;
	// 					if (tail == 1 + n * m) tail = 0;
	// 				}
	// 				d[nv] = d[v] + cost;
	// 			}
	// 		}
	// 	}
	// }
	while (!q.empty()) {
		auto e = *q.begin();
		q.erase(q.begin());
		int v = e.second;
		int cx = v / m;
		int cy = v % m;
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				if (dx * dx + dy * dy != 1) continue;
				int nx = cx + dx;
				int ny = cy + dy;
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				int from = h[cx][cy];
				int to = h[nx][ny];
				long long cost = from < to ? (long long) (a - b) * (to - from) : 0;
				int nv = nx * m + ny;
				if (d[nv] > d[v] + cost) {
					q.erase({d[nv], nv});
					d[nv] = d[v] + cost;
					q.insert({d[nv], nv});
				}
			}
		}
	}
	cout << d[(n - 1) * m + m - 1] + (long long) b * (h[n - 1][m - 1] - h[0][0]) << endl;
}