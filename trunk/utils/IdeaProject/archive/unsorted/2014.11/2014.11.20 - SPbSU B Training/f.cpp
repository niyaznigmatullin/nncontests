#include <cstdio>

int const N = 2222;
int dp[N][N];
int s[N];

int main() {
  freopen("bacon.in", "r", stdin);
  freopen("bacon.out", "w", stdout);
  int c = getchar();
  while (c <= 32) c = getchar();
  int n = 0;
  while (c > 32) {
    s[n++] = c;
    c = getchar();
  }
  int ans = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (i > 0 && j > 0) dp[i][j] = dp[i - 1][j - 1];
      if (s[i] == s[j]) dp[i][j]++; else dp[i][j] = 0;
    }
    int mx = 0;
    for (int j = 0; j < i; j++) if (mx < dp[i][j]) mx = dp[i][j];
    ans += (i + 1) - mx;
  }
  printf("%d\n", ans);
}