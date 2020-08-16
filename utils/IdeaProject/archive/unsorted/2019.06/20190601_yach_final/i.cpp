/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct point {
	int x, y;
};

struct event {
	int type;
	long double x;
	pair<int, int> c;
};

long double eps = 1e-9;

int comp(long double a, long double b) {
	long double d = a - b;
	if (d < 0) d = -d;
	if (d < eps) return 0;
	return a < b ? -1 : 1;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int xs, ys, dx, dy;
	cin >> xs >> ys >> dx >> dy;
	int n;
	cin >> n;
	vector<int> t(n);
	for (int i = 0; i < n; i++) {
		cin >> t[i];
	}
	int m, mc;
	cin >> m >> mc;
	vector<point> p(m);
	vector<int> rad(m);
	vector<int> cost(m);
	for (int i = 0; i < m; i++) {
		cin >> p[i].x >> p[i].y >> rad[i] >> cost[i];
	}
	if (dx == 0) {
		for (int i = 0; i < m; i++) swap(p[i].x, p[i].y);
		swap(xs, ys);
		swap(dx, dy);
	}
	if (dx < 0) {
		dx = -dx;
		for (int i = 0; i < m; i++) p[i].x = -p[i].x;
		xs = -xs;
	}
	vector<event> es;
	for (int i = 0; i < n; i++) {
		es.push_back({0, (double) (xs + (long long) dx * t[i]), {i, i}});
	}
	for (int i = 0; i < m; i++) {
		if (cost[i] < mc) continue;
		int dy = std::abs(ys - p[i].y);
		if (dy > rad[i]) continue;
		long double delta = (long double) ((long long) rad[i] * rad[i] - (long long) dy * dy);
		if (delta < 0) delta = 0;
		delta = sqrtl(delta);
		long double from = p[i].x - delta;
		long double to = p[i].x + delta;
		es.push_back({-1, from, {cost[i], i}});
		es.push_back({1, to, {cost[i], i}});
	}
	sort(es.begin(), es.end(), [](event const &a, event const &b) {
		int c = comp(a.x, b.x);
		if (c != 0) return c < 0;
		return a.type < b.type;
	});
	vector<int> ans(n);
	set<pair<int, int>> q;
	for (event &e : es) {
		if (e.type == -1) {
			q.insert(e.c);
		} else if (e.type == 1) {
			q.erase(e.c);
		} else {
			if (q.size() <= 1) {
				ans[e.c.second] = mc;
			} else {
				auto it = q.rbegin();
				it++;
				ans[e.c.second] = it->first;
			}
		}
	}
	for (int i = 0; i < n; i++) {
		if (i > 0) cout << ' ';
		cout << ans[i];
	}
	cout << endl;
}