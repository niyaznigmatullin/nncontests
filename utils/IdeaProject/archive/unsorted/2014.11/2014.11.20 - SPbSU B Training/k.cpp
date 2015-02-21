#include <cstdio>

int const N = 123456;

int const INF = 1 << 30;

int dp[N], a[N];

int main() {
  freopen("ladder.in", "r", stdin);
  freopen("ladder.out", "w", stdout);
  int n;
  scanf("%d", &n);
  for (int i = 1; i <= n; i++) {
    scanf("%d", a + i);
  }
  int k;
  scanf("%d", &k);
  for (int i = 1; i <= n + 1; i++) {
    dp[i] = -INF;
    for (int j = 0; j < i; j++) {
      if (i - j <= k && dp[i] < dp[j]) dp[i] = dp[j];
    }
    dp[i] += a[i];
  }
  printf("%d\n", dp[n + 1]);
}