#include <cstdio>

int main() {
	freopen("stars.in", "r", stdin);
	freopen("stars.out", "w", stdout);
	int a, b, c, d, e;
	scanf("%d%d%d%d%d", &a, &b, &c, &d, &e);
	int ans2 = (b - a + 1) - 2;
	if (d == e) ++ans2;
	int ans1 = (b > c - 1 ? c - 1 : b) - (c - d) + ((c - e) - a);
	printf("%d %d\n", ans1, ans2);
}