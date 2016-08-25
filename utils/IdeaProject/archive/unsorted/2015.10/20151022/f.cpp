#include <cstdio>

int d(int n) {
	int s = 0;
	while (n > 0) {
		s += n % 10;
		n /= 10;
	}
	return s;
}

int main() {
	freopen("mcd.in", "r", stdin);
	freopen("mcd.out", "w", stdout);
	int a, b;
	scanf("%d%d", &a, &b);
	int ans = 0;
	int best = -1;
	for (int i = 1; i * i <= a; i++) {
		if (a % i != 0) {
			continue;
		}
		if (b % i == 0 && d(i) > best) {
			best = d(i);
			ans = i;
		}
		if (i * i != a && b % (a / i) == 0 && d(a / i) > best) {
			best = d(a / i);
			ans = a / i;
		}
	}
	printf("%d\n", ans);
}