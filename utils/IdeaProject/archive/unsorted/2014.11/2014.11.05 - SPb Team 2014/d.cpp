#include <cstdio>

int const N = 12345;

int l[N], r[N], x[N];

int main() {
  freopen("dunes.in", "r", stdin);
  freopen("dunes.out", "w", stdout);
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) scanf("%d%d%d", l + i, r + i, x + i);
  for (int i = 0; i < m; i++) {
    int q;
    scanf("%d", &q);
    int ans = 0;
    for (int j = 0; j < n; j++) {
      if (q >= l[j] && q <= r[j]) ans += ((q - l[j]) & 1) == 1 ? -x[j] : x[j];
    }
    printf("%d\n", ans);
  }
}