#include <cstdio>
#include <algorithm>

void solve() {
  int n;
  scanf("%d", &n);
  int mx = 0;
  long long sum = 0;
  for (int i = 0; i < n; i++) {
    int x;
    scanf("%d", &x);
    sum += x;
    if (mx < x) mx = x;
  }
  long long ans = std::max((sum + 1) / 2, (long long) mx);
  printf("%lld\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
