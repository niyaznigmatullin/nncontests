/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()


#define err(...) fprintf(stderr, "%.2f : ", 1. * clock() / CLOCKS_PER_SEC), fprintf(stderr, __VA_ARGS__), fflush(stderr)

struct rect {
	int x1, y1, x2, y2, id;
};

long long inters(rect const &a, rect const & b) {
	int x1 = std::max(a.x1, b.x1);
	int x2 = std::min(a.x2, b.x2);
	int y1 = std::max(a.y1, b.y1);
	int y2 = std::min(a.y2, b.y2);
	if (x1 < x2 && y1 < y2) {
		return (long long) (x2 - x1) * (y2 - y1);
	} else {
		return 0;
	}
}

long long intersarea(rect const &z, vector<rect> const &a) {
	long long ret = 0;
	for (int j = 0; j < (int) a.size(); j++) {
		ret += inters(z, a[j]);
	}
	return ret;
}

long long area(rect const &a) {
	return (long long) (a.y2 - a.y1) * (a.x2 - a.x1);
}

long long isInside(int x, int y, rect const &a) {
	return x >= a.x1 && x <= a.x2 && y >= a.y1 && y <= a.y2;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, k;
	cin >> n >> k;
	vector<rect> a(n);
	long long ans = 0;
	vector<rect> good, bad;
	int const MAXX = 2000000;
	bad.push_back({-MAXX, -MAXX, MAXX, MAXX, -1});
	// long long const INF = 1LL << 60;
	for (int i = 0; i < n; i++) {
		int x, y;
		cin >> x >> y;
		int idGood = -2;
		int idBad = -2;
		for (auto &e : good) {
			if (isInside(x, y, e)) {
				idGood = std::max(idGood, e.id);
			}
		}
		for (auto &e : bad) {
			if (isInside(x, y, e)) {
				idBad = std::max(idBad, e.id);
			}
		}
		int l = 0;
		int r = k / 2 + 1;
		while (l < r - 1) {
			int mid = (l + r) >> 1;
			rect cur = {x - mid, y - mid, x + mid, y + mid, -1};
			long long garea = intersarea(cur, bad) - intersarea(cur, good);
			// err("%lld %d\n", garea, mid);
			if (idBad > idGood) {
				if (garea == 4LL * mid * mid) {
					l = mid;
				} else {
					r = mid;
				}
			} else {
				if (garea == 0) {
					l = mid;
				} else {
					r = mid;
				}
			}
		}
		if (idBad > idGood) {
			good.push_back({x - l, y - l, x + l, y + l, i});
			// err("good %d\n", l);
			ans += 4LL * l * l;
		} else {
			bad.push_back({x - l, y - l, x + l, y + l, i});
			// err("bad %d\n", l);
			ans -= 4LL * l * l;
		}
	}
	cout << ans << endl;
}
