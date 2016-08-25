#include <cstdio>
#include <algorithm>
int const N = 1234567;
int z[N], s[N];

int main() {
	freopen("delivery.in", "r", stdin);
	freopen("delivery.out", "w", stdout);
	int n, k, t;
	scanf("%d%d%d", &n, &k, &t);
	for (int i = 0; i < n; i++) scanf("%d", z + i);
	for (int i = 0; i < n; i++) scanf("%d", s + i);
	long long ct = z[0];
	for (int i = 0; i < n; i++) {
		if (ct + k < s[i]) {
			ct += k;
		} else {
			ct = std::max<long long>(ct, s[i]) + t;
		}
		if (i + 1 < n) ct += z[i + 1];
	}
	printf("%lld\n", ct);
}