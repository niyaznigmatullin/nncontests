#include <cstdio>
#include <algorithm>
#include <set>
using namespace std;

pair<int, int> a[3234567];

long long sum(long long x) {
	return x * (x + 1) / 2;
}

long long get(int c0, int n, int w, int k) {
	if (w + k <= c0) return sum(c0 - w) - sum(c0 - w - k);
	if (w >= c0) return sum(w - c0 + k - 1) - sum(w - c0 - 1);
	return sum(c0 - w) + sum(w + k - 1 - c0);
}

int main() {
	freopen("cinema.in", "r", stdin);
	freopen("cinema.out", "w", stdout);
	int n, m, k;
	scanf("%d%d%d", &n, &m, &k);
	for (int i = 0; i < m; i++) scanf("%d%d", &a[i].first, &a[i].second);
		set<int> rows;
	for (int i = 0; i < m; i++) rows.insert(a[i].first);		
	int r0, c0;
	scanf("%d%d", &r0, &c0);
	int r1 = r0;
	while (r1 >= 1 && rows.find(r1) != rows.end()) --r1;
	if (r1 >= 1) {
		a[m++] = {r1, 0};
		a[m++] = {r1, n + 1};
	}
	r1 = r0;
	while (r1 <= n && rows.find(r1) != rows.end()) ++r1;
	if (r1 <= n) {
		a[m++] = {r1, 0};
		a[m++] = {r1, n + 1};
	}
	for (int i : rows) {
		a[m++] = {i, 0};
		a[m++] = {i, n + 1};
	}
	std::sort(a, a + m);
	long long ans = 1LL << 60;
	for (int i = 0; i + 1 < m; i++) {
		if (a[i].first != a[i + 1].first) continue;
		int gap = a[i + 1].second - a[i].second - 1;
		if (gap < k) continue;
		long long costr = (long long) k * std::abs(r0 - a[i].first);
		ans = std::min(ans, get(c0, n, a[i].second + 1, k) + costr);
		ans = std::min(ans, get(c0, n, a[i + 1].second - k, k) + costr);
		if (c0 - k / 2 > a[i].second + 1 && c0 - k / 2 <= a[i + 1].second - k) {
			ans = std::min(ans, get(c0, n, c0 - k / 2, k) + costr);
		}
	}
	printf("%lld\n", ans == (1LL << 60) ? -1 : ans);
}