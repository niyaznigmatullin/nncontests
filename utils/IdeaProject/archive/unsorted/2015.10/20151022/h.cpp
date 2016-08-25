#include <cstdio>
#include <algorithm>

int const INF = 1 << 29;
int a[42];
int dp[1 << 19][20];
int b[42][42];

int main() {
	freopen("run.in", "r", stdin);
	freopen("run.out", "w", stdout);
	int n, k;
	scanf("%d%d", &n, &k);
	++n;
	for (int i = 0; i < k; i++) scanf("%d", a + i);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) 
			scanf("%d", b[i] + j);
	}
	for (int i = 0; i < 1 << (n - 1); i++) 
		std::fill(dp[i], dp[i] + n, INF);
	dp[0][0] = 0;
	for (int mask = 0; mask < 1 << (n - 1); mask++) {
		int bits = __builtin_popcount(mask);
		int cur = 0;
		while (a[cur] <= bits) bits -= a[cur++];
		for (int last = 0; last < n; last++) {
			int val = dp[mask][last];
			if (val == INF) continue;
			// printf("%d %d %d %d %d\n", mask, last, val, bits, a[cur]);
			for (int next = 1; next < n; next++) {
				if (((mask >> (next - 1)) & 1) == 1) continue;
				int cost = b[last][next];
				if (bits + 1 == a[cur])
					cost += b[next][0];
				int nLast = bits + 1 == a[cur] ? 0 : next;
				dp[mask | (1 << (next - 1))][nLast] = std::min(dp[mask | (1 << (next - 1))][nLast], val + cost);
			}
		}
	}
	printf("%d\n", dp[(1 << (n - 1)) - 1][0]);
}