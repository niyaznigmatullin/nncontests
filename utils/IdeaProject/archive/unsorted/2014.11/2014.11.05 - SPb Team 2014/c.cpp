#include <cstdio>
#include <algorithm>

int const INF = 1 << 30;
int const N = 1345;
int const MAXN = 111;
int f[MAXN][N];

int a[N], c[N];

int main() {
  freopen("conquest.in", "r", stdin);
  freopen("conquest.out", "w", stdout);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d%d", a + i, c + i);
  for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++) if (a[i] > a[j] || (a[i] == a[j] && c[i] > c[j])) {
      std::swap(a[i], a[j]);
      std::swap(c[i], c[j]);
    }
  for (int i = 0; i < MAXN; i++)
    for (int j = 0; j <= n; j++) f[i][j] = INF;
  f[0][n] = 0;
  for (int i = n - 1; i >= 0; i--) {
    int all = 0;
    for (int j = 0; j < i; j++) all += a[j];
    for (int eat = 0; eat < MAXN; eat++) {
      int val = f[eat][i + 1];
      if (val == INF) continue;
      int cur = eat + all;
      for (int z = 0; z <= a[i] && z + eat < MAXN; z++) {
        if (cur + z <= a[i] - z) continue;
        if (f[z + eat][i] > val + z * c[i])
          f[z + eat][i] = val + z * c[i];
      }
    }
  }
  int ans = INF;
  for (int i = 0; i < MAXN; i++) if (ans > f[i][0]) ans = f[i][0];
  printf("%d\n", ans);
}
