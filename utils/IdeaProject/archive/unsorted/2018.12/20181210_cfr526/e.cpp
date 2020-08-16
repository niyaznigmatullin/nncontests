
#include <bits/stdc++.h>

using namespace std;

long long const INF = 1LL << 60;

struct rect {
	int x, y;
	long long a;
};

struct line {
	long long a, b;
};

vector<line> lines;

void umul64wide (uint64_t a, uint64_t b, uint64_t *hi, uint64_t *lo)
{
    uint64_t a_lo = (uint64_t)(uint32_t)a;
    uint64_t a_hi = a >> 32;
    uint64_t b_lo = (uint64_t)(uint32_t)b;
    uint64_t b_hi = b >> 32;

    uint64_t p0 = a_lo * b_lo;
    uint64_t p1 = a_lo * b_hi;
    uint64_t p2 = a_hi * b_lo;
    uint64_t p3 = a_hi * b_hi;

    uint32_t cy = (uint32_t)(((p0 >> 32) + (uint32_t)p1 + (uint32_t)p2) >> 32);

    *lo = p0 + (p1 << 32) + (p2 << 32);
    *hi = p3 + (p1 >> 32) + (p2 >> 32) + cy;
}

void mul64wide (int64_t a, int64_t b, int64_t *hi, int64_t *lo)
{
    umul64wide ((uint64_t)a, (uint64_t)b, (uint64_t *)hi, (uint64_t *)lo);
    if (a < 0LL) *hi -= b;
    if (b < 0LL) *hi -= a;
}

void add(long long a, long long b) {
	line l = {a, b};
	while (lines.size() > 1) {
		auto &c = lines[lines.size() - 2];
		auto &d = lines.back();
		if () {
			lines.pop_back();
		} else break;
	}
	lines.push_back(l);
}

long long get(long long x) {
	int l = -1;
	int r = (int) lines.size() - 1;
	while (l < r - 1) {
		int mid = (l + r) >> 1;
		auto &e = lines[mid];
		auto &f = lines[mid + 1];
		if (x * (e.a - f.a) >= (f.b - e.b)) {
			r = mid;
		} else {
			l = mid;
		}
	}
	return lines[r].a * x + lines[r].b;
}

int main() {
	ios::sync_with_stdio(false);
	int n;
	cin >> n;
	vector<rect> a(n);
	for (int i = 0; i < n; i++) {
		int x, y;
		long long z;
		cin >> x >> y >> z;
		a[i] = {x, y, z};
	}
	sort(a.begin(), a.end(), [](rect const &a, rect const &b) {
		return a.x < b.x;
	});
	add(0, 0);
	long long ans = 0;
	for (int i = 0; i < n; i++) {
		long long value = get(a[i].y) + (long long) a[i].x * a[i].y - a[i].a;
		ans = std::max(ans, value);
		add(-a[i].x, value);
	}
	cout << ans << endl;
}