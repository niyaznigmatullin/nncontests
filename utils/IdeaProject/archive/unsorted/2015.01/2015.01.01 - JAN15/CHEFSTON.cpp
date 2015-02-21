#include <cstdio>

int const N = 1234567;

int a[N], b[N];

void solve() {
  int n, k;
  scanf("%d%d", &n, &k);
  long long ans = 0;
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  for (int i = 0; i < n; i++) scanf("%d", b + i);
  for (int i = 0; i < n; i++) {
    long long cur = (long long) (k / a[i]) * b[i];
    if (cur > ans) ans = cur;
  }
  printf("%lld\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}