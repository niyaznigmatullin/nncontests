#include <cstdio>

int main() {
	freopen("sapsan.in", "r", stdin);
	freopen("sapsan.out", "w", stdout);
	int n;
	scanf("%d", &n);
	n /= 2;
	int k = 2 * n / 3;
	if (k % 2 != 0) k--;
	while (true) {
		int v = k + 2;
		if (v + v / 2 <= n) {
			k = v;
		} else break;
	}
	printf("%d\n", 2 * k);
}