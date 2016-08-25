#include <cstdio>
#include <algorithm>

int const N = 123456;
long long const INF = 1LL << 60;
int const M = 333;
long long f[N];
long long b[M][M];
int a[M];
long long dp[M][M];
int pp[M][M];
int ans[M];

int main() {
	freopen("traffic.in", "r", stdin);
	freopen("traffic.out", "w", stdout);
	int n, k;
	scanf("%d%d", &n, &k);
	for (int i = 0; i < n; i++) scanf("%d", a + i);
	for (int j = 1; j <= k; j++) {
		for (int i = 1; i <= 100000; i++) {
			f[i] = (long long) i * (i - 1) / 2;
			if (i - j > 0)
				f[i] += f[i - j];
		}
		for (int i = 0; i < n; i++) {
			b[i][j] = a[i] > j ? f[a[i] - j] : 0;
		}
	}
	for (int i = 0; i <= k; i++) 
		for (int j = 0; j <= n; j++)
			dp[i][j] = INF;
	dp[0][0] = 0;
	for (int i = 0; i < n; i++) {
		for (int cur = 0; cur <= k; cur++) {
			long long val = dp[cur][i];
			if (val == INF) continue;
			for (int j = 1; j + cur <= k; j++) {
				long long got = val + b[i][j];
				if (dp[j + cur][i + 1] > got) {
					dp[j + cur][i + 1] = got;
					pp[j + cur][i + 1] = j;
				}
			}
		}
	}
	for (int i = n, j = k; i > 0; i--) {
		ans[i - 1] = pp[j][i];
		j -= pp[j][i];
	}
    printf("%lld\n", dp[k][n]);
    for (int i = 0; i < n; i++) {
    	if (i > 0) putchar(' ');
    	printf("%d", ans[i]);
    }
    puts("");
}