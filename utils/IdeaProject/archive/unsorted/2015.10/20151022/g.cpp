#include <cstdio>
#include <algorithm>

int a[1234567];

int main() {
	freopen("robot.in", "r", stdin);
	freopen("robot.out", "w", stdout);
	int n, k;
	scanf("%d%d", &n, &k);
	for (int i = 0; i < n; i++) scanf("%d", a + i);
	std::sort(a, a + n);
	int add = 0;
	for (int i = 0; i < k; i++) {
		if (a[i] < 0) {
			add -= 2 * a[i];
		}
	}
	int sub = 0;
	for (int i = 0; i < k; i++) {
		if (a[n - i - 1] > 0) {
			sub += 2 * a[n - i - 1];
		}
	}
	long long sum = 0;
	for (int i = 0; i < n; i++) sum += a[i];
	long long ans1 = sum + add;
	long long ans2 = sub - sum;
	printf("%lld\n", std::max(ans1, ans2));
}
