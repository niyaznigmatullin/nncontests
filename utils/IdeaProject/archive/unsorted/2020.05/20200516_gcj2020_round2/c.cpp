/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct point {
	int x;
	int y;

	point operator-(point const &p) const {
		return {x - p.x, y - p.y};
	}

	long long vmul(point const &a) const {
		return (long long) x * a.y - (long long) y * a.x;
	}
};

int gcd(int a, int b) {
	return b == 0 ? a : gcd(b, a % b);
}

void solve(int test) {
	int n;
	cin >> n;
	vector<point> p(n);
	for (int i = 0; i < n; i++) {
		int x, y;
		cin >> x >> y;
		p[i] = {x, y};
	}
	vector<point> vs;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (i == j) continue;
			point d = p[i] - p[j];
			if (d.x < 0 || (d.x == 0 && d.y < 0)) {
				d.x = -d.x;
				d.y = -d.y;
			}
			int g = gcd(abs(d.x), abs(d.y));
			d.x /= g;
			d.y /= g;
			vs.push_back(d);
		}
	}
	auto lex = [](point const &a, point const &b) {
		return a.x < b.x || (a.x == b.x && a.y < b.y);
	};
	auto eq = [](point const &a, point const &b) {
		return a.x == b.x && a.y == b.y;
	};
	sort(vs.begin(), vs.end(), lex);
	vs.resize(unique(vs.begin(), vs.end(), eq) - vs.begin());
	int ans = min(n, 2);
	for (point const &v : vs) {
		unordered_map<long long, int> cc;
		for (point const &c : p) {
			cc[c.vmul(v)]++;
		}
		int ones = 0;
		int odd = 0;
		for (auto &e : cc) {
			if (e.second == 1) ones++; else
			if ((e.second & 1) == 1) odd++;
		}
		int cur = n - ones - (odd & 1);
		cur += min(ones + (odd & 1), 2);
		ans = max(ans, cur);
	}
	cout << "Case #" << test << ": " << ans << '\n';
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);	
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}
}