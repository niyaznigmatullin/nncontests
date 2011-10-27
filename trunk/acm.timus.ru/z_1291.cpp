#include <iostream>
#include <string>

using namespace std;

bool a[1002][1002], u[1002];
int b[1002], num[1002], den[1002], n, s, v;

int gcd(int x, int y) {
	return (x == 0 ? y : gcd(y % x, x));
}

void dfs(int x, int k) {
	num[x] = k * b[s] * v;
	den[x] = b[x];
	u[x] = true;
	for (int i=1; i<=n; i++) if (a[x][i] && !u[i]) {
		dfs(i, -1 * k);
	}
}

int main() {
    scanf("%d", &n);
	for (int i=1; i<=n; i++) {
		scanf("%d", &b[i]);
		int x;
		do {
			scanf("%d", &x);
			a[i][x] = true;
			a[x][i] = true;
		} while (x != 0);
	}
	scanf("%d%d", &s, &v);
	dfs(s,1);
	for (int i=1; i<=n; i++) {
		if (num[i] == 0) {
			num[i] = 0;
			den[i] = 1;
		} else {
			int g = gcd(abs(num[i]), abs(den[i]));
			num[i] /= g;
			den[i] /= g;
		}
		printf("%d/%d\n", num[i], den[i]);
	}
    return 0;
}
