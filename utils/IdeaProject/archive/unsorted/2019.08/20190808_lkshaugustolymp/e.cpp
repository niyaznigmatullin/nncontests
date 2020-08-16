/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

vector<int> p;

struct event {
	long long x;
	long long y;
	int id;
	bool start;
};

int get(int v) {
	return p[v] == v ? v : (p[v] = get(p[v]));
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	long long w, h, k;
	cin >> w >> h >> k;
	int n;
	cin >> n;
	p.assign(n, 0);
	for (int i = 0; i < n; i++) p[i] = i;
	vector<long long> x(n), y(n);
	for (int i = 0; i < n; i++) {
		cin >> x[i] >> y[i];
	}
	vector<event> events(2 * n);
	for (int i = 0; i < n; i++) {
		events[2 * i] = {x[i], y[i], i, true};
		events[2 * i + 1] = {x[i] + k, y[i], i, false};
	}
	sort(events.begin(), events.end(), [](event const &a, event const &b) {
		if (a.x != b.x) return a.x < b.x;
		if (a.start != b.start) return b.start;
		return a.y < b.y;
	});
	set<pair<long long, int>> q;
	for (event const &e : events) {
		if (e.start) {
			auto it = q.upper_bound({e.y, n});
			if (it != q.end()) {
				int id = it->second;
				if (y[id] - y[e.id] < k) {
					p[get(e.id)] = get(id);
				}
			}
			it = q.lower_bound({e.y, 0});
			if (it != q.begin()) {
				--it;
				int id = it->second;
				if (y[e.id] - y[id] < k) {
					p[get(e.id)] = get(id);
				}
			}
			q.insert({y[e.id], e.id});
		} else {
			q.erase({y[e.id], e.id});
		}
	}
	vector<int> ids(n);
	for (int i = 0; i < n; i++) ids[i] = i;
	sort(ids.begin(), ids.end(), [&](int i, int j) {
		if (x[i] != x[j]) return x[i] < x[j];
		return y[i] < y[j];
	});
	for (int it = 1; it < n; it++) {
		int i = ids[it - 1];
		int j = ids[it];
		if (x[i] != x[j]) continue;
		if (y[j] - y[i] < k) {
			p[get(i)] = get(j);
		}
	}
	sort(ids.begin(), ids.end(), [&](int i, int j) {
		if (y[i] != y[j]) return y[i] < y[j];
		return x[i] < x[j];
	});
	for (int it = 1; it < n; it++) {
		int i = ids[it - 1];
		int j = ids[it];
		if (y[i] != y[j]) continue;
		if (x[j] - x[i] < k) {
			p[get(i)] = get(j);
		}
	}
	vector<int> onLeft(n);
	for (int i = 0; i < n; i++) {
		if (x[i] == 0 || x[i] == w || y[i] == 0 || y[i] == h) continue;
		if (x[i] < k || y[i] + k > h) {
			onLeft[get(i)] = 1;
		}
	}
	for (int i = 0; i < n; i++) {
		if (x[i] == 0 || x[i] == w || y[i] == 0 || y[i] == h) continue;
		if (x[i] + k > w || y[i] < k) {
			if (onLeft[get(i)]) {
				cout << "NO\n";
				return 0;
			}
		}
	}
	cout << "YES\n";
}
