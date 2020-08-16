/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct point {
	double x, y;

	double distance(point const &p) const {
		return operator-(p).length();
	}

	point operator-(point const &p) const {
		return {x - p.x, y - p.y};
	}
	point operator+(point const &p) const {
		return {x + p.x, y + p.y};
	}

	double lenSq() const {
		return x * x + y * y;
	}

	double length() const {
		return sqrt(lenSq());
	}

	point operator*(double d) const {
		return {x * d, y * d};
	}

	double vmul(point const &p) const {
		return x * p.y - y * p.x;
	}
};

pair<point, point> getTangents(point p, double radius, point q) {
    double d = p.distance(q);
    double d1 = radius * radius / d;
    point v = q - p;
    point vn = {-v.y, v.x};
    v = v * (d1 / v.length());
    point c = p + v;
    double d2 = radius * radius - d1 * d1;
    if (d2 < 0) d2 = 0;
    d2 = sqrt(d2);
    vn = vn * (d2 / vn.length());
    return {c - (vn), c + (vn)};
}

point zero = {0.0, 0.0};

struct that {
	point p;
	int id;
	bool start;
};

int part(point const &p) {
	return p.y > 0 || (p.y == 0 && p.x > 0);
}

int M;

bool between(int x, int left, int right) {
	if (left < right) {
		return left < x && x < right;
	} else {
		return x > left || x < right;
	}
}

int const MAXN = 123456;

int pp[20][MAXN], sp[20][MAXN];

int check(vector<point> const &p, double radius) {
	int n = (int) p.size();
	vector<that> q(2 * n);
	for (int i = 0; i < n; i++) {
		auto z = getTangents(zero, radius, p[i]);
		q[2 * i] = {z.first, i, true};
		q[2 * i + 1] = {z.second, i, false};
	}
	sort(q.begin(), q.end(), [](that const &a, that const &b) {
		int c = part(a.p) - part(b.p);
		if (c != 0) return c < 0;
		return a.p.vmul(b.p) > 0;
	});
	vector<pair<int, int>> arcs(n);
	for (int i = 0; i < 2 * n; i++) {
		auto const &e = q[i];
		if (e.start) {
			arcs[e.id].first = i;
		} else {
			arcs[e.id].second = i;
		}
	}
	// for (auto e : arcs) {
	// 	cout << e.first << ' ' << e.second << '\n';
	// }
	vector<int> parent(2 * n);
	vector<int> s1(2 * n);
	for (int i = 0, j = 0, count = 0; i < 2 * n; i++) {
		auto const &e = q[i];
		if (i == j) {
			if (q[j].start) {
				++count;
			}
			j = (j + 1) % (2 * n);
		}
		if (e.start) {
			count--;
			continue;
		}
		while (true) {
			auto const &f = q[j];
			if (f.start) {
				j = (j + 1) % (2 * n);
				count++;
				continue;
			}
			int from = arcs[f.id].first;
			if (!between(from, i, j)) {
				j = (j + 1) % (2 * n);
				continue;
			}
			break;
		}
		parent[i] = j;
		s1[i] = count;
	}
	int log = 0;
	while ((1 << log) <= n) {
		log++;
	}
	log++;
	for (int i = 0; i < 2 * n; i++) {
		if (!q[i].start) {
			pp[0][i] = parent[i];
			sp[0][i] = s1[i];
		}
	}
	for (int k = 1; k < log; k++) {
		for (int i = 0; i < 2 * n; i++) {
			if (!q[i].start) {
				pp[k][i] = pp[k - 1][pp[k - 1][i]];
				sp[k][i] = sp[k - 1][i] + sp[k - 1][pp[k - 1][i]];
			}
		}
	}
	int ans = n;
	for (int i = 0; i < 2 * n; i++) {
		if (q[i].start) continue;
		int cur = 0;
		int got = 0;
		int v = i;
		for (int k = log - 1; k >= 0; k--) {
			if (got + sp[k][v] < n) {
				got += sp[k][v];
				v = pp[k][v];
				cur += 1 << k;
			}
		}
		cur++;
		if (cur <= M) return cur;
		ans = min(ans, cur);
	}
	return ans;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m;
	cin >> n >> m;
	vector<point> p(n);
	M = m;
	double maxD = 1e20;
	set<pair<int, int>> ps;
	for (int i = 0; i < n; i++) {
		int x, y;
		cin >> x >> y;
		ps.insert({x, y});
		p[i].x = x;
		p[i].y = y;
		maxD = min(maxD, p[i].length());
	}
	if (ps.size() == 1) {
		printf("%.18f\n", maxD);
		return 0;
	}
	double left = 0;
	double right = maxD;
	for (int it = 0; left + 1e-10 < right && it < 70; it++) {
		double mid = (left + right) * .5;
		// cerr << mid << endl;
		if (check(p, mid) <= m) {
			left = mid;
		} else {
			right = mid;
		}
	}
	printf("%.17f\n", (left + right) * .5);
}
