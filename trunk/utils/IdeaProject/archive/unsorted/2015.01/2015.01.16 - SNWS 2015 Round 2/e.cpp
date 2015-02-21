#include <cstdio>
int const INF = 1 << 30;
int const N = 12345;

int f[N], dp[N];

void solve() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d", f + i);
  int sum = 0;
  for (int i = 0; i < n; i++) sum += f[i];
  for (int i = 0; i <= n; i++) dp[i] = INF;
  dp[0] = 0;
  for (int i = 0; i < n; i++) {
    int cs = 0;
    int cs2 = 0;
    for (int j = i + 1; j <= n; j++) {
      cs2 += cs;
      cs += f[j - 1];
      if (dp[j] > dp[i] + cs2 + sum - cs) {
        dp[j] = dp[i] + cs2 + sum - cs;
      }
    }
    sum -= f[i];
  }
  printf("%d\n", dp[n]);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}